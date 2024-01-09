package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.service.impl;

import org.springframework.stereotype.Service;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.exception.ResourceNotFoundException;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.warehouseexport.CreateWarehouseExport;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.warehouseexport.CreateWarehouseExportDetailDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.warehouse_export.WarehouseExport;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.warehouse_export.WarehouseExportDetail;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.warehouse_import.WarehouseImportDetail;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.repository.CustomerRepository;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.repository.ProductRepository;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.repository.WarehouseExportDetailRepository;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.repository.WarehouseExportRepository;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.service.WarehouseExportService;

import java.util.List;

@Service
public class WarehouseExportServiceImpl implements WarehouseExportService {
    private final WarehouseExportRepository warehouseExportRepository;
    private final WarehouseExportDetailRepository warehouseExportDetailRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public WarehouseExportServiceImpl(WarehouseExportRepository warehouseExportRepository, WarehouseExportDetailRepository warehouseExportDetailRepository, CustomerRepository customerRepository, ProductRepository productRepository) {
        this.warehouseExportRepository = warehouseExportRepository;
        this.warehouseExportDetailRepository = warehouseExportDetailRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void createWarehouseExport(CreateWarehouseExport createExport) {
        if (warehouseExportRepository.existsByCode(createExport.getCode())) {
            throw new ResourceNotFoundException("Mã xuất kho đã tồn tại!");
        }
        customerRepository.findById(createExport.getCustomerId()).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy khách hàng có id = " + createExport.getCustomerId()));
        vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.warehouse_export.WarehouseExport export = new vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.warehouse_export.WarehouseExport();
        export.setNote(createExport.getNote());
        export.setCustomerId(createExport.getCustomerId());
        vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.warehouse_export.WarehouseExport warehouseExport = this.warehouseExportRepository.save(export);
        List<CreateWarehouseExportDetailDto> detailDtos = createExport.getCreateWarehouseExportDetailDtos();
        if (createExport.getCreateWarehouseExportDetailDtos() != null && !createExport.getCreateWarehouseExportDetailDtos().isEmpty()) {
            createListExport(warehouseExport, detailDtos);
        }

    }

    @Override
    public void updateWarehouseExport(Long exportId, CreateWarehouseExport warehouseExport) {
        vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.warehouse_export.WarehouseExport existingExport = warehouseExportRepository.findById(exportId)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy đơn xuất kho với Id: " + exportId));
        customerRepository.findById(warehouseExport.getCustomerId()).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy khách hàng có id = " + warehouseExport.getCustomerId()));

        // Kiểm tra xem mã mới có trùng với mã của đơn nhập kho khác không
        if (existingExport.getCode().equals(warehouseExport.getCode()) && warehouseExportRepository.existsByCode(warehouseExport.getCode())) {
            throw new ResourceNotFoundException("Mã xuất kho đã tồn tại!");
        }

        // Cập nhật thông tin của đơn nhập kho
        existingExport.setCode(warehouseExport.getCode());
        existingExport.setNote(warehouseExport.getNote());
        existingExport.setCustomerId(warehouseExport.getCustomerId());
        vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.warehouse_export.WarehouseExport export = warehouseExportRepository.save(existingExport);

        // Xóa chi tiết cũ và thêm chi tiết mới
        warehouseExportDetailRepository.deleteByWarehouseExportId(exportId);

        List<CreateWarehouseExportDetailDto> detailDtos = warehouseExport.getCreateWarehouseExportDetailDtos();
        if (detailDtos != null && !detailDtos.isEmpty()) {
            createListExport(export, detailDtos);
        }
    }

    @Override
    public void deleteExport(Long id) {
        WarehouseExport warehouseImport = this.warehouseExportRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy id nhập kho = " + id));
        warehouseImport.setDeleted(true);
        warehouseExportRepository.save(warehouseImport);
        List<WarehouseExportDetail> warehouseExportDetails = this.warehouseExportDetailRepository.findAllByWarehouseExportId(warehouseImport.getId());
        for (WarehouseExportDetail exportDetail : warehouseExportDetails) {
            exportDetail.setDeleted(true);
            warehouseExportDetailRepository.save(exportDetail);
        }
    }

    private void createListExport(vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.warehouse_export.WarehouseExport warehouseExport, List<CreateWarehouseExportDetailDto> detailDtos) {
        for (CreateWarehouseExportDetailDto detailDto : detailDtos) {
            WarehouseExportDetail detail = new WarehouseExportDetail();
            productRepository.findById(detailDto.getProductId()).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy product có id = " + detailDto.getProductId()));
            detail.setWarehouseExportId(warehouseExport.getId());
            detail.setProductId(detailDto.getProductId());
            detail.setQuantity(detailDto.getQuantity());
            detail.setPrice(detailDto.getPrice());
            warehouseExportDetailRepository.save(detail);
        }
    }
}

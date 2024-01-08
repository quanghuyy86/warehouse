package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.service.impl;

import org.springframework.stereotype.Service;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.exception.ResourceNotFoundException;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.warehouseexport.CreateWarehouseExport;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.warehouseexport.CreateWarehouseExportDetailDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.warehouse_export.WarehouseExport;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.warehouse_export.WarehouseExportDetail;
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
        WarehouseExport export = new WarehouseExport();
        export.setNote(createExport.getNote());
        export.setCustomerId(createExport.getCustomerId());
        WarehouseExport warehouseExport = this.warehouseExportRepository.save(export);
        List<CreateWarehouseExportDetailDto> detailDtos = createExport.getCreateWarehouseExportDetailDtos();
        if (createExport.getCreateWarehouseExportDetailDtos() != null && !createExport.getCreateWarehouseExportDetailDtos().isEmpty()) {
            createListExport(warehouseExport, detailDtos);
        }

    }

    private void createListExport(WarehouseExport warehouseExport, List<CreateWarehouseExportDetailDto> detailDtos) {
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

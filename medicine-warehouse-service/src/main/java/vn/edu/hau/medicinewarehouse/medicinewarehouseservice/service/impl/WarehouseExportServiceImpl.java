package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.service.impl;

import jakarta.annotation.Nonnull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.common.dto.page.PageResponse;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.common.dto.page.PageResponseConverter;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.exception.ResourceNotFoundException;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.customer.CustomerDetailDto;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.warehouseexport.*;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.Customer;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.warehouse_export.WarehouseExport;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.warehouse_export.WarehouseExportDetail;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.repository.CustomerRepository;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.repository.ProductRepository;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.repository.WarehouseExportDetailRepository;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.repository.WarehouseExportRepository;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.service.WarehouseExportService;

import java.util.ArrayList;
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
        export.setCode(createExport.getCode());
        export.setNote(createExport.getNote());
        export.setCustomerId(createExport.getCustomerId());
        vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.warehouse_export.WarehouseExport warehouseExport = this.warehouseExportRepository.save(export);
        List<CreateWarehouseExportDetailDto> detailDtos = createExport.getCreateWarehouseExportDetailDtos();
        if (createExport.getCreateWarehouseExportDetailDtos() != null && !createExport.getCreateWarehouseExportDetailDtos().isEmpty()) {
            createListExport(warehouseExport, detailDtos);
        }

    }

    @Override
    @Transactional
    public void updateWarehouseExport(Long exportId, UpdateWarehouseExportDto warehouseExport) {
        vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.warehouse_export.WarehouseExport existingExport = warehouseExportRepository.findById(exportId)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy đơn xuất kho với Id: " + exportId));
        customerRepository.findById(warehouseExport.getCustomerId()).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy khách hàng có id = " + warehouseExport.getCustomerId()));


        // Cập nhật thông tin của đơn nhập kho
        existingExport.setNote(warehouseExport.getNote());
        existingExport.setCustomerId(warehouseExport.getCustomerId());
        vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.warehouse_export.WarehouseExport export = warehouseExportRepository.save(existingExport);

        // Xóa chi tiết cũ và thêm chi tiết mới
        warehouseExportDetailRepository.deleteAllByWarehouseExportId(exportId);

        List<CreateWarehouseExportDetailDto> detailDtos = warehouseExport.getCreateWarehouseExportDetailDtos();
        if (detailDtos != null && !detailDtos.isEmpty()) {
            createListExport(export, detailDtos);
        }
    }

    @Override
    @Transactional
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

    @Override
    public ResponseWarehouseExportDto detailExport(Long idWarehouseExport) {
        WarehouseExport warehouseExport = warehouseExportRepository.findById(idWarehouseExport).orElseThrow(() -> new ResourceNotFoundException("không tìm thấy id xuất kho = " + idWarehouseExport));
        Customer customer = customerRepository.findById(warehouseExport.getCustomerId()).orElseThrow(()-> new ResourceNotFoundException("Không tìm thấy khách hàng có id = " + warehouseExport.getCustomerId()));

        List<WarehouseExportDetail> warehouseExportDetails = warehouseExportDetailRepository.findAllByWarehouseExportId(idWarehouseExport);
        List<ResponseWarehouseExportDetailDto> responseWarehouseExportDetailDtos = new ArrayList<>();
        for (WarehouseExportDetail detail : warehouseExportDetails) {
            ResponseWarehouseExportDetailDto dto = convertToResponseDto(detail);
            responseWarehouseExportDetailDtos.add(dto);
        }
        return getResponseWarehouseExportDto(warehouseExport, responseWarehouseExportDetailDtos, customer);
    }

    @Override
    public PageResponse<ResponseWarehouseExportDto> getListWarehouseExport(WarehouseExportFilter request) {
        Pageable pageable = Pageable.ofSize(request.getPageSize()).withPage(request.getPageNumber() - 1);
        Page<ResponseWarehouseExportDto> responseWarehouseExportDtos = this.warehouseExportRepository.searchWarehouseExport(request.getKeyword(), pageable)
                .map(detail -> {

                            List<WarehouseExportDetail> warehouseExportDetails = warehouseExportDetailRepository.findAllByWarehouseExportId(detail.getId());
                            List<ResponseWarehouseExportDetailDto> responseWarehouseExportDetailDtos = new ArrayList<>();
                            for (WarehouseExportDetail wow : warehouseExportDetails) {
                                ResponseWarehouseExportDetailDto dto = convertToResponseDto(wow);
                                responseWarehouseExportDetailDtos.add(dto);
                            }
                            Customer customer = customerRepository.findById(detail.getCustomerId()).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy khách hàng có id = " + detail.getCustomerId()));
                            return getResponseWarehouseExportDto(detail, responseWarehouseExportDetailDtos, customer);
                        }
                );
        return PageResponseConverter.convert(responseWarehouseExportDtos);
    }

    @Nonnull
    private ResponseWarehouseExportDto getResponseWarehouseExportDto(WarehouseExport detail, List<ResponseWarehouseExportDetailDto> responseWarehouseExportDetailDtos, Customer customer) {
        CustomerDetailDto customerDetailDto = new CustomerDetailDto();
        customerDetailDto.setNote(customerDetailDto.getNote());
        customerDetailDto.setFullName(customer.getFullName());
        customerDetailDto.setAddress(customer.getAddress());
        customerDetailDto.setPhone(customer.getPhone());
        customerDetailDto.setEmail(customer.getEmail());
        customerDetailDto.setId(customer.getId());

        Double totalPrice = detail.getTotalPrice();
        if(totalPrice == null){
            totalPrice = 0.0;
        }

        return new ResponseWarehouseExportDto(
                detail.getId(),
                detail.getCode(),
                detail.getNote(),
                customerDetailDto,
                responseWarehouseExportDetailDtos,
                totalPrice
        );
    }


    private ResponseWarehouseExportDetailDto convertToResponseDto(WarehouseExportDetail exportDetail) {
        ResponseWarehouseExportDetailDto dto = new ResponseWarehouseExportDetailDto();

        dto.setIdImportDetail(exportDetail.getWarehouseExportId());
        dto.setProductName(exportDetail.getProduct().getName());
        dto.setId(exportDetail.getProduct().getId());
        dto.setQuantity(exportDetail.getQuantity());
        dto.setPrice(exportDetail.getPrice());

        return dto;
    }

    private void createListExport(WarehouseExport warehouseExport, List<CreateWarehouseExportDetailDto> detailDtos) {
        List<WarehouseExportDetail> warehouseExportDetails = new ArrayList<>();
        double totalPrice = 0;
        for (CreateWarehouseExportDetailDto detailDto : detailDtos) {
            WarehouseExportDetail detail = new WarehouseExportDetail();
            productRepository.findById(detailDto.getProductId()).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy product có id = " + detailDto.getProductId()));
            detail.setWarehouseExportId(warehouseExport.getId());
            detail.setProductId(detailDto.getProductId());
            detail.setQuantity(detailDto.getQuantity());
            detail.setPrice(detailDto.getPrice());
            double productTotalPrice = detailDto.getQuantity() * detailDto.getPrice();
            totalPrice += productTotalPrice;
            warehouseExportDetails.add(detail);
        }
        warehouseExport.setTotalPrice(totalPrice);
        warehouseExportDetailRepository.saveAll(warehouseExportDetails);

    }
}

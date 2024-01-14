package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.common.dto.page.PageResponse;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.common.dto.page.PageResponseConverter;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.exception.ResourceNotFoundException;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.warehouseimport.*;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.warehouse_import.WarehouseImport;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.warehouse_import.WarehouseImportDetail;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.repository.ProductRepository;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.repository.SupplierRepository;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.repository.WarehouseImportDetailRepository;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.repository.WarehouseImportRepository;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.service.WarehouseImportService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class WarehouseImportServiceImpl implements WarehouseImportService {
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;
    private final WarehouseImportRepository importRepository;
    private final WarehouseImportDetailRepository importDetailRepository;

    public WarehouseImportServiceImpl(ProductRepository productRepository, SupplierRepository supplierRepository, WarehouseImportRepository warehouseImportRepository, WarehouseImportDetailRepository warehouseImportDetailRepository) {
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
        this.importRepository = warehouseImportRepository;
        this.importDetailRepository = warehouseImportDetailRepository;
    }

    @Transactional
    @Override
    public void createWarehouseImport(CreateWarehouseImportDto importDto) {
        if (importRepository.existsByCode(importDto.getCode())) {
            throw new ResourceNotFoundException("Mã nhập kho đã tồn tại!");
        }
        supplierRepository.findById(importDto.getSupplierId()).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy nhà cung cấp có id = " + importDto.getSupplierId()));
        WarehouseImport warehouseImport = new WarehouseImport();
        warehouseImport.setCode(importDto.getCode());
        warehouseImport.setTime(new Date());
        warehouseImport.setNote(importDto.getNote());
        warehouseImport.setSupplierId(importDto.getSupplierId());
        WarehouseImport entity = this.importRepository.save(warehouseImport);
        List<CreateWarehouseImportDetailDto> detailDtos = importDto.getCreateWarehouseImportDetailDto();
        if (importDto.getCreateWarehouseImportDetailDto() != null && !importDto.getCreateWarehouseImportDetailDto().isEmpty()) {
            createListImport(entity, detailDtos);
        }
    }

    @Transactional
    @Override
    public void updateWarehouseImport(Long importId, CreateWarehouseImportDto importDto) {
        WarehouseImport existingImport = importRepository.findById(importId)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy đơn nhập kho với ID: " + importId));
        supplierRepository.findById(importDto.getSupplierId()).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy nhà cung cấp có id = " + importDto.getSupplierId()));

        // Kiểm tra xem mã mới có trùng với mã của đơn nhập kho khác không
        if (existingImport.getCode().equals(importDto.getCode()) && importRepository.existsByCode(importDto.getCode())) {
            throw new ResourceNotFoundException("Mã nhập kho đã tồn tại!");
        }

        // Cập nhật thông tin của đơn nhập kho
        existingImport.setCode(importDto.getCode());
        existingImport.setNote(importDto.getNote());
        existingImport.setSupplierId(importDto.getSupplierId());
        WarehouseImport updatedImport = importRepository.save(existingImport);

        // Xóa chi tiết cũ và thêm chi tiết mới
        importDetailRepository.deleteAllByWarehouseImportId(importId);

        List<CreateWarehouseImportDetailDto> detailDtos = importDto.getCreateWarehouseImportDetailDto();
        if (detailDtos != null && !detailDtos.isEmpty()) {
            createListImport(updatedImport, detailDtos);
        }
    }

    @Override
    public ResponseWarehouseImportDto detailImport(Long idWarehouseImport) {
        WarehouseImport warehouseImport = importRepository.findById(idWarehouseImport).orElseThrow(() -> new ResourceNotFoundException("không tìm thấy id nhập kho = " + idWarehouseImport));
        List<WarehouseImportDetail> warehouseImportDetails = importDetailRepository.findAllByWarehouseImportId(idWarehouseImport);
        List<ResponseWarehouseImportDetailDto> responseWarehouseImportDetailDtos = new ArrayList<>();
        for (WarehouseImportDetail detail : warehouseImportDetails) {
            ResponseWarehouseImportDetailDto dto = convertToResponseDto(detail);
            responseWarehouseImportDetailDtos.add(dto);
        }

        return new ResponseWarehouseImportDto(
                warehouseImport.getId(),
                warehouseImport.getCode(),
                warehouseImport.getNote(),
                warehouseImport.getSupplier().getId(),
                warehouseImport.getSupplier().getFullName(),
                responseWarehouseImportDetailDtos
        );
    }

    @Override
    public PageResponse<ResponseWarehouseImportDto> getListWarehouseImport(WarehouseImportFilter request) {
        Pageable pageable = Pageable.ofSize(request.getPageSize()).withPage(request.getPageNumber() - 1);
        Page<ResponseWarehouseImportDto> responseWarehouseImportDtos = this.importRepository.searchWarehouseImport(request.getKeyword(), pageable)
                .map(detail -> {

                            List<WarehouseImportDetail> warehouseImportDetails = importDetailRepository.findAllByWarehouseImportId(detail.getId());
                            List<ResponseWarehouseImportDetailDto> responseWarehouseImportDetailDtos = new ArrayList<>();
                            for (WarehouseImportDetail wow : warehouseImportDetails) {
                                ResponseWarehouseImportDetailDto dto = convertToResponseDto(wow);
                                responseWarehouseImportDetailDtos.add(dto);
                            }

                            return new ResponseWarehouseImportDto(
                                    detail.getId(),
                                    detail.getCode(),
                                    detail.getNote(),
                                    detail.getSupplier().getId(),
                                    detail.getSupplier().getFullName(),
                                    responseWarehouseImportDetailDtos);
                        }
                );
        return PageResponseConverter.convert(responseWarehouseImportDtos);
    }

    @Override
    @Transactional
    public void deleteImport(Long id) {
        WarehouseImport warehouseImport = this.importRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy id nhập kho = " + id));
        warehouseImport.setDeleted(true);
        importRepository.save(warehouseImport);
        List<WarehouseImportDetail> warehouseImportDetails = this.importDetailRepository.findAllByWarehouseImportId(warehouseImport.getId());
        for (WarehouseImportDetail importDetail : warehouseImportDetails) {
            importDetail.setDeleted(true);
            importDetailRepository.save(importDetail);
        }

    }

    private ResponseWarehouseImportDetailDto convertToResponseDto(WarehouseImportDetail warehouseImportDetail) {
        ResponseWarehouseImportDetailDto dto = new ResponseWarehouseImportDetailDto();

        dto.setIdImportDetail(warehouseImportDetail.getWarehouseImportId());
        dto.setProductName(warehouseImportDetail.getProduct().getName());
        dto.setId(warehouseImportDetail.getProduct().getId());
        dto.setQuantity(warehouseImportDetail.getQuantity());
        dto.setPrice(warehouseImportDetail.getPrice());

        return dto;
    }

    private void createListImport(WarehouseImport updatedImport, List<CreateWarehouseImportDetailDto> detailDtos) {
        for (CreateWarehouseImportDetailDto detailDto : detailDtos) {
            WarehouseImportDetail detail = new WarehouseImportDetail();
            productRepository.findById(detailDto.getProductId()).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy product có id = " + detailDto.getProductId()));
            detail.setWarehouseImportId(updatedImport.getId());
            detail.setProductId(detailDto.getProductId());
            detail.setQuantity(detailDto.getQuantity());
            detail.setPrice(detailDto.getPrice());
            importDetailRepository.save(detail);
        }
    }

}

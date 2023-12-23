package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.common.dto.page;

import org.springframework.data.domain.Page;

public class PageResponseConverter {
    private PageResponseConverter() {

    }

    public static <T> PageResponse<T> convert(Page<T> page) {
        PageResponse<T> response = new PageResponse<>();
        response.setPageNumber(page.getNumber() + 1);
        response.setPageSize(page.getSize());
        response.setTotalPageNumber(page.getTotalPages());
        response.setTotalSize(page.getTotalElements());
        response.setList(page.getContent());
        return response;
    }
}

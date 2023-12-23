package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.common.dto.page;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Setter
@Getter
@ToString
public class PagingOption {

    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    public static final PagingOption DEFAULT = new PagingOption();

    // example = "20"
    private int pageSize;

    // "Current Page", example = "1"
    private int pageNumber;

    public PagingOption() {
        this.pageSize = 20;
        this.pageNumber = 1;
    }

    public PagingOption(int pageSize, int pageNumber) {
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
    }

}

package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@JsonIgnoreProperties(ignoreUnknown = true)
public class Request {
    private int page = 0;
    private int pageSize = 10;
    private String keyword;
}

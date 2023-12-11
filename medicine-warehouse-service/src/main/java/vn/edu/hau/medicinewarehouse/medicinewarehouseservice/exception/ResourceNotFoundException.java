package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.exception;

import java.io.Serial;

public class ResourceNotFoundException extends RuntimeException{ // đưa ra một ngoại lệ cho Tài nguyên không tìm thấy trong bộ điều khiển Spring Boot. tạo một ResourceNotFoundExceptionlớp mở rộng RuntimeException.
    @Serial
    private static final long serialVersionUID = 1L;
    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}

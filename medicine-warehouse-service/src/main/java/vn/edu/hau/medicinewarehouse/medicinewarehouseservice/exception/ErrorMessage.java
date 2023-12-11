package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class ErrorMessage {// tạo cấu trúc phản hồi tin nhắn của riêng mình thay vì sử dụng phản hồi lỗi mặc định do Spring Boot cung cấp
    private int statusCode;
    private Date timestamp;
    private String message;
    private String description;
    public ErrorMessage() {
    }

    public ErrorMessage(int statusCode, Date timestamp, String message, String description) {
        this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.message = message;
        this.description = description;
    }
}

package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.supplier;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SupplierDetailDto {
    private Long id;
    private String fullName;
    private String phone;
    private String email;
    private String address;
    private String note;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
}

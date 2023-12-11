package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.dto.customer;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDetailDto {
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

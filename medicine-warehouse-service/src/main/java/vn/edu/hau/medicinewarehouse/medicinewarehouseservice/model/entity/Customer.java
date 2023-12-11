package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_customer")
public class Customer extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fullname")
    @NotNull(message = "Bắt buộc nhập tên khách hàng")
    private String fullName;

    @Column(name = "phone")
    @NotNull(message = "Bắt buộc nhập số điện thoại")
    private String phone;

    @Column(name = "email")
    @NotNull(message = "Bắt buộc nhập email")
    private String email;

    @Column(name = "address")
    @NotNull(message = "Bắt buộc nhập địa chỉ")
    private String address;

    @Column(name = "note")
    private String note;

}

package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_category")
public class Category extends BaseEntity{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotNull(message = "Bắt buộc nhập tên category")
    @Size(min = 1, max = 50)
    private String name;

    @Column(name = "description")
    @Size(min = 1)
    private String description;

    @OneToMany(cascade = CascadeType.ALL, //Khi xóa 1 category thì tất cả pruducts sẽ bị xóa
            fetch = FetchType.LAZY, // khi lấy product đấy, thì nó sẽ k lấy tất cả các sản phẩm product đấy, chỉ lấy thông tin của mặt hàng đấy thôi
            mappedBy = "categories") //giá trị của mappedBy bằng tên property của ManyToOne
    private Set<Product> products = new HashSet<Product>();
}

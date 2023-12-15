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
import java.util.List;
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

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "description", nullable = true)
    private String description;
//note
    @OneToMany(fetch = FetchType.LAZY, // khi lấy product đấy, thì nó sẽ k lấy tất cả các sản phẩm product đấy, chỉ lấy thông tin của mặt hàng đấy thôi
               mappedBy = "category") //giá trị của mappedBy bằng tên property của ManyToOne
    private List<Product> products;
}

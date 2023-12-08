package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class}) //sử dụng để tự động đặt giá trị cho các trường được đánh dấu @CreatedBy, @LastModifiedBy, @CreatedDate, và @LastModifiedDate của entity.
public abstract class BaseEntity implements Serializable {
  @Column(name = "is_delete")
  @JsonIgnore
  private boolean isDeleted = false;

  @Column(name = "created_by")
  @JsonIgnore
  private Long createdBy;

  @Column(name = "create_date", nullable = true)
  @Temporal(TemporalType.TIMESTAMP)
  @JsonIgnore
  private Date createdAt;

  @Column(name = "updated_by")
  @JsonIgnore
  private Long updatedBy;

  @Column(name = "updated_date", nullable = true)
  @JsonIgnore
  @Temporal(TemporalType.TIMESTAMP)
  private Date updatedAt;

  @PrePersist
  protected void onCreate() {
    createdAt = new Date();
  }

  @PreUpdate
  protected void onUpdate() {
    updatedAt = new Date();
  }

  public BaseEntity() {
  }

}

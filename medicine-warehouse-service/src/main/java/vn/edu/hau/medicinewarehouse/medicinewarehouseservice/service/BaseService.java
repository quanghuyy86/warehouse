package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.service;

import java.util.Collection;
import java.util.List;

public interface BaseService<T, ID> {
  List<T> getAll();

  T getById(ID id);

  List<T> getByIds(Collection<ID> ids);

  T createOrUpdate(ID id, T t);

  boolean deleteById(ID id);

}

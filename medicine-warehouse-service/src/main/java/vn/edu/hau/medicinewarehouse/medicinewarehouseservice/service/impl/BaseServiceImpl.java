package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.service.impl;

import java.util.Collection;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.model.entity.BaseEntity;
import vn.edu.hau.medicinewarehouse.medicinewarehouseservice.service.BaseService;

public class BaseServiceImpl<T extends BaseEntity, ID> implements BaseService<T, ID> {

  private static final Logger log = LoggerFactory.getLogger(BaseServiceImpl.class);
  protected JpaRepository<T, ID> repository;

  public BaseServiceImpl(JpaRepository<T, ID> repository) {
    this.repository = repository;
  }

  @Override
  public List<T> getAll() {
    return repository.findAll();
  }

  @Override
  public T getById(ID id) {
    T t = this.repository.findById(id).orElse((T) null);
    return t != null && !t.isDeleted() ? t : null;
  }

  @Override
  public List<T> getByIds(Collection<ID> ids) {
    return this.repository.findAllById(ids);
  }

  @Override
  public T createOrUpdate(ID id, T t) {
    if(id != null ){
      T e = this.getById(id);
      if(e != null){
        return this.repository.save(e);
      }else{
        return null;
      }
    }
    else {
      return this.repository.save(t);
    }
  }

  @Override
  public boolean deleteById(ID id) {
    try {
      T t = this.getById(id);
      t.setDeleted(true);
      this.repository.save(t);
      return true;
    }catch (Exception var3){
      return false;
    }
  }

}

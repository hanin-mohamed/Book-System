package com.jpabooks.base;

import com.jpabooks.errors.RecordNotFoundException;
import jakarta.persistence.MappedSuperclass;
import java.util.List;
import java.util.Optional;

@MappedSuperclass
public abstract class BaseService<T extends BaseEntity<ID>, ID extends Number> {

    private final BaseRepo<T, ID> baseRepo;

    public BaseService(BaseRepo<T, ID> baseRepo) {
        this.baseRepo = baseRepo;
    }

    public T findById(ID id) {
        Optional<T> entity= baseRepo.findById(id);
        if (entity.isPresent()) return entity.get();
        else throw new RecordNotFoundException("This Record with ID:- "+id + " not found.");
    }

    public T getById(ID id) {
        return baseRepo.getById(id);
    }

    public List<T> findAll() {
        return baseRepo.findAll();
    }

    public T insert(T entity) {
        if (entity.getId() != null) {
            throw new IllegalArgumentException("ID should be null when inserting a new entity.");
        }
        return baseRepo.save(entity);
    }

    public List<T> insertAll(List<T> entities) {
        return baseRepo.saveAll(entities);
    }

    public T update(T entity) {
        return baseRepo.save(entity);
    }

    public void deleteById(ID id) {
        baseRepo.deleteById(id);
    }
}

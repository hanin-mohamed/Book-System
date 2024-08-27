package com.jpabooks.base;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.MappedSuperclass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@MappedSuperclass
public  class BaseService<T extends BaseEntity<ID>, ID extends Number> {
   // @Autowired
    private BaseRepo<T, ID> baseRepo;


    public T findById(ID id) {
        return baseRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with ID: " + id));
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
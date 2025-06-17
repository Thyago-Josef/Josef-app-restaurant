package com.josef.Josef_app_restaurant.service;

import com.josef.Josef_app_restaurant.DTO.ItemOrderDTO;
import com.josef.Josef_app_restaurant.entities.ItemOrder;
import com.josef.Josef_app_restaurant.mapper.ItemOrderMapper;
import com.josef.Josef_app_restaurant.repository.ItensOrderRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



public abstract class ItemOrdemService<T extends ItemOrder, D extends ItemOrderDTO> {

    protected final ItemOrderMapper itemOrderMapper;
    protected final ItensOrderRepository<T> repository;


    public ItemOrdemService(ItensOrderRepository<T> repository, ItemOrderMapper itemOrderMapper ) {
        this.itemOrderMapper = itemOrderMapper;
        this.repository = repository;
    }

    protected abstract D mapToDTO(T entity);

    protected abstract T mapToEntity(D dto);

    public List<D> findAll() {
        List<T> entities = repository.findAll();
        return entities.stream()
                .map(this::mapToDTO) // Usa o método abstrato
                .collect(Collectors.toList());
    }

    public D save(D dto) {
        T entity = mapToEntity(dto);
        T savedEntity = repository.save(entity);
        return mapToDTO(savedEntity);
    }

    public Optional<D> findById(Long id) {
        return repository.findById(id)
                .map(this::mapToDTO);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    protected boolean existsById(long id) {
        return repository.existsById(id);
    }


//    public List<D> findAll(){
//        List<T> entities = repository.findAll();
//        return entities.stream()
//                .map(entity -> (D) itemOrderMapper.toDTO(entity))
//                .collect(Collectors.toList());
//    }
}

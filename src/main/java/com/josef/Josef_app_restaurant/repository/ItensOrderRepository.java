package com.josef.Josef_app_restaurant.repository;

import com.josef.Josef_app_restaurant.entities.ItemOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ItensOrderRepository<T extends ItemOrder> extends JpaRepository<T, Long> {


}

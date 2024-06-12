package com.vd.restaurant.restaurant_commission_calculator.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vd.restaurant.restaurant_commission_calculator.entities.MenuItem;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
}

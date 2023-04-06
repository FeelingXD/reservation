package com.zerobase.reservation.repository;

import com.zerobase.reservation.model.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop,Long> {
}

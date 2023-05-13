package com.zerobase.reservation.repository;

import com.zerobase.reservation.model.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ShopRepository extends JpaRepository<Shop, Long> {

}

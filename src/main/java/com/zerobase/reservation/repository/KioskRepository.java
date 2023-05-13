package com.zerobase.reservation.repository;

import com.zerobase.reservation.model.entity.Kiosk;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KioskRepository extends JpaRepository<Kiosk, Long> {

  @Override
  @EntityGraph(attributePaths = {"shop"})
  Optional<Kiosk> findById(Long id);
}

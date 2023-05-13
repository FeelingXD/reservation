package com.zerobase.reservation.repository;

import com.zerobase.reservation.model.entity.Manager;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {

  Optional<Manager> findByEmailAndPassword(String email, String password);

  Optional<Manager> findByIdAndEmail(Long id, String email);//token

  @EntityGraph(attributePaths = {"shop"})
  Optional<Manager> findAllById(Long id);
}

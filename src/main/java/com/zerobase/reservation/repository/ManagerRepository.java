package com.zerobase.reservation.repository;

import com.zerobase.reservation.model.entity.Manager;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Optional<Manager> findByEmailAndPassword(String email, String password);

    Optional<Manager> findByIdAndEmail(Long id, String Email);//token

    @EntityGraph(attributePaths = {"shop"})
    Optional<Manager> findAllById(Long id);
}

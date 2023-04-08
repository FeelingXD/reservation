package com.zerobase.reservation.repository;

import com.zerobase.reservation.model.entity.Customer;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmailAndPassword(String email, String password);

    Optional<Customer> findByIdAndEmail(Long id, String Email); //token

    @EntityGraph(attributePaths = {"reservation"})
    Optional<Customer> findAllById(Long id);

}

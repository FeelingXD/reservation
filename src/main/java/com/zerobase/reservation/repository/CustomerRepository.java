package com.zerobase.reservation.repository;

import com.zerobase.reservation.model.entity.Customer;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

  Optional<Customer> findByEmailAndPassword(String email, String password);

  Optional<Customer> findByIdAndEmail(Long id, String email); //token

  @EntityGraph(attributePaths = {"reservation"})
  Optional<Customer> findAllById(Long id);

}

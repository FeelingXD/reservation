package com.zerobase.reservation.repository;

import com.zerobase.reservation.model.entity.Customer;
import com.zerobase.reservation.model.entity.Reservation;
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

  @EntityGraph(attributePaths = {"shop"})
  Set<Reservation> findByCustomer(Customer c);

  @EntityGraph(attributePaths = {"shop", "customer"})
  Set<Reservation> findByCustomer_Phone(String phone);

  @EntityGraph(attributePaths = {"shop"})
  List<Reservation> findByShop_Manager_Id(Long managerId);

  @EntityGraph(attributePaths = {"customer", "shop"})
  Set<Reservation> findByIdAndCustomer_Email(Long id, String customerEmail);
}

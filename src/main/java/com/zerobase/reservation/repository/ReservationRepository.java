package com.zerobase.reservation.repository;

import com.zerobase.reservation.model.entity.Customer;
import com.zerobase.reservation.model.entity.Reservation;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @EntityGraph(attributePaths = {"shop"})
    Set<Reservation> findByCustomer(Customer c);

    @EntityGraph(attributePaths = {"shop", "customer"})
    Set<Reservation> findByCustomer_Phone(String phone);

    @EntityGraph(attributePaths = {"shop"})
    List<Reservation> findByShop_Manager_Id(Long manager_id);

    @EntityGraph(attributePaths = {"customer", "shop"})
    Set<Reservation> findByIdAndCustomer_Email(Long id, String customer_Email);
}

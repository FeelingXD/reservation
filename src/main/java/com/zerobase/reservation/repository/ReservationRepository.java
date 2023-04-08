package com.zerobase.reservation.repository;

import com.zerobase.reservation.model.entity.Customer;
import com.zerobase.reservation.model.entity.Reservation;
import org.hibernate.annotations.Fetch;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {

    @EntityGraph(attributePaths = {"shop"})
    Set<Reservation> findByCustomer(Customer c);

    @EntityGraph(attributePaths = {"shop","customer"})
    List<Reservation> findByCustomer_Phone(String phone);
}

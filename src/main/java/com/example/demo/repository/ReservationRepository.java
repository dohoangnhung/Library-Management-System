package com.example.demo.repository;

import com.example.demo.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, UUID> {

    @Query(
            nativeQuery = true,
            value = "select * from reservation where book_isbn = ?1 and status = 'WAITING'"
    )
    List<Reservation> selectReservedBooks(String isbn);

    @Query(
            nativeQuery = true,
            value = "select reserve_id from reservation where book_isbn = ?1 and member_id = ?2 and status = 'WAITING'"
    )
    Optional<UUID> selectIdOfWaitingReservation(String isbn, String memberId);

    @Modifying
    @Query(
            nativeQuery = true,
            value = "update reservation set status = 'COMPLETED' where reserve_id = ?1"
    )
    void markCompletedReservation(UUID reserveId);

    @Query(
            nativeQuery = true,
            value = "select * from reservation where book_isbn = ?1 and status = 'WAITING' and " +
                    "creation_date in (select min(creation_date) from reservation where book_isbn = ?1 and status = 'WAITING')"
    )
    Optional<Reservation> selectNearestReservation(String isbn);

}

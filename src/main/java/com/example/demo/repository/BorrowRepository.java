package com.example.demo.repository;

import com.example.demo.entity.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, UUID> {

    @Query(
            nativeQuery = true,
            value = "select * from borrow where book_barcode = ?1 and date_returned is null"
    )
    Optional<Borrow> selectBorrowedBookItemByBarcode(String barcode);

    @Query(
            nativeQuery = true,
            value = "select count(*) from borrow where member_id = ?1 and date_returned is null"
    )
    int numberOfIssuedBookByMemberId(String memberId);

    @Query(
            nativeQuery = true,
            value = "select * from borrow where date_returned is null and date_due_to_return < ?1"
    )
    List<Borrow> selectUnpaidAndOverdueBorrow(LocalDate currentDate);

}

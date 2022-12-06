package com.example.demo.repository;

import com.example.demo.entity.Fine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FineRepository extends JpaRepository<Fine, UUID> {

    @Query(
            nativeQuery = true,
            value = "select f.fine_id, f.borrow_id, f.amount, f.creation_date, f.is_paid " +
                    "from fine f, borrow b where f.borrow_id = b.borrow_id and b.member_id = ?1"
    )
    List<Fine> findAllByMemberId(String memberId);

    @Query(
            nativeQuery = true,
            value = "select * from fine where borrow_id = ?1"
    )
    Optional<Fine> findByBorrowId(UUID borrowId);

}

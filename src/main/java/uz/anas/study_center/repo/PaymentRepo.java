package uz.anas.study_center.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.anas.study_center.entity.Payment;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, UUID> {

    Page<Payment> findAllByCreatedAtBetween(LocalDateTime fromDate, LocalDateTime toDate, PageRequest pageRequest);

}

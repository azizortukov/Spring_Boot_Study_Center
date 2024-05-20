package uz.anas.study_center.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import uz.anas.study_center.entity.Payment;
import uz.anas.study_center.model.request.PaymentRequestDto;
import uz.anas.study_center.repo.PaymentRepo;
import uz.anas.study_center.repo.UserRepo;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {


    private final PaymentRepo paymentRepo;
    private final UserRepo userRepo;

    @Override
    public void savePaymentRequestDto(PaymentRequestDto payment) {
        paymentRepo.save(Payment.builder()
                .amount(payment.getAmount())
                .createdAt(LocalDateTime.now())
                .payType(payment.getPayType())
                .student(userRepo.findById(payment.getStudentId()).orElse(null))
                .build());
    }

    @Override
    public Page<Payment> getAll(Integer pageNumber, Integer pageSize, LocalDateTime fromDate, LocalDateTime toDate) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        if (fromDate != null && toDate != null) {
            return paymentRepo.findAllByCreatedAtBetween(fromDate, toDate, pageRequest);
        }else {
            return paymentRepo.findAll(pageRequest);
        }
    }
}

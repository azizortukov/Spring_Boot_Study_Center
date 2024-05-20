package uz.anas.study_center.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import uz.anas.study_center.entity.Payment;
import uz.anas.study_center.model.request.PaymentRequestDto;

import java.time.LocalDateTime;

@Service
public interface PaymentService {

    void savePaymentRequestDto(PaymentRequestDto paymentRequestDto);

    Page<Payment> getAll(Integer pageNumber, Integer pageSize, LocalDateTime fromDate, LocalDateTime toDate);
}

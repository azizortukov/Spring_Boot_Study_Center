package uz.anas.study_center.model.request;


import uz.anas.study_center.entity.enums.PayType;

import java.util.UUID;

public interface PaymentRequestDto {

    UUID getStudentId();
    Integer getAmount();
    PayType getPayType();


}

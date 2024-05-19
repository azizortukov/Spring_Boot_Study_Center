package uz.anas.study_center.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.anas.study_center.entity.enums.PayType;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @Min(value = 1, message = "Amount must be greater than zero!")
    private Integer amount;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PayType payType;


}

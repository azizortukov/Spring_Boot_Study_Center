package uz.anas.study_center.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.anas.study_center.entity.enums.PayType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "payments")
public class Payment{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    private User student;
    @Min(value = 1, message = "Amount must be greater than zero!")
    private int amount;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PayType payType;
    @Column(nullable = false)
    private LocalDateTime createdAt;

    public String createdAtFormatted() {
        return createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }


}

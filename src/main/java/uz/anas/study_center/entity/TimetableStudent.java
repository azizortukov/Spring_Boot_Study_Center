package uz.anas.study_center.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class TimetableStudent{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne(fetch = FetchType.EAGER)
    private User student;
    @ManyToOne
    private Timetable timetable;
    private int paid;
    private boolean paymentStatus;
    @OneToMany(mappedBy = "timetableStudent", cascade = CascadeType.ALL)
    List<StudentAttendance> attendances;

}

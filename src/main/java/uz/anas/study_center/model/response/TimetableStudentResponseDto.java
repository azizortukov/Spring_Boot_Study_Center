package uz.anas.study_center.model.response;

import java.time.LocalDate;
import java.util.UUID;

public interface TimetableStudentResponseDto {

    UUID getTimetableStudentId();
    String getPhoneNumber();
    Boolean[] getAttendances();
    LocalDate[] getLessonDates();

}

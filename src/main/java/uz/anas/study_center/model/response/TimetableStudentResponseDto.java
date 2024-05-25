package uz.anas.study_center.model.response;

import java.sql.Date;
import java.util.UUID;

public interface TimetableStudentResponseDto {

    UUID getTimetableStudentId();
    String getPhoneNumber();
    Boolean[] getAttendances();
    Date[] getLessonDates();

}

package uz.anas.study_center.model.response;

import java.util.UUID;

public interface TimetableStudentResponseDto {

    UUID getTimetableStudentId();
    String getPhoneNumber();
    Boolean[] getAttendances();
}

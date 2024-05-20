package uz.anas.study_center.model.request;

public interface UserRequestDto {
    String getFirstName();
    String getLastName();
    String getPhoneNumber();
    String getPassword();
    String getConfirmPassword();
}

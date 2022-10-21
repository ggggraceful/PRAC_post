package  com.sparta.week04_post1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

//비밀번호 확인 Dto
@AllArgsConstructor
@Data
public class PasswordCheckRequestDto {

    private String password;

}

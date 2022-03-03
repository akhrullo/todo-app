package uz.iDev.spring_security.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthUserCreateDto {
    private String username;
    private String password;
}

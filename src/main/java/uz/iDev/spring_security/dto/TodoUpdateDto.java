package uz.iDev.spring_security.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class TodoUpdateDto {
    private Long id;

    @Size(min = 10, max = 20, message = "value for title must be between {min} and {max}")
    private String title;

    @NotBlank(message = "description can not be empty")
    private String description;
}

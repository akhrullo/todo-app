package uz.elmurodov.spring_security.dto;

import lombok.Getter;
import lombok.Setter;

import javax.annotation.RegEx;
import javax.validation.constraints.*;

@Getter
@Setter
public class TodoCreateDto {

    //    @Min(value = 12, message = "min value for title is {value}")
//    @Max(value = 30, message = "max value for title is {value}")
    @Size(min = 10, max = 20, message = "value for title must be between {min} and {max}")
    private String title;

    @NotBlank(message = "description can not be empty")
    private String description;

}

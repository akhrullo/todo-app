package uz.elmurodov.spring_security.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import uz.elmurodov.spring_security.entity.auth.AuthUser;

import javax.persistence.*;


@Getter
@Setter
@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private boolean completed;

    @Column(nullable = false)
    private Long userId;
}

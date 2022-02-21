package uz.elmurodov.spring_security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.elmurodov.spring_security.entity.Todo;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    //    List<Todo> findAllByUser_IdAndCompletedNot(Long id);
//
//    @Query("from Todo t where t.user.id = :id and not t.completed")
//    List<Todo> findAllByUserId(@Param("id") Long id);
//
    List<Todo> findAllByUserId(Long id);
}

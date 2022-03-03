package uz.iDev.spring_security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.iDev.spring_security.entity.Todo;

import javax.transaction.Transactional;
import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    //    List<Todo> findAllByUser_IdAndCompletedNot(Long id);
//
//    @Query("from Todo t where t.user.id = :id and not t.completed")
//    List<Todo> findAllByUserId(@Param("id") Long id);
//
    List<Todo> findAllByUserId(Long id);


    @Transactional
    @Modifying
    @Query("update Todo e set e.completed = :completed where e.id = :id")
    void completeTodo(@Param("id") Long id, @Param("completed") boolean completed);

    @Transactional
    @Modifying
    @Query("update Todo t set t.title = ?1 , t.description = ?2 where t.id = ?3")
    void update(String title, String description, Long id);
}

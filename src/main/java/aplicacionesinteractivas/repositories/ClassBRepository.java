package aplicacionesinteractivas.repositories;

import aplicacionesinteractivas.models.ClassB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassBRepository extends JpaRepository<ClassB, Integer> {

    @Query(value = "select a_id from a_b where b_id = :bId", nativeQuery = true)
    List<Integer> findClassBListA(Integer bId);
}
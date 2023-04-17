package aplicacionesinteractivas.repositories;

import aplicacionesinteractivas.models.ClassA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassARepository extends JpaRepository<ClassA, Integer> {
}
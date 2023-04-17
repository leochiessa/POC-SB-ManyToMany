package aplicacionesinteractivas.services;

import aplicacionesinteractivas.models.ClassA;
import aplicacionesinteractivas.repositories.ClassARepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@Service
public class ClassAService {

    private final ClassARepository car;
    private final ModelMapper mm = new ModelMapper();

    @Autowired
    public ClassAService(ClassARepository car) {
        this.car = car;
    }

    public ResponseEntity add(ClassA ca) {
        try {
            car.save(ca);
            return ResponseEntity.status(CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    public ClassA get(Integer id) {
        try {
            return car.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public ResponseEntity update(Integer id, ClassA ca) {
        try {
            ClassA classA = car.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST));
            classA.setName(ca.getName());
            classA.setListB(ca.getListB());
            car.save(classA);
            return ResponseEntity.status(OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity delete(Integer id) {
        try {
            car.deleteById(id);
            return ResponseEntity.status(OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    public List<ClassA> getAll() {
        try {
            return car.findAll().stream().collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
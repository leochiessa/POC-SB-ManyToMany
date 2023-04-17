package aplicacionesinteractivas.services;

import aplicacionesinteractivas.models.ClassA;
import aplicacionesinteractivas.models.ClassADTO;
import aplicacionesinteractivas.models.ClassB;
import aplicacionesinteractivas.repositories.ClassARepository;
import aplicacionesinteractivas.repositories.ClassBRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Service
public class ClassBService {

    private final ClassARepository car;
    private final ClassBRepository cbr;
    private final ModelMapper mm = new ModelMapper();

    @Autowired
    public ClassBService(ClassARepository car, ClassBRepository cbr) {
        this.car = car;
        this.cbr = cbr;
    }

    public ResponseEntity add(ClassB cb) {
        try {
            cbr.save(cb);
            return ResponseEntity.status(CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    public ClassB get(Integer id) {
        try {
            return cbr.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<ClassADTO> getClassBListA(Integer id) {
        try {
            List<Integer> aIdList = cbr.findClassBListA(id);
            List<ClassADTO> listA = new ArrayList<>();
            for (Integer aId : aIdList) {
                ClassA classA = car.findById(aId).orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST));
                listA.add(mm.map(classA, ClassADTO.class));
            }
            return listA;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public ResponseEntity update(Integer id, ClassB cb) {
        try {
            ClassB classB = cbr.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST));
            classB.setName(cb.getName());
            classB.setListA(cb.getListA());
            cbr.save(classB);
            return ResponseEntity.status(OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity delete(Integer id) {
        try {
            cbr.deleteById(id);
            return ResponseEntity.status(OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    public List<ClassB> getAll() {
        try {
            return cbr.findAll().stream().collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
package aplicacionesinteractivas.controllers;

import aplicacionesinteractivas.models.ClassADTO;
import aplicacionesinteractivas.models.ClassB;
import aplicacionesinteractivas.services.ClassBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classB")
public class ClassBController {

    @Autowired
    private ClassBService cbs;

    @PostMapping("")
    public ResponseEntity add(@RequestBody ClassB cb) {
        return cbs.add(cb);
    }

    @GetMapping("/{id}")
    public ClassB get(@PathVariable Integer id) {
        return cbs.get(id);
    }

    @PostMapping("/{id}/update")
    public ResponseEntity update(@PathVariable Integer id, @RequestBody ClassB cb) {
        return cbs.update(id, cb);
    }

    @PostMapping("/{id}/delete")
    public ResponseEntity delete(@PathVariable Integer id) {
        return cbs.delete(id);
    }

    @GetMapping("")
    public List<ClassB> getAll() {
        return cbs.getAll();
    }

    @GetMapping("/{id}/listA")
    public List<ClassADTO> getClassBListA(@PathVariable Integer id) {
        return cbs.getClassBListA(id);
    }
}
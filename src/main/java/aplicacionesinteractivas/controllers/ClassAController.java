package aplicacionesinteractivas.controllers;

import aplicacionesinteractivas.models.ClassA;
import aplicacionesinteractivas.services.ClassAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classA")
public class ClassAController {

    @Autowired
    private ClassAService cas;

    @PostMapping("")
    public ResponseEntity add(@RequestBody ClassA ca) {
        return cas.add(ca);
    }

    @GetMapping("/{id}")
    public ClassA get(@PathVariable Integer id) {
        return cas.get(id);
    }

    @PostMapping("/{id}/update")
    public ResponseEntity update(@PathVariable Integer id, @RequestBody ClassA ca) {
        return cas.update(id, ca);
    }

    @PostMapping("/{id}/delete")
    public ResponseEntity delete(@PathVariable Integer id) {
        return cas.delete(id);
    }

    @GetMapping("")
    public List<ClassA> getAll() {
        return cas.getAll();
    }
}
package aplicacionesinteractivas.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ClassA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @ManyToMany
    @JoinTable(name = "a_b", joinColumns = @JoinColumn(name = "a_id"), inverseJoinColumns = @JoinColumn(name = "b_id"))
    private List<ClassB> listB;
}
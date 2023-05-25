package restaurant.ProjectApahida.model;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Setter
@Getter
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String name;
    private Integer age;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String username;
    private String password;
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String employeeCode;

    public Employee() {}

    public Employee(String name, Integer age, String username, String password, String employeeCode) {
        this.name = name;
        this.age = age;
        this.username = username;
        this.password = password;
        this.employeeCode = employeeCode;
    }
}

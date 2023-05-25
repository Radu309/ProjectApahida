package restaurant.ProjectApahida.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;


import java.io.Serializable;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "dish")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Dish implements Serializable{
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Float price;
    private Integer stock;
    @ManyToMany(mappedBy = "dishList", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Orders> dishList;


}

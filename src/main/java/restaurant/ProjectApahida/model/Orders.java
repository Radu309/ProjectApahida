package restaurant.ProjectApahida.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Setter
@Getter
@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
public class Orders implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Float totalPrice;
    private LocalDate localDate = LocalDate.now();
    private LocalTime localTime = LocalTime.now();
    private String status = "New Order";

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "order_dish",
        joinColumns = {
            @JoinColumn(name = "order_id", referencedColumnName = "id")
        },
        inverseJoinColumns = {
            @JoinColumn(name = "dish_id", referencedColumnName = "id")
        }
    )
    @JsonManagedReference
    @JsonIgnore
    private List<Dish> dishList;


}

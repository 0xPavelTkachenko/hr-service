package www.service.hrservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import www.service.hrservice.view.View;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "states")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class State {

    @JsonView(View.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonView(View.class)
    @Column
    private String name;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "state")
    private List<Person> people;

}

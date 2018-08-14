package www.service.hrservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import www.service.hrservice.view.View;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "accounts")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Account {

    @JsonView(View.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonView(View.class)
    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String password;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String patronym;

    @Column
    private String email;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "account")
    private List<Person> people;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "account")
    private List<Vacancy> vacancies;

}

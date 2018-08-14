package www.service.hrservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import www.service.hrservice.view.PersonView;
import www.service.hrservice.view.View;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "vacancies")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Vacancy {

    @JsonView(View.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonView(View.class)
    @Column
    private String name;

    @JsonView(View.class)
    @JsonProperty("is_open")
    @Column(name = "is_open", nullable = false)
    private Boolean open = true;

    @JsonView(View.class)
    @JsonProperty("created_by")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "created_by")
    private Account account;

    @JsonView(View.class)
    @JsonProperty("created_when")
    @JsonFormat(pattern = "dd.MM.yyyy")
    @Column(name = "created_when", updatable = false)
    private Date createdWhen;

    @JsonView(PersonView.class)
    @JsonIgnoreProperties("vacancies")
    @ManyToMany(mappedBy = "vacancies", fetch = FetchType.EAGER)
    private List<Person> people;

}

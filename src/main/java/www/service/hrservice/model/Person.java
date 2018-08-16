package www.service.hrservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import www.service.hrservice.view.AccountView;
import www.service.hrservice.view.View;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "people")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Person {

    @JsonView(View.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonView(View.class)
    @Column
    private String name;

    @JsonView(View.class)
    @Column
    private String surname;

    @JsonView(View.class)
    @Column
    private String patronym;

    @JsonView(AccountView.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column
    private Date birthday;

    @JsonView(AccountView.class)
    @Column
    private Integer gender;

    @JsonView(AccountView.class)
    @Column
    private String telephone;

    @JsonView(AccountView.class)
    @Column
    private String email;

    @JsonView(AccountView.class)
    @Column
    private String address;

    @JsonView(AccountView.class)
    @Column
    private Integer education;

    @JsonView(AccountView.class)
    @Column
    private String comment;

    @JsonView(AccountView.class)
    @Column
    private String about;

    @JsonView(View.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Europe/Moscow")
    @JsonProperty("received_when")
    @Column(name = "received_when")
    private Date receivedWhen;

    @JsonView(View.class)
    @JsonIgnoreProperties("people")
    @JsonProperty("created_by")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "created_by", updatable = false)
    private Account account;

    @JsonView(View.class)
    @JsonProperty("created_when")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "created_when", updatable = false)
    private Date createdWhen;

    @JsonView(View.class)
    @JsonIgnoreProperties("people")
    @JsonProperty("state")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "state_id")
    private State state;

    @JsonView(AccountView.class)
    @Column(nullable = false)
    private Boolean checked = false;

    @JsonView(AccountView.class)
    @Column(nullable = false)
    private Boolean ignored = false;

    @JsonView(AccountView.class)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "people_vacancies", joinColumns = @JoinColumn(name = "p_id"), inverseJoinColumns = @JoinColumn(name = "v_id"))
    @JsonIgnoreProperties("people")
    private List<Vacancy> vacancies;

}

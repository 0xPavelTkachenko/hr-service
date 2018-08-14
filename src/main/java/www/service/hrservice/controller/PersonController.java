package www.service.hrservice.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import www.service.hrservice.view.AccountView;
import www.service.hrservice.model.Person;
import www.service.hrservice.model.Result;
import www.service.hrservice.view.View;
import www.service.hrservice.service.PersonService;

import java.util.List;

@RestController
@RequestMapping("/${service.api.person:person}")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("/")
    public Result create(@RequestBody Person person) throws Exception {
        return new Result(true, personService.create(person));
    }

    @JsonView(View.class)
    @GetMapping("/")
    public Result findAll(@RequestParam(value = "limit", defaultValue = "0", required = false) Integer limit, @RequestParam(value = "offset", defaultValue = "0", required = false) Integer offset, @RequestParam(value = "ignored", required = false) Boolean ignored) {
        List<Person> list;
        if (ignored == null) {
            list = personService.findAll(limit, offset);
        } else {
            list = personService.findAllByIgnored(limit, offset, ignored);
        }
        return new Result(true, list);
    }

    @JsonView(AccountView.class)
    @GetMapping("/{id}")
    public Result findById(@PathVariable int id) throws Exception {
        return new Result(true, personService.findById(id));
    }

    @PutMapping("/{id}")
    public Result update(@PathVariable int id, @RequestBody Person person) throws Exception {
        personService.update(id, person);
        return new Result(true, null);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable int id) throws Exception {
        personService.delete(id);
        return new Result(true, null);
    }

}

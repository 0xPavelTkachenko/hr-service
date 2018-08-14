package www.service.hrservice.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import www.service.hrservice.model.Result;
import www.service.hrservice.model.Vacancy;
import www.service.hrservice.view.PersonView;
import www.service.hrservice.view.View;
import www.service.hrservice.service.VacancyService;

@RestController
@RequestMapping("/${service.api.vacancy:vacancy}")
public class VacancyController {

    @Autowired
    private VacancyService vacancyService;

    @PostMapping("/")
    public Result create(@RequestBody Vacancy vacancy) throws Exception {
        return new Result(true, vacancyService.create(vacancy));
    }

    @JsonView(View.class)
    @GetMapping("/")
    public Result findAll(@RequestParam(value = "limit", defaultValue = "0", required = false) Integer limit, @RequestParam(value = "offset", defaultValue = "0", required = false) Integer offset) {
        return new Result(true, vacancyService.findAll(limit, offset));
    }

    @JsonView(PersonView.class)
    @GetMapping("/{id}")
    public Result findById(@PathVariable int id) throws Exception {
        return new Result(true, vacancyService.findById(id));
    }

    @PutMapping("/{id}")
    public Result update(@PathVariable int id, @RequestBody Vacancy vacancy) throws Exception {
        vacancyService.update(id, vacancy);
        return new Result(true, null);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable int id) throws Exception {
        vacancyService.delete(id);
        return new Result(true, null);
    }

}

package www.service.hrservice.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import www.service.hrservice.model.State;
import www.service.hrservice.model.Result;
import www.service.hrservice.view.View;
import www.service.hrservice.service.StateService;

@RestController
@RequestMapping("/${service.api.state:state}")
public class StateController {

    @Autowired
    private StateService stateService;

    @PostMapping("/")
    public Result create(@RequestBody State state) throws Exception { ;
        return new Result(true, stateService.create(state));
    }

    @JsonView(View.class)
    @GetMapping("/")
    public Result findAll(@RequestParam(value = "limit", defaultValue = "0", required = false) Integer limit, @RequestParam(value = "offset", defaultValue = "0", required = false) Integer offset) {
        return new Result(true, stateService.findAll(limit, offset));
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable int id) throws Exception {
        return new Result(true, stateService.findById(id));
    }

    @PutMapping("/{id}")
    public Result update(@PathVariable int id, @RequestBody State state) throws Exception {
        stateService.update(id, state);
        return new Result(true, null);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable int id) throws Exception {
        stateService.delete(id);
        return new Result(true, null);
    }

}

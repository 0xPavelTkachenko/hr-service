package www.service.hrservice.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import www.service.hrservice.model.Account;
import www.service.hrservice.model.Result;
import www.service.hrservice.view.View;
import www.service.hrservice.service.AccountService;

@RestController
@RequestMapping("/${service.api.account:account}")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/")
    public Result create(@RequestBody Account account) throws Exception {
        return new Result(true, accountService.create(account));
    }

    @JsonView(View.class)
    @GetMapping("/")
    public Result findAll(@RequestParam(value = "limit", defaultValue = "0", required = false) Integer limit, @RequestParam(value = "offset", defaultValue = "0", required = false) Integer offset) {
        return new Result(true, accountService.findAll(limit, offset));
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable int id) throws Exception {
        return new Result(true, accountService.findById(id));
    }

    @PutMapping("/{id}")
    public Result update(@PathVariable int id, @RequestBody Account account) throws Exception {
        accountService.update(id, account);
        return new Result(true, null);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable int id) throws Exception {
        accountService.delete(id);
        return new Result(true, null);
    }

}

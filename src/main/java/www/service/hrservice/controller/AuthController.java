package www.service.hrservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import www.service.hrservice.model.Result;
import www.service.hrservice.service.AuthService;

@RestController
@RequestMapping("/${service.api.auth:auth}")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/admin/")
    public Result isAdmin(@RequestParam String key) {
        return new Result(authService.isAdmin(key), null);
    }

    @PostMapping("/client/")
    public Result isClient(@RequestParam String login, @RequestParam String password) {
        return new Result(authService.isClient(login, password), authService.getClientId(login, password));
    }

}

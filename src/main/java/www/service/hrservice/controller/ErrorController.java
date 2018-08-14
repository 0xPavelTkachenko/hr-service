package www.service.hrservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import www.service.hrservice.global.GlobalPropertyHandler;
import www.service.hrservice.model.Result;

import java.util.Locale;

@RestController
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @Autowired
    private GlobalPropertyHandler handler;

    @Autowired
    private MessageSource messageSource;

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")
    public Result getError() {
        return new Result(false, messageSource.getMessage("error.method.notfound", null, Locale.getDefault()));
    }

}

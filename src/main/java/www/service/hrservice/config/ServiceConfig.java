package www.service.hrservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import www.service.hrservice.service.*;

@Configuration
public class ServiceConfig {

    @Bean
    public AccountService accountService() {
        return new AccountServiceImpl();
    }

    @Bean
    public StateService stateService() {
        return new StateServiceImpl();
    }

    @Bean
    public PersonService personService() {
        return new PersonServiceImpl();
    }

    @Bean
    public VacancyService vacancyService() {
        return new VacancyServiceImpl();
    }

    @Bean
    public AuthService authService() {
        return new AuthServiceImpl();
    }

}

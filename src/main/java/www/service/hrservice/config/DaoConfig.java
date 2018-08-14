package www.service.hrservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import www.service.hrservice.dao.*;

@Configuration
public class DaoConfig {

    @Bean
    public AccountDao accountDao() {
        return new AccountDaoImpl();
    }

    @Bean
    public StateDao stateDao() {
        return new StateDaoImpl();
    }

    @Bean
    public PersonDao personDao() {
        return new PersonDaoImpl();
    }

    @Bean
    public VacancyDao vacancyDao() {
        return new VacancyDaoImpl();
    }

}

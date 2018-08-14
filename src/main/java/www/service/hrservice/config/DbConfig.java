package www.service.hrservice.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import www.service.hrservice.global.GlobalPropertyHandler;
import www.service.hrservice.model.*;

import javax.sql.DataSource;
import java.io.File;

@Configuration
public class DbConfig {

    @Autowired
    private GlobalPropertyHandler propertyHandler;

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder
                .create()
                .username(propertyHandler.getDbUsername())
                .password(propertyHandler.getDbPassword())
                .url("jdbc:h2:file:" + propertyHandler.getDbDir() + File.separator + propertyHandler.getDbName())
                .driverClassName("org.h2.Driver")
                .build();
    }

    @Bean
    public SessionFactory sessionFactory() {
        return new org.hibernate.cfg.Configuration()
                .setProperty("hibernate.connection.url", "jdbc:h2:file:" + propertyHandler.getDbDir() + File.separator + propertyHandler.getDbName())
                .setProperty("hibernate.connection.username", propertyHandler.getDbUsername())
                .setProperty("hibernate.connection.password", propertyHandler.getDbPassword())
                .setProperty("hibernate.connection.driver_class", "org.h2.Driver")
                .setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect")
                .setProperty("hibernate.current_session_context_class", "org.hibernate.context.internal.ThreadLocalSessionContext")
                .addAnnotatedClass(Account.class)
                .addAnnotatedClass(State.class)
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Vacancy.class)
                .buildSessionFactory();
    }

}

package www.service.hrservice.global;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("application.properties")
@Data
public class GlobalPropertyHandler {

    @Value("${service.key:root}")
    private String key;

    @Value("${service.db.dir}")
    private String dbDir;

    @Value("${service.db.name}")
    private String dbName;

    @Value("${service.db.username}")
    private String dbUsername;

    @Value("${service.db.password}")
    private String dbPassword;

}

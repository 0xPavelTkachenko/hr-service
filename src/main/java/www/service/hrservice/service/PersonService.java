package www.service.hrservice.service;

import www.service.hrservice.model.Person;

import java.util.List;

public interface PersonService extends ModelService<Person> {

    List<Person> findAllByIgnored(int limit, int offset, boolean ignored);

}

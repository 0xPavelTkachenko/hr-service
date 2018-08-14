package www.service.hrservice.dao;

import www.service.hrservice.model.Person;

import java.util.List;

public interface PersonDao extends Dao<Person> {

    List<Person> findAllByIgnored(int limit, int offset, boolean ignored);

}

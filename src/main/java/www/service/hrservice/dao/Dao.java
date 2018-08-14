package www.service.hrservice.dao;

import www.service.hrservice.dao.exception.ObjectNotFoundException;

import java.util.List;

public interface Dao<M> {

    int create(M model);

    List<M> findAll(int limit, int offset);

    M findById(int id) throws ObjectNotFoundException;

    void update(int id, M model) throws ObjectNotFoundException;

    void delete(int id) throws ObjectNotFoundException;

    boolean exists(int id);

}

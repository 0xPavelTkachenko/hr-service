package www.service.hrservice.service;

import www.service.hrservice.dao.exception.ObjectNotFoundException;
import www.service.hrservice.service.exception.ValidationException;

import java.util.List;

public interface ModelService<M> {

    int create(M model) throws ValidationException;

    List<M> findAll(int limit, int offset);

    M findById(int id) throws ObjectNotFoundException;

    void update(int id, M model) throws ObjectNotFoundException, ValidationException;

    void delete(int id) throws ObjectNotFoundException;

    boolean exists(int id);

}

package www.service.hrservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import www.service.hrservice.dao.StateDao;
import www.service.hrservice.dao.exception.ObjectNotFoundException;
import www.service.hrservice.model.State;

import java.util.List;

public class StateServiceImpl implements StateService {

    @Autowired
    private StateDao stateDao;

    @Override
    public int create(State state) {
        return stateDao.create(state);
    }

    @Override
    public List<State> findAll(int limit, int offset) {
        return stateDao.findAll(limit, offset);
    }

    @Override
    public State findById(int id) throws ObjectNotFoundException {
        return stateDao.findById(id);
    }

    @Override
    public void update(int id, State state) throws ObjectNotFoundException {
        stateDao.update(id, state);
    }

    @Override
    public void delete(int id) throws ObjectNotFoundException {
        stateDao.delete(id);
    }

    @Override
    public boolean exists(int id) {
        return stateDao.exists(id);
    }

}

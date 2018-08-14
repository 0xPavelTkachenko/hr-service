package www.service.hrservice.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import www.service.hrservice.dao.exception.ObjectNotFoundException;
import www.service.hrservice.model.State;

import java.util.List;

public class StateDaoImpl implements StateDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public int create(State state) {
        int id;
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            id = (Integer) session.save(state);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }

        return id;
    }

    @Override
    public List<State> findAll(int limit, int offset) {
        List<State> list;
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();

            if (limit <= 0) {
                if (offset <= 0) {
                    list = session.createQuery("FROM State").list();
                } else {
                    list = session.createQuery("FROM State").setFirstResult(offset).list();
                }
            } else {
                if (offset <= 0) {
                    list = session.createQuery("FROM State").setMaxResults(limit).list();
                } else {
                    list = session.createQuery("FROM State").setFirstResult(offset).setMaxResults(limit).list();
                }
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
        return list;
    }

    @Override
    public State findById(int id) throws ObjectNotFoundException {
        State state;
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            state = session.get(State.class, id);
            if (state == null) {
                throw new ObjectNotFoundException();
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
        return state;
    }

    @Override
    public void update(int id, State state) throws ObjectNotFoundException {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            if (session.get(State.class, id) == null) {
                throw new ObjectNotFoundException();
            }
            state.setId(id);
            session.merge(state);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void delete(int id) throws ObjectNotFoundException {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            State state = session.get(State.class, id);
            if (state == null) {
                throw new ObjectNotFoundException();
            }
            session.delete(state);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public boolean exists(int id) {
        State state;
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            state = session.get(State.class, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
        return state != null;
    }

}

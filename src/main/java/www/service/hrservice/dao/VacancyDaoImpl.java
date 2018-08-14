package www.service.hrservice.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import www.service.hrservice.dao.exception.ObjectNotFoundException;
import www.service.hrservice.model.Vacancy;

import java.util.List;

public class VacancyDaoImpl implements VacancyDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public int create(Vacancy vacancy) {
        int id;
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            id = (Integer) session.save(vacancy);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }

        return id;
    }

    @Override
    public List<Vacancy> findAll(int limit, int offset) {
        List<Vacancy> list;
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            if (limit <= 0) {
                if (offset <= 0) {
                    list = session.createQuery("FROM Vacancy").list();
                } else {
                    list = session.createQuery("FROM Vacancy").setFirstResult(offset).list();
                }
            } else {
                if (offset <= 0) {
                    list = session.createQuery("FROM Vacancy").setMaxResults(limit).list();
                } else {
                    list = session.createQuery("FROM Vacancy").setFirstResult(offset).setMaxResults(limit).list();
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
    public Vacancy findById(int id) throws ObjectNotFoundException {
        Vacancy vacancy;
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            vacancy = session.get(Vacancy.class, id);
            if (vacancy == null) {
                throw new ObjectNotFoundException();
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
        return vacancy;
    }

    @Override
    public void update(int id, Vacancy vacancy) throws ObjectNotFoundException {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            if (session.get(Vacancy.class, id) == null) {
                throw new ObjectNotFoundException();
            }
            vacancy.setId(id);
            session.merge(vacancy);
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
            Vacancy vacancy = session.get(Vacancy.class, id);
            if (vacancy == null) {
                throw new ObjectNotFoundException();
            }
            session.delete(vacancy);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public boolean exists(int id) {
        Vacancy vacancy;
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            vacancy = session.get(Vacancy.class, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
        return vacancy != null;
    }

}

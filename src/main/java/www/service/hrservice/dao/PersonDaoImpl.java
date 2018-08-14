package www.service.hrservice.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import www.service.hrservice.dao.exception.ObjectNotFoundException;
import www.service.hrservice.model.Person;

import java.util.List;

public class PersonDaoImpl implements PersonDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public int create(Person person) {
        int id;
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            id = (Integer) session.save(person);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }

        return id;
    }

    @Override
    public List<Person> findAll(int limit, int offset) {
        List<Person> list;
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            if (limit <= 0) {
                if (offset <= 0) {
                    list = session.createQuery("FROM Person").list();
                } else {
                    list = session.createQuery("FROM Person").setFirstResult(offset).list();
                }
            } else {
                if (offset <= 0) {
                    list = session.createQuery("FROM Person").setMaxResults(limit).list();
                } else {
                    list = session.createQuery("FROM Person").setFirstResult(offset).setMaxResults(limit).list();
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
    public List<Person> findAllByIgnored(int limit, int offset, boolean ignored) {
        List<Person> list;
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            if (limit <= 0) {
                if (offset <= 0) {
                    list = session.createQuery("FROM Person WHERE ignored = :ignored").setParameter("ignored", ignored).list();

                } else {
                    list = session.createQuery("FROM Person WHERE ignored = :ignored").setParameter("ignored", ignored).setFirstResult(offset).list();
                }
            } else {
                if (offset <= 0) {
                    list = session.createQuery("FROM Person WHERE ignored = :ignored").setParameter("ignored", ignored).setMaxResults(limit).list();
                } else {
                    list = session.createQuery("FROM Person WHERE ignored = :ignored").setParameter("ignored", ignored).setFirstResult(offset).setMaxResults(limit).list();
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
    public Person findById(int id) throws ObjectNotFoundException {
        Person person;
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            person = session.get(Person.class, id);
            if (person == null) {
                throw new ObjectNotFoundException();
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
        return person;
    }

    @Override
    public void update(int id, Person person) throws ObjectNotFoundException {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            if (session.get(Person.class, id) == null) {
                throw new ObjectNotFoundException();
            }
            person.setId(id);
            session.merge(person);
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
            Person person = session.get(Person.class, id);
            if (person == null) {
                throw new ObjectNotFoundException();
            }
            session.delete(person);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public boolean exists(int id) {
        Person person;
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            person = session.get(Person.class, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
        return person != null;
    }

}

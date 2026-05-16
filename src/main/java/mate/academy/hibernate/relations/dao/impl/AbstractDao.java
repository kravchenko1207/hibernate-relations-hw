package mate.academy.hibernate.relations.dao.impl;

import mate.academy.hibernate.relations.exception.DataProcessingException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public abstract class AbstractDao {
    protected final SessionFactory factory;

    protected AbstractDao(SessionFactory sessionFactory) {
        this.factory = sessionFactory;
    }

    public <T> T add(T entity) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            return entity;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can not add" + entity + "to DB", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public <T> T get(Long id, Class<T> clazz) {
        try (Session session = factory.openSession()) {
            return session.get(clazz, id);
        } catch (Exception e) {
            throw new DataProcessingException(
                    "Can not find by id " + id, e);
        }
    }

    public <T> List<T> getAll(Class<T> clazz) {
        try (Session session = factory.openSession()) {
            return session.createQuery(
                    "FROM " + clazz.getSimpleName(), clazz
            ).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException(
                    "Can not get all elements of " + clazz.getSimpleName(), e
            );
        }
    }

    public <T> T update(T entity) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can not update entity " + entity, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return entity;
    }

    public <T> void delete(Long id, Class<T> clazz) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            T entity = session.get(clazz, id);
            session.delete(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can not delete " + clazz.getSimpleName() + " with id " + id, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}


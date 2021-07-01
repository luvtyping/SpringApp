package springApp.repository;

import springApp.exception.HibernateDataManipulationException;
import springApp.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HibernateUserDao extends AbstractHibernateDao implements UserDao {
    public User create(User user) {
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.save(user);
                transaction.commit();
            } catch (Exception ex) {
                transaction.rollback();
                throw new HibernateDataManipulationException(ex.getMessage());
            }
        }
        return user;
    }

    public User update(User user) {
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.update(user);
                transaction.commit();
            } catch (Exception ex) {
                transaction.rollback();
                throw new HibernateDataManipulationException(ex.getMessage());
            }
        }
        return user;
    }

    public void remove(User user) {
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.delete(user);
                transaction.commit();
            } catch (Exception ex) {
                transaction.rollback();
                throw new HibernateDataManipulationException(ex.getMessage());
            }
        }
    }

    public List<User> findAll() {
        try (Session session = createSession()) {
            return (List<User>) session.createQuery("From users").list();
        }
    }

    public User findById(Long id) {
        try (Session session = createSession()) {
            return session.get(User.class, id);
        }
    }
}
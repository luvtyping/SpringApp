package springApp.repository;

import springApp.exception.HibernateSessionException;
import springApp.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

abstract class AbstractHibernateDao {
    private static final SessionFactory defaultSessionFactory;
    private static final String defaultConfigFile = "hibernate.cfg.xml";

    static {
        try {
            Configuration configuration = new Configuration().configure(defaultConfigFile);
            configuration.addAnnotatedClass(User.class);
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            defaultSessionFactory = configuration.buildSessionFactory(builder.build());
        } catch (Exception e) {
            throw new HibernateSessionException(e.getMessage());
        }
    }

    public Session createSession() {
        return defaultSessionFactory.openSession();
    }
}
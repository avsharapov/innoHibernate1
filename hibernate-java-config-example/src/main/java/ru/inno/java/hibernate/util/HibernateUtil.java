package ru.inno.java.hibernate.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.inno.java.hibernate.entity.Course;
import ru.inno.java.hibernate.entity.Instructor;
import ru.inno.java.hibernate.entity.InstructorDetail;
import ru.inno.java.hibernate.entity.Student;

import java.util.Properties;

public class HibernateUtil {
    private static Logger logger = LoggerFactory.getLogger(HibernateUtil.class);

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "org.postgresql.Driver");
                settings.put(Environment.URL, "jdbc:postgresql://localhost:5434/jpaDB");
                settings.put(Environment.USER, "postgres");
                settings.put(Environment.PASS, "qwerty");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQL10Dialect");

                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.FORMAT_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "create-drop");

                configuration.setProperties(settings);

                configuration.addAnnotatedClass(Student.class);
                configuration.addAnnotatedClass(Course.class);
                configuration.addAnnotatedClass(Instructor.class);
                configuration.addAnnotatedClass(InstructorDetail.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                logger.error("", e);
            }
        }
        return sessionFactory;
    }
}

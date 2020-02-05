package ru.inno.jpa.hibernate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.inno.jpa.hibernate.entity.Student;
import ru.inno.jpa.hibernate.util.JPAUtil;

import javax.persistence.EntityManager;

public class App {
    private static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {

        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        Student student = new Student("Ivan", "Bunin", "ivanbu@javaguides.com");
        logger.info("{}", student);
        entityManager.persist(student);
        entityManager.getTransaction().commit();
        entityManager.close();

        student.setLastName("Arkadiy");
        logger.info("{}", student);

        entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.merge(student);
        entityManager.getTransaction().commit();
        entityManager.close();

        entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        logger.info("=====");
        final Student student1 = entityManager.getReference(Student.class, student.getId());
        logger.info("=====");
        logger.info("{}", student1);
        entityManager.getTransaction().commit();
        entityManager.close();

        JPAUtil.shutdown();
    }

}

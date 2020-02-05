package ru.inno.java.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.inno.java.hibernate.entity.Instructor;
import ru.inno.java.hibernate.util.HibernateUtil;

import java.util.List;

public class InstructorDao {
    private static Logger logger = LoggerFactory.getLogger(InstructorDao.class);

    public void saveInstructor(Instructor instructor) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(instructor);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("", e);
        }
    }

    public List<Instructor> getInstructors() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Instructor", Instructor.class).list();
        }
    }
}

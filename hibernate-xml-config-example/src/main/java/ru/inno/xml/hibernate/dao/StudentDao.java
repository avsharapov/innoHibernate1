package ru.inno.xml.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.inno.xml.hibernate.entity.Student;
import ru.inno.xml.hibernate.util.HibernateUtil;

import java.util.List;

public class StudentDao {
    private static Logger logger = LoggerFactory.getLogger(StudentDao.class);

    public void saveStudent(Student student) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object

            session.save(student);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("", e);
        }
    }

    public List<Student> getStudents() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Student", Student.class).list();
        }
    }
}

package ru.inno.java.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.inno.java.hibernate.entity.Student;
import ru.inno.java.hibernate.util.HibernateUtil;

import javax.persistence.Query;
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

    public void insertStudent() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            String hql    = "INSERT INTO Student (firstName, lastName, email) SELECT firstName, lastName, email FROM Student";
            Query  query  = session.createQuery(hql);
            int    result = query.executeUpdate();
            System.out.println("Rows affected: " + result);

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("", e);
        }
    }


    public void deleteStudent(Student student) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
/*       Object mergeStudent = session.merge(student);
      session.remove(mergeStudent);*/
            final Query sqlQuery = session.createSQLQuery("DELETE FROM student WHERE id=" + student.getId());
            int         result   = sqlQuery.executeUpdate();
            System.out.println("Rows affected: " + result);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("", e);
        }
    }


}

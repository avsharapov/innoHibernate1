package ru.inno.xml.hibernate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.inno.xml.hibernate.dao.StudentDao;
import ru.inno.xml.hibernate.entity.Student;
import ru.inno.xml.hibernate.util.HibernateUtil;

import java.util.List;

public class App {
    private static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        StudentDao studentDao = new StudentDao();
        Student    student    = new Student("Ivan", "Bunin", "ivanbu@javaguides.com");
        studentDao.saveStudent(student);

        List<Student> students = studentDao.getStudents();
        students.forEach(s -> logger.info("{}", s.getFirstName()));

        HibernateUtil.shutdown();
    }
}

package ru.inno.java.hibernate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.inno.java.hibernate.dao.CourseDao;
import ru.inno.java.hibernate.dao.InstructorDao;
import ru.inno.java.hibernate.dao.StudentDao;
import ru.inno.java.hibernate.entity.Course;
import ru.inno.java.hibernate.entity.Instructor;
import ru.inno.java.hibernate.entity.InstructorDetail;
import ru.inno.java.hibernate.entity.Student;

import java.util.Collections;
import java.util.List;

public class App {
    private static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {

        //create some instructor
        InstructorDao    instructorDao    = new InstructorDao();
        Instructor       instructor       = new Instructor("Ernesto", "Guevara", "ergu@javaguides.com");
        InstructorDetail instructorDetail = new InstructorDetail("http://www.youtube.com", "Guitar");
        instructorDetail.setInstructor(instructor);
        instructor.setInstructorDetail(instructorDetail);

        // create some courses
        CourseDao courseDao = new CourseDao();

        Course tempCourse1 = new Course("Air Guitar - The Ultimate Guide");
        tempCourse1.setInstructor(instructor);

        Course tempCourse2 = new Course("The Pinball Masterclass");
        tempCourse2.setInstructor(instructor);

        courseDao.saveCourse(tempCourse1);
        courseDao.saveCourse(tempCourse2);

        //create some students
        StudentDao studentDao = new StudentDao();

        Student tempStudent1 = new Student("Ivan", "Bunin", "ivanbu@javaguides.com");
        tempStudent1.setCourses(Collections.singletonList(tempCourse1));
        studentDao.saveStudent(tempStudent1);

        Student tempStudent2 = new Student("Matvey", "Mashin", "mama@javaguides.com");
        tempStudent2.setCourses(Collections.singletonList(tempCourse2));
        studentDao.saveStudent(tempStudent2);

        studentDao.insertStudent();
        studentDao.deleteStudent(tempStudent1);

        List<Student> students = studentDao.getStudents();
        logger.info("==========================================");
        students.forEach(x -> logger.info("{}", x));
        logger.info("==========================================");
        List<Instructor> instructors = instructorDao.getInstructors();
        logger.info("==========================================");
        instructors.forEach(x -> logger.info("{}", x));
        logger.info("==========================================");
    }
}

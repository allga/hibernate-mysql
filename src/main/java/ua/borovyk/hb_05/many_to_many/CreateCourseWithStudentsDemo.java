package ua.borovyk.hb_05.many_to_many;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.borovyk.hb_05.many_to_many.entity.*;

public class CreateCourseWithStudentsDemo {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            //create a course
            Course course = new Course("Java for begginers");

            System.out.println("\nSaving the course...");
            session.save(course);
            System.out.println("Saved course: " + course);

            //create students
            Student student1 = new Student("John", "Doe", "doe@gmail.com");
            Student student2 = new Student("Mary", "Public", "public@gmail.com");

            //add students to the course
            course.addStudent(student1);
            course.addStudent(student2);

            //save the students
            System.out.println("\nSaving the students...");
            session.save(student1);
            session.save(student2);
            System.out.println("Saved students: " + course.getStudents());

            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            session.close();
            factory.close();
        }
    }
}

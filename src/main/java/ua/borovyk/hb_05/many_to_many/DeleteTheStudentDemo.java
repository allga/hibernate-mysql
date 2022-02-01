package ua.borovyk.hb_05.many_to_many;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.borovyk.hb_05.many_to_many.entity.*;

public class DeleteTheStudentDemo {
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

            //get the student
            int id = 2;
            Student student = session.get(Student.class, id);
            System.out.println("\nLoaded student: " + student);
            System.out.println("Courses: " + student.getCourses());

            //delete the student
            System.out.println("\nDeleting student...");
            session.delete(student);

            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            session.close();
            factory.close();
        }
    }
}

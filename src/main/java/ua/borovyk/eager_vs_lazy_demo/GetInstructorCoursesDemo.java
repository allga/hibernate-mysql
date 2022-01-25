package ua.borovyk.eager_vs_lazy_demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.borovyk.eager_vs_lazy_demo.entity.Course;
import ua.borovyk.eager_vs_lazy_demo.entity.Instructor;
import ua.borovyk.eager_vs_lazy_demo.entity.InstructorDetail;

public class GetInstructorCoursesDemo {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            // get the instructor from db
            int id = 1;
            Instructor instructor = session.get(Instructor.class, id);

            System.out.println("Instructor " + instructor);

            // get courses for the Instructor
            System.out.println("Courses " + instructor.getCourses());

            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            session.close();

            factory.close();
        }
    }
}

package ua.borovyk.eager_vs_lazy_demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.borovyk.eager_vs_lazy_demo.entity.Course;
import ua.borovyk.eager_vs_lazy_demo.entity.Instructor;
import ua.borovyk.eager_vs_lazy_demo.entity.InstructorDetail;

public class CreateCoursesDemo {
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

            // create some courses
            Course course1 = new Course("Math");
            Course course2 = new Course("English");

            // add courses to instructor
            instructor.addCourse(course1);
            instructor.addCourse(course2);

            // save the courses
            session.save(course1);
            session.save(course2);

            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            session.close();

            factory.close();
        }
    }
}

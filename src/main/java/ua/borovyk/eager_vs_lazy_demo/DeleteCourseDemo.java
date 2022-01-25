package ua.borovyk.eager_vs_lazy_demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.borovyk.eager_vs_lazy_demo.entity.Course;
import ua.borovyk.eager_vs_lazy_demo.entity.Instructor;
import ua.borovyk.eager_vs_lazy_demo.entity.InstructorDetail;

public class DeleteCourseDemo {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            //get a course
            int id = 16;
            Course course = session.get(Course.class, id);
            System.out.println(course);

            //delete the course
            System.out.println("Deleting Course " + id);

            session.delete(course);


            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            session.close();

            factory.close();
        }
    }
}

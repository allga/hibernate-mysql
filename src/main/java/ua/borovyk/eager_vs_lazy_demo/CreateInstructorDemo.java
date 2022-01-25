package ua.borovyk.eager_vs_lazy_demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.borovyk.eager_vs_lazy_demo.entity.Course;
import ua.borovyk.eager_vs_lazy_demo.entity.Instructor;
import ua.borovyk.eager_vs_lazy_demo.entity.InstructorDetail;

public class CreateInstructorDemo {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {

            Instructor instructor = new Instructor("Susan", "Public", "public@gmail.com");

            InstructorDetail instructorDetail =
                    new InstructorDetail(
                            "http://www.public.com/youtube",
                            "Video games"
                    );

            instructor.setInstructorDetail(instructorDetail);

            session.beginTransaction();

            System.out.println("Saving " + instructor);
            session.save(instructor);

            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            session.close();

            factory.close();
        }
    }
}

package ua.borovyk.hb_04_one_to_many_uni;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.borovyk.hb_04_one_to_many_uni.entity.Course;
import ua.borovyk.hb_04_one_to_many_uni.entity.Instructor;
import ua.borovyk.hb_04_one_to_many_uni.entity.InstructorDetail;
import ua.borovyk.hb_04_one_to_many_uni.entity.Review;

public class DeleteCourseWithReviewsDemo {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            // get the course
            int id = 11;
            Course course = session.get(Course.class, id);

            // print the course
            System.out.println("Deleting the course.....");
            System.out.println(course);

            // print the course reviews
            System.out.println(course.getReviews());

            // delete the course
            session.delete(course);

            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            session.close();
            factory.close();
        }
    }
}

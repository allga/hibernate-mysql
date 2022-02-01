package ua.borovyk.hb_04_one_to_many_uni;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.borovyk.hb_04_one_to_many_uni.entity.Course;
import ua.borovyk.hb_04_one_to_many_uni.entity.Instructor;
import ua.borovyk.hb_04_one_to_many_uni.entity.InstructorDetail;
import ua.borovyk.hb_04_one_to_many_uni.entity.Review;

public class CreateCourseWithReviewsDemo {
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

            //create a course
            Course course = new Course("Java for begginers");

            //add some reviews
            course.addReview(new Review("Great!"));
            course.addReview(new Review("Love it!"));
            course.addReview(new Review("Awful"));

            //save the course... and leverage the cascade all)
            System.out.println("Saving the course");
            System.out.println(course);
            System.out.println(course.getReviews());

            session.save(course);


            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            session.close();
            factory.close();
        }
    }
}

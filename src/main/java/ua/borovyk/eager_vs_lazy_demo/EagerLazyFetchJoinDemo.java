package ua.borovyk.eager_vs_lazy_demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ua.borovyk.eager_vs_lazy_demo.entity.Course;
import ua.borovyk.eager_vs_lazy_demo.entity.Instructor;
import ua.borovyk.eager_vs_lazy_demo.entity.InstructorDetail;

public class EagerLazyFetchJoinDemo {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            //option 1: Hibernate query with HQL

            // get the instructor from db
            int id = 1;

            Query<Instructor> query =
                    session.createQuery("select i from Instructor i "
                                            + "JOIN FETCH i.courses "
                                            + "where i.id=:theInstructorId",
                            Instructor.class) ;

            //set parameter on query
            query.setParameter("theInstructorId", id);

            //execute query ang get instructor
            Instructor instructor = query.getSingleResult();

            System.out.println("Instructor " + instructor);

            session.getTransaction().commit();

            //close the session
            session.close();

            // get courses for the Instructor
            System.out.println("----------------------------------------");
            System.out.println("Courses " + instructor.getCourses());

            System.out.println("Done!");
        } finally {
            session.close();

            factory.close();
        }
    }
}

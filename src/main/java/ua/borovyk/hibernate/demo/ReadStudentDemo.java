package ua.borovyk.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.borovyk.hibernate.demo.entity.Student;

public class ReadStudentDemo {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class).buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            System.out.println("Creating a new Student object");
            Student tmpStudent = new Student("Deffy", "Duck", "duck@gmail.com");

            session.beginTransaction();

            System.out.println("Saving the student...");
            System.out.println(tmpStudent);
            session.save(tmpStudent);

            session.getTransaction().commit();

            System.out.println("Saved student. Generated id " + tmpStudent.getId());

            // retrieving object from the DB

            //get a new session and start transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            System.out.println("Getting student with ID " + tmpStudent.getId());
            Student myStudent = session.get(Student.class, tmpStudent.getId());

            System.out.println("Get complete: " + myStudent);

            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            factory.close();
        }
    }
}

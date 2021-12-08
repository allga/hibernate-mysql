package ua.borovyk.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.borovyk.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class).buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            System.out.println("Creating a new Student objects");
            Student Student1 = new Student("Kate", "Scott", "scott@gmail.com");
            Student Student2 = new Student("Paul", "Smith", "smith@gmail.com");
            Student Student3 = new Student("Nick", "Junior", "junior@gmail.com");

            session.beginTransaction();

            System.out.println("Saving the student...");
            session.save(Student1);
            session.save(Student2);
            session.save(Student3);

            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            factory.close();
        }
    }
}


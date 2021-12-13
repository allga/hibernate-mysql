package ua.borovyk.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.borovyk.hibernate.demo.entity.Student;

import java.util.List;

public class QueryStudentDemo {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class).buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            List<Student> theStudents = session.createQuery("from Student").getResultList();

            displayStudents(theStudents);

            theStudents = session.createQuery("from Student s where s.lastName='Duck'").getResultList();

            System.out.println("\n\nLast name Duck");
            displayStudents(theStudents);

            System.out.println("\n\nLast name Scott or First name Nick");
            theStudents = session.createQuery("from Student s where s.lastName='Scott' OR s.firstName='Nick'").getResultList();
            displayStudents(theStudents);

            System.out.println("\n\nEmail contains duck");
            theStudents = session.createQuery("from Student s where s.email LIKE 'duck%'").getResultList();
            displayStudents(theStudents);

            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            factory.close();
        }
    }

    private static void displayStudents(List<Student> theStudents) {
        for(Student student: theStudents) {
            System.out.println(student);
        }
    }
}

package ua.borovyk.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.borovyk.hibernate.demo.entity.Student;

public class DeleteStudentDemo {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class).buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
//            int studentID =7;
//
//            session = factory.getCurrentSession();
//            session.beginTransaction();
//
//            System.out.println("Getting student with ID " + studentID);
//            Student myStudent = session.get(Student.class, studentID);
//
//            System.out.println("Deleting student: " + myStudent);
//            session.delete(myStudent);
//
//            session.getTransaction().commit();

            session = factory.getCurrentSession();
            session.beginTransaction();

            System.out.println("Delete with HQL query");
            session.createQuery("delete Student where id=5").executeUpdate();

            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            factory.close();
        }
    }
}

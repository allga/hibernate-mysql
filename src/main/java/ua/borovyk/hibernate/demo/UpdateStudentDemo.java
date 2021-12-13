package ua.borovyk.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.borovyk.hibernate.demo.entity.Student;

public class UpdateStudentDemo {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class).buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            int studentID =1;

            session = factory.getCurrentSession();
            session.beginTransaction();

            System.out.println("Getting student with ID " + studentID);
            Student myStudent = session.get(Student.class, studentID);

            System.out.println("Updating student...");
            myStudent.setEmail("scooby@ukr.net");

            session.getTransaction().commit();

            session = factory.getCurrentSession();
            session.beginTransaction();

            System.out.println("Update with query");
            session.createQuery("update Student set last_name='Golf' where ID=4").executeUpdate();

            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            factory.close();
        }
    }
}

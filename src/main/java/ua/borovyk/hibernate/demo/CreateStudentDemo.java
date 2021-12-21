package ua.borovyk.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.borovyk.hibernate.demo.entity.Student;

import java.text.ParseException;
import java.util.Date;

public class CreateStudentDemo {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class).buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            System.out.println("Creating a new Student object");

            String theDateOfBirthStr = "31/12/1984";
            Date theDateOfBirth = DateUtils.parseDate(theDateOfBirthStr);
            Student tmpStudent = new Student("Mark", "Sweet", "mark@ukr.net", theDateOfBirth);

            session.beginTransaction();

            System.out.println("Saving the student...");
            session.save(tmpStudent);

            session.getTransaction().commit();

            System.out.println("Done!");
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            factory.close();
        }
    }
}

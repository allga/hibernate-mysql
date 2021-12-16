package ua.borovyk.hwMySql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.borovyk.hwMySql.entity.Employee;

public class CreateEmployee {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class).buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {

            System.out.println("Create employee");
            Employee employee = new Employee("Albert", "Grinch", "Fiesta");

            session.beginTransaction();
            System.out.println("Save employee");
            session.save(employee);
            session.getTransaction().commit();

            System.out.println("Done");
        } finally {
            factory.close();
        }
    }
}

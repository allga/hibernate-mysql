package ua.borovyk.hwMySql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.borovyk.hwMySql.entity.Employee;

import java.util.List;

public class QueryEmployee {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class).buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {

            session.beginTransaction();

            System.out.println("Getting all employees");

            List<Employee> employees = session.createQuery("from Employee").getResultList();
            displayEmployees(employees);

            System.out.println("Getting employee with company Ford");

            employees = session.createQuery("from Employee s where s.company='Ford'").getResultList();
            displayEmployees(employees);

            session.getTransaction().commit();

            System.out.println("Done");
        } finally {
            factory.close();
        }
    }

    private static void displayEmployees(List<Employee> employees) {
        for(Employee employee : employees) {
            System.out.println(employee);
        }
    }
}

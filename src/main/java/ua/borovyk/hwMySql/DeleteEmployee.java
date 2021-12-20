package ua.borovyk.hwMySql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.borovyk.hwMySql.entity.Employee;

public class DeleteEmployee {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class).buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {

//            int employeeId = 4;
//            session.beginTransaction();
//
//            System.out.println("Getting employee with id: " + employeeId);
//            Employee employee = session.get(Employee.class, employeeId);
//
//            System.out.println("Deleting student: " + employeeId);
//            session.delete(employee);
//
//            session.getTransaction().commit();

            session.beginTransaction();

            session.createQuery("delete Employee where id=6").executeUpdate();
            session.getTransaction().commit();

            System.out.println("Done");
        } finally {
            factory.close();
        }
    }
}

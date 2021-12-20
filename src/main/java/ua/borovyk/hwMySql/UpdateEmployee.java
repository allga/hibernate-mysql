package ua.borovyk.hwMySql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.borovyk.hwMySql.entity.Employee;

public class UpdateEmployee {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class).buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            int employeeID = 1;

//            session.beginTransaction();

//            System.out.println("Getting employee");
//            Employee employee = session.get(Employee.class, employeeID);
//            employee.setCompany("Ford");
//
//            System.out.println("Save employee");
//            session.getTransaction().commit();
            System.out.println("Start with HQL");


            session = factory.getCurrentSession();
            session.beginTransaction();

            System.out.println("Updating with HQL");
            session.createQuery("update Employee set last_name='Golf' where ID=2").executeUpdate();

            session.getTransaction().commit();

            System.out.println("Done");
        } finally {
            factory.close();
        }
    }
}

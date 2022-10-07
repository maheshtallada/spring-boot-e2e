// We have one record in DB with the Employee details like, 101, John Doe, UK

// Open hibernate session
Session session = HibernateUtil.getSessionFactory().openSession();
session.beginTransaction();

// Fetch an Employee entity from the database very first time
Employee employee = (Employee) session.load(Employee.class, empId);
System.out.println("First call output : " + employee.getName());
 
// Request for Employee entity again
employee = (Employee) session.load(Employee.class, empId);
System.out.println("Second call output : "employee.getName());
 
session.getTransaction().commit();
HibernateUtil.shutdown();
 
// Output:
// First call output : John Doe
// Second call output : John Doe
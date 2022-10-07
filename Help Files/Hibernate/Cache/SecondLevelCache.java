// Open hibernate session
Session session = HibernateUtil.getSessionFactory().openSession();
session.beginTransaction();

// Employee entity is fecthed very first time (It will be cached in both first-level and second-level cache)
Employee employee = (Employee) session.load(Employee.class, empId);
System.out.println(employee.getName());

// Fetch the employee entity again
employee = (Employee) session.load(Employee.class, empId);
System.out.println(employee.getName()); //It will return from the first-level

// Evict from first level cache (That will remove employee object from first-level cache)
session.evict(employee);

// Fetch same entity again using same session
employee = (Employee) session.load(Employee.class, empId);
System.out.println(employee.getName()); //It will return from the second-level

// Fetch same entity again using another session
employee = (Employee) anotherSession.load(Employee.class, empId);
System.out.println(employee.getName());//It will return from the second-level

System.out.println("Response from the first-level : " + HibernateUtil.getSessionFactory().getStatistics().getEntityFetchCount());
System.out.println("Response from the second-level : " + HibernateUtil.getSessionFactory().getStatistics().getSecondLevelCacheHitCount());
 
// Output:
// Response from the first-level : 1
// Response from the second-level : 2
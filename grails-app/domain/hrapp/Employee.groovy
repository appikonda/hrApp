package hrapp

class Employee {
    String firstName;
    String lastName;
    String email;
    String phoneNumber;
    Integer salary;
    Date hireDate;
    Job job
    Department department;
    Employee managerId

    static constraints = {
        phoneNumber nullable: true
        managerId nullable: true
    }

    static mapping = {
        id column:'employee_id'
        firstName column: "first_name"
        lastName column: "last_name"
        phoneNumber column: "phone_number"
        hireDate column: "hire_date"
        managerId column: "manager_id"
        version false
    }
}

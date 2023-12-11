package hrapp

class Department {
    String departmentName;
    Location location;
    static constraints = {

        departmentName size:5..20,  blank: false, unique: false
    }

    static mapping = {
        id column:'department_id'
        departmentName column:'department_name'
        version false
    }
}

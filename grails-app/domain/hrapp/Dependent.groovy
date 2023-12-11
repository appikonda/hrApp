package hrapp

class Dependent {
    String firstName;
    String lastName;
    String relationship;
    Employee employee;

    static constraints = {

    }

    static mapping = {
        id column:'dependent_id'
        firstName column: "first_name"
        lastName column: "last_name"
        version false
    }
}

package hrapp

class Job {
    String jobTitle;
    Integer minSalary;
    Integer maxSalary;

    static constraints = {
        jobTitle column: "job_title"
        minSalary column: "min_salary"
        maxSalary column: "max_salary"
    }

    static mapping ={
        id column: 'job_id'
        jobTitle column: "job_title"
        minSalary column: "min_salary"
        maxSalary column: "max_salary"
        version false
    }
}

package work;

public class RegistrationDto {

    // ----------------------------------------------------------------
    // フィールド
    // ----------------------------------------------------------------
    private int DepartmentID;


    private int EmployeeID;


    private String last_name;
    private String first_name;
    private String birth_date;
    private String joining_date;
    private String department;

    // getter,setter

    public String getLast_name () {
        return last_name;
    }

    public void setLast_name (String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name () {
        return first_name;
    }

    public void setFirst_name (String first_name) {
        this.first_name = first_name;
    }

    public String getBirth_date () {
        return birth_date;
    }

    public void setBirth_date (String birth_date) {
        this.birth_date = birth_date;
    }

    public String getJoining_date () {
        return joining_date;
    }

    public void setJoining_date (String joining_date) {
        this.joining_date = joining_date;
    }

    public String getDepartment () {
        return department;
    }

    public void setDepartment (String department) {
        this.department = department;
    }
    public int getDepartmentID () {
        return DepartmentID;
    }

    public void setDepartmentID (int departmentID) {
        DepartmentID = departmentID;
    }

    public int getEmployeeID () {
        return EmployeeID;
    }

    public void setEmployeeID (int employeeID) {
        EmployeeID = employeeID;
    }

}

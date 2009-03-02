package pe.ezici.dto;

/**
 * A class that will be used as a data holder for the teacher data.
 */
public class Teacher {
    
    /**
     * A separator constant.
     */
    private final String separator = ",";
    
    /**
     * The teacher's name.
     */
    private String teachName;
    
    /**
     * The teacher's egn
     */
    private String egn;
    
    /**
     * The department.
     */
    private String department;
    
    /**
     * The faculty.
     */
    private String faculty;
    
    /**
     * The room number.
     */
    private String roomNumber;
    
    /**
     * Overridden toString method used to turn the data stored in this object
     * into string type.
     */
    public String toString() {
	StringBuffer teach = new StringBuffer();
	teach.append(teachName);
	teach.append(separator);
	teach.append(egn);
	teach.append(separator);
	teach.append(department);
	teach.append(separator);
	teach.append(faculty);
	teach.append(separator);
	teach.append(roomNumber);
	return teach.toString();
    }

    /**
     * @return the teachName
     */
    public String getTeachName() {
        return teachName;
    }

    /**
     * @param teachName the teachName to set
     */
    public void setTeachName(String teachName) {
        this.teachName = teachName;
    }

    /**
     * @return the egn
     */
    public String getEgn() {
        return egn;
    }

    /**
     * @param egn the egn to set
     */
    public void setEgn(String egn) {
        this.egn = egn;
    }

    /**
     * @return the department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * @param department the department to set
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * @return the faculty
     */
    public String getFaculty() {
        return faculty;
    }

    /**
     * @param faculty the faculty to set
     */
    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    /**
     * @return the roomNumber
     */
    public String getRoomNumber() {
        return roomNumber;
    }

    /**
     * @param roomNumber the roomNumber to set
     */
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }
}

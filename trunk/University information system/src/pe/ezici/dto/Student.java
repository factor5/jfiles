package pe.ezici.dto;

/**
 * A class that will be used as a data holder for the student data.
 */
public class Student {
    
    /**
     * A separator constant.
     */
    private final String separator = ",";
    
    /**
     * The student's name.
     */
    private String studentName;
    
    /**
     * The student's number.
     */
    private String facNumber;
    
    /**
     * The result for this student.
     */
    private float result;
    
    /**
     * The student's speciality.
     */
    private String speciality;
    
    /**
     * The course
     */
    private int course;
    
    /**
     * Overridden toString method used to turn the data stored in this object
     * into string type.
     */
    public String toString() {
	StringBuffer stud = new StringBuffer();
	stud.append(studentName);
	stud.append(separator);
	stud.append(facNumber);
	stud.append(separator);
	stud.append(speciality);
	stud.append(separator);
	stud.append(course);
	stud.append(separator);
	stud.append(result);
	return stud.toString();
    }

    /**
     * @return the studentName
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * @param studentName the studentName to set
     */
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    /**
     * @return the facNumber
     */
    public String getFacNumber() {
        return facNumber;
    }

    /**
     * @param facNumber the facNumber to set
     */
    public void setFacNumber(String facNumber) {
        this.facNumber = facNumber;
    }

    /**
     * @return the result
     */
    public float getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(float result) {
        this.result = result;
    }

    /**
     * @return the speciality
     */
    public String getSpeciality() {
        return speciality;
    }

    /**
     * @param speciality the speciality to set
     */
    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    /**
     * @return the course
     */
    public int getCourse() {
        return course;
    }

    /**
     * @param course the course to set
     */
    public void setCourse(int course) {
        this.course = course;
    }

}

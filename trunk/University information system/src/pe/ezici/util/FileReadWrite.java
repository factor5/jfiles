package pe.ezici.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import pe.ezici.dto.Staff;
import pe.ezici.dto.Student;
import pe.ezici.dto.Teacher;

/**
 * This class provides some convenient methods for reading data from file and
 * writing to a file.
 */
public class FileReadWrite {

    /**
     * The students file object
     */
    private static File studFile;

    /**
     * The teachers file object
     */
    private static File teachFile;

    /**
     * The students file object
     */
    private static File staffFile;

    /**
     * The list with students data.
     */
    public static List<Student> studentsList;

    /**
     * The list with teachers data.
     */
    public static List<Teacher> teacherList;

    /**
     * The list with staffs data.
     */
    public static List<Staff> staffList;

    /**
     * Constructor that checks if the needed three files exists and if not they
     * are created. Then the three lists are created from the data read from the
     * files.
     * 
     * @param fileStud
     * @param fileTeach
     * @param fileStaff
     * @throws IOException
     */
    public FileReadWrite(String fileStud, String fileTeach, String fileStaff)
	    throws IOException {
	checkResourceFiles(fileStud, fileTeach, fileStaff);
	prepareLists();
    }

    /**
     * Checks if the required files exists and if they don't it will create
     * them.
     * 
     * @param fileStud
     * @param fileTeach
     * @param fileStaff
     * @throws IOException
     */
    private void checkResourceFiles(String fileStud, String fileTeach,
	    String fileStaff) throws IOException {
	studFile = new File(fileStud);
	if (!studFile.exists()) {
	    studFile.createNewFile();
	}
	teachFile = new File(fileTeach);
	if (!teachFile.exists()) {
	    teachFile.createNewFile();
	}
	staffFile = new File(fileStaff);
	if (!staffFile.exists()) {
	    staffFile.createNewFile();
	}
    }

    /**
     * Prepares the three lists as first invokes the readFile method then
     * parseString method and finally calls the setupObjects.
     */
    private void prepareLists() {
	studentsList = new ArrayList<Student>();
	teacherList = new ArrayList<Teacher>();
	staffList = new ArrayList<Staff>();
	List<String> list = new ArrayList<String>();
	// prepare the students list
	String students = readFile(studFile).toString();
	if (!"".equals(students)) {
	    list = parseString(students);
	    setupObjects(list, 1);
	}

	// prepare the teachers list
	String teachers = readFile(teachFile).toString();
	if (!"".equals(teachers)) {
	    list = parseString(teachers);
	    setupObjects(list, 2);
	}

	// prepare the staffs list
	String staff = readFile(staffFile).toString();
	if (!"".equals(staff)) {
	    list = parseString(staff);
	    setupObjects(list, 3);
	}
    }

    /**
     * Creates objects from required type fills them with data and puts them in
     * the list.
     * 
     * @param list
     *                the list with data given as strings
     * @param type
     *                the type of the objects that should be created
     */
    private void setupObjects(List<String> list, int type) {
	switch (type) {
	case 1:
	    for (String string : list) {
		String[] fields = string.split(",");
		Student stud = new Student();
		stud.setStudentName(fields[0]);
		stud.setFacNumber(fields[1]);
		stud.setSpeciality(fields[2]);
		stud.setCourse(Integer.valueOf(fields[3]));
		stud.setResult(Float.valueOf(fields[4]));
		studentsList.add(stud);
	    }
	    break;
	case 2:
	    for (String string : list) {
		String[] fields = string.split(",");
		Teacher teacher = new Teacher();
		teacher.setTeachName(fields[0]);
		teacher.setEgn(fields[1]);
		teacher.setDepartment(fields[2]);
		teacher.setFaculty(fields[3]);
		teacher.setRoomNumber(fields[4]);
		teacherList.add(teacher);
	    }
	    break;
	case 3:
	    for (String string : list) {
		String[] fields = string.split(",");
		Staff staff = new Staff();
		staff.setStaffName(fields[0]);
		staff.setEgn(fields[1]);
		staff.setPosition(fields[2]);
		staff.setPhoneNumber(fields[3]);
		staffList.add(staff);
	    }
	    break;
	default:
	    break;
	}
    }

    /**
     * Splits the given source string around the '\r\n' signs and turns the
     * result string array into a list.
     * 
     * @param source
     *                the source string
     * @return a list with strings that represents data
     */
    private List<String> parseString(String source) {
	String[] stringObjects = source.split("\r\n");
	List<String> list = Arrays.asList(stringObjects);
	return list;
    }

    /**
     * Iterates the provided list and stores its content to a file.
     * 
     * @param type
     *                the type of the objects that are stored in the list
     * @return true if the data is stored successfully
     */
    public static boolean storeListInFile(int type) {
	File path = null;
	List list = null;
	if (type == 1) {
	    path = studFile;
	    list = new ArrayList<Student>();
	    list = studentsList;
	} else if (type == 2) {
	    path = teachFile;
	    list = new ArrayList<Teacher>();
	    list = teacherList;
	} else {
	    path = staffFile;
	    list = new ArrayList<Staff>();
	    list = staffList;
	}
	if (list == null || list.size() == 0) {
	    return false;
	}
	BufferedWriter bw = null;
	try {
	    // create the output stream
	    bw = new BufferedWriter(new FileWriter(path));
	    String string = null;
	    // iterate the list
	    for (Iterator iterator = list.iterator(); iterator.hasNext();) {
		switch (type) {
		case 1:
		    // get the next data from the list
		    Student student = (Student) iterator.next();
		    // call toString so to turn the object to string type
		    string = student.toString();
		    break;
		case 2:
		    Teacher teacher = (Teacher) iterator.next();
		    string = teacher.toString();
		    break;
		case 3:
		    Staff staff = (Staff) iterator.next();
		    string = staff.toString();
		    break;
		default:
		    break;
		}
		// write the result string in the file
		bw.write(string);
		// put a new line to separate the data
		bw.newLine();
	    }
	    // if everything is okay we return true
	    return true;
	} catch (IOException e) {
	    // if any error occurs we return false
	    return false;
	} finally {
	    // we close the stream in the finally clause
	    try {
		if (bw != null) {
		    bw.close();
		}
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
    }

    /**
     * Reads some data from file and appends it to a buffer.
     * 
     * @param path
     * @return
     */
    public StringBuilder readFile(File path) {
	if (path == null) {
	    return null;
	}
	BufferedReader br = null;
	StringBuilder result = null;
	result = new StringBuilder();
	int ch = 0;
	try {
	    br = new BufferedReader(new FileReader(path));
	    while ((ch = br.read()) != -1) {
		result.append((char) ch);
	    }
	    return result;
	} catch (IOException e) {
	    return null;
	} finally {
	    try {
		if (br != null) {
		    br.close();
		}
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
    }

    /**
     * @return the studentsList
     */
    public List<Student> getStudentsList() {
	return studentsList;
    }

    /**
     * @param studentsList
     *                the studentsList to set
     */
    public void setStudentsList(List<Student> studentsList) {
	this.studentsList = studentsList;
    }

    /**
     * @return the teacherList
     */
    public List<Teacher> getTeacherList() {
	return teacherList;
    }

    /**
     * @param teacherList
     *                the teacherList to set
     */
    public void setTeacherList(List<Teacher> teacherList) {
	this.teacherList = teacherList;
    }

    /**
     * @return the staffList
     */
    public List<Staff> getStaffList() {
	return staffList;
    }

    /**
     * @param staffList
     *                the staffList to set
     */
    public void setStaffList(List<Staff> staffList) {
	this.staffList = staffList;
    }
}

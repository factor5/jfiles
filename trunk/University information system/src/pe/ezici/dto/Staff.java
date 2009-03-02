package pe.ezici.dto;

/**
 * A class that will be used as a data holder for the staff data.
 */
public class Staff {

    /**
     * A separator constant.
     */
    private final String separator = ",";

    /**
     * The staff name.
     */
    private String staffName;

    /**
     * The staff's egn.
     */
    private String egn;

    /**
     * The staff's job position.
     */
    private String position;

    /**
     * The staff's phone number.
     */
    private String phoneNumber;

    /**
     * Overridden toString method used to turn the data stored in this object
     * into string type.
     */
    public String toString() {
	StringBuffer staff = new StringBuffer();
	staff.append(staffName);
	staff.append(separator);
	staff.append(egn);
	staff.append(separator);
	staff.append(position);
	staff.append(separator);
	staff.append(phoneNumber);
	return staff.toString();
    }

    /**
     * @return the staffName
     */
    public String getStaffName() {
	return staffName;
    }

    /**
     * @param staffName
     *                the staffName to set
     */
    public void setStaffName(String staffName) {
	this.staffName = staffName;
    }

    /**
     * @return the egn
     */
    public String getEgn() {
	return egn;
    }

    /**
     * @param egn
     *                the egn to set
     */
    public void setEgn(String egn) {
	this.egn = egn;
    }

    /**
     * @return the position
     */
    public String getPosition() {
	return position;
    }

    /**
     * @param position
     *                the position to set
     */
    public void setPosition(String position) {
	this.position = position;
    }

    /**
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
	return phoneNumber;
    }

    /**
     * @param phoneNumber
     *                the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
    }
}

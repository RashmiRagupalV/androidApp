package redflag.capstone.com.redflag.database;

/**
 * Created by Anshu on 10/20/14.
 */
public class Screener {
    private int screenerId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String contact;
    private String emailAddress;
    private int addressId;

    public int getScreenerId() {
        return screenerId;
    }

    public void setScreenerId(int screenerId) {
        this.screenerId = screenerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }
}

package contacts.phonebookrecord;

public class Organization extends PhoneBookRecord {
    private String address;

    public Organization() {
        setCreatedTime();
        setIsPeople(false);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Organization name: " + getName() +
                "\nAddress: " + getAddress() +
                "\nNumber: " + getNumber() +
                "\nTime created: " + getCreatedTime() +
                "\nTime last edit: " + getLastEditTime();
    }
}

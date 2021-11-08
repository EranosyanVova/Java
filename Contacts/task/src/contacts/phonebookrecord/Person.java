package contacts.phonebookrecord;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Person extends PhoneBookRecord {
    private String surname;
    private String gender;
    private LocalDate birthDay;

    public Person() {
        setCreatedTime();
        setIsPeople(true);
    }

    protected String getSurname() {
        return surname;
    }

    public Person setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getGender() {
        return gender == null ? "[no data]" : gender;
    }

    public Person setGender(String gender) {
        if ("M".equalsIgnoreCase(gender) || "F".equalsIgnoreCase(gender)) {
            this.gender = gender;
        } else {
            System.out.println("Bad gender!");
        }
        return this;
    }

    public String getBirthDay() {
        return birthDay == null ? "[no data]" : birthDay.toString();
    }

    public Person setBirthDay(String birthDay) {
        try {
            this.birthDay = LocalDate.parse(birthDay);
        } catch (DateTimeParseException e) {
            System.out.println("Bad birth date!");
        }
        return this;
    }

    @Override
    public String getName() {
        return super.getName() + " " + getSurname();
    }

    @Override
    public String toString() {
        return "Name: " + getName() +
                "\nSurname: " + getSurname() +
                "\nBirth date: " + getBirthDay() +
                "\nGender: " + getGender() +
                "\nNumber: " + getNumber() +
                "\nTime created: " + getCreatedTime() +
                "\nTime last edit: " + getLastEditTime();
    }
}




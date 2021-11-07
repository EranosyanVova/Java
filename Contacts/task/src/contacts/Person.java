package contacts;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract class PhoneBookRecord {
    private String name;
    private String number;
    private LocalDateTime createdTime;
    private LocalDateTime lastEditTime;
    private boolean isPeople;

    protected String getName() {
        return name;
    }

    protected PhoneBookRecord setName(String name) {
        this.name = name;
        return this;
    }

    public String getNumber() {
        return hasNumber() ? "[no number]" : number;
    }

    protected PhoneBookRecord setNumber(String number) {
        if (checkNumber(number)) {
            this.number = number;
        }
        else {
            System.out.println("Wrong number format!");
            this.number = null;
        }
        return this;
    }

    private boolean checkNumber(String number) {
        Pattern pattern = Pattern.compile("^(\\+*\\(+\\w+\\)+([\\s-]+\\w{2,})?|\\+*\\w+([\\s-]+\\(*\\w{2,}\\)*)?)([\\s-]\\w{2,})*");
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }

    protected boolean hasNumber() {
        return number == null;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime.withSecond(0).withNano(0);
    }

    public void setCreatedTime() {
        createdTime = LocalDateTime.now();
        lastEditTime = createdTime;
    }

    public LocalDateTime getLastEditTime() {
        return lastEditTime.withSecond(0).withNano(0);
    }

    public void setLastEditTime() {
        lastEditTime = LocalDateTime.now();
    }

    public boolean isPeople() {
        return isPeople;
    }

    public void setIsPeople(boolean isPeople) {
        this.isPeople = isPeople;
    }

}


public class Person extends PhoneBookRecord{
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

    protected Person setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getGender() {
        return gender == null ? "[no data]" : gender;
    }

    public Person setGender(String gender) {
        if (gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("F")) {
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

    protected String getFullName() {
        return getName() + " " + getSurname();
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

class Organization extends PhoneBookRecord {
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



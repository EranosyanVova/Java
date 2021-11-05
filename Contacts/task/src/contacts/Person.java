package contacts;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {

    private String name;
    private String surname;
    private String number = "";

    protected String getName() {
        return name;
    }

    protected String getSurname() {
        return surname;
    }

    protected String getNumber() {
        return number;
    }

    protected Person setName(String name) {
        this.name = name;
        return this;
    }

    protected Person setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    protected Person setNumber(String number) {
        if (checkNumber(number)) {
            this.number = number;
        }
        else {
            System.out.println("Wrong number format!");
            this.number = "";
        }
        return this;
    }

    private boolean checkNumber(String number) {
        Pattern pattern = Pattern.compile("^(\\+*\\(+\\w+\\)+([\\s-]+\\w{2,})?|\\+*\\w+([\\s-]+\\(*\\w{2,}\\)*)?)([\\s-]\\w{2,})*");
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }

    protected boolean hasNumber() {
        return !number.isEmpty();
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", firstname='" + surname + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}



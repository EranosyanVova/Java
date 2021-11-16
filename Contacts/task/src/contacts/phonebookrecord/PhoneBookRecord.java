package contacts.phonebookrecord;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class PhoneBookRecord {
    private String name;
    private String number;
    private LocalDateTime createdTime;
    private LocalDateTime lastEditTime;
    private boolean isPeople;

    public String getName() {
        return name;
    }

    public PhoneBookRecord setName(String name) {
        this.name = name;
        return this;
    }

    public String getNumber() {
        return hasNumber() ? "[no number]" : number;
    }

    public PhoneBookRecord setNumber(String number) {
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


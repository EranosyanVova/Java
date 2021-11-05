package contacts;

import java.util.ArrayList;

public class PhoneBook {
    private ArrayList<Person> phoneBook;

    public PhoneBook() {
        this.phoneBook = new ArrayList<>();
    }

    protected void addPersonInPhoneBook(Person person) {
        phoneBook.add(person);
    }
}

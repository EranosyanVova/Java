package contacts.createrecord;

import contacts.phonebookrecord.Person;
import contacts.phonebookrecord.PhoneBookRecord;

public class CreatePersonInPhoneBook implements CreateRecord {

    @Override
    public PhoneBookRecord createNewRecord() {
        Person person = new Person();

        System.out.print("Enter the name: ");
        person.setName(scanner.nextLine());

        System.out.print("Enter the surname: ");
        person.setSurname(scanner.nextLine());

        System.out.print("Enter the birth date: ");
        person.setBirthDay(scanner.nextLine());

        System.out.print("Enter the gender: ");
        person.setGender(scanner.nextLine());

        System.out.print("Enter the number: ");
        person.setNumber(scanner.nextLine());

        return person;
    }
}

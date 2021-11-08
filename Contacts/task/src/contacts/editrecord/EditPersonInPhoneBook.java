package contacts.editrecord;

import contacts.phonebookrecord.Person;
import contacts.phonebookrecord.PhoneBookRecord;

public class EditPersonInPhoneBook implements EditRecord {
    Person person;

    public EditPersonInPhoneBook(PhoneBookRecord phoneBookRecord) {
        person = (Person) phoneBookRecord;
    }

    @Override
    public void editRecord() {
        System.out.print("Select a field (name, surname, birth, gender, number): ");
        String lineToEdit = scanner.nextLine();
        switch (lineToEdit) {
            case "name":
                System.out.print("Enter the name: ");
                person.setName(scanner.nextLine());
                break;
            case "surname":
                System.out.print("Enter the surname: ");
                person.setSurname(scanner.nextLine());
                break;
            case "birth":
                System.out.print("Enter the birth date: ");
                person.setBirthDay(scanner.nextLine());
                break;
            case "gender":
                System.out.print("Enter the gender (M, F): ");
                person.setGender(scanner.nextLine());
                break;
            case "number":
                System.out.print("Enter number: ");
                person.setNumber(scanner.nextLine());
                break;
            default:
                System.out.println("Wrong choose: " + lineToEdit);
        }
        person.setLastEditTime();
        System.out.println("Saved");
    }
}

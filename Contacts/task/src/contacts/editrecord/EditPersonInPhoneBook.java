package contacts.editrecord;

import contacts.phonebookrecord.Person;
import contacts.phonebookrecord.PhoneBookRecord;

public class EditPersonInPhoneBook implements EditRecord {
    final Person personRecord;

    public EditPersonInPhoneBook(PhoneBookRecord phoneBookRecord) {
        personRecord = (Person) phoneBookRecord;
    }

    @Override
    public void editRecord() {
        System.out.printf("Select a field (%s): ", RecordsField.getPersonField());
        String lineToEdit = scanner.nextLine().toUpperCase();
        switch (RecordsField.valueOf(lineToEdit)) {
            case NAME:
                System.out.print("Enter the name: ");
                personRecord.setName(scanner.nextLine());
                break;
            case SURNAME:
                System.out.print("Enter the surname: ");
                personRecord.setSurname(scanner.nextLine());
                break;
            case BIRTH:
                System.out.print("Enter the birth date: ");
                personRecord.setBirthDay(scanner.nextLine());
                break;
            case GENDER:
                System.out.print("Enter the gender (M, F): ");
                personRecord.setGender(scanner.nextLine());
                break;
            case NUMBER:
                System.out.print("Enter number: ");
                personRecord.setNumber(scanner.nextLine());
                break;
            default:
                System.out.println("Wrong choose: " + lineToEdit);
        }
        personRecord.setLastEditTime();
        System.out.println("Saved");
    }
}

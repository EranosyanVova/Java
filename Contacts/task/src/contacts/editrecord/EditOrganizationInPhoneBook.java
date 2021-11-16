package contacts.editrecord;

import contacts.phonebookrecord.Organization;
import contacts.phonebookrecord.PhoneBookRecord;

public class EditOrganizationInPhoneBook implements EditRecord {
    final Organization organizationRecord;

    public EditOrganizationInPhoneBook(PhoneBookRecord phoneBookRecord) {
        organizationRecord = (Organization) phoneBookRecord;
    }

    @Override
    public void editRecord() {
        System.out.printf("Select a field (%s): ", RecordsField.getOrganizationField());
        String lineToEdit = scanner.nextLine().toUpperCase();
        switch (RecordsField.valueOf(lineToEdit)) {
            case NAME:
                System.out.print("Enter the name: ");
                organizationRecord.setName(scanner.nextLine());
                break;
            case ADDRESS:
                System.out.print("Enter the address: ");
                organizationRecord.setAddress(scanner.nextLine());
                break;
            case NUMBER:
                System.out.print("Enter number: ");
                organizationRecord.setNumber(scanner.nextLine());
                break;
            default:
                System.out.println("Wrong choose: " + lineToEdit);
        }
        organizationRecord.setLastEditTime();
        System.out.println("Saved");
    }
}

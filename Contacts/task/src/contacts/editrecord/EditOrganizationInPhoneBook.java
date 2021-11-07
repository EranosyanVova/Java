package contacts.editrecord;

import contacts.phonebookrecord.Organization;
import contacts.phonebookrecord.PhoneBookRecord;

public class EditOrganizationInPhoneBook implements EditRecord {
    Organization organization;

    public EditOrganizationInPhoneBook(PhoneBookRecord phoneBookRecord) {
        organization = (Organization) phoneBookRecord;
    }

    @Override
    public void editRecord() {
        System.out.print("Select a field (name, address, number): ");
        String lineToEdit = scanner.nextLine();
        switch (lineToEdit) {
            case "name":
                System.out.print("Enter the name: ");
                organization.setName(scanner.nextLine());
                break;
            case "address":
                System.out.print("Enter the surname: ");
                organization.setAddress(scanner.nextLine());
                break;
            case "number":
                System.out.print("Enter number: ");
                organization.setNumber(scanner.nextLine());
                break;
            default:
                System.out.println("Wrong choose: " + lineToEdit);
        }
        organization.setLastEditTime();
    }
}

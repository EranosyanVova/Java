package contacts.createrecord;

import contacts.phonebookrecord.Organization;
import contacts.phonebookrecord.PhoneBookRecord;

public class CreateOrganizationInPhoneBook implements CreateRecord {

    @Override
    public PhoneBookRecord creatRecord() {
        Organization organization = new Organization();
        System.out.print("Enter the organization name: ");
        organization.setName(scanner.nextLine());
        System.out.print("Enter the address: ");
        organization.setAddress(scanner.nextLine());
        System.out.print("Enter the number: ");
        organization.setNumber(scanner.nextLine());
        return organization;
    }
}

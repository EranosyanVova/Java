package contacts;

import contacts.createrecord.*;
import contacts.editrecord.*;
import contacts.phonebookrecord.*;

import java.util.ArrayList;
import java.util.Scanner;

public class PhoneBook {
    Scanner scanner = new Scanner(System.in);
    private ArrayList<PhoneBookRecord> phoneBook;

    public PhoneBook() {
        phoneBook = new ArrayList<>();
    }

    public void menu() {
        String choose;
        do {
            System.out.print("Enter action (add, remove, edit, count, info, exit): ");
            choose = scanner.nextLine();
            switch (choose) {
                case "add":
                    addRecordInPhoneBook();
                    break;
                case "remove":
                    remove();
                    break;
                case "edit":
                    edit();
                    break;
                case "count":
                    System.out.printf("The Phone Book has %d records.\n", phoneBook.size());
                    break;
                case "info":
                    infoAboutRecord();
                    break;
                case "exit":
                    System.exit(0);
                    break;
                default:
            }
            System.out.println();
        } while (true);
    }

    protected void addRecordInPhoneBook() {
        String choose;
        System.out.print("Enter the type (person, organization): ");
        choose = scanner.nextLine();
        CreateRecord createRecord;
        if ("person".equals(choose)) {
            createRecord = new CreatePersonInPhoneBook();
        }
        else if ("organization".equals(choose)) {
            createRecord = new CreateOrganizationInPhoneBook();
        }
        else {
            System.out.println("Wrong choose: " + choose);
            return;
        }
        phoneBook.add(createRecord.creatRecord());
        System.out.println("The record added.");
    }

    protected void infoAboutRecord() {
        if (!phoneBook.isEmpty()) {
            list();
            System.out.print("Enter index to show info: ");
            System.out.println(phoneBook.get(Integer.parseInt(scanner.nextLine()) - 1));
        } else {
            System.out.println("No records to see info!");
        }
    }

    protected void list() {
        for (int i = 0; i < phoneBook.size(); i++) {
            System.out.printf("%d. %s \n", i + 1, phoneBook.get(i).isPeople() ? ((Person)phoneBook.get(i)).getFullName() : phoneBook.get(i).getName());
        }
    }

    protected void remove() {
        if (!phoneBook.isEmpty()) {
            list();
            System.out.print("Select thee records: ");
            phoneBook.remove(scanner.nextInt() - 1);
            scanner.nextLine();
            System.out.println("The record removed!");
        }
        else {
            System.out.println("No records to remove!");
        }
    }

    protected void edit() {
        if (!phoneBook.isEmpty()) {
            list();
            System.out.print("Select thee records: ");
            int choose = scanner.nextInt() - 1;
            scanner.nextLine();
            EditRecord editRecord;
            if (phoneBook.get(choose).isPeople()) {
                editRecord = new EditPersonInPhoneBook(phoneBook.get(choose));
            } else {
                editRecord = new EditOrganizationInPhoneBook(phoneBook.get(choose));
            }
            editRecord.editRecord();
            System.out.println("The record updated!");
        }
        else {
            System.out.println("No records to edit!");
        }

    }

}

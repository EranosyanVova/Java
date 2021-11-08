package contacts;

import contacts.createrecord.*;
import contacts.editrecord.*;
import contacts.phonebookrecord.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class PhoneBook implements Serializable {
    static Scanner scanner = new Scanner(System.in);
    private ArrayList<PhoneBookRecord> phoneBook;

    public PhoneBook() {
        phoneBook = new ArrayList<>();
    }

    public void menu() {
        String choose;
        do {
            System.out.print("[menu] Enter action (add, list, search, count, exit): ");
            choose = scanner.nextLine();
            switch (choose) {
                case "add":
                    addRecordInPhoneBook();
                    break;
                case "list":
                    if (list(phoneBook)) {
                        listChoose();
                    }
                    break;
                case "search":
                    search();
                    break;
                case "count":
                    System.out.printf("The Phone Book has %d records.\n", phoneBook.size());
                    break;
                case "exit":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wrong choose: " + choose);
            }
            System.out.println();
        } while (true);
    }

    protected void search() {
        System.out.print("Enter search query: ");
        String choose = scanner.nextLine();
        ArrayList<PhoneBookRecord> searchResult = search(choose);
        list(searchResult);
        searchChoose(searchResult);
    }

    protected void searchChoose(ArrayList<PhoneBookRecord> searchResult) {
        System.out.print("\n[search] Enter action ([number], back, again): ");
        String choose = scanner.nextLine();
        switch (choose) {
            case "again":
                search();
            case "back":
                break;
            default:
                int numberOfRecord;
                try {
                    numberOfRecord = Integer.parseInt(choose) - 1;
                } catch (NumberFormatException e) {
                    System.out.println("Error! Not a number: " + choose);
                    break;
                }
                System.out.println(searchResult.get(numberOfRecord));
                for (int i = 0; i < phoneBook.size(); i++) {
                    if (phoneBook.get(i).getName().equals(searchResult.get(numberOfRecord).getName()) &&
                            phoneBook.get(i).getNumber().equals(searchResult.get(numberOfRecord).getNumber())) {
                        numberOfRecord = i;
                        break;
                    }
                }
                recordChoose(numberOfRecord);
        }
    }

    protected ArrayList<PhoneBookRecord> search(String query) {
        ArrayList<PhoneBookRecord> searchResult = new ArrayList<>();
        String regex = "(?i).*" + query + ".*";
        for (PhoneBookRecord phoneBookRecord : phoneBook) {
            if (phoneBookRecord.getName().matches(regex) || phoneBookRecord.getNumber().matches(regex)) {
                searchResult.add(phoneBookRecord);
            }
        }
        return searchResult;
    }

    protected void listChoose() {
        System.out.print("\n[list] Enter action ([number], back): ");
        String choose = scanner.nextLine();
        if (!"back".equals(choose)) {
            int numberOfRecord;
            try {
                numberOfRecord = Integer.parseInt(choose) - 1;
            } catch (NumberFormatException e) {
                System.out.println("Error! Not a number: " + choose);
                listChoose();
                return;
            }
            System.out.println(phoneBook.get(numberOfRecord));
            recordChoose(numberOfRecord);
        }
    }

    protected void recordChoose(int numberOfRecord){
        System.out.print("\n[record] Enter action (edit, delete, menu): ");
        String choose = scanner.nextLine();
        switch (choose) {
            case "edit":
                edit(numberOfRecord, phoneBook);
                System.out.println(phoneBook.get(numberOfRecord));
                recordChoose(numberOfRecord);
                break;
            case "delete":
                delete(numberOfRecord);
            case "menu":
                break;
        }
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

    protected boolean list(ArrayList<PhoneBookRecord> arrayList) {
        if (!phoneBook.isEmpty()) {
            for (int i = 0; i < arrayList.size(); i++) {
                System.out.printf("%d. %s \n", i + 1, arrayList.get(i).getName());
            }
            return true;
        } else {
            System.out.println("The Phone Book has 0 records.");
            return false;
        }
    }

    protected void delete(int choose) {
        if (!phoneBook.isEmpty()) {
            phoneBook.remove(choose);
            System.out.println("The record removed!");
        }
        else {
            System.out.println("No records to remove!");
        }
    }

    protected void edit(int choose, ArrayList<PhoneBookRecord> arrayList) {
        if (!arrayList.isEmpty()) {
            EditRecord editRecord;
            if (arrayList.get(choose).isPeople()) {
                editRecord = new EditPersonInPhoneBook(arrayList.get(choose));
            } else {
                editRecord = new EditOrganizationInPhoneBook(arrayList.get(choose));
            }
            editRecord.editRecord();
            System.out.println("The record updated!");
        }
        else {
            System.out.println("No records to edit!");
        }

    }

    public ArrayList<PhoneBookRecord> getPhoneBook() {
        return phoneBook;
    }

    public void setPhoneBook(ArrayList<PhoneBookRecord> phoneBook) {
        this.phoneBook = phoneBook;
    }
}

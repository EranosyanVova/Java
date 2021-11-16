package contacts;

import contacts.createrecord.*;
import contacts.editrecord.*;
import contacts.phonebookrecord.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PhoneBook implements Serializable {
    private Scanner scanner = new Scanner(System.in);
    private List<PhoneBookRecord> phoneBook = new ArrayList<>();

    public void menu() {
        String choose;
        do {
            System.out.printf("[menu] Enter action (%s): ", Action.getActionsForMenu());
            choose = scanner.nextLine().toUpperCase();
            switch (Action.valueOf(choose)) {
                case ADD:
                    addRecord();
                    break;
                case LIST:
                    if (listOfRecords(phoneBook)) {
                        listChoose();
                    }
                    break;
                case SEARCH:
                    searchQuery();
                    break;
                case COUNT:
                    System.out.printf("The Phone Book has %d records.\n", phoneBook.size());
                    break;
                case EXIT:
                    System.exit(0);
                default:
                    System.out.println("Wrong choose: " + choose);
            }
            System.out.println();
        } while (true);
    }

    protected void searchQuery() {
        System.out.print("Enter search query: ");
        String choose = scanner.nextLine();

        List<PhoneBookRecord> searchResult = search(choose);

        listOfRecords(searchResult);
        searchChoose(searchResult);
    }

    protected void searchChoose(List<PhoneBookRecord> searchResult) {
        System.out.printf("\n[search] Enter action (%s): ", Action.getActionsForSearch());
        String choose = scanner.nextLine().toUpperCase();
        switch (Action.valueOf(choose)) {
            case AGAIN:
                searchQuery();
            case BACK:
                break;
            default:
                int numberOfRecord;
                try {
                    numberOfRecord = Integer.parseInt(choose) - 1;
                } catch (NumberFormatException e) {
                    System.out.println("Error! Not a number: " + choose);
                    break;
                }
                PhoneBookRecord chosenRecord = searchResult.get(numberOfRecord);
                System.out.println(chosenRecord);

                for (int i = 0; i < phoneBook.size(); i++) {
                    PhoneBookRecord currentRecord = phoneBook.get(i);
                    if (currentRecord.getName().equals(chosenRecord.getName()) &&
                            currentRecord.getNumber().equals(chosenRecord.getNumber())) {
                        numberOfRecord = i;
                        break;
                    }
                }
                recordChoose(numberOfRecord);
        }
    }

    protected List<PhoneBookRecord> search(String query) {
        List<PhoneBookRecord> searchResult = new ArrayList<>();
        String regex = "(?i).*" + query + ".*";
        for (PhoneBookRecord phoneBookRecord : phoneBook) {
            if (phoneBookRecord.getName().matches(regex) || phoneBookRecord.getNumber().matches(regex)) {
                searchResult.add(phoneBookRecord);
            }
        }
        return searchResult;
    }

    protected void listChoose() {
        System.out.printf("\n[list] Enter action (%s): ", Action.getActionsForList());
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
        System.out.printf("\n[record] Enter action (%s): ", Action.getActionsForRecord());
        String choose = scanner.nextLine().toUpperCase();
        switch (Action.valueOf(choose)) {
            case EDIT:
                editChosenRecord(numberOfRecord, phoneBook);
                System.out.println(phoneBook.get(numberOfRecord));
                recordChoose(numberOfRecord);
                break;
            case DELETE:
                deleteRecord(numberOfRecord);
            case MENU:
                break;
        }
    }

    protected void addRecord() {
        System.out.print("Enter the type (person, organization): ");
        String choose = scanner.nextLine();
        CreateRecord createRecord = createRecordByType(choose);
        if (createRecord == null) {
            return;
        }
        phoneBook.add(createRecord.createNewRecord());
        System.out.println("The record added.");
    }

    protected CreateRecord createRecordByType(String recordType) {
        if ("person".equals(recordType)) {
            return new CreatePersonInPhoneBook();
        }
        else if ("organization".equals(recordType)) {
            return new CreateOrganizationInPhoneBook();
        }
        else {
            System.out.println("Wrong choose: " + recordType);
            return null;
        }
    }

    protected boolean listOfRecords(List<PhoneBookRecord> phoneBookRecords) {
        if (!phoneBook.isEmpty()) {
            for (int i = 0; i < phoneBookRecords.size(); i++) {
                System.out.printf("%d. %s \n", i + 1, phoneBookRecords.get(i).getName());
            }
            return true;
        }
        System.out.println("The Phone Book has 0 records.");
        return false;
    }

    protected void deleteRecord(int numberOfRecord) {
        if (!phoneBook.isEmpty()) {
            phoneBook.remove(numberOfRecord);
            System.out.println("The record removed!");
        }
        else {
            System.out.println("No records to remove!");
        }
    }

    protected void editChosenRecord(int choose, List<PhoneBookRecord> phoneBookRecords) {
        if (phoneBookRecords.isEmpty()) {
            System.out.println("No records to edit!");
            return;
        }
        EditRecord editChosenRecordInPhoneBook = editRecordByType(choose, phoneBookRecords);
        editChosenRecordInPhoneBook.editRecord();
        System.out.println("The record updated!");

    }

    protected EditRecord editRecordByType(int choose, List<PhoneBookRecord> phoneBookRecords) {
        if (phoneBookRecords.get(choose).isPeople()) {
            return new EditPersonInPhoneBook(phoneBookRecords.get(choose));
        }
        return new EditOrganizationInPhoneBook(phoneBookRecords.get(choose));
    }

    public List<PhoneBookRecord> getPhoneBook() {
        return phoneBook;
    }

    public void setPhoneBook(ArrayList<PhoneBookRecord> phoneBook) {
        this.phoneBook = phoneBook;
    }
}

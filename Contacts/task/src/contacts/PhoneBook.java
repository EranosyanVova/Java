package contacts;

import java.util.ArrayList;
import java.util.Scanner;

public class PhoneBook {
    Scanner scanner = new Scanner(System.in);
    private ArrayList<Person> phoneBook;

    public PhoneBook() {
        this.phoneBook = new ArrayList<>();
    }

    public void menu() {
        String choose;
        do {
            System.out.print("Enter action (add, remove, edit, count, list, exit): ");
            choose = scanner.nextLine();
            switch (choose) {
                case "add":
                    addPersonInPhoneBook();
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
                case "list":
                    list();
                    break;
                case "exit":
                    System.exit(0);
                    break;
                default:
            }
        } while (true);
    }


    protected void addPersonInPhoneBook() {
        System.out.print("Enter the name: ");
        String name = scanner.nextLine();
        System.out.print("Enter the surname: ");
        String surname = scanner.nextLine();
        System.out.print("Enter the number: ");
        String number = scanner.nextLine();
        phoneBook.add(new Person().setName(name).setSurname(surname).setNumber(number));
        System.out.println("The record added.");
    }

    protected void list() {
        for (int i = 0; i < phoneBook.size(); i++) {
            System.out.printf("%d. %s %s, %s\n", i + 1, phoneBook.get(i).getName(), phoneBook.get(i).getSurname(),
                    phoneBook.get(i).hasNumber() ? phoneBook.get(i).getNumber() : "[no number]");
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
            System.out.print("Select a field (name, surname, number): ");
            String lineToEdit = scanner.nextLine();
            switch (lineToEdit) {
                case "name":
                    System.out.print("Enter name: ");
                    phoneBook.get(choose).setName(scanner.nextLine());
                    break;
                case "surname":
                    System.out.print("Enter surname: ");
                    phoneBook.get(choose).setSurname(scanner.nextLine());
                    break;
                case "number":
                    System.out.print("Enter number: ");
                    phoneBook.get(choose).setNumber(scanner.nextLine());
                    break;
            }
            System.out.println("The record updated!");
        }
        else {
            System.out.println("No records to edit!");
        }

    }

}

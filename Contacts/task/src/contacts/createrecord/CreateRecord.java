package contacts.createrecord;

import contacts.phonebookrecord.PhoneBookRecord;

import java.util.Scanner;

public interface CreateRecord {
    Scanner scanner = new Scanner(System.in);
    PhoneBookRecord createNewRecord();
}

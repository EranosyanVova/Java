package contacts.editrecord;

public enum RecordsField {
    NAME,
    SURNAME,
    BIRTH,
    GENDER,
    NUMBER,
    ADDRESS;

    public static String getPersonField() {
        return String.format("%s, %s, %s, %s, %s", NAME, SURNAME, BIRTH, GENDER, NUMBER).toLowerCase() ;
    }

    public static String getOrganizationField() {
        return String.format("%s, %s, %s", NAME, ADDRESS, NUMBER).toLowerCase() ;
    }
}

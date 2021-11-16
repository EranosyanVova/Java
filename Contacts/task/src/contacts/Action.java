package contacts;

public enum Action{
    ADD,
    LIST,
    SEARCH,
    COUNT,
    EXIT,
    NUMBER,
    BACK,
    AGAIN,
    EDIT,
    DELETE,
    MENU;

    public static String getActionsForMenu() {
        return String.format("%s, %s, %s, %s, %s", ADD, LIST, SEARCH, COUNT, EXIT).toLowerCase() ;
    }

    public static String getActionsForSearch() {
        return String.format("[%s], %s, %s", NUMBER, BACK, AGAIN).toLowerCase() ;
    }

    public static String getActionsForList() {
        return String.format("[%s], %s", NUMBER, BACK).toLowerCase() ;
    }

    public static String getActionsForRecord() {
        return String.format("%s, %s, %s", EDIT, DELETE, MENU).toLowerCase() ;
    }
}

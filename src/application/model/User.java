package application.model;

public class User {
    public int nummer = 0;
    public String status = "";

    public String toString() {
        return nummer + "-" + status;
    }
}

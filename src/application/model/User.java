package application.model;

public class User {
    public String abtnumemr;
    public String title = "";
    public String name = "";
    public String street = "";
    public String zip;
    public String city = "";

    public String toString() {
        return title + " " + name;
    }
}

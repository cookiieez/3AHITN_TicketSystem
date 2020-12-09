package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Departments {

    public int nummer = 0;
    public String department = "";

    private static int num = 0;

    public String toString() {
        return nummer + "-" + department;
    }


    public static ObservableList<Departments> loadFile(String filename) {
        ObservableList<Departments> result = FXCollections.observableArrayList();
        String zeile = null;
        BufferedReader br = null;


        try {
            br = new BufferedReader(new FileReader(filename));

            try {
                while ((zeile = br.readLine()) != null) {
                    String[] split = zeile.split(";");

                    Departments d = new Departments();
                    d.nummer = Integer.parseInt(split[0]);
                    d.department = split[1];

                    result.add(d);
                    num++;

                }
            } finally {
                br.close();
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
        return result;
    }
}

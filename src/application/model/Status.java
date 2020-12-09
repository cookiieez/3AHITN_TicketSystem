package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.input.MouseEvent;

import java.io.*;

public class Status {

    public int nummer = 0;
    public String status = "";

    private static int num = 0;

    public String toString() {
        return nummer + "-" + status;
    }


    public static ObservableList<Status> loadFile(String filename) {
        ObservableList<Status> result = FXCollections.observableArrayList();
        String zeile = null;
        BufferedReader br = null;


        try {
            br = new BufferedReader(new FileReader(filename));

            try {
                while ((zeile = br.readLine()) != null) {
                    String[] split = zeile.split(";");

                    Status s = new Status();
                    s.nummer = Integer.parseInt(split[0]);
                    s.status = split[1];

                    result.add(s);
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

    public static void writeFile(String filename, ObservableList<Status> liste) {

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("stati.csv"));
            try {

                for (Status s : liste) {
                    bw.write(s.nummer + ";" + s.status + "\n");
                }

                bw.flush();
            } finally {
                bw.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

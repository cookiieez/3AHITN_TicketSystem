package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;

public class Ticket {
    public int id = 0;
    public String name = "";
    public String desc = "";
    public Status status = null;
    public Priority priority = null;

    private static int num = 0;

    public String toString() {
        return  id + "-" + name;
    }

    public static ObservableList<Ticket> loadFile(String filename) {
        ObservableList<Ticket> result = FXCollections.observableArrayList();
        String zeile = null;
        BufferedReader br = null;


        try {
            br = new BufferedReader(new FileReader(filename));

            try {
                while ((zeile = br.readLine()) != null) {
                    String[] split = zeile.split(";");

                    Ticket t = new Ticket();
                    t.id = Integer.parseInt(split[0]);
                    t.name = split[1];
                    t.desc = split[2];

                    Status s = new Status();
                    s.nummer = Integer.parseInt(split[3]);
                    t.status = s;

                    Priority p = new Priority();
                    p.priority = split[4];
                    t.priority = p;

                    num++;

                    result.add(t);

                }
            } finally {
                br.close();
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
        return result;
    }

    public static void writeFile(String filename, ObservableList<Ticket> liste) {

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
            try {

                for (Ticket t : liste) {
                    bw.write(t.id + ";" + t.name + ";" + t.desc + ";" + t.status.nummer + ";" + t.priority.priority + "\n");
                }

                bw.flush();
            } finally {
                bw.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getNum() {
        return num;
    }

    public static void setNum(int num) {
        Ticket.num = num;
    }
}
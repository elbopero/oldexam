import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFromFile {

    public  ArrayList<Equipment> equipment = new ArrayList<>();

    public void readFromFile(String filePath, ArrayList<Equipment> arrayList) {
        try {
            File equipmentList = new File(filePath);
            Scanner scan = new Scanner(equipmentList);

            while (scan.hasNextLine()) {
                String currentLine = scan.nextLine();
                if (currentLine.equals("Ball")) {
                    int id = Integer.parseInt(scan.nextLine());
                    String locker = scan.nextLine();
                    boolean needsReplacement = Boolean.parseBoolean(scan.nextLine());
                    BallType ballType = BallType.valueOf(scan.nextLine());
                    boolean needsAir = Boolean.parseBoolean(scan.nextLine());

                    Ball ball = new Ball(id, locker, needsReplacement, ballType, needsAir);
                    equipment.add(ball);
                    arrayList.add(ball);

                } else if(currentLine.equals("TableTennisRacket")) {
                    int id = Integer.parseInt(scan.nextLine());
                    String locker = scan.nextLine();
                    boolean needsReplacement = Boolean.parseBoolean(scan.nextLine());
                    boolean needsShifting = Boolean.parseBoolean(scan.nextLine());

                    TableTennisRacket ttr = new TableTennisRacket(id, locker, needsReplacement, needsShifting);
                    equipment.add(ttr);
                    arrayList.add(ttr);
                }
            }

            scan.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }

    public void printEquipment() {
        for (Equipment e: equipment) {
            System.out.println(e.toString());
        }
    }
}

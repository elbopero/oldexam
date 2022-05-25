import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Program {
    ReadFromFile readFromFile = new ReadFromFile();
    ArrayList<Equipment> equipmentList = new ArrayList<>();
    Connection con;

    public Program () throws SQLException, ClassNotFoundException {
        readFromFile.readFromFile("equipment.txt", equipmentList);
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/equip","root","tuttifrutti");
    }

    public void showAllEquipment() {
        System.out.println("------------------");
        System.out.println("  ALL EQUIPMENT  ");
        System.out.println("-------------------");
        for (Equipment e: equipmentList) {
            System.out.println(e.toString());
        }

        //readFromFile.printEquipment();
    }

    public void printBallNeedingMoreAir() {
        System.out.println("-----------------------");
        System.out.println("   NEEDS MORE AIR");
        System.out.println("------------------------");
        for (Equipment e: equipmentList) {

            if (e instanceof Ball && ((Ball) e).isNeedsAir()) {
                System.out.println(e);
            }
        }
    }

    public void printEquipmentNeedingToBeReplaced() {
        System.out.println("------------------------");
        System.out.println("   NEEDS REPLACEMENT");
        System.out.println("-------------------------");
        for (Equipment e: equipmentList) {
            if (e.isNeedsReplacement())
            System.out.println(e);
        }
    }

    public void printTableTennisRacketsNeedingNewPad() {
        System.out.println("-------------------------");
        System.out.println("     NEEDS PADDING");
        System.out.println("--------------------------");
        for (Equipment e: equipmentList) {
            if (e instanceof TableTennisRacket && ((TableTennisRacket) e).isNeedsShifting()) {
                System.out.println(e);
            }
        }
    }

    /* public void test() {
        showAllEquipment();
        printBallNeedingMoreAir();
        printEquipmentNeedingToBeReplaced();
        printTableTennisRacketsNeedingNewPad();
    } */

    public void runProgram() {
        Scanner scan = new Scanner(System.in);

        try {
            while (true) {
                showMenu();
                String s = scan.nextLine().strip();
                switch (Integer.parseInt(s)) {
                    case 1 -> showAllEquipment();
                    case 2 -> printBallNeedingMoreAir();
                    case 3 -> printEquipmentNeedingToBeReplaced();
                    case 4 -> printTableTennisRacketsNeedingNewPad();
                    case 5 -> {
                        System.out.println("Have a nice day!");
                        insertLockers();
                       // insertBalls();
                        insertTableTennisRacket();
                        System.exit(0);
                    }
                }
            }
        } catch (NumberFormatException n) {
            System.out.println("Wrong number entered.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void showMenu() {
        System.out.println("----------------------------------");
        System.out.println("Welcome to the Equipment program!");
        System.out.println("------------------------------------");
        System.out.println("Enter one of the following numbers for more details.");
        System.out.println("Enter 1 to show all equipment");
        System.out.println("Enter 2 to show ball that needs more air");
        System.out.println("Enter 3 to show equipment that needs to be fully replaced");
        System.out.println("Enter 4 to show Table Tennis Rackets that need new padding.");
        System.out.println("Enter 5 to exit the program.");
    }






        public boolean insertLockers() throws SQLException {
            PreparedStatement stmt = con.prepareStatement("INSERT IGNORE INTO locker(address)"+
                    "VALUES(?)");

            int sec = 7;
            while(sec != 7) {
                for (int i = 1; i < 8; i++) {
                    stmt.setString(1, "Locker " + i);
                    stmt.executeUpdate();
                    sec++;
                }
                sec = 7;
                //stmt.setString(1, "Floor 1");
            }

            return true;
        }

        public boolean insertBalls() throws SQLException {

            try {
                PreparedStatement stmt = con.prepareStatement("INSERT IGNORE INTO ball(id, `replace`, balltype, needsair, lockerid)" +
                        " VALUES(?,?,?,?,?)");

                for (Equipment e : equipmentList) {
                    if (e instanceof Ball) {

                        stmt.setInt(1, e.getId());
                        stmt.setBoolean(2, e.isNeedsReplacement());
                        stmt.setString(3, String.valueOf(((Ball) e).ballType));
                        stmt.setBoolean(4, ((Ball) e).isNeedsAir());
                        stmt.setInt(5, Integer.parseInt(e.getLocker().split(" ")[1]));
                        stmt.executeUpdate();
                    }
                }

                return true;
            } catch (SQLException s) {
                s.printStackTrace();
                return false;
            }
        }

        public boolean insertTableTennisRacket() {
        try {
            PreparedStatement stmt = con.prepareStatement("INSERT IGNORE INTO tabletennisracket(id,needsreplacement,needsleather,lockerid)"+
                    "VALUES(?,?,?,?)");

            for (Equipment e: equipmentList) {
                if (e instanceof TableTennisRacket){
                    stmt.setInt(1,e.getId());
                    stmt.setBoolean(2,e.isNeedsReplacement());
                    stmt.setBoolean(3,((TableTennisRacket) e).isNeedsShifting());
                    stmt.setInt(4, Integer.parseInt(e.getLocker().split(" ")[1]));

                    stmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
        }


}

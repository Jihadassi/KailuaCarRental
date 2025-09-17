import java.sql.*;
import java.util.Scanner;

public class AccessDB {
    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/carrental";
    static final String USER = "jihadassi";
    static final String PASSWORD = "Jihad123";
    static Connection con;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            con = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            System.out.println("Forbundet til database.");

            boolean running = true;
            while (running) {
                System.out.println("\n1. Biler");
                System.out.println("2. Kunder");
                System.out.println("3. Kontrakter");
                System.out.println("0. Afslut");
                System.out.print("Vælg: ");

                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        car.carMenu(sc, con);
                        break;
                    case 2:
                        customer.customerMenu(sc, con);
                        break;
                    case 3:
                        contract.contractMenu(sc, con);
                        break;
                    case 0:
                        running = false;
                        break;
                    default:
                        System.out.println("Ugyldigt valg. Prøv igen.");
                }
            }

            con.close();
        } catch (SQLException e) {
            System.out.println("Fejl: " + e.getMessage());
        }
    }
}

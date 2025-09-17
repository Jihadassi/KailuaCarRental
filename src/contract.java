import java.sql.*;
import java.util.Scanner;

public class contract {
    public static void contractMenu(Scanner sc, Connection con) {
        boolean contract = true;

        while(contract){
            System.out.println("\n\n---KONTRAKT---");
            System.out.println("1. Tilføj kontrakt");
            System.out.println("2. Slet kontrakt");
            System.out.println("3. Se alle kontrakter");
            System.out.println("0. Tilbage");
            System.out.print("Vælg en funktion: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addContract(sc, con);
                    break;
                case 2:
                    deleteContract(sc, con);
                    break;
                case 3:
                    showContracts(con);
                    break;
                case 0:
                    contract = false;
                default:
                    System.out.println("Ugyldigt valg.");
            }
        }
    }

        private static void addContract(Scanner sc, Connection con) {
            try {
                System.out.print("Kunde ID: ");
                int customerId = sc.nextInt();

                System.out.print("Bil ID: ");
                int carId = sc.nextInt();
                sc.nextLine();

                System.out.print("Fra dato & tid: ");
                String fromDateTime = sc.nextLine();

                System.out.print("Til dato & tid: ");
                String toDateTime = sc.nextLine();

                System.out.print("Max km: ");
                String maxKm = sc.nextLine();

                System.out.print("Nuværende km: ");
                int currentKm = sc.nextInt();
                sc.nextLine();

                String sql = "INSERT INTO contracts (customer_id, car_id, from_dateTime, to_dateTime, max_km, current_km) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(sql);

                ps.setInt(1, customerId);
                ps.setInt(2, carId);
                ps.setString(3, fromDateTime);
                ps.setString(4, toDateTime);
                ps.setString(5, maxKm);
                ps.setInt(6, currentKm);
                ps.executeUpdate();
                ps.close();

                System.out.println("Bil tilføjet.");
            } catch (SQLException e) {
                System.out.println("Fejl: " + e.getMessage());
            }
        }

    private static void deleteContract(Scanner sc, Connection con) {
        try {
            System.out.print("Indtast kontrakt ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            String sql = "DELETE FROM contracts WHERE contract_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            ps.close();

            if (rows > 0) System.out.println("Kontrakt slettet.");
            else System.out.println("Ingen kontrakt fundet med ID " + id);
        } catch (SQLException e) {
            System.out.println("Fejl: " + e.getMessage());
        }
    }

    private static void showContracts(Connection con) {
        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM contracts");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("contract_id") +
                        ", Kunde ID: " + rs.getInt("customer_id") +
                        ", Bil ID: " + rs.getInt("car_id") +
                        ", Fra: " + rs.getString("from_dateTime") +
                        ", Til: " + rs.getString("to_dateTime") +
                        ", Max km: " + rs.getString("max_km") +
                        ", Nuværende km: " + rs.getInt("current_km"));
            }
            s.close();
        } catch (SQLException e) {
            System.out.println("Fejl: " + e.getMessage());
        }
    }
}

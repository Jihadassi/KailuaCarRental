import java.sql.*;
import java.util.Scanner;

public class customer {
    public class CustomerMenu {

    }
        public static void customerMenu(Scanner sc, Connection con) {
            boolean customer = true;

            while (customer) {
                System.out.println("\n\n---KUNDER---");
                System.out.println("1. Tilføj kunde");
                System.out.println("2. Slet kunde");
                System.out.println("3. Se alle kunder");
                System.out.println("0. Tilbage");
                System.out.print("Vælg en funktion: ");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        addCustomer(sc, con);
                        break;
                    case 2:
                        deleteCustomer(sc, con);
                        break;
                    case 3:
                        showCustomers(con);
                        break;
                    case 0:
                        customer = false;
                    default:
                        System.out.println("Ugyldigt valg.");
                }
            }
        }

        private static void addCustomer(Scanner sc, Connection con) {
            try {
                System.out.print("Indtast navn: ");
                String name = sc.nextLine();

                System.out.print("Indtast efternavn: ");
                String surname = sc.nextLine();

                System.out.print("Indtast adresse: ");
                String adress = sc.nextLine();

                System.out.print("Indtast postnummer: ");
                String zip = sc.nextLine();

                System.out.print("Indtast by: ");
                String city = sc.nextLine();

                System.out.print("Indtast telefon nummer: ");
                String phone = sc.nextLine();

                System.out.print("Indtast email: ");
                String email = sc.nextLine();

                System.out.print("Indtast kørekort nummer: ");
                String driverLicenseNumber = sc.nextLine();

                System.out.print("Indtast kørekortets dato: ");
                String driverSince = sc.nextLine();

                String sql = "INSERT INTO customer (name, surname, adress, zip, city, phone, email, driverLicenseNumber, driverSince) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(sql);

                ps.setString(1, name);
                ps.setString(2, surname);
                ps.setString(3, adress);
                ps.setString(4, zip);
                ps.setString(5, city);
                ps.setString(6, phone);
                ps.setString(7, email);
                ps.setString(8, driverLicenseNumber);
                ps.setString(9, driverSince);

                ps.executeUpdate();
                ps.close();

                System.out.println("Kunde tilføjet.");
            } catch (SQLException e) {
                System.out.println("Fejl: " + e.getMessage());
            }
        }

    private static void deleteCustomer(Scanner sc, Connection con) {
        try {
            System.out.print("Indtast kundens ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            String sql = "DELETE FROM customer WHERE customer_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            ps.close();

            if (rows > 0) System.out.println("Kunde slettet.");
            else System.out.println("Ingen kunde fundet med ID " + id);
        } catch (SQLException e) {
            System.out.println("Fejl: " + e.getMessage());
        }
    }

    private static void showCustomers(Connection con) {
        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM customer");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("customer_id") +
                        ", Name: " + rs.getString("name") +
                        ", Surname: " + rs.getString("surname") +
                        ", Adress: " + rs.getString("adress") +
                        ", Zip: " + rs.getString("zip") +
                        ", City: " + rs.getString("city") +
                        ", Email: " + rs.getString("email") +
                        ", Driver License Number: " + rs.getInt("driverLicenseNumber") +
                        ", Driver since: " + rs.getString("driver_since"));
            }

            s.close();
        } catch (SQLException e) {
            System.out.println("Fejl: " + e.getMessage());
        }
    }
}

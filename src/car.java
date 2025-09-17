import java.sql.*;
import java.util.Scanner;

public class car {

    public static void carMenu(Scanner sc, Connection con) {
        boolean car = true;

        while (car) {
            System.out.println("\n\n---BIL---");
            System.out.println("1. Tilføj bil");
            System.out.println("2. Slet bil");
            System.out.println("3. Opdater kilometertal");
            System.out.println("4. Se alle biler");
            System.out.println("0. Tilbage");
            System.out.print("Vælg en funktion: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addCar(sc, con);
                    break;
                case 2:
                    deleteCar(sc, con);
                    break;
                case 3:
                    updateCarKm(sc, con);
                    break;
                case 4:
                    showCars(con);
                    break;
                case 0:
                    car = false;
                    break;
                default:
                    System.out.println("Ugyldigt valg. Prøv igen.");
            }
        }
    }

    private static void addCar(Scanner sc, Connection con) {
        try {
            System.out.print("Indtast brand: ");
            String brand = sc.nextLine();

            System.out.print("Indtast model: ");
            String model = sc.nextLine();

            System.out.print("Indtast fuel: ");
            String fuel = sc.nextLine();

            System.out.print("Indtast plate: ");
            String plate = sc.nextLine();

            System.out.print("Indtast registration year: ");
            int year = sc.nextInt();
            sc.nextLine();

            System.out.print("Indtast registration month: ");
            int month = sc.nextInt();
            sc.nextLine();

            System.out.print("Indtast km: ");
            int km = sc.nextInt();
            sc.nextLine();

            System.out.print("Indtast car group: ");
            String group = sc.nextLine();

            String sql = "INSERT INTO cars (brand, model, fuel, plate, registration_year, registration_month, km, car_group) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, brand);
            ps.setString(2, model);
            ps.setString(3, fuel);
            ps.setString(4, plate);
            ps.setInt(5, year);
            ps.setInt(6, month);
            ps.setInt(7, km);
            ps.setString(8, group);

            ps.executeUpdate();
            ps.close();

            System.out.println("Bil tilføjet.");
        } catch (SQLException e) {
            System.out.println("Fejl: " + e.getMessage());
        }
    }

    private static void deleteCar(Scanner sc, Connection con) {
        try {
            System.out.print("Indtast bilens ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            String sql = "DELETE FROM cars WHERE car_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            ps.close();

            if (rows > 0) System.out.println("Bil slettet.");
            else System.out.println("Ingen bil fundet med ID " + id);
        } catch (SQLException e) {
            System.out.println("Fejl: " + e.getMessage());
        }
    }

    private static void updateCarKm(Scanner sc, Connection con) {
        try {
            System.out.print("Indtast bilens ID: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("Indtast nyt kilometertal: ");
            int km = sc.nextInt();
            sc.nextLine();

            String sql = "UPDATE cars SET km = ? WHERE car_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, km);
            ps.setInt(2, id);
            int rows = ps.executeUpdate();
            ps.close();

            if (rows > 0) System.out.println("Kilometertal opdateret.");
            else System.out.println("Ingen bil fundet med ID " + id);
        } catch (SQLException e) {
            System.out.println("Fejl: " + e.getMessage());
        }
    }

    private static void showCars(Connection con) {
        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM cars");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("car_id") +
                        ", Brand: " + rs.getString("brand") +
                        ", Model: " + rs.getString("model") +
                        ", Fuel: " + rs.getString("fuel") +
                        ", Plate: " + rs.getString("plate") +
                        ", Year: " + rs.getInt("registration_year") +
                        ", Month: " + rs.getInt("registration_month") +
                        ", Km: " + rs.getInt("km") +
                        ", Group: " + rs.getString("car_group"));
            }
            s.close();
        } catch (SQLException e) {
            System.out.println("Fejl: " + e.getMessage());
        }
    }
}
import java.sql.*;
import java.util.*;
public class Main {
    public static final int min = 100;
    public static final int max = 5000;

    public static class User {
        private String username;
        private String password;
        Scanner in = new Scanner(System.in);

        public User() { }

        public String getu() {
            System.out.println("Enter username:");
            username = in.next();
            return username;
        }

        public String getp() {
            System.out.println("Enter password:");
            password = in.next();
            return password;
        }
    }

    public static class Passenger {
        private int number;
        private String name;
        private String bus_number;
        private String type;
        private String date;
        private String from;
        private String to;
        Scanner in = new Scanner(System.in);

        public int getNumber() {
            Random r = new Random();
            number = r.nextInt(max - min + 1) + min;
            return number;
        }

        public String getName() {
            System.out.println("Enter the name of the passenger:");
            name = in.next();
            return name;
        }

        public String getBusNumber() {
            System.out.println("Enter bus number:");
            bus_number = in.next();
            return bus_number;
        }

        public String getType() {
            System.out.println("Enter your class type:");
            type = in.next();
            return type;
        }

        public String getDate() {
            System.out.println("Enter the journey date (YYYY-MM-DD):");
            date = in.next();
            return date;
        }

        public String getFrom() {
            System.out.println("Enter starting place:");
            from = in.next();
            return from;
        }

        public String getTo() {
            System.out.println("Enter your destination:");
            to = in.next();
            return to;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        User u1 = new User();
        String username = u1.getu();
        String password = u1.getp();

        String jdbcUrl = "jdbc:mysql://localhost:3306/Impu?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");


            try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
                System.out.println("User Connection Granted.\n");

                while (true) {
                    String insertQuery = "INSERT INTO reservations VALUES (?, ?, ?, ?, ?, ?, ?)";
                    String deleteQuery = "DELETE FROM reservations WHERE pnr_number = ?";
                    String showQuery = "SELECT * FROM reservations";

                    System.out.println("Enter the choice:");
                    System.out.println("1. Insert Record.");
                    System.out.println("2. Delete Record.");
                    System.out.println("3. Show All Records.");
                    System.out.println("4. Exit.");
                    int choice = in.nextInt();

                    if (choice == 1) {
                        Passenger p1 = new Passenger();
                        int number = p1.getNumber();
                        String name = p1.getName();
                        String bus_number = p1.getBusNumber();
                        String type = p1.getType();
                        String date = p1.getDate();
                        String from = p1.getFrom();
                        String to = p1.getTo();

                        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                            preparedStatement.setInt(1, number);
                            preparedStatement.setString(2, name);
                            preparedStatement.setString(3, bus_number);
                            preparedStatement.setString(4, type);
                            preparedStatement.setString(5, date);
                            preparedStatement.setString(6, from);
                            preparedStatement.setString(7, to);

                            int rowsAffected = preparedStatement.executeUpdate();
                            if (rowsAffected > 0) {
                                System.out.println("Record added successfully.");
                            } else {
                                System.out.println("No records were added.");
                            }
                        } catch (SQLException e) {
                            System.err.println("SQLException: " + e.getMessage());
                        }

                    } else if (choice == 2) {
                        System.out.println("Enter the number to delete the record:");
                        int number = in.nextInt();
                        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                            preparedStatement.setInt(1, number);
                            int rowsAffected = preparedStatement.executeUpdate();

                            if (rowsAffected > 0) {
                                System.out.println("Record deleted successfully.");
                            } else {
                                System.out.println("No records were deleted.");
                            }
                        } catch (SQLException e) {
                            System.err.println("SQLException: " + e.getMessage());
                        }
                    } else if (choice == 3) {
                        try (PreparedStatement preparedStatement = connection.prepareStatement(showQuery);
                             ResultSet resultSet = preparedStatement.executeQuery()) {
                            System.out.println("\nAll records printing:\n");
                            while (resultSet.next()) {
                                String number = resultSet.getString("number");
                                String name = resultSet.getString("name");
                                String bus_number = resultSet.getString("bus_number");
                                String type = resultSet.getString("type");
                                String date = resultSet.getString("date");
                                String from = resultSet.getString("from");
                                String to = resultSet.getString("to");

                                System.out.println("PNR Number: " + number);
                                System.out.println("Passenger Name: " + name);
                                System.out.println("Bus Number: " + bus_number);
                                System.out.println("Class Type: " + type);
                                System.out.println("Journey Date: " + date);
                                System.out.println("From: " + from);
                                System.out.println("To: " + to);
                                System.out.println("--------------");
                            }
                        } catch (SQLException e) {
                            System.err.println("SQLException: " + e.getMessage());
                        }
                    } else if (choice == 4) {
                        System.out.println("Exiting the program.");
                        break;
                    } else {
                        System.out.println("Invalid Choice Entered.");
                    }
                }

            } catch (SQLException e) {
                System.err.println("Connection Failed: " + e.getMessage());
            }

        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found: " + e.getMessage());
        }

        in.close();
    }
}




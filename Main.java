import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.util.Scanner;


    static final String DB_URL = "jdbc:mysql://localhost:3306/clinic_management_system";
    static final String USER = "root";
    static final String PASS = "pspatil000@1148";

    static Connection conn;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            int choice;
            do {
                System.out.println("\n--- Clinic Management System ---");
                System.out.println("1. Add patient");
                System.out.println("2. Add doctor");
                System.out.println("3. view doctor");
                System.out.println("4. view patient");
                System.out.println("4.Exit");

                System.out.print("Enter your choice: ");
                choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        addpatients1();
                        break;
                    case 2:
                        adddoctor2();
                        break;
                    case 3:
                        viewdoctor2();
                        break;
                    case 4:
                        viewpatients1();
                    default:
                        System.out.println("Invalid choice.");
                }
            } while (choice != 4);

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void addpatients1() throws SQLException {
        System.out.print("Enter patient's name: ");
        String name = sc.nextLine();
        System.out.print("Enter age: ");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter doctor ID: ");
        int doctorId = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter gender: ");
        String gender = sc.nextLine();


        String sql = "INSERT INTO patients1 (name, age, doctorId,gender) VALUES (?, ?, ?,? )";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, name);
        pst.setInt(2, age);
        pst.setInt(3, doctorId);
        pst.setString(4, gender);


        pst.executeUpdate();
        System.out.println("Patient added successfully.");
    }

    static void viewpatients1() throws SQLException {
        String sql = "SELECT * FROM patients1";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        System.out.println("\n--- patients1 list ---");
        while (rs.next()) {
            System.out.println("name: " + rs.getString("name")
                    + ", age: " + rs.getString("age")
                    + ", doctorId: " + rs.getInt("doctorId")
                    + ", gender: " + rs.getString("gender"));

        }
    }
    static void  adddoctor2() throws SQLException {
        System.out.print("Enter doctor's Id: ");
        int doctorId = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter doctor's name: ");
        String name = sc.nextLine();
        System.out.print("Enter specialization: ");
        String speclalization = sc.nextLine();

        String sql = "INSERT INTO doctor2 (doctorId,name, speclalization) VALUES (?, ?,?)";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, doctorId);
        pst.setString(2, name);
        pst.setString(3, speclalization);
        pst.executeUpdate();
        System.out.println("Doctor added successfully.");
    }

    static void viewdoctor2 () throws SQLException {
        String sql = "SELECT * FROM doctor2";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        System.out.println("\n--- Doctors List ---");
        while (rs.next()) {
            System.out.println("doctorId: " + rs.getInt("doctorId")
                    + ", Name: " + rs.getString("name")
                    + ", speclalization: " + rs.getString("speclalization"));
        }
    }


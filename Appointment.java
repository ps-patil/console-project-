import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;


public class Appointment {
    static final String DB_URL = "jdbc:mysql://localhost:3306/clinic_management_system";
    static final String USER = "root";
    static final String PASS = "pspatil000@1148";

    static Connection conn;
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] ignoredArgs) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            int choice;
            do {
                System.out.println("\n--- Clinic Management System ---");
                System.out.println("1. Add appointment");
                System.out.println("2.  View appointment");

                System.out.println("3.Exit");

                System.out.print("Enter your choice: ");
                choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        patient_appointments();
                        break;
                    case 2:
                        viewappointment();
                        break;


                    default:
                        System.out.println("Invalid choice.");
                }
            } while (choice != 2);

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void patient_appointments() throws SQLException {
        System.out.println("Enter clinic_name");
        String clinic_name = sc.nextLine();
        System.out.print("Enter patient's ID: ");
        int patient_id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Doctor ID: ");
        int Doctor_id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter date of appointment (yyyy-mm-dd): ");
        Scanner scanner = sc;
        String doaInput = scanner.nextLine();
        LocalDate doa = LocalDate.parse(doaInput);
        System.out.println("Enter appointment_status");
        String appointment_status = sc.nextLine();
        System.out.println("Enter appointment_fees");
        String appointment_fees = sc.nextLine();
        System.out.println("Enter patient_status ");
        String patient_status = sc.nextLine();


        String sql = "INSERT INTO patient_appointments(clinic_name,patient_id, Doctor_id , doa,appointment_status,appointment_fees,patient_status ) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, clinic_name);
        pst.setInt(2, patient_id);
        pst.setInt(3, Doctor_id);
        pst.setDate(4, Date.valueOf(doa));
        pst.setString(5, appointment_status);
        pst.setString(6, appointment_fees);
        pst.setString(7, patient_status);


        pst.executeUpdate();
        System.out.println(" appointment_Patient added successfully.");
    }

    static void viewappointment() throws SQLException {

        String sql = "SELECT * FROM patient_appointments";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        System.out.println("\n---appointments details  ---");
        while (rs.next()) {
            System.out.println("clinic_name: " + rs.getString("clinic_name")
                    + ", patient_id: " + rs.getInt("patient_id")
                    + ", Doctor_id: " + rs.getInt("Doctor_id")
                    + ", doa: " + rs.getDate("doa")
                    + ", appointment_status: " + rs.getString("appointment_status")
                    + ",appointment_fees" + rs.getString("appointment_fees")
                    + ", patient_status " + rs.getString("patient_status"));
        }
    }
}



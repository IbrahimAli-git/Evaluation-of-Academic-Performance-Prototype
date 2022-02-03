import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int attendance = 0;
        int courseWorkMarks = 0;
        int examinationMarks = 0;

        try {
            System.out.println("Welcome to the program");
            System.out.println("For evaluating a student's marks please enter as follows:");
            System.out.println("---------------------------------------------------------");
            Thread.sleep(2000);

            System.out.println("Please enter attendance");
            attendance = Integer.parseInt(bufferedReader.readLine());
            System.out.println();

            System.out.println("Please enter coursework marks");
            courseWorkMarks = Integer.parseInt(bufferedReader.readLine());
            System.out.println();

            System.out.println("Please enter examination marks");
            examinationMarks = Integer.parseInt(bufferedReader.readLine());
            System.out.println();

            String excellent = "EXCELLENT";

            if (attendance > 80 && courseWorkMarks > 80 && examinationMarks > 80) {
                System.out.println("The academic performance of student is " + excellent);


                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/softwareprojectdatabase", "root", "WR*=ACHoXo0$c$j+wrlt");

                Statement statement = connection.createStatement();

                int value = 99;

                String insertion = "INSERT INTO tbl1_software_projects(student_overall_performance) VALUES(" + value + ")";

                statement.executeUpdate(insertion);

                ResultSet resultSet = statement.executeQuery("SELECT * FROM tbl1_software_projects WHERE student_overall_performance = " + value);

                while (resultSet.next()) {
                    System.out.println(resultSet.getInt(1));
                }

                connection.close();


            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

package com.company;

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


        System.out.println("Welcome to the program");
        System.out.println("Please choose an option below:");
        System.out.println("Enter 1 for evaluating overall performance:");
        System.out.println("Enter 2 for retrieving specific performances");
        System.out.println("Enter 3 for updating a student's performance");
        System.out.println("Enter 4 for deleting a student's record");
        System.out.println("---------------------------------------------------------");

        try {
            System.out.println("Please enter option");
            int option = Integer.parseInt(bufferedReader.readLine());
            System.out.println();

            Connection  connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/softwareprojectdatabase", "root", "WR*=ACHoXo0$c$j+wrlt");

            if (option == 1){
                int attendance = 0; int courseWorkMarks = 0; int examMarks = 0;
                int overallPerformance = calculateOverallPerformance(bufferedReader, attendance, courseWorkMarks, examMarks);
                create(connection, overallPerformance);

            } if (option == 2){
                System.out.println("Please enter student id:");
                int idOfStudent = Integer.parseInt(bufferedReader.readLine());
                retrieve(connection, idOfStudent);
            } if (option == 3){
                update(connection, bufferedReader);
            } if (option == 4){
                delete(connection, bufferedReader);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void create(Connection con, int overallPerformance) throws Exception{
        Statement st = con.createStatement();
        st.executeUpdate("INSERT INTO tbl1_software_projects(student_overall_performance) VALUES(" + overallPerformance + ")");

        ResultSet rs = st.executeQuery("SELECT * FROM tbl1_software_projects WHERE student_overall_performance = " + overallPerformance);

        while (rs.next()){
            int studentId = rs.getInt(1);

            System.out.println("The student's id is" + studentId);

        }
        st.close();
    }

    public static int calculateOverallPerformance(BufferedReader bufferedReader, int attendance, int courseWorkMarks, int examMarks) throws Exception{
        System.out.println("Please enter attendance marks");
        attendance = Integer.parseInt(bufferedReader.readLine());
        System.out.println();

        System.out.println("Please enter coursework marks");
        courseWorkMarks = Integer.parseInt(bufferedReader.readLine());
        System.out.println();

        System.out.println("Please enter examination marks");
        examMarks = Integer.parseInt(bufferedReader.readLine());
        System.out.println();

        if (attendance > 80 && courseWorkMarks > 80 && examMarks > 80) {
            System.out.println("The academic performance of student is excellent");

        }
        int overallPerformance = (attendance + courseWorkMarks + examMarks) / 3;
        return overallPerformance;
    }

    public static void retrieve(Connection connection, int id) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM tbl1_software_projects WHERE student_id = " + id);

        while (resultSet.next()){
            System.out.println("The student's overall performance is " + resultSet.getInt("student_overall_performance"));
        }
    }

    public static void update(Connection connection, BufferedReader br) throws Exception {
        System.out.println("Please enter student's id:");
        System.out.println();
        int studentId = Integer.parseInt(br.readLine());

        System.out.println("Please enter student's performance:");
        System.out.println();
        int overallPerformance = Integer.parseInt(br.readLine());

        Statement st = connection.createStatement();
        st.executeUpdate("UPDATE tbl1_software_projects SET student_overall_performance = " + overallPerformance + " WHERE student_id = " + studentId);
        st.close();
        System.out.println("Student's record has updated successfully");
    }

    public static void delete(Connection connection, BufferedReader br) throws Exception {
        System.out.println("Please enter student's id:");
        System.out.println();
        int studentId = Integer.parseInt(br.readLine());


        Statement st = connection.createStatement();
        st.execute("DELETE FROM tbl1_software_projects WHERE student_id = " + studentId);
        st.close();
        System.out.println("Student's record has deleted successfully");
    }
}


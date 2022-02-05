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
        int option = 0;
        int attendance = 0;
        int courseWorkMarks = 0;
        int examinationMarks = 0;
        int id = 0;
        Connection connection = null;

        System.out.println("Welcome to the program");
        System.out.println("Please choose an option below:");
        System.out.println("Enter 1 for evaluating overall performance:");
        System.out.println("Enter 2 for retrieving specific performances");
        System.out.println("---------------------------------------------------------");

        try {
            Thread.sleep(2000);
            System.out.println("Please enter option");
            option = Integer.parseInt(bufferedReader.readLine());
            System.out.println();

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/softwareprojectdatabase", "root", "WR*=ACHoXo0$c$j+wrlt");

            if (option == 1) {
                System.out.println("Please enter attendance marks");
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

                    Statement statement = connection.createStatement();
                    int value = (attendance + courseWorkMarks + examinationMarks) / 3;

                    String insertion = "INSERT INTO tbl1_software_projects(student_overall_performance) VALUES(" + value + ")";
                    statement.executeUpdate(insertion);


                    ResultSet resultSet = statement.executeQuery("SELECT student_id FROM tbl1_software_projects WHERE student_overall_performance = " + value);
                    while (resultSet.next()){
                        System.out.println(resultSet.getInt(1));
                    }
                }
            } else if (option == 2) {
                System.out.println("Please enter student id:");
                id = Integer.parseInt(bufferedReader.readLine());

                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM tbl1_software_projects WHERE student_id = " + id);

                while (resultSet.next()){
                    System.out.println("The student's id = " + resultSet.getInt("student_overall_performance"));
                }
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

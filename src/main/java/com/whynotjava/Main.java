package com.whynotjava;

import java.sql.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Main {

     @RequestMapping("/")
     public String home(){
        return "home";
     }

    public static void main(String[] args){
        SpringApplication.run(Main.class, args);

        String url = "jdbc:sqlite:db.db";
    try (Connection connection = DriverManager.getConnection(url)) {
        System.out.println("Connection to SQLITE has been done!");
        String createTable = "CREATE TABLE IF NOT EXISTS test (one TEXT, num INT)";

        Statement s = connection.createStatement();

        s.executeUpdate(createTable);
        System.out.println("Table created!");

        // s.executeUpdate("INSERT INTO test (one, num) VALUES ('super cool message', 69)");
        String sql = "INSERT INTO test (one, num) VALUES (?, ?)";
        String one = "not getting SQL injected right now!";
        int num = 69;

        PreparedStatement p = connection.prepareStatement(sql);
        p.setString(1, one);
        p.setInt(2, num);
        p.executeUpdate();

        System.out.println("Inserted data!");

        ResultSet rs = s.executeQuery("SELECT * FROM test");
        System.out.println("Selecting data!");
        while(rs.next()){
            System.out.println("colume entry: one='"+rs.getString("one")+"' num='"+rs.getInt("num")+"'");
        }
        
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
    
}

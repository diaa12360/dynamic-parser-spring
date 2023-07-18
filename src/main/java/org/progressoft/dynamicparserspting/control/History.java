package org.progressoft.dynamicparserspting.control;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.progressoft.dynamicparserspting.connection.DatabaseConnection;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
@RestController
@RequestMapping("/history")
public class History {
    @PostMapping
    protected void post(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Connection connection = new DatabaseConnection("mydb").setConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from history_table");
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            ArrayList<String> DbColumnNames = new ArrayList<>();
            for (int i = 1; i <= 3; i++) {
                DbColumnNames.add(metaData.getColumnName(i));
            }
            req.setAttribute("DbColumnNames", DbColumnNames);
            ArrayList<ArrayList<String>> tableData = new ArrayList<>();
            int x = 0;
            while (resultSet.next()){
                tableData.add(new ArrayList<>());
                for (int i = 1; i <= 3; i++) {
                    tableData.get(x).add(resultSet.getString(i));
                }
                x++;
            }
            System.out.println(tableData);
            req.setAttribute("tableData", tableData);
            req.getRequestDispatcher("/WEB-INF/views/historyPage.jsp").forward(req, resp);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

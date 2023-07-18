package org.progressoft.dynamicparserspting.control;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.progressoft.dynamicparserspting.connection.DatabaseConnection;
import org.progressoft.dynamicparserspting.connection.HistoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/history")
public class History {
    @Autowired
    HistoryRepo history;
    @PostMapping
    protected void post(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<org.progressoft.dynamicparserspting.connection.History> data = history.findAll();
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
                tableData.get(x).add(data.get(x).getFileName());
                tableData.get(x).add(data.get(x).getSummation());
                tableData.get(x).add(data.get(x).getAverage());
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

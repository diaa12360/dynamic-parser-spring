package org.progressoft.dynamicparserspting.control;

import com.progressoft.interns.advanced.exception.UtilityException;
import com.progressoft.interns.advanced.utility.ParsedDataUtility;
import com.progressoft.interns.advanced.utility.ParsedDataUtilityImpl;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.progressoft.dynamicparserspting.connection.DatabaseConnection;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/utility")
public class DataUtility {
    private final int PAGE_SIZE = 15;
    @PostMapping
    protected void post(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ArrayList<HashMap<String, String>> result = (ArrayList<HashMap<String, String>>) request.getSession().getAttribute("result");
        response.setContentType("text/html");
        String buttonValue = request.getParameter("button");
        String columnName = request.getParameter("column");
        switch (buttonValue) {
            case "Summation" -> {
                String summation = getSumOf(result, columnName);
                request.setAttribute("value", summation);
                sendSummationToDatabase(summation, request.getSession().getAttribute("fileName").toString());
                request.getRequestDispatcher("/WEB-INF/views/result.jsp").forward(request, response);
            }
            case "Average" -> {
                String average = getAverageOf(result, columnName);
                sendAverageToDatabase(average, request.getSession().getAttribute("fileName").toString());
                request.setAttribute("value", getAverageOf(result, columnName));
                request.getRequestDispatcher("/WEB-INF/views/result.jsp").forward(request, response);
            }
            case "Column" -> {
                request.getSession().setAttribute("columnName", columnName);
                request.getSession().setAttribute("dataColumn", new ParsedDataUtilityImpl().getColumnData(result, columnName));
                doGet(request, response);
            }
            case "BackFromDataPage" -> request.getRequestDispatcher("/WEB-INF/views/uploadPage.jsp").forward(request, response);
            case "BackFromResultPage" -> request.getRequestDispatcher("/WEB-INF/views/dataPage.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int index = 1;
        if (request.getParameter("pageNumber") != null)
            index = Integer.parseInt(request.getParameter("pageNumber"));
        ArrayList<String> columnData = (ArrayList<String>) request.getSession().getAttribute("dataColumn");
        int startIndex = (index - 1) * PAGE_SIZE;
        int endIndex = Math.min(startIndex + PAGE_SIZE - 1, columnData.size() - 1);
        System.out.println(startIndex + " " + endIndex + " " + index);
        List<String> sublist = columnData.subList(startIndex, endIndex);
        List<String> currentData = new ArrayList<>(sublist);
        System.out.println(currentData);
        request.setAttribute("currentData", currentData);
        request.getRequestDispatcher("/WEB-INF/views/columnData.jsp").forward(request, response);
    }

    private String getAverageOf(ArrayList<HashMap<String, String>> parsedData, String columnName) {
        ParsedDataUtility<ArrayList<HashMap<String, String>>> dataUtility = new ParsedDataUtilityImpl();
        BigDecimal average;
        try {
            average = dataUtility.getAverageOfColumn(parsedData, columnName);
        } catch (UtilityException e) {
            return "Error in the Data, try to choose other column";
        }
        return average.toString();
    }

    private String getSumOf(ArrayList<HashMap<String, String>> parsedData, String columnName) {
        ParsedDataUtility<ArrayList<HashMap<String, String>>> dataUtility = new ParsedDataUtilityImpl();
        BigDecimal summation;
        try {
            summation = dataUtility.getSummationOfColumn(parsedData, columnName);
        } catch (UtilityException e) {
            return "Error in the Data, try to choose other column";
        }
        return summation.toString();
    }

    private void sendSummationToDatabase(String sum, String fileName) {
        try {
            Connection connection = new DatabaseConnection("mydb").setConnection();
            String sql;
            PreparedStatement preparedStatement;
            sql = "UPDATE history_table SET summation = ? where file_name = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, sum);
            preparedStatement.setString(2, fileName);
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendAverageToDatabase(String average, String fileName) {
        try {
            Connection connection = new DatabaseConnection("mydb").setConnection();
            String sql;
            PreparedStatement preparedStatement;
            sql = "UPDATE history_table SET average = ? where file_name = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, average);
            preparedStatement.setString(2, fileName);
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

package org.progressoft.dynamicparserspting.control;


import com.progressoft.interns.advanced.parser.CSVParser;
import com.progressoft.interns.advanced.parser.JSONParser;
import com.progressoft.interns.advanced.parser.Parser;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.progressoft.dynamicparserspting.connection.DatabaseConnection;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.rmi.server.LogStream.log;


@Controller
public class UploadFilePage{
    private static final long serialVersionUID = 1L;

    private static final String UPLOAD_DIR = "uploads";

    @PostMapping("/data")
    protected void post(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String buttonValue = req.getParameter("button1");
        if (buttonValue.equals("Upload"))
            parseFile(req, resp);
    }


    private ArrayList<HashMap<String, String>> parseFile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String applicationPath = req.getServletContext().getRealPath("");
        String uploadFilePath = applicationPath + UPLOAD_DIR + File.separator;
        File fileSaveDir = new File(uploadFilePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        String fileName = null;
        for (Part part : req.getParts()) {
            //(ArrayList<Part>) req.getParts()
            fileName = getFileName(part);
            part.write(uploadFilePath + File.separator + fileName);
            break;
        }
        File file = new File(uploadFilePath + File.separator + fileName);
        String extension = "";

        int index = file.getName().lastIndexOf('.');
        if (index > 0) {
            extension = file.getName().substring(index + 1);
        }
        Parser<ArrayList<HashMap<String, String>>> parser;
        if (extension.equals("json"))
            parser = new JSONParser();
        else
            parser = new CSVParser();
        RequestDispatcher rq = req.getRequestDispatcher("dataPage.jsp");
        ArrayList<HashMap<String, String>> result = parser.parse(file.getAbsolutePath());
        ArrayList<String> columnNames = new ArrayList<>(result.get(0).keySet());
        int totalPages = (int) Math.ceil((double) result.size() / 15);
        req.getSession().setAttribute("totalPages", totalPages);
        req.getSession().setAttribute("columnsName", columnNames);
        req.getSession().setAttribute("result", result);
        req.getSession().setAttribute("fileName", fileName);
        try {
            DatabaseConnection cc = new DatabaseConnection("mydb");
            Connection connection = cc.setConnection();
            String sql = "create table history_table(" +
                    "file_name varchar(255) not NULL," +
                    "summation varchar(255)," +
                    "average varchar(255)," +
                    "PRIMARY KEY (file_name))";
            PreparedStatement preparedStatement;
            if (!DatabaseConnection.tableExists(connection, "history_table")) {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.executeUpdate();
            }
            if(!DatabaseConnection.hasRecord(connection, "'"+ fileName +"'", "history_table", "file_name")) {
                sql = "insert into history_table(file_name) values (?)";
                preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, fileName);
                preparedStatement.executeUpdate();
            }
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        get(req, resp);
        try {
            Files.delete(Paths.get(file.getAbsolutePath()));
        } catch (IOException e) {
            log("File Not Found");
        }
        return result;
    }

    @GetMapping("/data")
    protected void get(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int index = 1;
        if (req.getParameter("pageNumber") != null)
            index = Integer.parseInt(req.getParameter("pageNumber"));
        ArrayList<HashMap<String, String>> result = (ArrayList<HashMap<String, String>>) req.getSession().getAttribute("result");
        int startIndex = (index - 1) * 15;
        int endIndex = Math.min(startIndex + 15 - 1, result.size() - 1);
        System.out.println(startIndex + " " + endIndex + " " + index);
        List<HashMap<String, String>> sublist = result.subList(startIndex, endIndex);
        List<HashMap<String, String>> currentData = new ArrayList<>(sublist);
        System.out.println(currentData);
        req.getSession().setAttribute("currentMainData", currentData);
        req.getRequestDispatcher("/WEB-INF/views/dataPage.jsp").forward(req, resp);
    }

    private String getFileName(Part part) {
        String contentDis = part.getHeader("content-disposition");
        String[] tokens = contentDis.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length() - 1);
            }
        }
        return "";
    }

}

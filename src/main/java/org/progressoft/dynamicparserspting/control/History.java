package org.progressoft.dynamicparserspting.control;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.progressoft.dynamicparserspting.connection.HistoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/history")
public class History {
    @Autowired
    HistoryRepo history;
    @PostMapping
    protected void post(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<org.progressoft.dynamicparserspting.connection.History> data = history.findAll();
        ArrayList<ArrayList<String>> tableData = new ArrayList<>();
        for (int i = 0; i < data.size(); i++){
            tableData.add(new ArrayList<>());
            tableData.get(i).add(data.get(i).getFileName());
            tableData.get(i).add(data.get(i).getSummation());
            tableData.get(i).add(data.get(i).getAverage());
        }
        req.setAttribute("tableData", tableData);
        ArrayList<String> DbColumnNames = new ArrayList<>();
        DbColumnNames.add("File Name");
        DbColumnNames.add("Summation");
        DbColumnNames.add("Average");
        req.setAttribute("DbColumnNames", DbColumnNames);
        req.getRequestDispatcher("/WEB-INF/views/historyPage.jsp").forward(req, resp);
    }
}

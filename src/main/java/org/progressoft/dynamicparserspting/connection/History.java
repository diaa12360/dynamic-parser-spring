package org.progressoft.dynamicparserspting.connection;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "history_table")
@Table
@Data
public class History {
    @Id
    @Column(
            name="file_name",
            updatable = false,
            columnDefinition = "TEXT"
    )
    private String fileName;
    @Column(
            name="summation",
            updatable = false,
            columnDefinition = "TEXT"

    )
    private String summation;
    @Column(
            name="average",
            updatable = false,
            columnDefinition = "TEXT"
    )
    private String average;

    public History(String fileName, String summation, String average) {
        this.fileName = fileName;
        this.summation = summation;
        this.average = average;
    }

    public History() {

    }

    public String getSummation() {
        return summation;
    }

    public String getAverage() {
        return average;
    }
}

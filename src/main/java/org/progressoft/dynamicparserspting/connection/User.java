package org.progressoft.dynamicparserspting.connection;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "login_table")
@Table
@Data
public class User {
    @Id
    @Column(
            name = "username",
            columnDefinition = "TEXT"
    )
    private String username;
    @Column(name = "password")
    private int password;

    public User(String username, int password) {
        this.username = username;
        this.password = password;
    }

    public User() {
    }
}

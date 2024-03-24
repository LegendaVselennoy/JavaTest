package com.example;

import com.example.test.ConnectionManager;
import com.example.test.passengers.TableManager;
import org.junit.jupiter.api.extension.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;

public class DatabaseOperationsExtension implements
        BeforeAllCallback, AfterAllCallback,
        BeforeEachCallback, AfterEachCallback {

    private Connection connection;
    private Savepoint savepoint;

    @Override
    public void beforeAll(ExtensionContext context) {
        connection = ConnectionManager.openConnection();
        TableManager.dropTable(connection);
        TableManager.createTable(connection);
    }

    @Override
    public void afterAll(ExtensionContext context) {
        ConnectionManager.closeConnection();
    }

    @Override
    public void beforeEach(ExtensionContext context)
            throws SQLException {
        connection.setAutoCommit(false);
        savepoint = connection.setSavepoint("savepoint");
    }

    @Override
    public void afterEach(ExtensionContext context)
            throws SQLException {
        connection.rollback(savepoint);
    }
}

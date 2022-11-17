package com.amboiko.services;

import com.amboiko.model.User;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBService {
    private static final String GET_USER = "SELECT id, user_name FROM user WHERE email= ? AND password= ?";
    private static final String GET_USER_BY_ID = "SELECT id, user_name FROM user WHERE id=?";
    private static final String INSERT_USER = "INSERT INTO user (email, password, user_name) VALUES(?, ?, ?)";
    private final String dbHost;
    private final String user;
    private final String password;
    Connection connection = null;
    Logger logger;

    public DBService() {
        this("jdbc:mysql://localhost:8889/hw_06_web", "root", "root");
    }

    public DBService(String dbHost, String user, String password) {
        this.dbHost = dbHost;
        this.user = user;
        this.password = password;
        logger = Logger.getLogger(DBService.class.getName());
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbHost, user, password);
    }

    public User getUser(String email, String password) {
        User user = null;
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, EncodingService.md5EncryptionWithSalt(password));

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("user_name")
                );
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException ex) {
            logger.log(Level.WARNING, ex.getMessage());

        }
        return user;
    }

    public User getUserById(int id) {
        User user = null;
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_ID);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("user_name")
                );
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException ex) {
            logger.log(Level.WARNING, ex.getMessage());

        }
        return user;
    }

    public int storeUser(String email, String password, String userName) {
        int result = 0;
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, EncodingService.md5EncryptionWithSalt(password));
            preparedStatement.setString(3, userName);

            int affectedRows = preparedStatement.executeUpdate();


            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    result = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }


            preparedStatement.close();
        } catch (SQLException ex) {
            logger.log(Level.WARNING, ex.getMessage());
        }

        return result;
    }


}

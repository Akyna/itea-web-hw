package com.amboiko.services;

import com.amboiko.model.Category;
import com.amboiko.model.Product;
import com.amboiko.model.User;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class DBService {

    private static final String DB_HOST = "jdbc:mysql://localhost:8889/hw_09_web";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    private static final String GET_CATEGORIES = "SELECT * FROM categories";
    private static final String GET_PRODUCTS_IN_CATEGORY = "SELECT pr.id AS p_id, pr.title AS p_title, pr.description AS p_description, pr.price AS p_price, pr.img_path AS p_img_path, cat.id AS c_id, cat.title AS c_title, cat.description AS c_description, cat.img_path AS c_img_path FROM products pr JOIN categories cat ON pr.category_id=cat.id WHERE cat.id=?";
    private static final String GET_PRODUCT = "SELECT * FROM products WHERE id=?";
    private static final String GET_PRODUCTS = "SELECT * FROM products WHERE id IN (?)";

    private static final String GET_USER = "SELECT id, user_name FROM user WHERE email= ? AND password= ?";
    private static final String GET_USER_BY_ID = "SELECT id, user_name FROM user WHERE id=?";
    private static final String INSERT_USER = "INSERT INTO user (email, password, user_name) VALUES(?, ?, ?)";

    private final String dbHost;
    private final String user;
    private final String password;
    Logger logger;

    public DBService() {
        this(DB_HOST, DB_USER, DB_PASSWORD);
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

    public List<Category> getCategories() {
        List<Category> result = new ArrayList<>();

        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery(GET_CATEGORIES);

            while (resultSet.next()) {
                result.add(new Category()
                        .setId(resultSet.getLong("id"))
                        .setTitle(resultSet.getString("title"))
                        .setDescription(resultSet.getString("description"))
                        .setImage(resultSet.getString("img_path"))
                );


            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            logger.log(Level.WARNING, ex.getMessage());

        }
        return result;
    }

    public List<Product> getProducts(String categoryId) {
        List<Product> result = new ArrayList<>();
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCTS_IN_CATEGORY);
            preparedStatement.setInt(1, Integer.parseInt(categoryId));
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                result.add(new Product()
                        .setId(resultSet.getLong("p_id"))
                        .setTitle(resultSet.getString("p_title"))
                        .setDescription(resultSet.getString("p_description"))
                        .setImage(resultSet.getString("p_img_path"))
                        .setPrice(resultSet.getDouble("p_price"))
                );


            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException ex) {
            logger.log(Level.WARNING, ex.getMessage());

        }

        return result;
    }

    public Product getProduct(String productId) {
        Product result = null;
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCT);
            preparedStatement.setInt(1, Integer.parseInt(productId));
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                result = new Product()
                        .setId(resultSet.getLong("id"))
                        .setTitle(resultSet.getString("title"))
                        .setDescription(resultSet.getString("description"))
                        .setImage(resultSet.getString("img_path"))
                        .setPrice(resultSet.getDouble("price"));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException ex) {
            logger.log(Level.WARNING, ex.getMessage());

        }

        return result;
    }

    public List<Product> getProducts(int[] idList) {
        List<Product> result = new ArrayList<>();
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCT);
            Array array = preparedStatement.getConnection().createArrayOf("INT", new int[][]{idList});
            preparedStatement.setArray(1, array);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                result.add(new Product()
                        .setId(resultSet.getLong("id"))
                        .setTitle(resultSet.getString("title"))
                        .setDescription(resultSet.getString("description"))
                        .setImage(resultSet.getString("img_path"))
                        .setPrice(resultSet.getDouble("price")));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException ex) {
            logger.log(Level.WARNING, ex.getMessage());

        }

        return result;
    }
}

package pl.javastart.personalbudget;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class ReceiptDao {
    private final Connection connection = connect();
    private ArrayList<Receipt> receipts;
    public Optional<ArrayList<Receipt>> findAll() {
        Connection connection = connect();
        final String sql = "SELECT id, type, description, amount, date FROM personal_budget";
        PreparedStatement preparedStatement = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (true) {
                receipts = new ArrayList<>();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String type = resultSet.getString("type");
                    String description = resultSet.getString("description");
                    BigDecimal amount = resultSet.getBigDecimal("amount");
                    String date = resultSet.getString("date");
                    receipts.add(new Receipt(id, type, description, amount, date));
                }
                return Optional.of(receipts);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        closeConnection(connection);
        return Optional.empty();
    }

    public Optional<ArrayList<Receipt>> findByType(String typeName) {
        Connection connection = connect();
        final String sql = "SELECT id, type, description, amount, date FROM personal_budget WHERE type = ?";
        PreparedStatement preparedStatement = null;
        try {
            Statement statement = connection.createStatement();
            preparedStatement.setString(1, typeName);
            ResultSet resultSet = statement.executeQuery(sql);
            if (true) {
                receipts = new ArrayList<>();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String type = resultSet.getString("type");
                    String description = resultSet.getString("description");
                    BigDecimal amount = resultSet.getBigDecimal("amount");
                    String date = resultSet.getString("date");
                    receipts.add(new Receipt(id, type, description, amount, date));
                }
                return Optional.of(receipts);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        closeConnection(connection);
        return Optional.empty();
    }

    public void deleteById(Integer id) {
        Connection connection = connect();
        PreparedStatement preparedStatement = null;
        try {
            String sql = "DELETE FROM personal_budget WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, String.valueOf(id));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(connection);
    }

    public void add(Receipt receipt) {
        Connection connection = connect();
        PreparedStatement preparedStatement = null;
        try {
            String sql = "INSERT INTO personal_budget(type, description, amount, date) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, receipt.getType());
            preparedStatement.setString(2, receipt.getDescription());
            preparedStatement.setBigDecimal(3, receipt.getAmount());
            preparedStatement.setString(4, receipt.getDate());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(connection);
    }

    public void update(Receipt receipt) {
        Connection connection = connect();
        PreparedStatement preparedStatement = null;
        try {
            String sql = "UPDATE personal_budget SET type = ? , description = ?, amount = ?, date = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, receipt.getType());
            preparedStatement.setString(2, receipt.getDescription());
            preparedStatement.setBigDecimal(3, receipt.getAmount());
            preparedStatement.setString(4, receipt.getDate());
            preparedStatement.setInt(5, receipt.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(connection);
    }

    private void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String url = "jdbc:mysql://localhost:3306/db_java_start?serverTimezone=UTC&characterEncoding=utf8";
        try {
            return DriverManager.getConnection(url, "root", "admin");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

package am.hitech.jdbc.repo;

import am.hitech.jdbc.model.Account;
import am.hitech.jdbc.util.DataSource;

import java.sql.*;

public class AccountRepo {

    //this all is about transactions

    Connection connection = DataSource.getConnection();

    public void transfer(int from, int to, int amount) {
        String addBalance = "update account set balance = balance + ? where user_id = ?";
        String deductBalance = "update account set balance = balance - ? where user_id = ?";

        try {

            connection.setAutoCommit(false);

            PreparedStatement statement = connection.prepareStatement(addBalance);
            statement.setInt(1, amount);
            statement.setInt(2, to);
            statement.executeUpdate();



            statement = connection.prepareStatement(deductBalance);
            statement.setInt(1, amount);
            statement.setInt(2, from);
            statement.executeUpdate();

            connection.commit();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {

            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }

    public int createAccount(Account account) {
        String query = "insert into account values(0, ?, ?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, account.getUserName());
            statement.setString(2, account.getPassword());
            statement.setInt(3, account.getBalance());
            statement.setInt(4, account.getUserId());


            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteAccount(Account account) {
        String query = "delete from account where username = " + account.getUserName() + " and" +
                "password = " + account.getPassword();

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int updateAccount(int user_id, String username, String password) {
        String query = "update account set username = ?, password = ?" +
                "where user_id = " + user_id;

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);

            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int findByPhone(int code, int number) {


        /*String query = "SELECT user_id FROM phone_numbers WHERE `number` = " + number + "" +
                "AND phone_code_id = (" +
                "SELECT id" +
                "FROM phone_codes" +
                "WHERE `code` = " + code +")";*/

        String query = "SELECT * FROM `account` " +
                "WHERE user_id = (" +
                "SELECT user_id " +
                "FROM phone_numbers " +
                "WHERE `number` = ? " +
                "AND phone_code_id = (" +
                "SELECT id " +
                "FROM phone_codes " +
                "WHERE `code` = ?))";
        try {

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, number);
            statement.setInt(2, code);

            ResultSet resultSet = statement.executeQuery();

            int id = 0;

            while (resultSet.next()) {
                id =  resultSet.getInt("user_id");
                System.out.println("id is found");
            }

        return id;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}

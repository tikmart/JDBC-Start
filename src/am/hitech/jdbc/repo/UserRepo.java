package am.hitech.jdbc.repo;

import am.hitech.jdbc.model.User;
import am.hitech.jdbc.util.DataSource;

import java.sql.*;

public class UserRepo {
    Connection connection = DataSource.getConnection();


    public int createUserV1(User user) {
        String query = "insert into user values(" + user.getId() + ", '" + user.getFirstName() + "','" +
                user.getLastName() + "','" + user.getEmail() + "'," + user.getAge() + ")";

        try {
            Statement statement = connection.createStatement();
            return statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int createUserV2(User user) {
        String query = "insert into user values(0, ?, ?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setInt(4, user.getAge());

            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public User getById(int id) {
        User user = null;

        String query = "select * from user where id = " + id;

        try {
            ResultSet resultSet = connection.createStatement().executeQuery(query);

            user = buildUser(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }


    public User getByUser(String userName) {
        User user = null;
        String query = "select * from user where email = " + userName;

        try {
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            user = buildUser(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }






    private User buildUser(ResultSet resultSet) {
        User user = null;

        try {
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt(1));
                user.setFirstName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setEmail(resultSet.getString(4));
                user.setAge(resultSet.getInt(5));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }

    public int updateUser(int id, String firstName, String lastName) {
        User user = null;
        String query = "update user set first_name = ?, last_name = ?" +
                "where id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setInt(3, id);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }

}

























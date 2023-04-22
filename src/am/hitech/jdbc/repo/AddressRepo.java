package am.hitech.jdbc.repo;

import am.hitech.jdbc.model.Address;
import am.hitech.jdbc.util.DataSource;
import am.hitech.jdbc.util.exceptionmessage.ErrorMessage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class AddressRepo {
    Connection connection = DataSource.getConnection();




    public Address getByUserID(int id) {
//        connection.setReadOnly(true);
        Address address = null;

        String query = "select * from address where id = " + id;

        try {
            ResultSet resultSet = connection.createStatement().executeQuery(query);

            address = buildAddress(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return address;
    }

    public Address getById(int id) {
        Address address = null;

        String query = "select * from address where user_id = " + id;

        try {
            ResultSet resultSet = connection.createStatement().executeQuery(query);

            address = buildAddress(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return address;
    }

    public List getAllAddress() {
        List<Address> list = new ArrayList<>();

        String query = "select * from address";

        try {
            ResultSet resultSet = connection.createStatement().executeQuery(query);

            while (resultSet.next()) {
                list.add(buildAddress(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public int deleteByUserId(int id) {
        String query = "delete from address where user_id =" + id;

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public int updateAddress(int userId, String country, String city, String street, int home) {
       String query = "update address set country = ?, city = ?, street = ?, home = ? where user_id = " + userId;

       try {
           PreparedStatement statement = connection.prepareStatement(query);
           statement.setString(1, country);
           statement.setString(2, city);
           statement.setString(3, street);
           statement.setInt(4, home);

           return statement.executeUpdate();
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
    }


    public int createAddress(Address address) {
        String query = "insert into address values(0, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, address.getCountry());
            statement.setString(2, address.getCity());
            statement.setString(3, address.getStreet());
            statement.setInt(4, address.getHome());

            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
     }




    private Address buildAddress(ResultSet resultSet) {
        Address address = null;

        try {
            while (resultSet.next()) {
                address = new Address();

                address.setId(resultSet.getInt(1));
                address.setCountry(resultSet.getString(2));
                address.setCity(resultSet.getString(3));
                address.setStreet(resultSet.getString(4));
                address.setHome(resultSet.getInt(5));
                address.setUserId(resultSet.getInt(6));


            }
            return address;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

      /*  try {
            address = new Address();
            address.setId(resultSet.getInt(1));
            address.setCountry(resultSet.getString(2));
            address.setCity(resultSet.getString(3));
            address.setStreet(resultSet.getString(4));
            address.setHome(resultSet.getInt(5));
            address.setUserId(resultSet.getInt(6));

            return address;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
    }
}

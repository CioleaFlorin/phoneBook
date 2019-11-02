package org.fasttrackIT.phonebook.persistance;

import org.fasttrackIT.phonebook.domain.PhoneContact;
import org.fasttrackIT.phonebook.transfer.CreatePhoneContactRequest;

import java.io.IOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhoneContactRepository {

    public void createPhoneContact(CreatePhoneContactRequest request) throws SQLException, IOException, ClassNotFoundException {
        String sql = "INSERT INTO phone_contact (first_name, last_name, phone_number) VALUES(?,?,?)";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, request.getFirst_name());
            preparedStatement.setString(2, request.getLast_name());
            preparedStatement.setString(3, request.getPhoneNumber());
            preparedStatement.executeUpdate();
        }


    }


    public List<PhoneContact> getPhoneContacts() throws SQLException, IOException, ClassNotFoundException {
        String sql = "SELECT id, first_name, last_name, phone_number FROM phone_contact";
        List<PhoneContact> phoneContacts = new ArrayList<>();

        try (Connection connection = DatabaseConfiguration.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                PhoneContact phoneContact = new PhoneContact();
                phoneContact.setId(resultSet.getLong("id"));
                phoneContact.setFirstName(resultSet.getString("first_name"));
                phoneContact.setLastName(resultSet.getString("last_name"));
                phoneContact.setPhoneNumber(resultSet.getString("phone_number"));

                phoneContacts.add(phoneContact);

            }
        }
        return phoneContacts;
    }


    public void deletePhoneContact(long id) throws SQLException, IOException, ClassNotFoundException {
        String sql = "DELETE FROM phone_contact WHERE id=?";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
    }


    public List<PhoneContact> getPhoneContactByName(String first_name, String last_name) throws SQLException, IOException, ClassNotFoundException {
        String sql = "SELECT id, first_name, last_name, phone_number FROM phone_contact";
        List<PhoneContact> phoneContacts = new ArrayList<>();
        try (Connection connection = DatabaseConfiguration.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                PhoneContact phoneContact = new PhoneContact();
                phoneContact.setId(resultSet.getLong("id"));
                phoneContact.setFirstName(resultSet.getString("first_name"));
                phoneContact.setLastName(resultSet.getString("last_name"));
                phoneContact.setPhoneNumber(resultSet.getString("phone_number"));

                if (phoneContact.getLastName().equals(last_name) | phoneContact.getFirstName().equals(first_name))
                    phoneContacts.add(phoneContact);

            }
        }
        return phoneContacts;
    }

    public void updatePhoneContact(long id, String first_name, String last_name, String phone_number) throws SQLException, IOException, ClassNotFoundException {
        String sql = "UPDATE phone_contact SET first_name=?, last_name=?, phone_number=? WHERE id=?";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, first_name);
            preparedStatement.setString(2, last_name);
            preparedStatement.setString(3, phone_number);
            preparedStatement.setLong(4, id);

            preparedStatement.executeUpdate();
        }
    }

    public void deleteMorePhoneContacts(long[] id) throws SQLException, IOException, ClassNotFoundException {
        String sql = "DELETE FROM phone_contact WHERE id=?";
        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            for (int i = 0; i < id.length; i++) {
                preparedStatement.setLong(1, id[i]);
                preparedStatement.executeUpdate();
            }
        }

    }
}

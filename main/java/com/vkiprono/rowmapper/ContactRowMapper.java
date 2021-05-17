package com.vkiprono.rowmapper;

import com.vkiprono.models.Contact;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactRowMapper implements RowMapper<Contact> {
    @Override
    public Contact mapRow(ResultSet resultSet, int i) throws SQLException {
        Contact contact = new Contact();
       // contact.setContactId(resultSet.getLong("contact_id"));
        contact.setContactId(resultSet.getLong("contact_id"));
        contact.setUserId(resultSet.getLong("user_id"));
        contact.setFirstName(resultSet.getString("firstname"));
        contact.setLastName(resultSet.getString("lastname"));
        contact.setPhone(resultSet.getString("phone"));
        contact.setEmail(resultSet.getString("email"));
        contact.setAddress(resultSet.getString("address"));
        contact.setRemark(resultSet.getString("remark"));

        return contact;
    }

}

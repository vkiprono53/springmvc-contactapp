package com.vkiprono.rowmapper;

import com.vkiprono.models.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonRowMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet resultSet, int i) throws SQLException {
        Person person = new Person();
        person.setUserId(resultSet.getLong("user_id"));
        person.setFirstName(resultSet.getString("firstname"));
        person.setLastName(resultSet.getString("lastname"));
        person.setPhone(resultSet.getString("phone"));
        person.setEmail(resultSet.getString("email"));
        person.setAddress(resultSet.getString("address"));
        person.setLoginName(resultSet.getString("login_name"));
        person.setRole(resultSet.getInt("role"));
        person.setLoginStatus(resultSet.getInt("login_status"));
        return person;

    }
}

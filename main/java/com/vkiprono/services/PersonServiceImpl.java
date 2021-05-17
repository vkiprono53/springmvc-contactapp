package com.vkiprono.services;

import com.vkiprono.models.Person;
import com.vkiprono.repositories.PersonDAOI;
import com.vkiprono.rowmapper.PersonRowMapper;
import com.vkiprono.utils.Constants;
import exceptions.UserBlockedExeptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service

public class PersonServiceImpl extends NamedParameterJdbcDaoSupport implements PersonServiceI {
    @Autowired
    private PersonDAOI personDAOI;

    @Autowired
    public void setDatasource(DataSource dataSource) {
        super.setDataSource(dataSource);
    }

    @Override
    public void registerUser(Person person) {
        personDAOI.save(person);
    }

    @Override
    public Person login(String loginName, String password) {
        String query = "SELECT user_id, firstname,lastname,phone,email,address,login_name,role,login_status " +
                "FROM user WHERE login_name =:loginName AND password =:password";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("loginName", loginName);
        map.put("password", password);

        try {
            Person person = getNamedParameterJdbcTemplate().queryForObject(query, map, new PersonRowMapper());
            if (person.getLoginStatus().equals(Constants.LOGIN_STATUS_INACTIVE)) {
                throw new UserBlockedExeptions("You are not allowed to login.Please Contact the System Admin.");

            } else
                return person;

        } catch (EmptyResultDataAccessException | UserBlockedExeptions exception) {
            exception.printStackTrace();

            return null;
        }
    }

    @Override
    public void register(String firstName, String lastName, String phone,
                         String email, String address, String loginName, String password) {
        Person person = new Person();
        personDAOI.save(person);
    }

    @Override
    public List<Person> getAllUsers() {
        return personDAOI.findAll();
    }

    @Override
    public void changeLoginStatus(String loginName, int loginStatus) {

    }
}

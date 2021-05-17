package com.vkiprono.repositories;

import com.vkiprono.models.Person;
import com.vkiprono.rowmapper.PersonRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Repository
public class PersonDAOImpl extends NamedParameterJdbcDaoSupport implements PersonDAOI {
    private static final Logger logger = Logger.getLogger(PersonDAOImpl.class.getName());
    private Map<String, Object> map = new HashMap<String, Object>();

    @Autowired
    public void setDatasource(DataSource dataSource) {
        super.setDataSource(dataSource);
    }

    @Override
    public void save(Person person) {
        logger.info(":::::::::::::::::BEGINNING OF SAVE PERSON METHOD::::::::");
        String query = "INSERT INTO user(firstname,lastname,phone,email,address,login_name,password,role,login_status" +
                ") VALUES(:firstname,:lastname,:phone,:email,:address,:login_name,:password,:role,:login_status)";


        logger.info(":::::::::::::::::PERSONDAOIMPL PERSON DATA HERE ARE:::::::: FirstName===="+person.getFirstName());

        map.put("firstname", person.getFirstName());
        map.put("lastname", person.getLastName());
        map.put("phone", person.getPhone());
        map.put("email", person.getEmail());
        map.put("address", person.getAddress());
        map.put("login_name", person.getLoginName());
        map.put("password", person.getPassword());
        map.put("role", person.getRole());
        map.put("login_status", person.getLoginStatus());


        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource parameterSource = new MapSqlParameterSource(map);
        super.getNamedParameterJdbcTemplate().update(query, parameterSource, keyHolder);
        Long userId = keyHolder.getKey().longValue();
        person.setUserId(userId);
        logger.info("END OF SAVE PERSON METHOD::::::::");

    }

    @Override
    public void update(Person person) {
        logger.info(":::::::::::::::BEGIN OF PERSON UPDATE METHOD:::::::::");
        String sql = "UPDATE user SET(firstname=:firstname,lastname=:lastname,phone=:phone,email=:email,address=:address,role=:role,login_status=:login_status" +
                "WHERE user_id=:userId";
        map.put("firstname", person.getFirstName());
        map.put("lastname", person.getLastName());
        map.put("phone", person.getPhone());
        map.put("email", person.getEmail());
        map.put("address", person.getAddress());
        map.put("role", person.getRole());
        map.put("login_status", person.getLoginStatus());
        map.put("userId", person.getUserId());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource parameterSource = new MapSqlParameterSource(map);
        getNamedParameterJdbcTemplate().update(sql, parameterSource, keyHolder);
        logger.info(":::::::::::::::END OF UPDATING USER METHOD::::::::");

    }

    @Override
    public void delete(Person person) {
        logger.info("::::BEGINNING TO DELETE PERSON METHOD::::::::");
        String sql = "DELETE FROM user WHERE user_id = :userId";
        getJdbcTemplate().update(sql, person.getUserId());
        logger.info("::::END OF DELETE PERSON METHOD::::::::");

    }

    @Override
    public void deleteById(Long personId) {

    }

    @Override
    public Person findById(Long personId) {
        logger.info("::::BEGINNING OF FIND BY PERSON ID METHOD::::::::");

        String query = "SELECT user_id, firstname,lastname,phone,email,address,login_name,role,login_status FROM user WHERE user_id = ?";
        Person person = getJdbcTemplate().queryForObject(query, new PersonRowMapper(), personId);
        logger.info("::::ENDING FIND BY PERSON ID::::::::");

        return person;
    }

    @Override
    public List<Person> findAll() {
        String query = "SELECT user_id, firstname,lastname,phone,email,address,login_name,role,login_status FROM user";
        List<Person> personList = getJdbcTemplate().query(query, new PersonRowMapper());
        return personList;

    }

    @Override
    public List<Person> findByProp(String prop, Object object) {
        String query = "SELECT user_id, firstname,lastname,phone,email,address,login_name,role,login_status FROM user WHERE " + prop + "=?";
        List<Person> personList = getJdbcTemplate().query(query, new PersonRowMapper(), object);
        return personList;
    }
}


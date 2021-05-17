package com.vkiprono.repositories;

import com.vkiprono.models.Contact;
import com.vkiprono.rowmapper.ContactRowMapper;
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

@Repository
public class ContactDAOImp extends NamedParameterJdbcDaoSupport implements ContactDAOI {

    private Map<String, Object> map = new HashMap<String, Object>();

    @Autowired
    public void setDatasource(DataSource dataSource) {
        super.setDataSource(dataSource);
    }

    @Override
    public void save(Contact contact) {
        logger.info(":::::::::::::::::ContactDAOImp.save::::BEGINNING OF SAVE CONTACT DETAILS::::::::");
        String query = "INSERT INTO contact(user_id,firstname,lastname,phone,email,address,remark" +
                ") VALUES(:userId,:firstname,:lastname,:phone,:email,:address,:remark)";

        map.put("userId", contact.getUserId());
        map.put("firstname", contact.getFirstName());
        map.put("lastname", contact.getLastName());
        map.put("phone", contact.getPhone());
        map.put("email", contact.getEmail());
        map.put("address", contact.getAddress());
        map.put("remark", contact.getRemark());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource parameterSource = new MapSqlParameterSource(map);
        getNamedParameterJdbcTemplate().update(query, parameterSource, keyHolder);
        Long contactId = keyHolder.getKey().longValue();
        contact.setContactId(contactId);
        logger.info("END OF SAVE CONTACT METHOD::::::::");

    }

    @Override
    public void update(Contact contact) {
        logger.info(":::::::::::::::BEGIN OF CONTACT UPDATE METHOD:::::::::");
        String sql = "UPDATE contact SET firstname=:firstname,lastname=:lastname,phone=:phone,email=:email,address=:address,remark=:remark " +
                "WHERE contact_id=:contactId";


        map.put("contactId", contact.getContactId());
        //  map.put("userId", contact.getUserId());
        map.put("firstname", contact.getFirstName());
        map.put("lastname", contact.getLastName());
        map.put("phone", contact.getPhone());
        map.put("email", contact.getEmail());
        map.put("address", contact.getAddress());
        map.put("remark", contact.getRemark());

        getNamedParameterJdbcTemplate().update(sql, map);
        logger.info(":::::::::::::::END OF UPDATING USER METHOD::::::::");
//
//
//        String sql = "UPDATE contact SET name=:name, phone=:phone, email=:email, address=:address, remark=:remark WHERE contactId=:contactId";
//        Map m = new HashMap();
//        m.put("contactId", c.getContactId());
//        m.put("name", c.getName());
//        m.put("phone", c.getPhone());
//        m.put("email", c.getEmail());
//        m.put("address", c.getAddress());
//        m.put("remark", c.getRemark());
//        getNamedParameterJdbcTemplate().update(sql, m);
    }

    @Override
    public void delete(Contact contact) {
        logger.info("::::BEGINNING DELETE METHOD IN CONTACT CLASS::::::::");
        String sql = "DELETE FROM contact WHERE contact_id = :contactId";
        getJdbcTemplate().update(sql, contact.getContactId());
        logger.info("::::END OF DELETE METHOD IN CONTACT CLASS::::::::");
    }

    @Override
    public void deleteById(Long contactId) {
        logger.info("::::======DELETE BY ID=====:::::::");
        String sql = "DELETE FROM contact WHERE contact_id =?";
        getJdbcTemplate().update(sql, contactId);
        logger.info(":::::::END OF CONTACT.deleteById::::::::");

    }

    @Override
    public Contact findById(Long contactId) {
        logger.info("::::BEGINNING OF FINDBYID METHOD IN CONTACTDAOIMPL::::::::");

        String query = "SELECT contact_id,user_id,firstname,lastname,phone,email,address,remark FROM contact WHERE contact_id = ?";
        Contact contact = getJdbcTemplate().queryForObject(query, new ContactRowMapper(), contactId);
        logger.info("::::ENDING FIND BY ID INSIDE CONTACT IMPL::::::::");

        return contact;
    }

    @Override
    public List<Contact> findAll() {
        logger.info("::::BEGINNING OF FINDALL METHOD IN CONTACTDAOIMPL::::::::");

        String query = "SELECT contact_id,user_id,firstname,lastname,phone,email,address,remark FROM contact";
        List<Contact> contacts = getJdbcTemplate().query(query, new ContactRowMapper());
        logger.info("::::ENDING FINDALL METHOD IN CONTACTDAOIMP::::::::");

        return contacts;
    }

    @Override
    public List<Contact> findByProp(String prop, Object object) {

        logger.info("::::BEGINNING OF FINDALL METHOD IN CONTACTDAOIMPL::::::::");

        String query = "SELECT contact_id,user_id,firstname,lastname,phone,email,address,remark FROM contact WHERE " + prop + "=?";
        // String query = "SELECT * FROM contact WHERE " + prop + "=?";
        List<Contact> contacts = getJdbcTemplate().query(query, new ContactRowMapper(), object);
        logger.info("::::ENDING FINDALL METHOD IN CONTACTDAOIMP::::::::");

        return contacts;
    }

    @Override
    public List<Contact> findUserContact(Long personId) {

        return null;
    }

    @Override
    public List<Contact> findUserContact(Long personId, String text) {
        logger.info("::::BEGINNING OF findUserContact METHOD IN CONTACTDAOIMPL::::::::");
        String query = "SELECT c.contact_id, c.user_id, c.firstname, c.lastname,c.phone, c.email,c.address, c.remark FROM contact c WHERE  \n" +
                " c.user_id =? AND (c.firstname LIKE '%"+text+"%' OR c.lastname LIKE '%"+text+"%' OR c.address " +
                "LIKE '%"+text+"%' OR c.phone LIKE '%"+text+"%' OR c.email LIKE '%"+text+"%' OR c.remark LIKE '%"+text+"%')";

        List<Contact> contact = getJdbcTemplate().query(query, new ContactRowMapper(), personId);

        logger.info("::::END OF findUserContact METHOD IN CONTACTDAOIMPL::::::::");
        return contact;
    }


}

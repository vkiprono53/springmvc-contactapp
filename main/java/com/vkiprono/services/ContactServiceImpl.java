package com.vkiprono.services;

import com.vkiprono.models.Contact;
import com.vkiprono.repositories.ContactDAOI;
import com.vkiprono.utils.StringUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

@Service
public class ContactServiceImpl extends NamedParameterJdbcDaoSupport implements ContactServiceI {
    @Autowired
    private ContactDAOI contactDAOI;

    @Autowired
    public void setDatasource(DataSource dataSource) {
        super.setDataSource(dataSource);
    }

    @Override
    public void save(Contact contact) {
        contactDAOI.save(contact);

    }

    @Override
    public void update(Contact contact) {
        contactDAOI.update(contact);

    }

    @Override
    public void delete(Contact contact) {
        contactDAOI.delete(contact);

    }

    @Override
    public void delete(Long[] contactIds) {
        String contacts = StringUtility.convertArrayToCommaList(contactIds);
        logger.info(":::::::::IDS HERE:::::" + contacts.toString());
        String query = "DELETE FROM contact WHERE contact_id IN ("+contacts+")";
        getJdbcTemplate().update(query);

    }

    @Override
    public void deleteById(Long contactId) {
        contactDAOI.deleteById(contactId);
    }

    @Override
    public List<Contact> findByProp(String prop, Object object) {
        return contactDAOI.findByProp(prop,object);
    }

    @Override
    public List<Contact> findAll() {
        return contactDAOI.findAll();
    }

    @Override
    public Contact findById(Long contactId) {
        return contactDAOI.findById(contactId);
    }

    @Override
    public List<Contact> getUsersContacts(Long personId) {
        return contactDAOI.findByProp("personId", personId);
    }

    @Override
    public List<Contact> getUsersContacts(Long userId, String inputSearch) {
        return contactDAOI.findUserContact(userId, inputSearch);
    }

}

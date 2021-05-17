package com.vkiprono.repositories;

import com.vkiprono.models.Contact;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import java.util.List;

public interface ContactDAOI {
    public void save(Contact Contact);
    public void update(Contact Contact);
    public void delete(Contact Contact);
    public void deleteById(Long contactId);
    public Contact findById(Long contactId);
    public List<Contact> findAll();
    public List<Contact> findByProp(String prop, Object object);
    public List<Contact> findUserContact(Long personId);
    public List<Contact> findUserContact(Long personId, String text);
}

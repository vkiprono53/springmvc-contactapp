package com.vkiprono.services;

import com.vkiprono.models.Contact;

import java.util.List;

public interface ContactServiceI {
    public void save(Contact contact);

    public void update(Contact contact);
    public void delete(Contact contact);
    public void delete(Long[] contactIds);
    public void deleteById(Long contactId);
    public List<Contact> findByProp(String prop, Object object);
    public List<Contact> findAll();

    public Contact findById(Long contactId);

    public List<Contact> getUsersContacts(Long userId);
    public List<Contact> getUsersContacts(Long userId, String inputSearch);
}

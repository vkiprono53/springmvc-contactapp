package com.vkiprono.services;

import com.vkiprono.models.Person;

import java.util.List;

public interface PersonServiceI {
    public void registerUser(Person person);

    /**
     *
     * @param loginName
     * @param password
     * @return
     */
    public Person login(String loginName, String password);

    public void register(String firstName, String lastName, String phone, String email, String address, String loginName, String password);

    public List<Person> getAllUsers();
    public void changeLoginStatus(String loginName, int loginStatus);
}

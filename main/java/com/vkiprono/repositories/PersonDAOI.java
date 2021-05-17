package com.vkiprono.repositories;

import com.vkiprono.models.Person;

import java.util.List;

public interface PersonDAOI {
    public void save(Person person);
    public void update(Person person);
    public void delete(Person person);
    public void deleteById(Long personId);
    public Person findById(Long personId);
    public List<Person> findAll();
    public List<Person> findByProp(String prop, Object object);
}

package ru.netology.hibernate.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.netology.hibernate.entity.Person;
import ru.netology.hibernate.repository.DAORepository;

import java.util.List;

@Service
@AllArgsConstructor
public class DAOService {

    private final DAORepository repository;

    public List<Person> getPersonsByCity(String city) {
        return repository.getPersonsByCity(city);
    }

}

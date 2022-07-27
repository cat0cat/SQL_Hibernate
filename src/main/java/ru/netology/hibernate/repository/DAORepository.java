package ru.netology.hibernate.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.netology.hibernate.entity.Person;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@AllArgsConstructor
public class DAORepository {
    private EntityManager entityManager;
    public List<Person> getPersonsByCity(String city) {
        return entityManager.createQuery("SELECT p FROM Person p WHERE p.cityOfLiving = :city", Person.class)
                .setParameter("city", city)
                .getResultList();
    }

}

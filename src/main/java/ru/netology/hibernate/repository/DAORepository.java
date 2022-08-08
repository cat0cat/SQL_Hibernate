package ru.netology.hibernate.repository;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Repository;
import ru.netology.hibernate.entity.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Repository
@AllArgsConstructor
public class DAORepository implements CommandLineRunner {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void run (String... args) throws Exception {
        var names = List.of("Ivan", "Petr", "Maxim", "Alex", "Mike");
        var surnames = List.of("Ivanov", "Petrov", "Koshkin", "Belkin", "Strelkin");
        var cities = List.of("Moscow", "Tula", "Kaluga", "Samara", "Sochi");
        var random = new Random();
        IntStream.range(0,25).
                forEach(i -> {
                    var person = Person.builder()
                            .name(names.get(random.nextInt(names.size())))
                            .surname(surnames.get(random.nextInt(surnames.size())))
                            .age(random.nextInt(30))
                            .phoneNumber(random.nextInt(123456))
                            .cityOfLiving(cities.get(random.nextInt(surnames.size())))
                            .build();
                    entityManager.persist(person);

                });
    }

    public List<Person> getPersonsByCity(String city) {
        return entityManager.createQuery("SELECT p FROM Person p WHERE p.cityOfLiving = :city", Person.class)
                .setParameter("city", city)
                .getResultList();
    }

}

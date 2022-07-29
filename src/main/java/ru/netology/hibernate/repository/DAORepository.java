package ru.netology.hibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.netology.hibernate.entity.Person;
import ru.netology.hibernate.entity.PersonId;

import java.util.List;
import java.util.Optional;

@Repository
public interface DAORepository extends JpaRepository<Person, PersonId>  {

    @Query(value = "select p from Person p where p.cityOfLiving = :city")
    List<Person> findByCityOfLiving(@Param("city") String city);

    @Query(value = "select p from Person p where p.age < :age order by p.age")
    List<Person> findByAgeLessThanOrderByAge(@Param("age") int age);

    @Query(value = "select p from Person p where p.name = :name and p.surname = :surname")
    Optional<Person>findByNameAndSurname(@Param("name") String name, @Param("surname") String surname);

}

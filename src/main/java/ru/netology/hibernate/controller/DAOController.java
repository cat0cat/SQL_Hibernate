package ru.netology.hibernate.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.hibernate.entity.Person;
import ru.netology.hibernate.service.DAOService;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/persons")
public class DAOController {

    private final DAOService service;

    @Secured({"ROLE_READ"})
    @GetMapping("/by-city")
    public List<Person> getPersons(@RequestParam("city") String city) {
        return service.getPersonsByCity(city);
    }

    @RolesAllowed({"ROLE_WRITE"})
    @GetMapping("/by-age")
    public List<Person> getAge(@RequestParam("age") int age) {
        return service.getAge(age);
    }


    @GetMapping("/name-and-surname")
    @PreAuthorize("hasRole('ROLE_WRITE') or hasRole('ROLE_DELETE')")
    public Optional<Person> getNameSurname(@RequestParam String name, @RequestParam String surname) {
        return service.getNameSurname(name, surname);
    }

    @GetMapping("/welcome")
    @PostAuthorize("#username == authentication.principal.username")
    public String getWelcome(String username) {
        return "Welcome, " + username;
    }

    @PreAuthorize("hasRole('ROLE_READ') or hasRole('ROLE_WRITE')")
    @GetMapping("/")
    public String get() {
        return "Hello from GET";
    }
}

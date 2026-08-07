package com.wexa.jobreferralnetwork.controller;

import com.wexa.jobreferralnetwork.model.Person;
import com.wexa.jobreferralnetwork.model.ReferralRequest;
import com.wexa.jobreferralnetwork.service.PersonService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
@CrossOrigin
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    // Create Person
    @PostMapping
    public String createPerson(@RequestBody Person person) {
        personService.createPerson(person);
        return "Person created successfully";
    }

    // Get All Persons
    @GetMapping
    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    // Create Referral Relationship
    @PostMapping("/referral")
    public String createReferral(@RequestBody ReferralRequest request) {
        personService.createReferral(request.getFromId(), request.getToId());
        return "Referral created successfully";
    }

    // Get referrals within 2 hops
    @GetMapping("/{id}/referrals")
    public List<Person> getReferrals(@PathVariable String id) {
        return personService.getReferralsWithinTwoHops(id);
    }
}
package com.wexa.jobreferralnetwork.service;

import com.wexa.jobreferralnetwork.model.Person;
import com.wexa.jobreferralnetwork.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    // Create Person
    public void createPerson(Person person) {
        personRepository.save(person);
    }

    // Get All Persons
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    // Create Referral Relationship
    public void createReferral(String fromId, String toId) {
        personRepository.createReferral(fromId, toId);
    }

    // Get referrals within 2 hops
    public List<Person> getReferralsWithinTwoHops(String personId) {
        return personRepository.getReferralsWithinTwoHops(personId);
    }

}
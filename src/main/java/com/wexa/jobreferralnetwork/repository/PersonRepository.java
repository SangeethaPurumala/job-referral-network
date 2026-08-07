package com.wexa.jobreferralnetwork.repository;

import com.wexa.jobreferralnetwork.model.Person;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;
import org.neo4j.driver.Session;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static org.neo4j.driver.Values.parameters;

@Repository
public class PersonRepository {

    private final Driver driver;

    public PersonRepository(Driver driver) {
        this.driver = driver;
    }

    // Create Person node
    public void save(Person person) {

        try (Session session = driver.session()) {

            session.run(
                """
                CREATE (p:Person {
                    id: $id,
                    name: $name,
                    email: $email,
                    phone: $phone,
                    location: $location
                })
                """,
                parameters(
                        "id", person.getId(),
                        "name", person.getName(),
                        "email", person.getEmail(),
                        "phone", person.getPhone(),
                        "location", person.getLocation()
                )
            );
        }
    }

    // Get all persons
    public List<Person> findAll() {

        List<Person> persons = new ArrayList<>();

        try (Session session = driver.session()) {

            var result = session.run("MATCH (p:Person) RETURN p");

            while (result.hasNext()) {

                Record record = result.next();

                var node = record.get("p").asNode();

                Person person = new Person();

                person.setId(node.get("id").toString());
                person.setName(node.get("name").asString());
                person.setEmail(node.get("email").asString());
                person.setPhone(node.get("phone").asString());
                person.setLocation(node.get("location").asString());

                persons.add(person);
            }
        }

        return persons;
    }

    // Create referral relationship
    public void createReferral(String fromId, String toId) {

        try (Session session = driver.session()) {

            session.run(
                """
                MATCH (a:Person), (b:Person)
                WHERE toString(a.id) = $fromId
                  AND toString(b.id) = $toId
                CREATE (a)-[:REFERRED]->(b)
                """,
                parameters(
                        "fromId", fromId,
                        "toId", toId
                )
            );
        }
    }

    // Multi-hop traversal (1 to 2 hops)
    public List<Person> getReferralsWithinTwoHops(String personId) {

        List<Person> referrals = new ArrayList<>();

        try (Session session = driver.session()) {

            var result = session.run(
                """
                MATCH (p:Person)-[:REFERRED*1..2]->(r:Person)
                WHERE toString(p.id) = $personId
                RETURN DISTINCT r
                """,
                parameters("personId", personId)
            );

            while (result.hasNext()) {

                Record record = result.next();

                var node = record.get("r").asNode();

                Person person = new Person();

                person.setId(node.get("id").toString());
                person.setName(node.get("name").asString());
                person.setEmail(node.get("email").asString());
                person.setPhone(node.get("phone").asString());
                person.setLocation(node.get("location").asString());

                referrals.add(person);
            }
        }

        return referrals;
    }
}
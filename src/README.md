# Job Referral Network using CognoDB

## Overview

Job Referral Network is a graph database application built using Spring Boot and CognoDB.

This application manages people and their referral relationships. Users can add persons, create referral connections, and find referrals using graph traversal.

## Why Graph Database?

Referral networks are based on relationships between people.

A graph database is suitable because relationships are stored directly between nodes. Finding referrals through multiple connections is easier compared to relational database joins.

## Technology Stack

- Java 17
- Spring Boot
- CognoDB Graph Database
- Neo4j Java Driver
- HTML
- CSS
- JavaScript
- Bootstrap

## Data Model

Node:

Person

Properties:
- id
- name
- email
- phone
- location


Relationship:

(Person)-[:REFERRED]->(Person)


## Features

- Add Person
- View all persons
- Create referral relationship
- Find referrals within 2 hops
- Graph-based relationship management


## Main Cypher Queries

### Get all persons

```cypher
MATCH (p:Person)
RETURN p;
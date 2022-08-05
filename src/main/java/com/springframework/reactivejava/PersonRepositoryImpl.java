package com.springframework.reactivejava;

import com.springframework.reactivejava.domain.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/*
 * Created by Ramit Kovvalpurail
 */
public class PersonRepositoryImpl implements PersonRepository {

    Person ramit = new Person(1, "Ramit", "Kovvalpurail");
    Person sayali = new Person(2, "Sayali", "Dagde");
    Person madhav = new Person(3, "Madhav", "Baklol");
    Person hemant = new Person(4, "Hemant", "Bisht");

    @Override
    public Mono<Person> findById(final Integer id) {
        return findAll().filter(person -> {
            return person.getId().equals(id);
        }).next();
    }

    @Override
    public Flux<Person> findAll() {
        return Flux.just(madhav, sayali, ramit, hemant);
    }
}

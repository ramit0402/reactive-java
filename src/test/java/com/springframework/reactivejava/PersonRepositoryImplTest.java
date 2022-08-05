package com.springframework.reactivejava;

import com.springframework.reactivejava.domain.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;


class PersonRepositoryImplTest {

    PersonRepositoryImpl personRepository;

    @BeforeEach
    void setUp() {
        personRepository = new PersonRepositoryImpl();
    }

    @Test
    void findByIdBlockFunction() {
        Mono<Person> personMono = personRepository.findById(2);

        Person person = personMono.block();

        System.out.println("Printing Mono Block Output: " + person);
    }

    @Test
    void findByIdSubscribeFunction() {
        Mono<Person> personMono = personRepository.findById(3);

        StepVerifier.create(personMono).expectNextCount(1).verifyComplete();

        personMono.subscribe(person -> {
            System.out.println("Printing Mono Subscribe Output: " + person);
        });
    }

    @Test
    void findByIdSubscribeFunctionNotFound() {
        Mono<Person> personMono = personRepository.findById(5);

        StepVerifier.create(personMono).verifyComplete();
        // OR
        StepVerifier.create(personMono).expectNextCount(0).verifyComplete();

        personMono.subscribe(person -> {
            System.out.println("Printing Mono Subscribe Output: " + person);
        });
    }

    @Test
    void findByIdMapFunction() {
        Mono<Person> personMono = personRepository.findById(4);

        personMono.map(person -> {
            System.out.println("Printing Mono Map Output: " + person);

            return person.getFirstName();
        }).subscribe(firstname -> {
            System.out.println("Printing Firstname from Mono Map Output: " + firstname);
        });
    }

    @Test
    void findAllBlockFirstFunction() {
        Flux<Person> personFlux = personRepository.findAll();

        Person person = personFlux.blockFirst();

        System.out.println("Printing Flux Block First Output: " + person);
    }

    @Test
    void findAllSubscribeFunction() {
        Flux<Person> personFlux = personRepository.findAll();

        StepVerifier.create(personFlux).expectNextCount(4).verifyComplete();

        personFlux.subscribe(person -> {
            System.out.println("Printing Flux Subscribe Output: " + person);
        });
    }

    @Test
    void findAllFluxToListMono() {
        Flux<Person> personFlux = personRepository.findAll();

        Mono<List<Person>> personListMono = personFlux.collectList();

        personListMono.subscribe(personList -> {
            System.out.println("Printing Flux List Output: " + personList);
        });
    }

    @Test
    void findAllFilterById() {
        Flux<Person> personFlux = personRepository.findAll();

        final Integer ID = 2;

        Mono<Person> personMono = personFlux.filter(person -> {
           return person.getId().equals(ID);
        }).next();

        personMono.subscribe(person -> {
            System.out.println("Printing Flux Filter Using ID Output: " + person);
        });
    }

    @Test
    void findAllFilterByIdWhichIsNotPresent() {
        Flux<Person> personFlux = personRepository.findAll();

        final Integer ID = 5;

        Mono<Person> personMono = personFlux.filter(person -> {
            return person.getId().equals(ID);
        }).next();

        personMono.subscribe(person -> {
            System.out.println("Printing Flux Filter Using ID Output: " + person);
        });
    }

    @Test
    void findAllFilterByIdWithException() {
        Flux<Person> personFlux = personRepository.findAll();

        final Integer ID = 5;

        Mono<Person> personMono = personFlux.filter(person -> {
            return person.getId().equals(ID);
        }).single();

        personMono
        .doOnError(throwable -> {
            System.out.println("I went boom!!!!!");
        })
        .onErrorReturn(Person.builder().id(ID).build())
        .subscribe(person -> {
            System.out.println("Printing Flux Filter Using ID Output: " + person);
        }, error -> {
            System.out.println("Printing Flux Filter Error Output: " + error.getStackTrace()[0]);
        });
    }
}
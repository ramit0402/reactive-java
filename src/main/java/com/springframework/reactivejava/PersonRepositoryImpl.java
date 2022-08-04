package com.springframework.reactivejava;

import com.springframework.reactivejava.domain.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/*
 * Created by Ramit Kovvalpurail
 */
public class PersonRepositoryImpl implements PersonRepository {
    @Override
    public Mono<Person> findById(Integer id) {
        return null;
    }

    @Override
    public Flux<Person> findAll() {
        return null;
    }
}

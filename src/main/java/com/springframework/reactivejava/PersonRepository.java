package com.springframework.reactivejava;

import com.springframework.reactivejava.domain.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/*
 * Created by Ramit Kovvalpurail
 */
public interface PersonRepository {

    Mono<Person> findById(Integer id);

    Flux<Person> findAll();
}

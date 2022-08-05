package com.springframework.reactivejava.domain;

import lombok.*;

/*
 * Created by Ramit Kovvalpurail
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private Integer id;
    private String firstName;
    private String lastName;
}

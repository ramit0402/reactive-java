package com.springframework.reactivejava.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

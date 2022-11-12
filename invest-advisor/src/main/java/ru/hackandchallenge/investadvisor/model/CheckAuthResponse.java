package ru.hackandchallenge.investadvisor.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CheckAuthResponse {

    private Integer userId;
    private String role;

}

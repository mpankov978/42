package ru.hackandchallenge.investadvisor.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CheckAuthRequest {

    private final String jwt;

}

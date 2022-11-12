package ru.hackandchallenge.investadvisor.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CheckAuthRequest {

    private final String jwt;

}

package ru.hackandchallenge.investadvisor.dto.investing;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class InvestingNewsDto {

    private String title;
    private String link;
    private String preview;
    private String from;

}

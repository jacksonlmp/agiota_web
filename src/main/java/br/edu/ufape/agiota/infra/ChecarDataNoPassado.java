package br.edu.ufape.agiota.infra;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import static java.util.Calendar.DAY_OF_MONTH;

@Component
public class ChecarDataNoPassado {

    public void handle(Date date, String errorMessage) {
        LocalDate data = date.toInstant().atZone(ZoneId.of("UTC")).toLocalDate();

        LocalDate hoje = LocalDate.now(ZoneId.of("UTC"));

        if (data.isBefore(hoje)) {
            throw new IllegalArgumentException(errorMessage);
        }
    }


}

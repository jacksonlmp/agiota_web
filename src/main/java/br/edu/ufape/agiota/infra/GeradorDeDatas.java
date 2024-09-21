package br.edu.ufape.agiota.infra;

import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

import static java.util.Calendar.DAY_OF_MONTH;

@Component
public class GeradorDeDatas {

    public Date getDataMaisNDias(Date date, int dias) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(DAY_OF_MONTH, dias);

        return c.getTime();
    }


}

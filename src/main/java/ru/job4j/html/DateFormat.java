package ru.job4j.html;

import java.time.*;
import java.util.List;

public class DateFormat {
    public LocalDateTime getDate(String s) {
        String[] data;
        if (s.contains("сегодня")) {
            LocalDate today = LocalDate.now();
            data = s.replaceAll(
                    "^.*(\\d{2}):(\\d{2})",
                    today.getDayOfMonth() + " "
                            + today.getMonthValue() + " "
                            + today.getYear() + " "
                            + "$1 $2")
                    .split(" ");
        } else if (s.contains("вчера")) {
            LocalDate yesterday = LocalDate.now().minusDays(1);
            data = s.replaceAll(
                    "^.*(\\d{2}):(\\d{2})",
                    yesterday.getDayOfMonth() + " "
                            + yesterday.getMonthValue() + " "
                            + yesterday.getYear() + " "
                            + "$1 $2")
                    .split(" ");
        } else {
            data = s.replaceAll(
                    "^(\\d{1,2})\\s(\\S{3})\\s(\\d{2}),\\s(\\d{2}):(\\d{2})",
                    "$1 $2 20$3 $4 $5")
                    .split(" ");
            data[1] = String.valueOf(getMonth(data[1]));
        }
        LocalDate day = LocalDate.of(Integer.parseInt(data[2]), Integer.parseInt(data[1]), Integer.parseInt(data[0]));
        LocalTime time = LocalTime.of(Integer.parseInt(data[3]), Integer.parseInt(data[4]));
        return LocalDateTime.of(day, time);
    }

    private int getMonth(String s) {
        List<String> months =
                List.of("", "янв", "фев", "мар", "апр", "май", "июн", "июл", "авг", "сен", "окт", "ноя", "дек");
        return months.stream().filter(month -> month.equals(s)).findFirst().map(months::indexOf).orElse(0);
    }
}
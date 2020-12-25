package ru.job4j.html;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class DateFormatTest {
    DateFormat df = new DateFormat();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    public void whenToday() {
        String today = sdf.format(new Date());
        assertThat(df.getDate("сегодня, 02:30").toString(), is(today + "T02:30"));
    }

    @Test
    public void whenYesterday() {
        String yesterday = sdf.format(new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000));
        assertThat(df.getDate("вчера, 12:06").toString(), is(yesterday + "T12:06"));
    }

    @Test
    public void whenAnyOtherDate() {
        assertThat(df.getDate("2 дек 19, 22:29").toString(), is("2019-12-02T22:29"));
        assertThat(df.getDate("31 янв 16, 10:56").toString(), is("2016-01-31T10:56"));
    }
}
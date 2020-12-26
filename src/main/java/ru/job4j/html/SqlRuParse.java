package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SqlRuParse {
    public static void main(String[] args) throws Exception {
        Document doc = Jsoup.connect("https://www.sql.ru/forum/job-offers").get();
        Elements row = doc.select(".postslisttopic");
        Elements dates = doc.select("td:matches((^\\d{1,2}\\s\\S{3}\\s\\d{2}|^\\S{5,7}),\\s\\d{2}:\\d{2})");
        int i = 0;
        for (Element date : dates) {
            Element td = row.get(i);
            Element href = td.child(0);
            System.out.println(href.attr("href"));
            System.out.println(href.text());
            System.out.println(date.text());
            i++;
        }
    }
}
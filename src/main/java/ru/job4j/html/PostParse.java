package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class PostParse {
    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect(
                "https://www.sql.ru/forum/1325330/"
                        + "lidy-be-fe-senior-cistemnye-analitiki-qa-i-devops-moskva-do-200t").get();
        PostParse pp = new PostParse();
        System.out.println(pp.headerParse(doc)
                + System.lineSeparator()
                + pp.descriptionParse(doc)
                + System.lineSeparator()
                + pp.createdParse(doc));
    }

    public String headerParse(Document doc) {
        return doc.select(".messageHeader").get(0).text();
    }

    public String descriptionParse(Document doc) {
        return doc.select(".msgBody").get(1).text();
    }

    public String createdParse(Document doc) {
        return doc.select(".msgFooter")
                .get(0)
                .text()
                .replaceAll("^(.*,\\s\\d{2}:\\d{2}).*", "$1");
    }
}
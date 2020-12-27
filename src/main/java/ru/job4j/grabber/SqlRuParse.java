package ru.job4j.grabber;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.html.DateFormat;
import ru.job4j.html.PostParse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SqlRuParse implements Parse {

    @Override
    public List<Post> list(String link) throws IOException {
        List<Post> rsl = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Document doc = Jsoup.connect(link + i).get();
            Elements rows = doc.select(".postslisttopic");
            for (Element e : rows) {
                Element href = e.child(0);
                Post post = detail(href.attr("href"));
                if (post.getDescription().toLowerCase().contains("java")
                        || post.getHeader().toLowerCase().contains("java")) {
                    rsl.add(post);
                }
            }
        }
        return rsl;
    }

    @Override
    public Post detail(String link) throws IOException {
        Document doc = Jsoup.connect(link).get();
        PostParse pp = new PostParse();
        return new Post(
                pp.headerParse(doc),
                pp.descriptionParse(doc),
                link,
                new DateFormat().getDate(pp.createdParse(doc))
            );
    }
}
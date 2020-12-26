package ru.job4j.grabber;

import org.apache.maven.settings.Settings;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PsqlStore implements Store, AutoCloseable {
    private final Connection cnn;

    public PsqlStore(Properties cfg) throws SQLException {
        try {
            Class.forName(cfg.getProperty("driver-class-name"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        cnn = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.login"),
                cfg.getProperty("jdbc.password")
        );
    }

    @Override
    public void save(Post post) {
        try (PreparedStatement statement = cnn.prepareStatement(
                "insert into post(header, description, link, created) values (?, ?, ?, ?) on conflict do nothing",
                Statement.RETURN_GENERATED_KEYS
        )) {
            statement.setString(1, post.getHeader());
            statement.setString(2, post.getDescription());
            statement.setString(3, post.getLink());
            statement.setTimestamp(4, Timestamp.valueOf(post.getCreated()));
            statement.execute();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    post.setId(generatedKeys.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Post> getAll() {
        List<Post> posts = new ArrayList<>();
        try (PreparedStatement statement = cnn.prepareStatement("select * from post")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    posts.add(new Post(
                            resultSet.getInt("id"),
                            resultSet.getString("header"),
                            resultSet.getString("description"),
                            resultSet.getString("link"),
                            resultSet.getTimestamp("created").toLocalDateTime()
                    ));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return posts;
    }

    @Override
    public Post findById(String id) {
        Post post = new Post();
        try (PreparedStatement statement = cnn.prepareStatement(
                "select * from post where id = ?"
        )) {
            statement.setInt(1, Integer.parseInt(id));
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    post.setId(resultSet.getInt("id"));
                    post.setHeader(resultSet.getString("header"));
                    post.setDescription(resultSet.getString("description"));
                    post.setLink(resultSet.getString("link"));
                    post.setCreated(resultSet.getTimestamp("created").toLocalDateTime());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return post.getId() != 0 ? post : null;
    }

    @Override
    public void close() throws Exception {
        if (cnn != null) {
            cnn.close();
        }
    }

    public static void main(String[] args) throws SQLException, IOException {
        Properties properties = new Properties();
        ClassLoader loader = Settings.class.getClassLoader();
        try (InputStream in = loader.getResourceAsStream("app.properties")) {
            properties.load(in);
        }
        SqlRuParse sqlParse = new SqlRuParse();
        PsqlStore psql = new PsqlStore(properties);
        sqlParse.list("https://www.sql.ru/forum/job-offers/").forEach(psql::save);
        List<Post> all = psql.getAll();
        all.forEach(i -> System.out.println(i + System.lineSeparator()));
    }
}
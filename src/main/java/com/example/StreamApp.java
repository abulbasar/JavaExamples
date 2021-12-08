package com.example;

import com.example.models.Movie;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamApp {

    /*
    * Download the data file https://drive.google.com/file/d/16etgjQXq4LAtbEn7Rs4DBDWmanxfUiSf/view?usp=sharing
    * */

    public void start() throws IOException {
        // https://www.univocity.com/pages/univocity_parsers_tutorial
        CsvParserSettings settings = new CsvParserSettings();
        settings.getFormat().setLineSeparator("\n");
        CsvParser parser = new CsvParser(settings);

        Path path = Paths.get("/data/ml-latest-small/movies.csv");
        Stream<String> stream = Files.lines(path, StandardCharsets.UTF_8);
        stream.filter(line -> line.contains("Godfather")).forEach(System.out::println);


        stream = Files.lines(path, StandardCharsets.UTF_8);
        final List<String> lines = stream.collect(Collectors.toList());

        Stream<Movie> movieStream
                = lines.stream()
                .filter(line -> line.length() > 0 && !line.startsWith("movieId"))
                .map(line -> {
                    final String[] tokens = parser.parseLine(line);
                        final Movie movie = new Movie();
                        movie.setMovieId(Integer.parseInt(tokens[0]));
                        movie.setTitle(tokens[1]);
                        movie.setGenres(tokens[2]);
                        return movie;
                    });


        movieStream
                = lines.stream().flatMap(line -> {
                    if(!line.isEmpty() && !line.startsWith("movieId")) {
                        final String[] tokens = parser.parseLine(line);
                        final Movie movie = new Movie();
                        movie.setMovieId(Integer.parseInt(tokens[0]));
                        movie.setTitle(tokens[1]);
                        movie.setGenres(tokens[2]);
                        return Stream.of(movie);
                    }
                    return Stream.empty();
                });


        final List<Movie> movies = movieStream.collect(Collectors.toList());

        System.out.println(movies.size());


        final List<Movie> godfatherMovies = movies.stream().filter(m -> m.getTitle().contains("Godfather")).collect(Collectors.toList());
        System.out.println("Complete");

    }

    public static void main(String[] args) throws IOException {
        new StreamApp().start();
    }
}

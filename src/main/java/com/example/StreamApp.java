package com.example;

import com.example.common.Tuple2;
import com.example.models.BankAccount;
import com.example.models.Movie;
import com.example.services.Helper;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
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

        System.out.println("-------------- Infinite Stream (w limit) -----------------");
        final Stream<BankAccount> bankAccountStream = Stream.generate(Helper::createSample);

        // Use limit to limit the number of records
        bankAccountStream.limit(3).forEach(System.out::println);

        System.out.println("-------------- Range Int stream -----------------");
        IntStream.range(0, godfatherMovies.size()).forEach((int i)-> {
            final Movie movie = godfatherMovies.get(i);
            System.out.println(movie.getTitle());
        });

        System.out.println("-------------- Random Double stream -----------------");
        final DoubleStream randomDoubles = Helper.random.doubles();
        randomDoubles.limit(10).forEach(System.out::println);

        System.out.println("-------------- Random Strings stream -----------------");
        IntStream.range(0, 10)
                .mapToObj(i -> Helper.randomString(5, 10))
                .forEach(System.out::println)
        ;

        System.out.println("-------------- Reduce stream -----------------");
        final double sum = Helper.random.doubles().limit(10).reduce(0.0, (a, b) -> a + b);
        System.out.println("Sum of double stream: " + sum);

        System.out.println("-------------- Group stream -----------------");
        final List<Tuple2<String, Integer>> tuples = IntStream.range(0, 10)
                .mapToObj(i -> Helper.randomString(5, 10))
                .map(s -> new Tuple2<>(s, s.length()))
                .collect(Collectors.toList());

        final Map<String, List<Tuple2<String, Integer>>> groupedStream = tuples.stream()
                .collect(Collectors.groupingBy(Tuple2::getV1));

        for (Map.Entry<String, List<Tuple2<String, Integer>>> pair : groupedStream.entrySet()) {
            final Integer sumOfLength = pair.getValue().stream().map(Tuple2::getV2).reduce(0, Integer::sum);
            System.out.println(pair.getKey() + "=>" + sumOfLength/pair.getValue().size());
        }

        System.out.println("Complete");
    }

    public static void main(String[] args) throws IOException {
        new StreamApp().start();
    }
}

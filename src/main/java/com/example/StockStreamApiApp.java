package com.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StockStreamApiApp {


    // File used for this demo
    // https://docs.google.com/spreadsheets/d/18WX32McncnN2Xy37ADyTUgq3VKSmwHED2VcAZoBwJe0/edit?usp=sharing

    public static void main(String[] args) throws Exception{
        final StockStreamApiApp app = new StockStreamApiApp();
        app.start(args);
    }

    @Data
    public static class Stock {
        String date;
        Double open;
        Double high;
        Double low;
        Double close;
        Double volume;
        Double adjclose;
        String symbol;

        public String getWeekDay(){
            return LocalDate.parse(date).getDayOfWeek().toString();
        }

        public Double getReturn(){
            return 100 * (close - open)/open;
        }
    }

    private Stock toSock(String line){
        final String[] tokens = line.split(",");
        //date,open,high,low,close,volume,adjclose,symbol
        Stock stock = new Stock();
        stock.setDate(tokens[0]);
        stock.setOpen(Double.parseDouble(tokens[1]));
        stock.setHigh(Double.parseDouble(tokens[2]));
        stock.setLow(Double.parseDouble(tokens[3]));
        stock.setClose(Double.parseDouble(tokens[4]));
        stock.setVolume(Double.parseDouble(tokens[5]));
        stock.setAdjclose(Double.parseDouble(tokens[6]));
        stock.setSymbol(tokens[7]);
        return stock;
    }

    @Data
    public static class StockStats{
        private String symbol;
        private Integer count;
        private Double avgVolume;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Tuple2<T1, T2>{
        T1 _1;
        T2 _2;
    }

    private void start(String[] args) throws IOException {
        List<String> values = Arrays.asList("Apple", "Banana", "Carrot");
        values.forEach(item -> {
            System.out.println(item.toLowerCase(Locale.ROOT) + " => " + item.length());
        });

        final Stream<String> upperCaseValueStream = values.stream().map(value -> value.toUpperCase());
        final List<String> upperCaseValues = upperCaseValueStream.collect(Collectors.toList());
        for (String value : upperCaseValues) {
            System.out.println(value);
        }
        String inputPath = args[0];
        System.out.println("Input path: " + inputPath);
        final Stream<String> linesStream = Files.lines(Paths.get(inputPath));
        final List<String> lines = linesStream.collect(Collectors.toList());
        System.out.println(lines.size());

        /*
        * filter: quality each input record
        * map: transfer each input to an output
        * flatMap: project multiple records from a item which is a collection
        * forEach: take some action for each value
        * collect: aggregate the values
        *
        * */

        final List<Stock> stocks = lines
                .stream().filter(line -> !line.startsWith("date"))
                .map(this::toSock)
                .collect(Collectors.toList());

        stocks.stream().limit(10).forEach(System.out::println);

        System.out.println("Number of records in stocks: " + stocks.size());


//        final IntStream range = IntStream.range(0, 10);
//        range.forEach(System.out::println);
//        range.count(); // throws an exception
        final List<String> distinctSymbols = stocks.stream()
                .map(Stock::getSymbol).distinct().collect(Collectors.toList());

        System.out.println("Distinct symbols, count: " + distinctSymbols.size());
        System.out.println(distinctSymbols);

        final Map<String, Integer> countBySymbol = stocks.stream().map(Stock::getSymbol)
                .collect(Collectors.toMap(Function.identity(), e -> 1, Math::addExact));

        System.out.println("Count by symbol: " + countBySymbol);

        final List<Map.Entry<String, Integer>> countBySymbolSorted = countBySymbol
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(t -> t.getValue()))
                .collect(Collectors.toList())
                ;

        Collections.reverse(countBySymbolSorted);

        System.out.println("Count by symbol (sorted by count desc): " + countBySymbolSorted.subList(0, 3));


        final Map<String, List<Stock>> stocksGroupedBySymbol = stocks.stream()
                .collect(Collectors.groupingBy(Stock::getSymbol));

        final Stream<StockStats> stockStatsStream = stocksGroupedBySymbol.entrySet().stream().map(p -> {
            final String symbol = p.getKey();
            final List<Stock> stocksValues = p.getValue();
            final Double sumOfVolume = stocksValues.stream()
                    .map(Stock::getVolume).reduce(0.0, Double::sum);
            // 1, 5, 9, 3, 8

            final StockStats stat = new StockStats();
            stat.setSymbol(symbol);
            stat.setCount(stocksValues.size());
            stat.setAvgVolume(sumOfVolume / stocksValues.size());
            return stat;
        }).sorted(Comparator.comparing(StockStats::getAvgVolume).reversed())
        ;

        System.out.println("----- Top 3 stocks by avg volume ---- ");
        stockStatsStream.limit(3).forEach(System.out::println);

        final Map<String, Integer> countByDayOfWeek = stocks.stream().map(Stock::getWeekDay)
                .collect(Collectors.toMap(Function.identity(), e -> 1, Math::addExact));

        System.out.println("------ Count by day of the week ------------");
        System.out.println(countByDayOfWeek);

        System.out.println("------ Top 3 stocks with highest intra day return in 2016 ------------");
        final Stream<Stock> stocksWithSortedReturn2016 = stocks.stream()
                .filter(s -> s.getDate().startsWith("2016"))
                .sorted(Comparator.comparing(Stock::getReturn).reversed());

        stocksWithSortedReturn2016.limit(3)
                .map(s -> String.format("Stock: %s, Date: %s, Return: %.2f"
                        , s.getSymbol(), s.getDate(), s.getReturn()))
                .forEach(System.out::println);


        final Random random = new Random();
        random.ints() // infinite stream
                .limit(10)
                .forEach(System.out::println);

        final DoubleStream randomDoubleInfi = random.doubles(); // infinite stream of (primitive) double values

        final List<Double> random10Doubles = randomDoubleInfi.limit(1000).boxed().collect(Collectors.toList());


        final IntStream intStream = IntStream.of(1, 5, 19, 4, 10);

        final List<Tuple2<Integer, Double>> randomTuples = IntStream.range(0, 10).mapToObj(i -> new Tuple2<>(i, random.nextDouble()))
                .collect(Collectors.toList());

        randomTuples.stream().forEach(System.out::println);
    }
}

package ua.university;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Book> books = List.of(
                new Book("Clean Code", "Robert Martin", 2008, List.of("clean", "practice", "java")),
                new Book("Effective Java", "Joshua Bloch", 2018, List.of("java", "best", "api")),
                new Book("Modern Java", "Nicolai Parlog", 2020, List.of("java", "streams", "records")),
                new Book("Java Concurrency", "Brian Goetz", 2006, List.of("concurrency", "java"))
        );
        System.out.println("Task number 1");

        System.out.println("1 out");
        books.stream()
                .filter(b -> b.year() > 2015)
                .forEach(System.out::println);

        System.out.println("2 out");

        books.stream()
                .map(b -> b.toString().toUpperCase())
                .sorted()
                .forEach(System.out::println);

        System.out.println("3 out");
        List<String> result = books.stream()
                .filter(b -> b.year() > 2015)
                .map(b -> b.title().toUpperCase())
                .sorted()
                .limit(3)
                .toList();

        result.forEach(System.out::println);

        System.out.println("Task number 2");

        System.out.println("1 out");
        books.stream()
                .flatMap(b -> b.tags().stream())
                .distinct()
                .sorted()
                .forEach(System.out::println);

        System.out.println("2 out");

        Map<String, Long> tagFrequency = books.stream()
                .flatMap(b -> b.tags().stream())
                .collect(Collectors.groupingBy(
                        tag -> tag,
                        Collectors.counting()
                ));

        System.out.println(tagFrequency);

        System.out.println("3 out");
        tagFrequency.entrySet().stream()
                .sorted(
                        Comparator.<Map.Entry<String, Long>>comparingLong(e -> e.getValue()).reversed()
                                .thenComparing(Map.Entry::getKey)
                )
                .limit(3)
                .forEach(e -> System.out.println(e.getKey() + " : " + e.getValue()));

        System.out.println("Task number 3");

        List<Sale> sales = List.of(
                new Sale("a@ex.com", "Tea", 120),
                new Sale("b@ex.com", "Cake", 200),
                new Sale("a@ex.com", "Tea", 120),
                new Sale("c@ex.com", "Coffee", 150),
                new Sale("b@ex.com", "Cake", 200)
        );
        System.out.println("1 out");

        Map<String, Integer> revenueByProduct = sales.stream()
                .collect(Collectors.toMap(
                        Sale::product,
                        Sale::cents,
                        Integer::sum
                ));

        revenueByProduct.forEach((k, v) ->
                System.out.println(k + " : " + v));


        System.out.println("2 out");
        Map<String, Long> transactionsByCustomer = sales.stream()
                .collect(Collectors.groupingBy(
                        Sale::customerEmail,
                        Collectors.counting()
                ));

        transactionsByCustomer.forEach((k, v) ->
                System.out.println(k + " : " + v));

        System.out.println("Task number 4");

        List<Result> results = List.of(
                new Success("OK"),
                new Failure("Error 1"),
                new Success("Done"),
                new Failure("Error 2"),
                new Failure("Error 3")
        );

        System.out.println("1 out");
        long successCount = results.stream()
                .filter(r -> r instanceof Success)
                .count();

        long failureCount = results.stream()
                .filter(r -> r instanceof Failure)
                .count();

        System.out.println("Success: " + successCount);
        System.out.println("Failure: " + failureCount);


        System.out.println("2 out");
        List<String> failureMessages = results.stream()
                .filter(r -> r instanceof Failure)
                .map(r -> ((Failure) r).message())
                .toList();

        failureMessages.forEach(System.out::println);

        System.out.println("Task number 5");

        System.out.println("1 out");
        Map<Boolean, List<Book>> recentVsOld = books.stream()
                .collect(Collectors.partitioningBy(b -> b.year() > 2015));

        System.out.println("true:");
        recentVsOld.get(true).forEach(System.out::println);


        System.out.println("false:");
        recentVsOld.get(false).forEach(System.out::println);


        System.out.println("2 out");
        Map<String, Integer> revenueSorted = sales.stream()
                .collect(Collectors.toMap(
                        Sale::product,
                        Sale::cents,
                        Integer::sum,
                        TreeMap::new
                ));

        revenueSorted.forEach((k, v) ->
                System.out.println(k + " : " + v));

    }
}
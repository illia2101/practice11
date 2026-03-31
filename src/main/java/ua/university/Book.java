package ua.university;

import java.util.List;

public record Book(String title, String author, int year, List<String> tags) {}

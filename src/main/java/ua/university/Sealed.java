package ua.university;

sealed interface Result permits Success, Failure {}

record Success(String message) implements Result {}

record Failure(String message) implements Result {}

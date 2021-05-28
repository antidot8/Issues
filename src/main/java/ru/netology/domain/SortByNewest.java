package ru.netology.domain;

import java.util.Comparator;

public class SortByNewest implements Comparator<Issue> {
    public int compare(Issue o1, Issue o2) {
        return o2.getId() - o1.getId();
    }
}
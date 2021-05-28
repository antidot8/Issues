package ru.netology.domain;

import java.util.Comparator;

public class SortByOldest implements Comparator<Issue> {
    public int compare(Issue o1, Issue o2) {
        return o1.getId() - o2.getId();
    }
}
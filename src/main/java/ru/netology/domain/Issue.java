package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Issue implements Comparable<Issue> {
    private int id;
    private String title;
    private Set<Author> author = new HashSet<>();
    private Set<Assignee> assignee = new HashSet<>();
    private Set<Label> label = new HashSet<>();
    private boolean opened;

    @Override
    public int compareTo(Issue o) {
        return id - o.id;
    }

    @Data
    public class Label {
    }

    @Data
    public class Author {
    }

    @Data
    public class Assignee {
    }
}
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
    private Set<String> author = new HashSet<>();
    private Set<String> assignee = new HashSet<>();
    private Set<String> label = new HashSet<>();
    private boolean opened;

    public Issue(Set<String> author) {
        this.author = author;
    }

    @Override
    public int compareTo(Issue o) {
        return id - o.id;
    }
}
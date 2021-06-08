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

    public Set<String> getAuthors() {
        return author;
    }

    @Override
    public int compareTo(Issue o) {
        return id - o.id;
    }

    public Set<String> getLabels() {
        return label;
    }

    public Set<String> getAssignees() {
        return assignee;
    }
}
package ru.netology.manager;

import ru.netology.domain.Issue;
import ru.netology.domain.SortByNewest;
import ru.netology.domain.SortByOldest;
import ru.netology.repository.IssueRepository;

import java.util.ArrayList;
import java.util.Set;
import java.util.function.Predicate;

public class IssueManager {
    private final IssueRepository repository;

    public IssueManager(IssueRepository repository) {
        this.repository = repository;
    }

    public void add(Issue issue) {
        repository.save(issue);
    }

    public ArrayList<Issue> findOpened() {
        ArrayList<Issue> tmp = new ArrayList<>();
        for (Issue issue : repository.getAll()) {
            if (issue.isOpened()) {
                tmp.add(issue);
            }
        }
        return tmp;
    }

    public ArrayList<Issue> findClosed() {
        ArrayList<Issue> tmp = new ArrayList<>();
        for (Issue issue : repository.getAll()) {
            if (!issue.isOpened()) {
                tmp.add(issue);
            }
        }
        return tmp;
    }

    public ArrayList<Issue> filterByAuthor(String author, Predicate<String> predicate) {
        ArrayList<Issue> tmp = new ArrayList<>();
        for (Issue issue : repository.getAll()) {
            if (predicate.test(author)) {
                tmp.add(issue);
            }
        }
        return tmp;
    }



    public ArrayList<Issue> filterByLabel(Set<String> label) {
        ArrayList<Issue> tmp = new ArrayList<>();
        for (Issue issue : repository.getAll()) {
            if (issue.getLabel().equals(label)) {
                tmp.add(issue);
            }
        }
        return tmp;
    }

    public ArrayList<Issue> filterByAssignee(Set<String> assignee) {
        ArrayList<Issue> tmp = new ArrayList<>();
        for (Issue issue : repository.getAll()) {
            if (issue.getAssignee().equals(assignee)) {
                tmp.add(issue);
            }
        }
        return tmp;
    }

    public ArrayList<Issue> sortByNewest() {
        SortByNewest sortByNewest = new SortByNewest();
        ArrayList<Issue> tmp = repository.getAll();
        tmp.sort(sortByNewest);
        return tmp;
    }

    public ArrayList<Issue> sortByOldest() {
        SortByOldest sortByOldest = new SortByOldest();
        ArrayList<Issue> tmp = repository.getAll();
        tmp.sort(sortByOldest);
        return tmp;
    }

    public void openIssue(Issue issue) {
        repository.openIssue(issue.getId());
    }

    public void closeIssue(Issue issue) {
        repository.closeIssue(issue.getId());
    }

    public ArrayList<Issue> findAll() {
        return repository.getAll();
    }
}
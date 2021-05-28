package ru.netology.manager;

import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Set;

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
            if (issue.isOpened()) {
            } else {
                tmp.add(issue);
            }
        }
        return tmp;
    }

    public ArrayList<Issue> filterByAuthor(Set<Issue.Author> author) {
        ArrayList<Issue> tmp = new ArrayList<>();
        for (Issue issue : repository.getAll()) {
            if (issue.getAuthor() == author) {
                tmp.add(issue);
            }
        }
        return tmp;
    }

    public ArrayList<Issue> filterByLabel(Set<Issue.Label> label) {
        ArrayList<Issue> tmp = new ArrayList<>();
        for (Issue issue : repository.getAll()) {
            if (issue.getLabel() == label) {
                tmp.add(issue);
            }
        }
        return tmp;
    }

    public ArrayList<Issue> filterByAssignee(Set<Issue.Assignee> assignee) {
        ArrayList<Issue> tmp = new ArrayList<>();
        for (Issue issue : repository.getAll()) {
            if (issue.getAssignee() == assignee) {
                tmp.add(issue);
            }
        }
        return tmp;
    }

    public ArrayList<Issue> sortByNewest(Issue issue, Comparator<Issue> sortByNewest) {
        ArrayList<Issue> tmp = repository.getAll();
        tmp.sort(sortByNewest);
        return tmp;
    }

    public ArrayList<Issue> sortByOldest(Issue issue, Comparator<Issue> sortByOldest) {
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
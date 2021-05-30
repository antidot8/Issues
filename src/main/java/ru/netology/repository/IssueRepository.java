package ru.netology.repository;

import ru.netology.domain.NotFoundException;
import ru.netology.domain.Issue;

import java.util.ArrayList;

public class IssueRepository {
    ArrayList<Issue> issues = new ArrayList<>();

    public void save(Issue issue) {
        issues.add(issue);
    }

    public ArrayList<Issue> getAll() {
        return issues;
    }

    public void openIssue(int id) {
        for (Issue issue : issues)
            if (findById(id) != null) {
                if (issue.getId() == id) {
                    issue.setOpened(true);
                }
            } else {
                throw new NotFoundException("Issue # " + id + " нельзя открыть");
            }
    }

    public void closeIssue(int id) {
        for (Issue issue : issues)
            if (findById(id) != null) {
                if (issue.getId() == id) {
                    issue.setOpened(false);
                }
            } else {
                throw new NotFoundException("Issue # " + id + " нельзя закрыть");
            }
    }

    public Issue findById(int id) {
        for (Issue issue : issues) {
            if (issue.getId() == id) {
                return issue;
            }
        }
        return null;
    }
}
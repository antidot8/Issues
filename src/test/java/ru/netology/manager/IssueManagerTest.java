package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
import ru.netology.domain.SortByNewest;
import ru.netology.domain.SortByOldest;
import ru.netology.repository.IssueRepository;

import java.util.Arrays;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class IssueManagerTest {
    private final IssueRepository repository = new IssueRepository();
    private final IssueManager manager = new IssueManager(repository);
    private final SortByNewest sortByNewest = new SortByNewest();
    private final SortByOldest sortByOldest = new SortByOldest();
    private final Issue issue1 = new Issue(1, "title1", "author1", "assignee1", "label1", true);
    private final Issue issue2 = new Issue(2, "title2", "author2", "assignee1", "label2", false);
    private final Issue issue3 = new Issue(3, "title3", "author3", "assignee1", "label2", true);
    private final Issue issue4 = new Issue(4, "title4", "author4", "assignee2", "label3", true);
    private final Issue issue5 = new Issue(5, "title5", "author5", "assignee3", "label3", true);
    private final Issue issue6 = new Issue(6, "title6", "author5", "assignee4", "label4", false);


    @BeforeEach
    void setUp() {
        manager.add(issue1);
        manager.add(issue2);
        manager.add(issue3);
        manager.add(issue4);
        manager.add(issue5);
        manager.add(issue6);
    }
}
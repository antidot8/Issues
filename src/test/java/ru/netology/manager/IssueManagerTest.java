package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
import ru.netology.domain.SortByNewest;
import ru.netology.domain.SortByOldest;
import ru.netology.repository.IssueRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IssueManagerTest {
    private final IssueRepository repository = new IssueRepository();
    private final IssueManager manager = new IssueManager(repository);
    private final SortByNewest sortByNewest = new SortByNewest();
    private final SortByOldest sortByOldest = new SortByOldest();
    Set<String> author1 = Set.of("author1");
    Set<String> author2 = Set.of("author2");
    Set<String> author3 = Set.of("author3");
    Set<String> author4 = Set.of("author4");
    Set<String> assignee1 = Set.of("assignee1");
    Set<String> assignee2 = Set.of("assignee2");
    Set<String> assignee3 = Set.of("assignee3");
    Set<String> assignee4 = Set.of("assignee4");
    Set<String> label1 = Set.of("label1");
    Set<String> label2 = Set.of("label2");
    Set<String> label3 = Set.of("label3");
    Set<String> label4 = Set.of("label4");
    Set<String> label5 = Set.of("label5");
    private final Issue issue1 = new Issue(1, "title1", author1, assignee1, label1, true);
    private final Issue issue2 = new Issue(2, "title2", author2, assignee1, label2, false);
    private final Issue issue3 = new Issue(3, "title3", author3, assignee1, label2, true);
    private final Issue issue4 = new Issue(4, "title4", author4, assignee2, label5, true);
    private final Issue issue5 = new Issue(5, "title5", author2, assignee3, label3, true);
    private final Issue issue6 = new Issue(6, "title6", author4, assignee4, label4, false);


    @BeforeEach
    void setUp() {
        manager.add(issue1);
        manager.add(issue2);
        manager.add(issue3);
        manager.add(issue4);
        manager.add(issue5);
        manager.add(issue6);
    }

    @Test
    public void showAll () {
        ArrayList<Issue> expected = manager.findAll();
        ArrayList actual = new ArrayList(Arrays.asList(issue1, issue2, issue3, issue4, issue5, issue6));
        assertEquals(expected, actual);
    }

    @Test
    public void filteredByAuthor () {
        ArrayList<Issue> expected = manager.filterByAuthor(author2);
        ArrayList actual = new ArrayList(Arrays.asList(issue2, issue5));
        assertEquals(expected, actual);
    }
}
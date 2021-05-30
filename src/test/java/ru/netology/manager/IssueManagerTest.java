package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
import ru.netology.domain.NotFoundException;
import ru.netology.repository.IssueRepository;

import java.util.*;
import java.util.function.Predicate;

import static java.util.function.Predicate.isEqual;
import static org.junit.jupiter.api.Assertions.*;

public class IssueManagerTest {
    private final IssueRepository repository = new IssueRepository();
    private final IssueManager manager = new IssueManager(repository);
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
    private final Issue issue4 = new Issue(4, "title4", author4, assignee2, label5, false);
    private final Issue issue5 = new Issue(5, "title5", author2, assignee3, label3, true);
    private final Issue issue6 = new Issue(6, "title6", author4, assignee4, label4, false);


    @BeforeEach
    void setUp() {
        manager.add(issue1);
        manager.add(issue2);
        manager.add(issue3);
        manager.add(issue4);
        manager.add(issue5);
    }

//FixMe ненужный тест
    @Test
    public void showAll () {
        ArrayList<Issue> expected = new ArrayList<>(Arrays.asList(issue1, issue2, issue3, issue4, issue5));
        ArrayList<Issue> actual = manager.findAll();
        assertEquals(expected, actual);
    }

//    @Test
//    public void filteredByAuthor () {
//        ArrayList<Issue> expected = new ArrayList<>(Arrays.asList(issue2, issue5));
//        ArrayList<Issue> search = new ArrayList<>(Arrays.asList(author2));
//        Predicate<Set<String>> author = x -> x.equals(search);
//        ArrayList<Issue> actual = manager.filterByAuthor(author);
//        assertEquals(expected, actual);
//    }

    @Test
    public void findClosedIssue () {
        ArrayList<Issue> expected = new ArrayList<>(Arrays.asList(issue2, issue4));
        ArrayList<Issue> actual = manager.findClosed();
        assertEquals(expected, actual);
    }

    @Test
    public void findClosedIssueIfClosedNotExist () {
        ArrayList<Issue> expected = new ArrayList<>(Collections.emptyList());
        manager.openIssue(issue2);
        manager.openIssue(issue4);
        ArrayList<Issue> actual = manager.findClosed();
        assertEquals(expected, actual);
    }

    @Test
    public void findOpenedIssue () {
        ArrayList<Issue> expected = new ArrayList<>(Arrays.asList(issue1, issue3, issue5));
        ArrayList<Issue> actual = manager.findOpened();
        assertEquals(expected, actual);
    }

    @Test
    public void findOpenedIssueIfOpenedNotExist () {
        ArrayList<Issue> expected = new ArrayList<>(Collections.emptyList());
        manager.closeIssue(issue1);
        manager.closeIssue(issue3);
        manager.closeIssue(issue5);
        ArrayList<Issue> actual = manager.findOpened();
        assertEquals(expected, actual);
    }

    @Test
    public void closeIssue () {
        ArrayList<Issue> expected = new ArrayList<>(Arrays.asList(issue2, issue3, issue4));
        manager.closeIssue(issue3);
        ArrayList<Issue> actual = manager.findClosed();
        assertEquals(expected, actual);
    }

    @Test
    public void closeIssueIfAlreadyClosed () {
        ArrayList<Issue> expected = new ArrayList<>(Arrays.asList(issue2, issue4));
        manager.closeIssue(issue2);
        ArrayList<Issue> actual = manager.findClosed();
        assertEquals(expected, actual);
    }

    @Test
    public void closeIssueIfNotExist () {
        assertThrows(NotFoundException.class, () -> manager.closeIssue(issue6));
    }

    @Test
    public void openIssue () {
        ArrayList<Issue> expected = new ArrayList<>(Arrays.asList(issue1, issue2, issue3, issue5));
        manager.openIssue(issue2);
        ArrayList<Issue> actual = manager.findOpened();
        assertEquals(expected, actual);
    }

    @Test
    public void openIssueIfAlreadyOpened () {
        ArrayList<Issue> expected = new ArrayList<>(Arrays.asList(issue1, issue3, issue5));
        manager.openIssue(issue1);
        ArrayList<Issue> actual = manager.findOpened();
        assertEquals(expected, actual);
    }

    @Test
    public void openIssueIfNotExist () {
        assertThrows(NotFoundException.class, () -> manager.openIssue(issue6));
    }

    @Test
    public void filteredByLabel () {
        ArrayList<Issue> expected = new ArrayList<>(Arrays.asList(issue2, issue3));
        ArrayList<Issue> actual = manager.filterByLabel(label2);
        assertEquals(expected, actual);
    }

    @Test
    public void filteredByLabelIfNotExist () {
        ArrayList<Issue> expected = new ArrayList<>(Collections.emptyList());
        Set<String> Label6 = Set.of("Label6");
        ArrayList<Issue> actual = manager.filterByAssignee(Label6);
        assertEquals(expected, actual);
    }

    @Test
    public void filteredByAssignee () {
        ArrayList<Issue> expected = new ArrayList<>(Arrays.asList(issue1, issue2, issue3));
        ArrayList<Issue> actual = manager.filterByAssignee(assignee1);
        assertEquals(expected, actual);
    }

    @Test
    public void filteredByAssigneeIfNotExist () {
        ArrayList<Issue> expected = new ArrayList<>(Collections.emptyList());
        Set<String> assignee5 = Set.of("assignee5");
        ArrayList<Issue> actual = manager.filterByAssignee(assignee5);
        assertEquals(expected, actual);
    }

    @Test
    public void SortByOldest () {
        ArrayList<Issue> expected = new ArrayList<>(Arrays.asList(issue1, issue2, issue3, issue4, issue5));
        ArrayList<Issue> actual = manager.sortByOldest();
        assertEquals(expected, actual);
    }

    @Test
    public void SortByNewest () {
        ArrayList<Issue> expected = new ArrayList<>(Arrays.asList(issue5, issue4, issue3, issue2, issue1));
        ArrayList<Issue> actual = manager.sortByNewest();
        assertEquals(expected, actual);
    }
}
package ru.project.voting.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.project.voting.model.Vote;
import ru.project.voting.repository.cruds.CrudVoteRepository;
import ru.project.voting.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static ru.project.voting.TestData.*;


class VoteServiceTest extends AbstractServiceTest{

    @Autowired
    private VoteService voteService;

    @Autowired
    private CrudVoteRepository crudVoteRepository;

    @Test
    void update() throws Exception {
        log.info("updateTest");
        Vote updated = getUpdated();
        voteService.update(updated, USER_ID1);
        assertEquals(voteService.get(USER_ID1, TEST_DATE_USER_1), updated);
    }

    @Test
    void updateNotFoundWrongUserId() throws Exception {
        log.info("updateNotFoundWrongUserIdTest");
        Vote updated = getUpdated();
        voteService.update(updated, USER_ID2);
        assertEquals(crudVoteRepository.findAll(), Arrays.asList(VOTE_1, updated));
    }

    @Test
    void create() {
        log.info("createTest");
        Vote ceated = getCreated();
        voteService.create(new Vote(USER_3, MENU_2, LocalDate.now()), USER_ID3);
        assertEquals(ceated, voteService.get(USER_ID3, LocalDate.now()));
    }

    @Test
    void get() {
        log.info("getTest");
        Vote actual = voteService.get(USER_ID2, TEST_DATE_USER_2);
        Vote expected = VOTE_1;
        expected.setId(VOTE_ID1);
        assertEquals(actual, expected);
    }

    @Test
    void getNotFoundWrongUserId() throws Exception {
        log.info("getNotFoundWrongUserIdTest");
        boolean expected = false;
        Vote received = voteService.get(USER_ID1, TEST_DATE_USER_2);
        if(received == null) expected = true;
        assertEquals(true, expected);
    }

    @Test
    void getNotFoundWrongDate() throws Exception {
        log.info("getNotFoundWrongDateTest");
        boolean expected = false;
        Vote received = voteService.get(USER_ID2, LocalDate.now());
        if(received == null) expected = true;
        assertEquals(true, expected);
    }


    @Test
    void delete() throws Exception {
        log.info("deleteTest");
        voteService.delete(VOTE_ID1, USER_ID2);
        Vote vote = VOTE_2;
        vote.setId(VOTE_ID2);
        assertEquals(crudVoteRepository.findAll(), Arrays.asList(vote));
    }

    @Test
    void deleteNotFoundWrongUserId() throws Exception {
        log.info("deleteNotFoundWrongUserIdTest");
        assertThrows(NotFoundException.class, () ->
                voteService.delete(VOTE_ID1, 1));
    }

    @Test
    void deleteNotFoundWrongId() throws Exception {
        log.info("deleteNotFoundWrongIdTest");
        assertThrows(NotFoundException.class, () ->
                voteService.delete(1, USER_ID2));
    }

    @Test
    void saveOrUpdateUserVoteVoiceExist() throws Exception {
        log.info("saveOrUpdateUserVoteVoiceExistTest");
        Vote updated = getUpdated();
        voteService.saveOrUpdateUserVote(USER_ID1, MENU_1, TEST_SEPARATED_TIME_MAX);
        assertEquals(voteService.get(USER_ID1, TEST_DATE_USER_1), updated);
    }

    @Test
    void saveOrUpdateUserVoteVoiceNotExist() throws Exception {
        log.info("saveOrUpdateUserVoteVoiceNotExistTest");
        Vote created = getCreated();
        voteService.saveOrUpdateUserVote(USER_ID3, MENU_2, TEST_SEPARATED_TIME_MAX);
        assertEquals(voteService.get(USER_ID3, LocalDate.now()), created);
    }

    @Test
    void cancelVote() {
        log.info("cancelVoteTest");
        voteService.cancelVote(USER_ID2, TEST_DATE_USER_2, TEST_SEPARATED_TIME_MAX);
        Vote vote = VOTE_2;
        vote.setId(VOTE_ID2);
        assertEquals(crudVoteRepository.findAll(), Arrays.asList(vote));
    }

    @Test
    void cancelVoteAfterSeparatedTime() {
        log.info("cancelVoteAfterSeparatedTimeTest");
        voteService.cancelVote(USER_ID2, TEST_DATE_USER_2, TEST_SEPARATED_TIME_MIN);
        assertEquals(crudVoteRepository.findAll(), Arrays.asList(VOTE_1, VOTE_2));
    }

    @Test
    void cancelVoteVoiceNotFound() {
        log.info("cancelVoteVoiceNotFoundTest");
        voteService.cancelVote(USER_ID1, TEST_DATE_USER_2, TEST_SEPARATED_TIME_MAX);
        assertEquals(crudVoteRepository.findAll(), Arrays.asList(VOTE_1, VOTE_2));
    }
}
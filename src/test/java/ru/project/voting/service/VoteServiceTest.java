package ru.project.voting.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.project.voting.model.Vote;
import ru.project.voting.repository.CrudVoteRepository;
import ru.project.voting.util.exception.NotFoundException;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.project.voting.TestData.*;


class VoteServiceTest extends AbstractServiceTest{

    @Autowired
    VoteService voteService;

    @Autowired
    CrudVoteRepository crudVoteRepository;

    @Test
    void update() throws Exception {
        Vote updated = getUpdated();
        voteService.update(updated, USER_ID1);
        assertMatch(voteService.get(USER_ID1, TEST_DATE_USER_1), updated);
    }

    @Test
    void updateNotFoundWrongUserID() throws Exception {
        Vote updated = getUpdated();
        voteService.update(updated, USER_ID2);
        assertMatch(crudVoteRepository.findAll(), VOTE_1, updated);
    }

    @Test
    void create() {
        Vote ceated = getCreated();
        voteService.create(new Vote(USER_3, MENU_2, LocalDate.now()), USER_ID3);
        assertMatch(ceated, voteService.get(USER_ID3, LocalDate.now()));
    }

    @Test
    void get() throws Exception{
        Vote actual = voteService.get(USER_ID2, TEST_DATE_USER_2);
        Vote expected = VOTE_1;
        expected.setId(VOTE_ID1);
        assertMatch(actual, expected);
    }

    @Test
    void getNotFoundWrongUserId() throws Exception {
        boolean expected = false;
        Vote received = voteService.get(USER_ID1, TEST_DATE_USER_2);
        if(received == null) expected = true;
        assertThat(true).isEqualTo(expected);
    }

    @Test
    void getNotFoundWrongDate() throws Exception {
        boolean expected = false;
        Vote received = voteService.get(USER_ID2, LocalDate.now());
        if(received == null) expected = true;
        assertThat(true).isEqualTo(expected);
    }


    @Test
    void delete() throws Exception {
        voteService.delete(VOTE_ID1, USER_ID2);
        Vote vote = VOTE_2;
        vote.setId(VOTE_ID2);
        assertMatch(crudVoteRepository.findAll(), vote);
    }

    @Test
    void deleteNotFoundWrongUserId() throws Exception {
        assertThrows(NotFoundException.class, () ->
                voteService.delete(VOTE_ID1, 1));
    }

    @Test
    void deleteNotFoundWrongId() throws Exception {
        assertThrows(NotFoundException.class, () ->
                voteService.delete(1, USER_ID2));
    }

    @Test
    void saveOrUpdateUserVoteVoiceExist() throws Exception {
        Vote updated = getUpdated();
        voteService.saveOrUpdateUserVote(USER_ID1, MENU_1, TEST_SEPARATED_TIME_MAX);
        assertMatch(voteService.get(USER_ID1, TEST_DATE_USER_1), updated);
    }

    @Test
    void saveOrUpdateUserVoteVoiceNotExist() throws Exception {
        Vote created = getCreated();
        voteService.saveOrUpdateUserVote(USER_ID3, MENU_2, TEST_SEPARATED_TIME_MAX);
        assertMatch(voteService.get(USER_ID3, LocalDate.now()), created);
    }

    @Test
    void cancelVote() {
        voteService.cancelVote(USER_ID2, TEST_DATE_USER_2, TEST_SEPARATED_TIME_MAX);
        Vote vote = VOTE_2;
        vote.setId(VOTE_ID2);
        assertMatch(crudVoteRepository.findAll(), vote);
    }

    @Test
    void cancelVoteAfterSeparatedTime() {
        voteService.cancelVote(USER_ID2, TEST_DATE_USER_2, TEST_SEPARATED_TIME_MIN);
        assertMatch(crudVoteRepository.findAll(), VOTE_1, VOTE_2);
    }

    @Test
    void cancelVoteVoiceNotFound() {
        voteService.cancelVote(USER_ID1, TEST_DATE_USER_2, TEST_SEPARATED_TIME_MAX);
        assertMatch(crudVoteRepository.findAll(), VOTE_1, VOTE_2);
    }
}
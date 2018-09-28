package ru.project.voting.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.project.voting.model.Menu;
import ru.project.voting.model.User;
import ru.project.voting.model.Vote;
import ru.project.voting.repository.VoteRepository;
import ru.project.voting.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;

import static ru.project.voting.web.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Transactional
    public void saveOrUpdateUserVote(int userId, Menu menu, LocalTime separatedTime) {
        Assert.notNull(menu, "menu can`t be null");
           Vote vote = get(userId, LocalDate.now());
        if (vote != null){ // if voice exist in db...
            if(LocalTime.now().isBefore(separatedTime)) {//and current time before separatedTime
                vote.setMenu(menu);//set menu in voice witch was getting from db
                update(vote, userId);  //update our voice
            }
        }
        else { // in other situations save a new voice
            create(new Vote(new User(), menu, LocalDate.now()), userId); // user will be setup for writing voice in repository method 'save'
        }
    }

    //if the user changed his mind to vote  for any menu. Just cancel.
    @Transactional
    public void cancelVote(int userId, LocalDate date, LocalTime time) {
        Vote vote = get(userId, date);
        if (vote != null && LocalTime.now().isBefore(time)) {
            delete(vote.getId(), userId);
        }
    }


    public Vote update(Vote vote, int userId) {
        Assert.notNull(vote, "Vote can`t be null");
        return voteRepository.save(vote, userId);
    }


    public Vote create(Vote vote, int userId) {
        Assert.notNull(vote, "Vote can`t be null");
        return voteRepository.save(vote, userId);
    }


    public Vote get(int userId, LocalDate date) {
        return voteRepository.get(userId, date);
    }


    public void delete(int id, int userId) throws NotFoundException {
        checkNotFoundWithId(voteRepository.delete(id, userId), id);
    }
}

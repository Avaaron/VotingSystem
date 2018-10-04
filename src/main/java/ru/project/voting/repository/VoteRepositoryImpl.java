package ru.project.voting.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.project.voting.model.Vote;
import ru.project.voting.repository.cruds.CrudUserRepository;
import ru.project.voting.repository.cruds.CrudVoteRepository;

import java.time.LocalDate;

@Repository
public class VoteRepositoryImpl implements VoteRepository{

    @Autowired
    protected CrudVoteRepository crudVoteRepository;

    @Autowired
    protected CrudUserRepository crudUserRepository;

    @Transactional
    @Override
    public Vote save(Vote vote, int userId) {
        if(!vote.isNew() && get(userId, vote.getDate()) == null) {
            return  null;
        }
        vote.setUser(crudUserRepository.getOne(userId));
        return crudVoteRepository.save(vote);

    }

    @Override
    public Vote get(int userId, LocalDate date) {
        return crudVoteRepository.getForUserAndDate(userId, date).filter(vote ->
                (vote.getUser().getId() == userId && vote.getDate().compareTo(date) == 0))
                .orElse(null);
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudVoteRepository.delete(id, userId) != 0;
    }
}

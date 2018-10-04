package ru.project.voting.repository;

import ru.project.voting.model.Restaurant;
import ru.project.voting.model.Vote;

import java.time.LocalDate;;

public interface VoteRepository {

    Vote save(Vote vote, int userId);

    boolean delete(int Id, int userId);

    Vote get(int userId, LocalDate date);
}

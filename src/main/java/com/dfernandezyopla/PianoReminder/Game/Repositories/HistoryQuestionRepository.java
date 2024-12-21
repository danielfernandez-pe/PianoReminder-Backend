package com.dfernandezyopla.PianoReminder.Game.Repositories;

import com.dfernandezyopla.PianoReminder.Game.Entities.HistoryQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryQuestionRepository extends JpaRepository<HistoryQuestion, Long> {
}

package de.workshop.smells.input_and_output;

import java.util.List;

public class ScoreService {

    public int addScoreAndReturn(List<Integer> scoreHistory, int newScore) {
        scoreHistory.add(newScore);
        return newScore;
    }
}

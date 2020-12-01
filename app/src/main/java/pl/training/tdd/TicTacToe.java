package pl.training.tdd;

import java.util.HashSet;
import java.util.Set;

public class TicTacToe {

    public static final int MINIMAL_FIELD_INDEX = 1;
    public static final int MAXIMUM_FIELD_INDEX = 9;

    private static final Set<Set<Integer>> winningSequences = Set.of(
            Set.of(1, 2, 3), Set.of(4, 5, 6), Set.of(7, 8, 9),
            Set.of(1, 4, 7), Set.of(2, 5, 8), Set.of(3, 6, 9),
            Set.of(1, 5, 9), Set.of(3, 5, 7)
    );

    private final Set<Integer> takenFields = new HashSet<>();
    private Player currentPlayer = Player.CIRCLE;

    boolean takeField(int index) {
        if (isFieldIndexOutOfRange(index)) {
            return false;
        }
        currentPlayer = currentPlayer.reverse();
        return takenFields.add(index);
    }

    private boolean isFieldIndexOutOfRange(int index) {
        return MINIMAL_FIELD_INDEX > index || MAXIMUM_FIELD_INDEX < index;
    }

    public boolean isGameOver() {
        return allFieldsAreTaken() || containsWinningSequence();
    }

    private boolean allFieldsAreTaken() {
        return takenFields.size() == MAXIMUM_FIELD_INDEX;
    }

    private boolean containsWinningSequence() {
        return winningSequences.stream().anyMatch(takenFields::containsAll);
    }

    public Player getPlayer() {
        return currentPlayer;
    }

}

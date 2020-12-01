package pl.training.tdd;

import java.util.*;
import java.util.stream.Collectors;

public class TicTacToe {

    public static final int MINIMAL_FIELD_INDEX = 1;
    public static final int MAXIMUM_FIELD_INDEX = 9;

    private static final Set<Set<Integer>> winningSequences = Set.of(
            Set.of(1, 2, 3), Set.of(4, 5, 6), Set.of(7, 8, 9),
            Set.of(1, 4, 7), Set.of(2, 5, 8), Set.of(3, 6, 9),
            Set.of(1, 5, 9), Set.of(3, 5, 7)
    );
    private final Map<Integer, Player> takenFields = new HashMap<>();

    private Player currentPlayer = Player.CIRCLE;

    boolean takeField(int index) {
        if (isFieldIndexOutOfRange(index) || isFieldTaken(index)) {
            return false;
        }
        currentPlayer = currentPlayer.reverse();
        takenFields.put(index, currentPlayer);
        return true;
    }

    private boolean isFieldTaken(int fieldIndex) {
        return takenFields.get(fieldIndex) != null;
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
        var currentPlayerFields = takenFields.values().stream()
                .filter(player -> player == currentPlayer)
                .collect(Collectors.toList());
        return winningSequences.stream().anyMatch(currentPlayerFields::containsAll);
    }

    public Player getPlayer() {
        return currentPlayer;
    }

    public Optional<Player> getWinner() {
        return Optional.empty();
    }

}

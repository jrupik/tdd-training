package pl.training.tdd;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static pl.training.tdd.TicTacToe.MAXIMUM_FIELD_INDEX;
import static pl.training.tdd.TicTacToe.MINIMAL_FIELD_INDEX;

public class TicTacToeTest {

    private final TicTacToe ticTacToe = new TicTacToe();

    @Test
    void free_field_can_be_taken() {
        assertTrue(ticTacToe.takeField(MINIMAL_FIELD_INDEX));
    }

    @Test
    void occupied_field_cannot_be_taken() {
        ticTacToe.takeField(MINIMAL_FIELD_INDEX);
        assertFalse(ticTacToe.takeField(MINIMAL_FIELD_INDEX));
    }

    @ValueSource(ints = {1, 1000})
    @ParameterizedTest
    void field_with_index_below_minimal_index_cannot_be_taken(int delta) {
        assertFalse(ticTacToe.takeField(MINIMAL_FIELD_INDEX - delta));
    }

    @ValueSource(ints = {1, 1000})
    @ParameterizedTest
    void field_with_index_above_maximum_index_cannot_be_taken(int delta) {
        assertFalse(ticTacToe.takeField(MAXIMUM_FIELD_INDEX + delta));
    }

    @Test
    void game_ends_when_all_fields_are_taken() {
        for (int index = MINIMAL_FIELD_INDEX; index <= MAXIMUM_FIELD_INDEX; index++) {
            ticTacToe.takeField(index);
        }
        assertTrue(ticTacToe.isGameOver());
    }

    static Stream<Set<Integer>> winningSequences() {
        return Stream.of(
                Set.of(1, 2, 3), Set.of(4, 5, 6), Set.of(7, 8, 9),
                Set.of(1, 4, 7), Set.of(2, 5, 8), Set.of(3, 6, 9),
                Set.of(1, 5, 9), Set.of(3, 5, 7)
        );
    }

    // todo
    @Test
    void game_ends_when_player_took_winning_sequence() {
        ticTacToe.takeField(1);
        ticTacToe.takeField(4);
        ticTacToe.takeField(2);
        ticTacToe.takeField(5);
        ticTacToe.takeField(3);
        assertTrue(ticTacToe.isGameOver());
    }

    @Test
    void the_first_player_to_take_the_winning_sequence_wins_the_game() {
        Player player = ticTacToe.getPlayer();
        ticTacToe.takeField(1);
        ticTacToe.takeField(4);
        ticTacToe.takeField(2);
        ticTacToe.takeField(5);
        ticTacToe.takeField(3);
        assertEquals(player, ticTacToe.getWinner());
    }

    @Test
    void the_player_changes_when_after_field_is_taken() {
        Player firstPlayer = ticTacToe.getPlayer();
        ticTacToe.takeField(MINIMAL_FIELD_INDEX);
        assertNotEquals(firstPlayer, ticTacToe.getPlayer());
    }

    @Test
    void the_player_does_not_change_when_after_field_has_not_been_taken() {
        ticTacToe.takeField(MINIMAL_FIELD_INDEX);
        Player firstPlayer = ticTacToe.getPlayer();
        ticTacToe.takeField(MINIMAL_FIELD_INDEX);
        assertNotEquals(firstPlayer, ticTacToe.getPlayer());
    }

}

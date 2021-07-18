package com.dydu.hoover;

import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class RoomTest {

    private final String[][] matrix = new String[][]{
            new String[]{"M", " ", "M"},
            new String[]{" ", "M", "M"}
    };

    @Test
    public void shouldBuildRoomFromMatrix() {
        Room room = Room.build(matrix);

        assertThat(room.spots).containsExactlyInAnyOrder(
                new Spot(new Position(0, 0), false),
                new Spot(new Position(0, 1), true),
                new Spot(new Position(0, 2), false),
                new Spot(new Position(1, 0), true),
                new Spot(new Position(1, 1), false),
                new Spot(new Position(1, 2), false)
        );

        Spot spot = room.spots.stream().filter(s -> s.getPosition().equals(new Position(0, 1))).findFirst().orElseThrow();
        assertThat(spot.getNeighbors()).containsExactlyInAnyOrder(
                new Spot(new Position(0, 0), false),
                new Spot(new Position(0, 2), false),
                new Spot(new Position(1, 1), false)
        );
    }

    @Test
    public void shouldReturnRandomAccessibleUncleanedSpot() {
        Room room = Room.build(matrix);
        Optional<Spot> randomUncleanedSpot = room.getRandomUncleanedSpot();
        assertThat(randomUncleanedSpot).isPresent();
        assertThat(randomUncleanedSpot.get()).isIn(new Spot(new Position(0, 1), true), new Spot(new Position(1, 0), true));
    }

    @Test
    public void shouldReturnTrueWhenRoomHasBeenCleaned() {
        Room room = Room.build(matrix);

        assertThat(room.hasBeenCompletelyCleaned()).isFalse();

        Optional<Spot> randomUncleanedSpot = room.getRandomUncleanedSpot();
        while (randomUncleanedSpot.isPresent()) {
            randomUncleanedSpot.get().clean();
            randomUncleanedSpot = room.getRandomUncleanedSpot();
        }

        assertThat(room.hasBeenCompletelyCleaned()).isTrue();
    }
}
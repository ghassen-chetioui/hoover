package com.dydu.hoover;

import org.junit.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class SpotTest {

    @Test
    public void shouldReturnAccessibleUncleanedNeighbors() {
        Spot spot = new Spot(new Position(2, 2), true);
        Spot topNeighbor = new Spot(new Position(1, 2), true);
        Spot bottomNeighbor = new Spot(new Position(3, 2), false);
        Spot leftNeighbor = new Spot(new Position(2, 2), true);
        Spot rightNeighbor = new Spot(new Position(2, 3), true);
        rightNeighbor.clean();
        spot.addNeighbor(topNeighbor);
        spot.addNeighbor(bottomNeighbor);
        spot.addNeighbor(leftNeighbor);
        spot.addNeighbor(rightNeighbor);
        Set<Spot> accessibleUncleanedNeighbors = spot.getAccessibleUncleanedNeighbors();

        assertThat(accessibleUncleanedNeighbors).containsExactlyInAnyOrder(topNeighbor, leftNeighbor);

    }
}
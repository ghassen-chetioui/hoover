package com.dydu.hoover;

import java.util.*;
import java.util.stream.Collectors;

public class Room {

    final Set<Spot> spots;

    public Room(Set<Spot> spots) {
        this.spots = spots;
    }

    public Optional<Spot> getRandomUncleanedSpot() {
        List<Spot> uncleanedSpots = this.spots.stream().filter(cell -> cell.isAccessible() && !cell.isCleaned()).collect(Collectors.toList());
        if (uncleanedSpots.isEmpty()) {
            return Optional.empty();
        } else {
            Collections.shuffle(uncleanedSpots);
            return Optional.of(uncleanedSpots.iterator().next());
        }
    }

    public boolean hasBeenCompletelyCleaned() {
        return this.getRandomUncleanedSpot().isEmpty();
    }

    public static Room build(String[][] matrix) {
        Spot[][] spots = new Spot[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                var cell = new Spot(new Position(i, j), " ".equals(matrix[i][j]));
                spots[i][j] = cell;
                if (i > 0) {
                    var left = spots[i - 1][j];
                    cell.addNeighbor(left);
                    left.addNeighbor(cell);
                }
                if (j > 0) {
                    var top = spots[i][j - 1];
                    cell.addNeighbor(top);
                    top.addNeighbor(cell);
                }
            }
        }
        return new Room(Arrays.stream(spots).flatMap(Arrays::stream).collect(Collectors.toSet()));
    }
}

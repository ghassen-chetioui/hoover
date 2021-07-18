package com.dydu.hoover;

import java.util.*;

public class Hoover {

    public List<Spot> clean(Room room) {
        Objects.requireNonNull(room, "Room should not be null");
        List<Spot> path = new ArrayList<>();
        Deque<Spot> stack = new ArrayDeque<>();
        Spot initial = room.getRandomUncleanedSpot().orElseThrow(IllegalArgumentException::new);
        stack.add(initial);
        while (!stack.isEmpty() && !room.hasBeenCompletelyCleaned()) {
            Spot current = stack.peek();
            current.clean();
            path.add(current);
            Set<Spot> getAccessibleUncleanedNeighbors = current.getAccessibleUncleanedNeighbors();
            if (!getAccessibleUncleanedNeighbors.isEmpty()) {
                stack.push(getAccessibleUncleanedNeighbors.iterator().next());
            } else {
                stack.pop();
            }
        }
        return path;
    }
}



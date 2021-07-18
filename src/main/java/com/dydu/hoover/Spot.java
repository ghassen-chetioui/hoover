package com.dydu.hoover;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Spot {

    private final Position position;
    private final boolean accessible;
    private boolean cleaned;
    private final Set<Spot> neighbors;

    public Spot(Position position, boolean accessible) {
        this.position = position;
        this.accessible = accessible;
        this.neighbors = new HashSet<>();
    }

    public Position getPosition() {
        return position;
    }

    public boolean isAccessible() {
        return accessible;
    }

    public boolean isCleaned() {
        return this.cleaned;
    }

    public void addNeighbor(Spot spot) {
        this.neighbors.add(spot);
    }

    public Set<Spot> getNeighbors() {
        return new HashSet<>(this.neighbors);
    }

    public Set<Spot> getAccessibleUncleanedNeighbors() {
        return this.neighbors.stream()
                .filter(n -> n.accessible && !n.cleaned)
                .collect(Collectors.toSet());
    }

    public void clean() {
        this.cleaned = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spot spot = (Spot) o;
        return accessible == spot.accessible && position.equals(spot.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, accessible);
    }
}

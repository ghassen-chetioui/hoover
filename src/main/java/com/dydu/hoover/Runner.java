package com.dydu.hoover;

import com.dydu.hoover.utils.MatrixFileReader;

import java.util.List;

public class Runner {

    public static void main(String[] args) {
        String[][] matrix = new MatrixFileReader().readFile("/hoover1.txt");
        Hoover hoover = new Hoover();
        List<Spot> path = hoover.clean(Room.build(matrix));
        path.forEach(cell -> System.out.println(cell.getPosition()));
    }
}

package com.dydu.hoover;

import com.dydu.hoover.utils.MatrixFileReader;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class HooverTest {


    @Test
    public void shouldCleanAllRoom() {
        Room room1 = Room.build(new MatrixFileReader().readFile("/hoover1.txt"));
        Room room2 = Room.build(new MatrixFileReader().readFile("/maze1.txt"));

        Hoover hoover = new Hoover();

        assertThat(room1.hasBeenCompletelyCleaned()).isFalse();
        hoover.clean(room1);
        assertThat(room1.hasBeenCompletelyCleaned()).isTrue();

        assertThat(room2.hasBeenCompletelyCleaned()).isFalse();
        hoover.clean(room2);
        assertThat(room2.hasBeenCompletelyCleaned()).isTrue();
    }
}
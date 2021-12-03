package com.example.adventofcode2021;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Value;
import org.junit.jupiter.api.Test;


class Day2 extends AbstractDay {

    @Test
    void day2_part1() throws IOException {
        List<Pair> input = file("day2").stream()
                                      .map(line -> {
                                          String[] splitted = line.split(" ");
                                          return new Pair(splitted[0], Integer.parseInt(splitted[1]));
                                      })
                                      .collect(Collectors.toList());


        int horizontal = 0;
        int depth = 0;

        for (Pair pair : input) {
            if (pair.getCommand().equals("forward")) {
                horizontal = horizontal + pair.getValue();
            }
            if (pair.getCommand().equals("down")) {
                depth = depth + pair.getValue();
            }
            if (pair.getCommand().equals("up")) {
                depth = depth - pair.getValue();
            }
        }

        System.out.println(horizontal);
        System.out.println(depth);
        System.out.println("Result: " + (horizontal * depth));
    }

    @Test
    void day2_part2() throws IOException {
        List<Pair> input = file("day2").stream()
                                      .map(line -> {
                                          String[] splitted = line.split(" ");
                                          return new Pair(splitted[0], Integer.parseInt(splitted[1]));
                                      })
                                      .collect(Collectors.toList());


        int horizontal = 0;
        int depth = 0;
        int aim = 0;

        for (Pair pair : input) {
            if (pair.getCommand().equals("forward")) {
                horizontal = horizontal + pair.getValue();
                depth = depth + (aim * pair.getValue());
            }
            if (pair.getCommand().equals("down")) {
                aim = aim + pair.getValue();
            }
            if (pair.getCommand().equals("up")) {
                aim = aim - pair.getValue();
            }
        }

        System.out.println(horizontal);
        System.out.println(depth);
        System.out.println(aim);
        System.out.println("Result: " + (horizontal * depth));
    }

    @Value
    static class Pair {

        String command;
        int value;
    }

}

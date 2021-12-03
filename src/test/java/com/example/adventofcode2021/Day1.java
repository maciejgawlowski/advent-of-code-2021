package com.example.adventofcode2021;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Value;
import org.junit.jupiter.api.Test;


class Day1 extends AbstractDay {

    @Test
    void day1_part1() throws IOException {
        List<Integer> numbers = file("day1").stream()
                                      .map(Integer::valueOf)
                                      .collect(Collectors.toList());

        int counter = 0;

        for (int i = 1; i < numbers.size(); i++) {
            if (numbers.get(i) > numbers.get(i - 1)) {
                counter++;
            }
        }

        System.out.println(counter);
    }

    @Test
    void day1_part2() throws IOException {
        List<Integer> numbers = file("day1").stream()
                                      .map(Integer::valueOf)
                                      .collect(Collectors.toList());

        int counter = 0;

        for (int i = 0; i < numbers.size(); i++) {
            if ((i + 3) >= numbers.size()) {
                break;
            }
            int sum1 = numbers.get(i) + numbers.get(i + 1) + numbers.get(i + 2);
            int sum2 = numbers.get(i + 1) + numbers.get(i + 2) + numbers.get(i + 3);
            if (sum2 > sum1) {
                counter++;
            }
        }

        System.out.println(counter);
    }

}

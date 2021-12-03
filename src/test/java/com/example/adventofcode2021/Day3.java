package com.example.adventofcode2021;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.junit.jupiter.api.Test;


class Day3 extends AbstractDay {

    @Test
    void day3_part1() throws IOException {
        List<String> lines = new ArrayList<>(file("day3"));

        Map<Integer, Bit> bits = new HashMap<>();
        for (int i = 0; i < 12; i++) {
            bits.put(i, new Bit());
        }

        for (String line : lines) {
            char[] chars = line.toCharArray();

            for (int i = 0; i < chars.length; i++) {
                if (Character.getNumericValue(chars[i]) == 1) {
                    bits.get(i)
                        .increaseOne();
                } else {
                    bits.get(i)
                        .increaseZero();
                }
            }
        }

        String gammaRateString = "";
        String epsilonRateString = "";

        for (int i = 0; i < bits.size(); i++) {
            Bit bit = bits.get(i);
            if (bit.getOnesCounter() > bit.getZerosCounter()) {
                gammaRateString = gammaRateString + "1";
                epsilonRateString = epsilonRateString + "0";
            } else {
                gammaRateString = gammaRateString + "0";
                epsilonRateString = epsilonRateString + "1";
            }
        }

        Integer gammaRate = Integer.parseInt(gammaRateString, 2);
        Integer epsilonRate = Integer.parseInt(epsilonRateString, 2);

        System.out.println("Gamma rate: " + gammaRateString + " - " + gammaRate);
        System.out.println("Epsilon rate: " + epsilonRateString + " - " + epsilonRate);
        System.out.println("Result: " + (gammaRate * epsilonRate));
    }

    @Test
    void day3_part2() throws IOException {
        List<String> oxygenLines = new ArrayList<>(file("day3"));
        List<String> co2Lines = new ArrayList<>(file("day3"));

        String oxygenGeneratorRatingString = "";
        String co2ScrubberRatingString = "";

        for (int i = 0; i < 12; i++) {
            Bit bit = getBitByPosition(oxygenLines, i);
            System.out.println(bit);
            if (bit.getOnesCounter() >= bit.getZerosCounter()) {
                int finalI = i;
                oxygenLines = oxygenLines.stream()
                                         .filter(line -> Character.getNumericValue(line.charAt(finalI)) == 1)
                                         .collect(Collectors.toList());
            } else {
                int finalI = i;
                oxygenLines = oxygenLines.stream()
                                         .filter(line -> Character.getNumericValue(line.charAt(finalI)) == 0)
                                         .collect(Collectors.toList());
            }
            if (oxygenLines.size() == 1) {
                oxygenGeneratorRatingString = oxygenLines.get(0);
                System.out.println(
                    "Found the only value at char counter " + i + ". The value is: " + oxygenGeneratorRatingString);
                break;
            }
        }

        for (int i = 0; i < 12; i++) {
            Bit bit = getBitByPosition(co2Lines, i);
            System.out.println(bit);
            if (bit.getOnesCounter() < bit.getZerosCounter()) {
                int finalI = i;
                co2Lines = co2Lines.stream()
                                   .filter(line -> Character.getNumericValue(line.charAt(finalI)) == 1)
                                   .collect(Collectors.toList());
            } else {
                int finalI = i;
                co2Lines = co2Lines.stream()
                                   .filter(line -> Character.getNumericValue(line.charAt(finalI)) == 0)
                                   .collect(Collectors.toList());
            }
            if (co2Lines.size() == 1) {
                co2ScrubberRatingString = co2Lines.get(0);
                System.out.println(
                    "Found the only value at char counter " + i + ". The value is: " + co2ScrubberRatingString);
                break;
            }
        }

        Integer oxygenGeneratorRating = Integer.parseInt(oxygenGeneratorRatingString, 2);
        Integer co2ScrubberRating = Integer.parseInt(co2ScrubberRatingString, 2);

        System.out.println("Result: " + (oxygenGeneratorRating * co2ScrubberRating));

    }

    private Bit getBitByPosition(List<String> lines, int position) {
        Bit bit = new Bit();

        for (String line : lines) {
            char[] chars = line.toCharArray();

            if (Character.getNumericValue(chars[position]) == 1) {
                bit.increaseOne();
            } else {
                bit.increaseZero();
            }
        }
        return bit;
    }

    @Getter
    @NoArgsConstructor
    @ToString
    static class Bit {

        private int zerosCounter = 0;
        private int onesCounter = 0;

        void increaseZero() {
            this.zerosCounter = this.zerosCounter + 1;
        }

        void increaseOne() {
            this.onesCounter = this.onesCounter + 1;
        }
    }

}

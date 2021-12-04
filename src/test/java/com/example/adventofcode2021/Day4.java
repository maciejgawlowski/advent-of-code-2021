package com.example.adventofcode2021;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Getter;
import lombok.ToString;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;


class Day4 extends AbstractDay {

    @Test
    void day4_part1() throws IOException {
        List<Integer> drawnNumbers = Arrays.asList(0, 56, 39, 4, 52, 7, 73, 57, 65, 13, 3, 72, 69, 96, 18, 9, 49, 83,
            24, 31, 12, 64, 29, 21, 80, 71, 66, 95, 2, 62, 68, 46, 11, 33, 74, 88, 17, 15, 5, 6, 98, 30, 51, 78, 76, 75,
            28, 53, 87, 48, 20, 22, 55, 86, 82, 90, 47, 19, 25, 1, 27, 60, 94, 38, 97, 58, 70, 10, 43, 40, 89, 26, 34,
            32, 23, 45, 50, 91, 61, 44, 35, 85, 63, 16, 99, 92, 8, 36, 81, 84, 79, 37, 93, 67, 59, 54, 41, 77, 42, 14);

        List<List<Integer>> numbers = file("day4")
            .stream()
            .map(line -> Stream.of(line.split(" "))
                               .filter(StringUtils::isNotBlank)
                               .map(Integer::parseInt)
                               .collect(Collectors.toList())
            )
            .collect(Collectors.toList());

        List<Board> boards = new ArrayList<>();

        Board board = new Board();
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i).isEmpty()) {
                boards.add(board);
                board = new Board();
            } else {
                board.getRows().add(numbers.get(i));
                board.getDrawnNumber().add(Arrays.asList(FALSE, FALSE, FALSE, FALSE, FALSE));
            }
        }
        boards.add(board);

        int numberCalled = 0;
        Optional<Board> bingo = null;
        for (Integer drawnNumber : drawnNumbers) {
            boards.forEach(b -> {
                    for (int i = 0; i < b.getRows().size(); i++) {
                        List<Integer> row = b.getRows().get(i);
                        if (row.contains(drawnNumber)) {
                            int drawnNumberIndex = row.indexOf(drawnNumber);
                            b.getDrawnNumber().get(i).set(drawnNumberIndex, TRUE);
                        }
                    }
                });
            bingo = boards.stream()
                          .filter(Board::isBingo)
                          .findFirst();
            if (bingo.isPresent()) {
                numberCalled = drawnNumber;
                System.out.println(bingo.get());
                break;
            }
        }

        Board bingoBoard = bingo.get();

        int unmarkedNumbersSum = 0;
        for (int i = 0; i < bingoBoard.getRows().size(); i++) {
            List<Integer> row = bingoBoard.getRows().get(i);
            for (int j = 0; j < row.size(); j++) {
                if (bingoBoard.getDrawnNumber().get(i).get(j).equals(FALSE)) {
                    unmarkedNumbersSum = unmarkedNumbersSum + row.get(j);
                }
            }
        }


        System.out.println(numberCalled);
        System.out.println(unmarkedNumbersSum);
        System.out.println("Result: " + (numberCalled * unmarkedNumbersSum));

    }

    @Test
    void day4_part2() throws IOException {
        List<Integer> drawnNumbers = Arrays.asList(0, 56, 39, 4, 52, 7, 73, 57, 65, 13, 3, 72, 69, 96, 18, 9, 49, 83,
            24, 31, 12, 64, 29, 21, 80, 71, 66, 95, 2, 62, 68, 46, 11, 33, 74, 88, 17, 15, 5, 6, 98, 30, 51, 78, 76, 75,
            28, 53, 87, 48, 20, 22, 55, 86, 82, 90, 47, 19, 25, 1, 27, 60, 94, 38, 97, 58, 70, 10, 43, 40, 89, 26, 34,
            32, 23, 45, 50, 91, 61, 44, 35, 85, 63, 16, 99, 92, 8, 36, 81, 84, 79, 37, 93, 67, 59, 54, 41, 77, 42, 14);

        List<List<Integer>> numbers = file("day4")
            .stream()
            .map(line -> Stream.of(line.split(" "))
                               .filter(StringUtils::isNotBlank)
                               .map(Integer::parseInt)
                               .collect(Collectors.toList())
            )
            .collect(Collectors.toList());

        List<Board> boards = new ArrayList<>();

        Board board = new Board();
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i).isEmpty()) {
                boards.add(board);
                board = new Board();
            } else {
                board.getRows().add(numbers.get(i));
                board.getDrawnNumber().add(Arrays.asList(FALSE, FALSE, FALSE, FALSE, FALSE));
            }
        }
        boards.add(board);

        int numberCalled = 0;
        List<Board> boardsWithoutBingo = new ArrayList<>();
        for (Integer drawnNumber : drawnNumbers) {
            boards.forEach(b -> {
                    for (int i = 0; i < b.getRows().size(); i++) {
                        List<Integer> row = b.getRows().get(i);
                        if (row.contains(drawnNumber)) {
                            int drawnNumberIndex = row.indexOf(drawnNumber);
                            b.getDrawnNumber().get(i).set(drawnNumberIndex, TRUE);
                        }
                    }
                });
            List<Board> bwb = boards.stream()
                                    .filter(b -> !b.isBingo())
                                    .collect(Collectors.toList());
            if (!bwb.isEmpty()) {
                boardsWithoutBingo = bwb;
            } else {
                System.out.println(boardsWithoutBingo);
                numberCalled = drawnNumber;
                break;
            }
        }

        Board bingoBoard = boardsWithoutBingo.get(0);

        int unmarkedNumbersSum = 0;
        for (int i = 0; i < bingoBoard.getRows().size(); i++) {
            List<Integer> row = bingoBoard.getRows().get(i);
            for (int j = 0; j < row.size(); j++) {
                if (bingoBoard.getDrawnNumber().get(i).get(j).equals(FALSE)) {
                    unmarkedNumbersSum = unmarkedNumbersSum + row.get(j);
                }
            }
        }


        System.out.println(numberCalled);
        System.out.println(unmarkedNumbersSum);
        System.out.println("Result: " + (numberCalled * unmarkedNumbersSum));

    }

    @Getter
    @ToString
    static class Board {

        private List<List<Integer>> rows = new ArrayList<>();
        private List<List<Boolean>> drawnNumber = new ArrayList<>();

        boolean isBingo() {
            return isRowBingo() || isColumnBingo();
        }

        private boolean isRowBingo() {
            boolean isRowBingo = drawnNumber.stream()
                                   .anyMatch(row -> row.stream()
                                                       .allMatch(value -> value.equals(TRUE)));
            return isRowBingo;
        }

        private boolean isColumnBingo() {
            for (int i = 0; i < drawnNumber.size(); i++) {
                if (drawnNumber.get(0)
                               .get(i)
                               .equals(TRUE) &&
                    drawnNumber.get(1)
                               .get(i)
                               .equals(TRUE) &&
                    drawnNumber.get(2)
                               .get(i)
                               .equals(TRUE) &&
                    drawnNumber.get(3)
                               .get(i)
                               .equals(TRUE) &&
                    drawnNumber.get(4)
                               .get(i)
                               .equals(TRUE)) {
                    return true;
                }
            }
            return false;
    }

}
}

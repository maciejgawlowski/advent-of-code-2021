package com.example.adventofcode2021;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class AbstractDay {

    List<String> file(String fileName) throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get("src/test/resources/" + fileName + ".txt"))) {
            return stream.collect(Collectors.toList());
        }
    }
}

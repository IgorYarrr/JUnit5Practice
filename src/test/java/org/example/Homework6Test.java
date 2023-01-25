package org.example;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

public class Homework6Test {
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "©", "¶", "…", "¾"})
    void valueSourceTest(String symbol){
        System.out.println(symbol);
    }

    @ParameterizedTest
    @CsvFileSource(files = "src/test/java/resources/test.csv")
    void csvFileSourceTest(String nickname, int age, String color, String condition){
        System.out.println(nickname + " " + age + " " + color + " " + condition);
    }

    static class CatArgumentsProvider implements ArgumentsProvider{
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context){
            return Stream.of(
                    Arguments.of(new Cat(5, "Barsik", "Alex")),
                    Arguments.of(new Cat(15, "Pushok", "Max")),
                    Arguments.of(new Cat(3, "Tom", "George"))
            );
        }
    }

    @ParameterizedTest
    @ArgumentsSource(CatArgumentsProvider.class)
    void argumentsSourceTest(Cat c){
        System.out.println(c.name + " " + c.age + " " + c.owner);
    }
}

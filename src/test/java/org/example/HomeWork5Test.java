package org.example;

import org.junit.jupiter.api.*;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assumptions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HomeWork5Test {
    @Test
    @Disabled
    public void disabledTest(){
        System.out.println("disabled test");
    }

    @Nested
    public class NestedClass{
        @Test
        public void nestedTest1(){
            System.out.println("nested1");
        }
        @Test
        public void nestedTest2(){
            System.out.println("nested2");
        }
    }

    @Order(4)
    @Test
    @Timeout(value = 3000, unit = TimeUnit.MILLISECONDS)
    public void timeoutTest() throws InterruptedException {
        Thread.sleep(4000);
        System.out.println("how?");
    }

    @Order(2)
    @Test
    public void assumeTrueTest(){
        assumeTrue(true);
        System.out.println("assumeTrue precondition has been passed");
    }

    @Test
    public void assumeFalseTest(){
        assumeFalse(false);
        System.out.println("assumeFalse precondition has been passed");
    }

    @Order(3)
    @Test
    public void assumingThatTest(){
        assumingThat(true, ()->{
            System.out.println("assumingThat precondition has been passed");
        });
        System.out.println("But this message will show up even assumingThat was false");
    }

    @Order(1)
    @DisplayName("My Repeated Test")
    @RepeatedTest(value = 3, name = "{displayName} - {currentRepetition} of {totalRepetitions}")
    public void repeatedTestTest(){
        System.out.println("Test repeated");
    }
}

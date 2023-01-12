package org.example;


import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
public class AppTest 
{
    @BeforeAll
    static void beforeAll(){
        System.out.println("Запуск BeforeAll");
    }

    @BeforeEach
    public void beforeEach(){
        System.out.println("Запуск BeforeEach");
    }

    @Test
    @DisplayName("Тест 1")
    @Tag("Special")
    public void test1()
    {
        System.out.println("Запуск теста 1");
    }
    @Test
    @DisplayName("Тест 2")
    @Disabled("Починить тест!\n")
    public void test2()
    {
        System.out.println("Запуск теста 2");
    }
    @Test
    @DisplayName("Тест 3")
    public void test3()
    {
        System.out.println("Запуск теста 3");
        String str1 = "sample text";
        String str2 = "sample text";
        Assertions.assertEquals(str1, str2);
        Assertions.assertNull(str1);
    }
    @Test
    @DisplayName("Fail Test")
    public void failTest(){
        Assertions.fail("Ошибка!");
    }

    @AfterEach
    public void AfterEach(){
        System.out.println("Запуск AfterEach");
    }

    @AfterAll
    static void AfterAll(){
        System.out.println("Запуск AfterAll");
    }
}

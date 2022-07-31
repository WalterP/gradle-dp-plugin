package com.vpontus.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Volodymyr Pontus <v.pontus@gmail.com> 24/07/2022
 */

public class Startup {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext("com.vpontus.example");

        System.out.println("Startup");
    }
}

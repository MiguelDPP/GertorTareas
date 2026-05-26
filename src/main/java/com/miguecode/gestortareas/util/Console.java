package com.miguecode.gestortareas.util;

import java.util.Scanner;
import java.util.function.Function;

public final class Console {
    private static final Scanner input = new Scanner(System.in);

    private Console() {}

    public static <T> T readValidator(String message, Function<String, T> parser, Function<T, Boolean> validator) {
        boolean isValidated = false;
        T value = null;
        while (!isValidated) {
            value = Console.read(message, parser);
            isValidated = validator.apply(value);
            if (!isValidated) System.out.println("-- Ingrese un valor correcto --");
        }
        return value;
    }

    public static <T> T read(String message, Function<String, T> parser) {

        T value = null;
        boolean isValid = false;

        while (!isValid) {
            try {
                System.out.print(message);
                value = parser.apply(input.nextLine());
                isValid = true;
            } catch (NumberFormatException e) {
                System.out.println("---- Valor incorrecto, intenta de nuevo ----");
            }
        }

        return value;
    }

    public static void printException(RuntimeException e) {
        System.out.printf("--- Error: %s ---\n", e.getMessage());
    }
}

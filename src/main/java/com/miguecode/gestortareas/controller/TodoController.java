package com.miguecode.gestortareas.controller;

import com.miguecode.gestortareas.ui.MenuView;
import com.miguecode.gestortareas.util.Console;
import com.miguecode.gestortareas.util.Validator;

public class TodoController {
    public void run() {
        short op = 0;

        System.out.println("---- Bienvenido a su Gestor de Tareas WTS (World Todo Solution) ----");
        do {
            MenuView.showInitialMenu();
            op = Console.readValidator("Seleccione una opción: ", Short::parseShort, Validator::positiveNumber);

            switch (op) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:
                    break;
                case 4:

                    break;
                case 5:
                    System.out.println("--- Ha salido del sistema ---");
                    break;
                default:
                    System.out.println("--- Opción incorrecta ---");
            }
        } while (op != 5);
    }
}

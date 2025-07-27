package model;

import java.io.IOException;

public class utilidades {

public class ConsoleUtil {

    public static void pause() {
        System.out.println("\nPressione ENTER para continuar...");
        try {
            System.in.read();
        } catch (IOException e) {}
    }

    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Erro ao limpar tela.");
        }
    }
}

}

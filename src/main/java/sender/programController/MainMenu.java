package sender.programController;


import sender.CSVFilesController.CSVFilesSearcher;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

import static java.lang.System.exit;

/*
MainMenu do rozpoczynania pracy z aplikacja
 */
public class MainMenu {

    boolean optionFlag = true;
    CSVFilesSearcher csvFilesSearcher = new CSVFilesSearcher();
    ProgramController programController = new ProgramController();

    public void showingMenu() throws IOException, InterruptedException, TimeoutException {

        Scanner input = new Scanner(System.in);
        while (optionFlag) {

            System.out.println("<--------------------------------------> " +
                    "\nWybierz co chcesz zrobić:" +
                    "\na) Włączenie programu wysyłającego z ustawieniami domyślnymi" +
                    "\nb) Zmiana ustawień programu" +
                    "\nx) Zamknięcie programu");

            String choice = input.nextLine();

            switch (choice) {
                case "a":
                    //używamy w metodzie domyślnego obiektu
                    csvFilesSearcher.searchAndSend(programController.setDefaultProperties());
                    break;
                case "b":
                    programController.changeSettings();
                    break;
                case "x":
                    System.out.println("Zamykamy program do wysyłania danych.");
                    exit(0);
                    optionFlag = false;
                    break;
                default:
                    System.out.println("\tBłąd!!!\nWybierz opcje " +
                            "(wpisz małą literę a, b lub x w konsolę).");
                    break;
            }
        }
    }
}
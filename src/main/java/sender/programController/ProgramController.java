package sender.programController;

import sender.CSVFilesController.CSVFilesSearcher;
import sender.modelProperties.ModelProperties;

import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

public class ProgramController {
    //włączamy ustawienia domyślne
    public ModelProperties setDefaultProperties() {
        ModelProperties modelProperties = new ModelProperties();

        modelProperties.setSuffixOfFiles(".csv");
        modelProperties.getPathToSearchList().add("C:\\Users\\User\\Desktop\\dummy\\");

        return modelProperties;
    }

    public void changeSettings() throws IOException {
        ModelProperties modelProperties = new ModelProperties();

        boolean optionFlag = true;

        Scanner input = new Scanner(System.in);
        modelProperties.setSuffixOfFiles(".csv");

        while (optionFlag) {

            System.out.println("<--------------------------------------> " +
                    "\nWybierz co chcesz zrobić:" +
                    "\na) Dodać ścieżkę do przeszukania" +
                    "\nb) Pokaż ścieżki do przeszukania" +
                    "\nc) Usuń wszystkie ścieżki z listy" +
                    "\nd) Zakończenie dokonywania zmian i uruchomienie programu" +
                    "\nx) Zakończenie dokonywania zmian");

            String choice = input.nextLine();

            switch (choice) {

                case "a":
                    boolean flag = true;
                    while (flag) {
                        System.out.println("a) Podaj ścieżkę folderu z którego chcesz otrzymywać pliki:\n" +
                                "\tDomyślna: C:\\Users\\User\\Desktop\\dummy\\" +
                                "\nx) Zakończyć dodawanie");
                        Scanner puts = new Scanner(System.in);
                        String choose = puts.nextLine();

                        switch (choose) {
                            case "a":
                                Scanner put = new Scanner(System.in);
                                System.out.println("Wklej ścieżkę:");
                                String newPath = put.nextLine();
                                modelProperties.getPathToSearchList().add(newPath);
                                break;
                            case "x":
                                flag = false;
                                break;
                            default:
                                System.out.println("\tBłąd!!!\nWybierz opcje " +
                                        "(wpisz małą literę a, b lub x w konsolę).");
                                break;
                        }
                    }

                    break;
                case "b":
                    Set<String> showPaths = modelProperties.getPathToSearchList();
                    if (showPaths.isEmpty()) {
                        System.out.println("Lista jest pusta!");
                    }
                    for (String s : showPaths) {
                        System.out.println(s);
                    }
                    break;

                case "c":
                    Set<String> removePaths = modelProperties.getPathToSearchList();
                    removePaths.clear();
                    break;

                case "d":
                    CSVFilesSearcher csvFilesSearcher = new CSVFilesSearcher();
                    csvFilesSearcher.searchAndSend(modelProperties);
                    optionFlag = false;

                    break;
                case "x":
                    System.out.println("Zamykamy program do edycji danych.");
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

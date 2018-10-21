package sender;



import sender.programController.MainMenu;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MessageSender {
    public static void main(String[] args) throws IOException, InterruptedException, TimeoutException {
        System.out.println("<-------------------------------------->" +
                "\n\tWitaj w systemie przesyłania danych!!!");
        MainMenu mainMenu = new MainMenu();
        mainMenu.showingMenu();
    }
}

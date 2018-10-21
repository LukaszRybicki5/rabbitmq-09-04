package receiver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.*;
import com.rabbitmq.client.QueueingConsumer.Delivery;
import receiver.jsonParser.JSONFileMaker;
import receiver.messageObject.MessageObject;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

import static java.lang.System.exit;

public class MessageReceiver {

    private static String EXCHANGE_NAME = "TopicDurable";
    private static String QUEUE_NAME = "DummyLoad";

    public static void main(String[] args) throws IOException, ShutdownSignalException, ConsumerCancelledException, NullPointerException, InterruptedException, TimeoutException {

        System.out.println("<-------------------------------------->" +
                "\n\tWitaj w systemie odbierania danych!");
        boolean optionFlag = true;
        while (optionFlag) {
            Scanner input = new Scanner(System.in);
            System.out.println("<--------------------------------------> " +
                    "\nWybierz co chcesz zrobić:" +
                    "\na) Włączenie programu odbierającego z ustawieniami domyślnymi" +
                    "\nb) Zmiana domyślnej ścieżki programu" +
                    "\nx) Zamknięcie programu");
            String choice = input.nextLine();

            switch (choice) {
                case "a":
                    receivingFiles("C:\\Users\\User\\Desktop\\newJson\\");
                    break;
                case "b":
                    changingPathOfReceivedFiles();
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

    private static void changingPathOfReceivedFiles() throws InterruptedException, TimeoutException, IOException, InputMismatchException {
        Scanner put = new Scanner(System.in);
        System.out.println("Podaj ścieżkę folderu do którego chcesz otrzymywać pliki:\n" +
                "Domyślna: C:\\Users\\User\\Desktop\\newJson\\");
        String Path = put.nextLine();
        receivingFiles(Path);

    }

    private static void receivingFiles(String Path) throws InterruptedException, IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "topic", true);
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
        channel.basicConsume(QUEUE_NAME, true, queueingConsumer);

        ObjectMapper mapper = new ObjectMapper();
        while (true) {
            Delivery delivery = queueingConsumer.nextDelivery();
            if (delivery.getBody()[0] == 123) {

                MessageObject messageObject = mapper.readValue(delivery.getBody(), MessageObject.class);
                System.out.println("Odebrano plik: " + messageObject.getFileName() + "!");
                System.out.println(messageObject.getCSVList());
                JSONFileMaker jsonFileMaker = new JSONFileMaker();
                jsonFileMaker.putDataToPersonList(messageObject.getCSVList(), messageObject.getFileName(), Path);
                System.out.println("Stworzono plik JSON");
            }
        }
    }
}


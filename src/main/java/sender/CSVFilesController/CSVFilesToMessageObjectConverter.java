package sender.CSVFilesController;

import receiver.messageObject.MessageObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CSVFilesToMessageObjectConverter {

        public MessageObject getMessageObjectFromCSV(String path, String FileName) throws FileNotFoundException {
        File file = new File(path);

        Scanner scanner = new Scanner(file);
        scanner.useDelimiter(",");

        MessageObject messageObject = new MessageObject();
        messageObject.setFileName(FileName);
        ArrayList<String> listOfFields = new ArrayList<>();

        while (scanner.hasNext()) {
           String data = scanner.next();
            System.out.print(data + "\t");
            listOfFields.add(data);

        }
            messageObject.setCSVList(listOfFields);
        System.out.println(messageObject.getFileName());
        scanner.close();
        return messageObject;
    }
}


package sender.CSVFilesController;

import sender.modelProperties.ModelProperties;
import receiver.messageObject.MessageObject;
import sender.programController.MessageObjectSender;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeoutException;

/*
Klasa wyszukuje wszystkie pliki .csv w katalogu i subkatalogach
 */
public class CSVFilesSearcher {

    String FILE_SUFFIX = ".csv";

    public void searchAndSend(ModelProperties modelProperties) throws IOException{

            Set<String> pathToSearchList = modelProperties.getPathToSearchList();
            for (String s : pathToSearchList) {
                getAllFilesInDirectory(s);
        }
    }

    public void getAllFilesInDirectory(String dirPath) throws FileNotFoundException {
        System.out.println(dirPath);
        File[] fileList = getFileListInCatalog(dirPath);

        CSVFilesToMessageObjectConverter csvFilesToJSONConverter = new CSVFilesToMessageObjectConverter();

        for (File file : fileList) {
            System.out.println("File: " + file.getName());
            //Wywołujemy klasę zmieniającą pliki .csv na JSON
            StringBuilder stringBuilder = new StringBuilder(dirPath);
            String newDirPathToConvert = stringBuilder.append("\\").append(file.getName()).toString();

            StringBuilder fileName = new StringBuilder(file.getName());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH-mm-ss-SSSS");
            String NameForFile = simpleDateFormat.format(new Date()).toString();
            String newNameForFile = fileName.delete(fileName.length() - 4, fileName.length()).append("_").append(NameForFile).toString();

            //Zapisujemy .csv w object
            MessageObject messageObject = csvFilesToJSONConverter.getMessageObjectFromCSV(newDirPathToConvert, newNameForFile);

            MessageObjectSender messageObjectSender = new MessageObjectSender();
            try {
                messageObjectSender.sendingMessageObject(messageObject);
            } catch (IOException | TimeoutException e) {
                e.printStackTrace();
            }
        }

        List<String> directoryLists = getDirectoryPath(dirPath);

        for (String path : directoryLists) {
            System.out.println(path);
            getAllFilesInDirectory(path);
        }
    }

    private File[] getFileListInCatalog(String dirPath) {
        File dir = new File(dirPath);

        //symulujemy długość przesyłu danych 1 sekunda przestoju
        try {
            Thread.sleep(500);
        } catch (InterruptedException _ignored) {
            Thread.currentThread().interrupt();
        }

        File[] fileList = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(FILE_SUFFIX);
            }
        });
        return fileList;
    }

    private List<String> getDirectoryPath(String dirPath) {
        File file = new File(dirPath);
        String[] names = file.list();
        ArrayList<String> directoryList = new ArrayList<>();

        for (String name : names) {
            if (new File(dirPath + name).isDirectory()) {
                StringBuilder stringBuilder = new StringBuilder(dirPath);
                String newDirPath = stringBuilder.append(name).append("\\").toString();
                directoryList.add(newDirPath);
            }
        }
        return directoryList;
    }
}




package receiver.messageObject;

import java.util.ArrayList;

public class MessageObject {

    private String FileName;
    private  ArrayList<String> CSVList;

    public MessageObject() {
    }

    public MessageObject(String fileName, ArrayList<String> CSVList) {
        this.FileName = fileName;
        this.CSVList = CSVList;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public ArrayList<String> getCSVList() {
        return CSVList;
    }

    public void setCSVList(ArrayList<String> CSVList) {
        this.CSVList = CSVList;
    }
}

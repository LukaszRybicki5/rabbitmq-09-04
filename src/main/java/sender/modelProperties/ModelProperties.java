package sender.modelProperties;

import java.util.HashSet;
import java.util.Set;

public class ModelProperties {

    private Set<String> PathToSearchList = new HashSet<>();
    private int searchInterval;
    private String suffixOfFiles;
    private char punctuationMark;

    public ModelProperties() {
    }

    public ModelProperties(Set<String> pathToSearchList, String suffixOfFiles, char punctuationMark) {
        PathToSearchList = pathToSearchList;
        this.suffixOfFiles = suffixOfFiles;
        this.punctuationMark = punctuationMark;
    }

    public ModelProperties(Set<String> pathToSearchList, int searchInterval, String suffixOfFiles, char punctuationMark) {
        PathToSearchList = pathToSearchList;
        this.searchInterval = searchInterval;
        this.suffixOfFiles = suffixOfFiles;
        this.punctuationMark = punctuationMark;
    }

    public Set<String> getPathToSearchList() {
        return PathToSearchList;
    }

    public void setPathToSearchList(Set<String> pathToSearchList) {
        PathToSearchList = pathToSearchList;
    }

    public int getSearchInterval() {
        return searchInterval;
    }

    public void setSearchInterval(int searchInterval) {
        this.searchInterval = searchInterval;
    }

    public String getSuffixOfFiles() {
        return suffixOfFiles;
    }

    public void setSuffixOfFiles(String suffixOfFiles) {
        this.suffixOfFiles = suffixOfFiles;
    }

    public char getPunctuationMark() {
        return punctuationMark;
    }

    public void setPunctuationMark(char punctuationMark) {
        this.punctuationMark = punctuationMark;
    }
}

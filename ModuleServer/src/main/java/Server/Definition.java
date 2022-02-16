package Server;

import java.util.ArrayList;

public class Definition {
    private final String dict;
    private final String dictType;
    private final Integer year;
    private final ArrayList<String> text;

    public Definition(String dict, String dictType, Integer year, ArrayList<String> text) {
        this.dict = dict;
        this.dictType = dictType;
        this.year = year;
        this.text = text;
    }

    public String getDict() {
        return dict;
    }

    public String getDictType() {
        return dictType;
    }

    public Integer getYear() {
        return year;
    }

    public ArrayList<String> getText() {
        return text;
    }

    /**
     * Method compares two definitions based on their text array list
     * @return True if objects are equal, false otherwise
     */
    public boolean equals(Definition o) {
        if (!dict.equals(o.getDict()) && !dictType.equals(o.getDictType()) && !year.equals(o.getYear()))
            return false;

        if (text.size() != o.getText().size())
            return false;

        for (String t : text) {
            if (!o.getText().contains(t))
                return false;
        }

        return true;
    }
}

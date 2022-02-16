package Server;

import java.util.ArrayList;

public class Word {
    private final String word;
    private final String word_en;
    private final String type;
    private final ArrayList<String> singular;
    private final ArrayList<String> plural;
    private ArrayList<Definition> definitions;

    public Word(String word, String word_en, String type, ArrayList<String> singular,
                ArrayList<String> plural, ArrayList<Definition> definitions) {
        this.word = word;
        this.word_en = word_en;
        this.type = type;
        this.singular = singular;
        this.plural = plural;
        this.definitions = definitions;
    }

    public String getWord() {
        return word;
    }

    public String getWord_en() {
        return word_en;
    }

    public String getType() {
        return type;
    }

    public ArrayList<String> getSingular() {
        return singular;
    }

    public ArrayList<String> getPlural() {
        return plural;
    }

    public ArrayList<Definition> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(ArrayList<Definition> definitions) {
        this.definitions = definitions;
    }

    /**
     * @return method return dictionary of synonyms if it exists in data base
     */
    public Definition getSynonymDict(){
        Definition definition = null;

        for(Definition def : this.getDefinitions()){
            if(def.getDictType().equalsIgnoreCase("synonyms"))
                definition = def;
        }

        return definition;
    }

    /**
     * This method compare two objects of class Word and verify if it's the same object with different definitions
     * @return True - if objects are equals and false otherwise
     */
    public boolean equals(Word word1) {

        if (this.getDefinitions().size() != word1.getDefinitions().size())
            return false;

        ArrayList<Definition> definitions = word1.getDefinitions();
        for (Definition definition : this.getDefinitions()) {
            if (!definitions.contains(definition))
                return false;

            Definition definition1 = definitions.get(definitions.indexOf(definition));
            if (!definition.equals(definition1))
                return false;
        }

        return true;
    }
}

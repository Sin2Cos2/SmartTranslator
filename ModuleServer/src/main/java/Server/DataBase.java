package Server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class DataBase {
    private final HashMap<String, HashMap<String, Word>> dictionary;
    private FileReader fileReader;
    private Gson gson;
    private Type collectionType;

    /**
     * Parse all json files, create a dictionary of words with definitions and synonyms
     */
    public DataBase() {
        dictionary = new HashMap<>();
        gson = new GsonBuilder().setPrettyPrinting().create();
        collectionType = new TypeToken<ArrayList<Word>>() {
        }.getType();

        ArrayList<String> listOfFiles = Utils.getListOfFiles();
        if (listOfFiles == null) {
            System.out.println("No JSON files was found");
            return;
        }

        for (String file : listOfFiles) {
            try {
                fileReader = new FileReader(file);
                ArrayList<Word> words = gson.fromJson(fileReader, collectionType);

                for (Word word : words)
                    addWord(word, file.split("_")[0]);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public HashMap<String, HashMap<String, Word>> getDictionary() {
        return dictionary;
    }

    /**
     * Method add word to dictionary
     * @param word which is needed to add
     * @param language of word
     * @return True if word was added, false otherwise
     */
    public boolean addWord(Word word, String language) {
        String wordEn = word.getWord_en();
        if (!dictionary.containsKey(wordEn))
            dictionary.put(wordEn, new HashMap<>());

        HashMap<String, Word> map = dictionary.get(wordEn);
        if (map.containsKey(language)) {
            Word tmpWord = map.get(language);
            if (word.equals(tmpWord)) {
                System.out.println("Word " + word.getWord() + " is already in dictionary");
                return false;
            }
        }

        map.put(language, word);
        return true;
    }

    /**
     * Method search passed word in dictionary and return it translated in english
     * @param word to find
     * @param language of word
     * @return translated word in english if it was found, null otherwise
     */
    public String findWord(String word, String language) {

        word = word.toLowerCase(Locale.ROOT);

        for (Map.Entry<String, HashMap<String, Word>> entry : this.dictionary.entrySet()) {
            if (!entry.getValue().containsKey(language))
                continue;
            Word tmpWord = entry.getValue().get(language);

            if (tmpWord.getPlural().contains(word) || tmpWord.getSingular().contains(word))
                return tmpWord.getWord_en();
        }

        if(language.equalsIgnoreCase("en"))
            return word;

        return null;
    }

    /**
     * Remove word from dictionary
     * @param word to remove
     * @param language of word
     * @return True if word was removed, false otherwise
     */
    public boolean removeWord(String word, String language) {

        String tmpWord = findWord(word, language);
        if (tmpWord == null)
            return false;

        if (language.equalsIgnoreCase("en") && !dictionary.get(word).containsKey(language)) {
            System.out.println("Word " + tmpWord + " was not found in dictionary");
            return false;
        }

        dictionary.get(tmpWord).remove(language);
        System.out.println("Word " + tmpWord + "(" + word + ") was removed from dictionary");

        return true;
    }

    /**
     * Method add definition to a word if it exists in dictionary
     * @param word to which you need to add a definition
     * @param language of word
     * @param definition you want to add
     * @return True if definition was added, false otherwise
     */
    public boolean addDefinition(String word, String language, Definition definition) {

        if(dictionary.isEmpty())
            return false;

        if (language.equalsIgnoreCase("en") && !dictionary.get(word).containsKey(language))
            return false;

        String wordEn = findWord(word, language);
        if (wordEn == null)
            return false;

        Word tmpWord = dictionary.get(wordEn).get(language);
        tmpWord.getDefinitions().add(definition);

        System.out.println("Definition was added successfully");

        return true;
    }

    /**
     * Method remove definition from passed word if it exists in dictionary
     * @param word from which you need to remove a definition
     * @param language of word
     * @param dictionary name you want to remove
     * @return True if definition was removed, false otherwise
     */
    public boolean removeDefinition(String word, String language, String dictionary) {

        if(dictionary.isEmpty())
            return false;

        if (language.equalsIgnoreCase("en") && !this.dictionary.get(word).containsKey(language))
            return false;

        String wordEn = findWord(word, language);
        if (wordEn == null)
            return false;

        Word tmpWord = this.dictionary.get(wordEn).get(language);

        for (Definition definition : tmpWord.getDefinitions())
            if (definition.getDict().equalsIgnoreCase(dictionary)) {
                tmpWord.getDefinitions().remove(definition);
                break;
            }

        System.out.println("Definition was removed successfully");

        return true;
    }

    /**
     * Get all definition of a word if it exists in dictionary
     * @param word for which you need definitions
     * @param language of word
     * @return ArrayList of definitions if word exists, null otherwise
     */
    public ArrayList<Definition> getDefinitionsForWord(String word, String language) {
        ArrayList<Definition> definitions = new ArrayList<>();
        Word objectWord;

        String wordEn = findWord(word, language);
        if (wordEn == null)
            return null;

        try {
            objectWord = dictionary.get(wordEn).get(language);

            for (Definition definition : objectWord.getDefinitions())
                definitions.add(definition);

            Collections.sort(definitions, (o1, o2) -> {
                if (o1.getYear() > o2.getYear())
                    return 1;
                if (o1.getYear() < o2.getYear())
                    return -1;
                return 0;
            });
        } catch (NullPointerException e) {
            return null;
        }

        return definitions;
    }

    /**
     * Export in file all words of specified language in JSON format
     * @param language of words
     */
    public void exportDictionary(String language) {
        ArrayList<Word> words = new ArrayList<>();

        for (Map.Entry<String, HashMap<String, Word>> entry : this.getDictionary().entrySet()) {
            HashMap<String, Word> map = entry.getValue();
            if (map.containsKey(language)) {
                Word word = map.get(language);
                word.setDefinitions(this.getDefinitionsForWord(word.getWord(), language));
                words.add(word);
            }
        }

        Collections.sort(words, (o1, o2) -> o1.getWord().compareTo(o2.getWord()));
        String dataBase = gson.toJson(words);
        try {
            BufferedWriter buf = new BufferedWriter(new FileWriter("./export_DataBase.json"));
            buf.write(dataBase);
            buf.flush();
            buf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

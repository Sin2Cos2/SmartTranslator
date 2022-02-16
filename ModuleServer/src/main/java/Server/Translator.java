package Server;

import java.util.*;

public class Translator {

    private final DataBase dataBase;

    public Translator() {
        dataBase = new DataBase();
    }

    public DataBase getDataBase() {
        return dataBase;
    }

    /**
     * @param word you want to translate
     * @param fromLanguage language of word
     * @param toLanguage language you want to translate in
     * @return translated word if it exists in dictionary, original word otherwise
     */
    public String translateWord(String word, String fromLanguage, String toLanguage) {

        boolean isSingular;
        HashMap<String, Word> tmpMap;
        word = word.toLowerCase(Locale.ROOT);

        String wordEn = dataBase.findWord(word, fromLanguage);
        if (wordEn == null) {
            return word;
        }

        tmpMap = dataBase.getDictionary().get(wordEn);
        if(tmpMap == null)
            return word;

        /**
         * If destination language is english and in dictionary it exists only as key,
         * return string we have as key
         */
        if (toLanguage.equalsIgnoreCase("en") && !tmpMap.containsKey(toLanguage))
            return wordEn;
        /**
         * If dictionary doesn't contain word in wanted language, return original word
         */
        if (!tmpMap.containsKey(toLanguage))
            return word;
        /**
         * In case we want to translate an english word which exists in dictionary only as key,
         * return standard form of word in wanted language
         * Otherwise return translated word in dependency of index in plural or singular array
         */
        if (fromLanguage.equalsIgnoreCase("en") && !tmpMap.containsKey(fromLanguage))
            return tmpMap.get(toLanguage).getWord();

        Word tmpWord = tmpMap.get(fromLanguage);
        isSingular = tmpWord.getSingular().contains(word);

        if (tmpWord.getType().equalsIgnoreCase("noun"))
            return isSingular ? tmpMap.get(toLanguage).getSingular().get(0) : tmpMap.get(toLanguage).getPlural().get(0);

        Integer index = isSingular ? tmpWord.getSingular().indexOf(word) : tmpWord.getPlural().indexOf(word);

        tmpWord = tmpMap.get(toLanguage);
        return isSingular ? tmpWord.getSingular().get(index) : tmpWord.getPlural().get(index);
    }

    /**
     * Method split sentence into and array list and translate each word separately
     * In the end, concatenate all translated words in one sentence
     * @param sentence you want to translate
     * @param fromLanguage of sentence
     * @param toLanguage wanted sentence's language
     * @return Translated Sentence
     */
    public String translateSentence(String sentence, String fromLanguage, String toLanguage) {
        ArrayList<String> splitSentence = new ArrayList<>(List.of(sentence.split(" ")));
        ArrayList<String> newSentence = new ArrayList<>();
        for (String s : splitSentence) {
            String value = Utils.verifyString(s);
            if (value != null)
                newSentence.add(this.translateWord(value, fromLanguage, toLanguage));
        }

        return String.join(" ", newSentence);
    }

    /**
     * Method translate sentence in 3 different ways
     * @param sentence you want to translate
     * @param fromLanguage of sentence
     * @param toLanguage wanted sentence's language
     * @return Translated Sentence
     */
    public ArrayList<String> translateSentences(String sentence, String fromLanguage, String toLanguage) {
        ArrayList<String> res = new ArrayList<>();

        String translatedSentence = this.translateSentence(sentence, fromLanguage, toLanguage);
        ArrayList<String> secondSentence = new ArrayList<>();
        ArrayList<String> thirdSentence = new ArrayList<>();
        ArrayList<String> splitSentence = new ArrayList<>(List.of(translatedSentence.split(" ")));

        /**
         * Iterate through array with translated words, add them in array in case we don't find any synonyms
         * Search for each in dictionary, if english form of word wasn't found skip to the next iteration
         * If we have found word only as key and don't have instance of class Word for it, skip to the next
         * iteration
         * If word doesn't have synonym dictionaries skip to the next iteration
         */
        for (String value : splitSentence) {
            secondSentence.add(value);
            thirdSentence.add(value);

            String wordEn = dataBase.findWord(value, toLanguage);
            if (wordEn == null)
                continue;

            Word word = dataBase.getDictionary().get(wordEn).get(toLanguage);
            if (word == null)
                continue;

            Definition synonyms = word.getSynonymDict();
            if (synonyms == null)
                continue;

            /**
             * Add first synonym to second array
             * Add second synonym to third array
             */
            for (String synonym : synonyms.getText()) {
                if (secondSentence.contains(value)) {
                    secondSentence.remove(value);
                    secondSentence.add(synonym);
                } else if (thirdSentence.contains(value)) {
                    thirdSentence.remove(value);
                    thirdSentence.add(synonym);
                }
            }
        }

        sentence = String.join(" ", translatedSentence);
        String secondSent = String.join(" ", secondSentence);
        String thirdSent = String.join(" ", thirdSentence);

        /**
         * If second or third sentence are equals with first, initialize them with null
         * If second is equal with third, initialize third with null
         */
        if (sentence.equalsIgnoreCase(secondSent))
            secondSent = null;
        if (sentence.equalsIgnoreCase(thirdSent))
            thirdSent = null;
        if (secondSent != null && secondSent.equalsIgnoreCase(thirdSent))
            thirdSent = null;

        res.add(sentence);
        if(secondSent != null)
            res.add(secondSent);
        if(thirdSent != null)
            res.add(thirdSent);
        return res;
    }

}

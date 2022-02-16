# SmartTranslator – Serviciu de traducere texte

## Description

This project is created to manage a simplified version of an google translate.

Project was created using maven and all tests are implemented using JUnit

The project was realised using 2 separated modules:

- SmartTranslator
- ClientWindow

SmartTranslator implements all backend work. ClientWindow implements simple user interface

ClientWindow module depends on SmartTranslator module

---

## SmartTranslator

SmartTranslator have 2 folders with code. One for tests and one for project.

## Project

### Definition

Instances of this class contains information about dictionary books with definitions or synonyms

### Word

Instance of Word class contains information about word, such as:

- Word in original language
- Word in english
- Type
- Singular forms
- Plural forms
- List of definitions

### Utils

This abstract class implements two methods.

```Java
public abstract class Utils {
    public static String verifyString(String value);

    public static ArrayList<String> getListOfFiles();
}
```

Thirst method verify string value for punctuation marks. Second method return list of all files with JSON extension.

### DataBase

This class realize a simplifies database, which contains information about all words in hashmap.

When instance of this class is created, we get list of JSON files with words and information about them and parse them
all.

#### Dictionary structure

> HashMap<String, HashMap<String, Word>:
>
>       => (String)Word in english => HashMap<String, Word>:
>
>                                                           => (String)Language => (Word)Word
>
>                                                           => (String)Language => (Word)Word
>
>       => (String)Word in english => HashMap<String, Word>:
>
>                                                           ...

#### Functionality

All details are described in the code.

```Java
public class DataBase {
    //Adding a word in dictionary
    public boolean addWord(Word word, String language);

    //Searching for passed word in dictionary and return it translated in english
    public String findWord(String word, String language);

    //Remove word from dictionary
    public boolean removeWord(String word, String language);

    //Adding definition to word in dictionary
    public boolean addDefinition(String word, String language, Definition definition);

    //Remove definition from word in dictionary
    public boolean removeDefinition(String word, String language, String dictionary);

    //Return all found definitions of passed word
    public ArrayList<Definition> getDefinitionsForWord(String word, String language);

    //Export in file all objects of class Word of specified language in JSON format.
    public void exportDictionary(String language);
}
```

### Translator

This class realize simple translator if passed word are in dictionary. Contains an instance of the DataBase class
inside

#### Functionality

All details are described in the code.

```Java
public class Translator {
    //Translate word if find it in database's dictionary
    public String translateWord(String word, String fromLanguage, String toLanguage);

    //Translate passed sentence
    public String translateSentence(String sentence, String fromLanguage, String toLanguage);

    //Return ArrayList which contains 3 or fewer options for translating a sentence
    public ArrayList<String> translateSentences(String sentence, String fromLanguage, String toLanguage);
}
```

## Unit Tests

Tests are realized using JUnit.

There are 8 classes to test each method. Every class contains 3-6 tests to check the robustness of the code for
different inputs

There is a problem with running tests with maven, in this case 3 tests fail, but it's not happening if run tests manually.
It might be because of romanian characters.

---

## ClientWindow

A simple graphic user interface, implemented with Swing.

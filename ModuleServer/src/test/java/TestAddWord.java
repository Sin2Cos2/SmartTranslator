import Server.DataBase;
import Server.Definition;
import Server.Word;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class TestAddWord {
    DataBase dataBase = new DataBase();
    Word word;

    @Test
    public void testAddWord1() {
        ArrayList<String> singular = new ArrayList<>();
        singular.add("clock");
        ArrayList<String> plural = new ArrayList<>();
        singular.add("clocks");
        ArrayList<Definition> definitions = new ArrayList<>();
        word = new Word("clock", "clock", "noun", singular, plural, definitions);
        dataBase.addWord(word, "en");
        Assert.assertEquals(6, dataBase.getDictionary().size());
        System.out.println(dataBase.getDictionary().keySet());
        Assert.assertEquals(1, dataBase.getDictionary().get("clock").size());
        System.out.println(dataBase.getDictionary().get("clock").keySet());
    }

    @Test
    public void testAddWord2() {
        ArrayList<String> singular = new ArrayList<>();
        singular.add("dog");
        ArrayList<String> plural = new ArrayList<>();
        singular.add("dogs");
        ArrayList<Definition> definitions = new ArrayList<>();
        word = new Word("dog", "dog", "noun", singular, plural, definitions);
        dataBase.addWord(word, "en");
        Assert.assertEquals(5, dataBase.getDictionary().size());
        System.out.println(dataBase.getDictionary().keySet());
        Assert.assertEquals(2, dataBase.getDictionary().get("dog").size());
        System.out.println(dataBase.getDictionary().get("dog").keySet());
    }

    @Test
    public void testAddWord3() {
        ArrayList<String> singular = new ArrayList<>();
        singular.add("lac");
        ArrayList<String> plural = new ArrayList<>();
        singular.add("lacuri");
        ArrayList<Definition> definitions = new ArrayList<>();
        word = new Word("lac", "lake", "noun", singular, plural, definitions);
        dataBase.addWord(word, "ro");
        Assert.assertEquals(6, dataBase.getDictionary().size());
        System.out.println(dataBase.getDictionary().keySet());
        Assert.assertEquals(1, dataBase.getDictionary().get("lake").size());
        System.out.println(dataBase.getDictionary().get("lake").keySet());
    }

    @Test
    public void testAddWord4() {
        ArrayList<String> singular = new ArrayList<>();
        singular.add("chien");
        ArrayList<String> plural = new ArrayList<>();
        singular.add("chiens");
        ArrayList<Definition> definitions = new ArrayList<>();
        word = new Word("chien", "dog", "noun", singular, plural, definitions);
        dataBase.addWord(word, "fr");
        Assert.assertEquals( 5, dataBase.getDictionary().size());
        System.out.println(dataBase.getDictionary().keySet());
        Assert.assertEquals( 2, dataBase.getDictionary().get("dog").size());
        System.out.println(dataBase.getDictionary().get("dog").keySet());
    }

    @Test
    public void testAddWord5() {
        ArrayList<String> singular = new ArrayList<>();
        singular.add("chat");
        ArrayList<String> plural = new ArrayList<>();
        singular.add("chats");
        ArrayList<Definition> definitions = new ArrayList<>();
        word = new Word("chat", "cat", "noun", singular, plural, definitions);
        dataBase.addWord(word, "fr");
        Assert.assertEquals( 5, dataBase.getDictionary().size());
        System.out.println(dataBase.getDictionary().keySet());
        Assert.assertEquals( 2, dataBase.getDictionary().get("cat").size());
        System.out.println(dataBase.getDictionary().get("dog").keySet());
    }
}

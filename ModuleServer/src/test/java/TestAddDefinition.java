import Server.DataBase;
import Server.Definition;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class TestAddDefinition {
    DataBase dataBase = new DataBase();

    @Test
    public void testAddDefinition1() {
        Definition definition = new Definition("Def", "Synonym", 1990, new ArrayList<>());
        Assert.assertEquals(false, dataBase.addDefinition("dog", "en", definition));
    }

    @Test
    public void testAddDefinition2() {
        Definition definition = new Definition("Def", "Synonym", 1990, new ArrayList<>());
        Assert.assertEquals(false, dataBase.addDefinition("машина", "ru", definition));
    }

    @Test
    public void testAddDefinition3() {
        Definition definition = new Definition("Def", "Synonym", 1990, new ArrayList<>());
        Assert.assertEquals(true, dataBase.addDefinition("chat", "fr", definition));
        int size = dataBase.getDictionary().get("cat").get("fr").getDefinitions().size();
        Assert.assertEquals(3, size);
    }
}

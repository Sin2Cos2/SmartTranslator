import Server.DataBase;
import Server.Definition;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class TestGetDefinitionsForWord {
    DataBase dataBase = new DataBase();

    @Test
    public void testGetDefinitionForWord1(){
        ArrayList<Definition> definitions = dataBase.getDefinitionsForWord("game", "en");
        Assert.assertEquals(null, definitions);
    }

    @Test
    public void testGetDefinitionForWord2(){
        ArrayList<Definition> definitions = dataBase.getDefinitionsForWord("машина", "ru");
        Assert.assertEquals(null, definitions);
    }

    @Test
    public void testGetDefinitionForWord3(){
        ArrayList<Definition> definitions = dataBase.getDefinitionsForWord("pisici", "ro");
        Assert.assertEquals(2, definitions.size());
    }
}

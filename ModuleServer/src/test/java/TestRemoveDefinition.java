import Server.DataBase;
import org.junit.Assert;
import org.junit.Test;

public class TestRemoveDefinition {
    DataBase dataBase = new DataBase();

    @Test
    public void testRemoveDefinition1() {
        boolean res = dataBase.removeDefinition("машина", "ru", "Словарь");
        Assert.assertEquals(false, res);
    }

    @Test
    public void testRemoveDefinition2() {
        boolean res = dataBase.removeDefinition("game", "en", "Definition");
        Assert.assertEquals(false, res);
    }

    @Test
    public void testRemoveDefinition3() {
        boolean res = dataBase.removeDefinition("chat", "fr", "Larousse");
        Assert.assertEquals(true, res);
    }

    @Test
    public void testRemoveDefinition4() {
        boolean res = dataBase.removeDefinition("mergem", "ro", "Micul dicționar academic, ediția a II-a");
        Assert.assertEquals(true, res);
    }
}

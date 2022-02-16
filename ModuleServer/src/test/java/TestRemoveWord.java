import Server.DataBase;
import org.junit.Assert;
import org.junit.Test;

public class TestRemoveWord {
    DataBase dataBase = new DataBase();

    @Test
    public void testRemoveWord1() {
        boolean res = dataBase.removeWord("chats", "fr");
        Assert.assertEquals(true, res);
    }

    @Test
    public void testRemoveWord2(){
        boolean res = dataBase.removeWord("mergem", "ro");
        Assert.assertEquals(true, res);
    }

    @Test
    public void testRemoveWord3(){
        boolean res = dataBase.removeWord("game", "en");
        Assert.assertEquals(false, res);
    }

    @Test
    public void testRemoveWord4(){
        boolean res = dataBase.removeWord("машина", "ru");
        Assert.assertEquals(false, res);
    }
}

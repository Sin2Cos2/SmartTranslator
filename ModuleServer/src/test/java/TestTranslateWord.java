import Server.Translator;
import org.junit.Assert;
import org.junit.Test;

public class TestTranslateWord {
    Translator translator = new Translator();

    @Test
    public void testTranslateWord1(){
        String res = translator.translateWord("chats", "fr", "ro");
        Assert.assertEquals("pisici", res);
    }

    @Test
    public void testTranslateWord2(){
        String res = translator.translateWord("game", "en", "fr");
        Assert.assertEquals("jeu", res);
    }

    @Test
    public void testTranslateWord3(){
        String res = translator.translateWord("Lac", "ro", "fr");
        Assert.assertEquals("lac", res);
    }

    @Test
    public void testTranslateWord4(){
        String res = translator.translateWord("mergem", "ro", "en");
        Assert.assertEquals("walk", res);
    }

    @Test
    public void testTranslateWord5(){
        String res = translator.translateWord("mangent", "fr", "en");
        Assert.assertEquals("eat", res);
    }
}

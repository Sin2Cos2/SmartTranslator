import Server.Translator;
import org.junit.Assert;
import org.junit.Test;

public class TestTranslateSentence {
    Translator translator = new Translator();

    @Test
    public void testTranslateSentence1(){
        String res = translator.translateSentence("Dog eat cat", "en", "ro");
        Assert.assertEquals("câine eat pisică", res);
    }

    @Test
    public void testTranslateSentence2(){
        String res = translator.translateSentence("câine eat pisică", "ro", "en");
        Assert.assertEquals("dog eat cat", res);
    }

    @Test
    public void testTranslateSentence3(){
        String res = translator.translateSentence("машины не умеют летать", "ru", "fr");
        Assert.assertEquals("машины не умеют летать", res);
    }

    @Test
    public void testTranslateSentence4(){
        String res = translator.translateSentence("chats jeux et mangent", "fr", "ro");
        Assert.assertEquals("pisici jeux et mangent", res);
    }

    @Test
    public void testTranslateSentence5(){
        String res = translator.translateSentence("chats jeux et mangent", "fr", "en");
        Assert.assertEquals("cat game et eat", res);
    }

    @Test
    public void testTranslateSentence6(){
        String res = translator.translateSentence("chats jeux et mangent", "fr", "ge");
        Assert.assertEquals("chats jeux et mangent", res);
    }
}

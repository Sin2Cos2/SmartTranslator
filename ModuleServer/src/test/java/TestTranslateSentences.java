import Server.Translator;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class TestTranslateSentences {
    Translator translator = new Translator();

    @Test
    public void testTranslateSentences1() {
        ArrayList<String> res = translator.translateSentences("Dog eat cat", "en", "ro");
        Assert.assertEquals(3, res.size());
        System.out.println(res);
        Assert.assertEquals("lătrător eat mâță", res.get(1));
    }

    @Test
    public void testTranslateSentences2() {
        ArrayList<String> res = translator.translateSentences("Chat jeux", "fr", "en");
        Assert.assertEquals(1, res.size());
        System.out.println(res);
        Assert.assertEquals("cat game", res.get(0));
    }

    @Test
    public void testTranslateSentences3() {
        ArrayList<String> res = translator.translateSentences("машина летает все таки", "ru", "ro");
        Assert.assertEquals(1, res.size());
        System.out.println(res);
        Assert.assertEquals("машина летает все таки", res.get(0));
    }

    @Test
    public void testTranslateSentences4() {
        ArrayList<String> res = translator.translateSentences("pisici merg mergi", "ro", "fr");
        Assert.assertEquals(3, res.size());
        System.out.println(res);
        Assert.assertEquals("mistigri merg mergi", res.get(2));
    }
}

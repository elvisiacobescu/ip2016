package junit.test.cases;

import StringMatching.src.textmatchscore.JaroWinkler;
import org.junit.Test;

/**
 * Created by Mihai on 16.05.2016.
 *
 */
public class JaroWinklerTest {

    /**
     * Primeste ca input 2 stringuri de lungime mica, returneaza un indice de similaritate
     * calculat ca 1-sim, unde sim e obtinuta din functie
     *
     * @throws Exception
     */
    @Test
    public void getScoreForNullStrings() throws Exception {
        JaroWinkler jw = new JaroWinkler("", "");
        assert (jw.getScore() == 1);
    }

    @Test
    public void getScoreForProperStrings() {
        JaroWinkler jw = new JaroWinkler("a", "a");
        assert (jw.getScore() == 0);

    }

    @Test
    public void getScoreForDifferentChars() {
        JaroWinkler jw = new JaroWinkler("a", "B");
        System.out.println(jw.getScore());
    }

    @Test
    public void getScoreForTextbookExample() {
        JaroWinkler jw = new JaroWinkler("MARTHA", "MARHTA");
        assert (jw.getScore() == 0.03888893127441406);
    }
}
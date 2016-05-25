package junit.test.cases.textaligner;

import StringMatching.src.textaligner.KnuthMorrisPratt;
import org.junit.Test;

/**
 *
 * Created by Mihai on 16.05.2016.
 */
public class KnuthMorrisPrattTest {

    KnuthMorrisPratt kmp = new KnuthMorrisPratt("cocacola", "co");
    @Test
    public void doesItWork(){
        System.out.println("nobody will ever know");
    }
}
package TagTerminator;

import java.nio.file.Files;
import java.nio.file.Paths;


public class Base {

    protected static final String ENCODING = "utf-8";

    protected static void print(String text) {
        System.out.println(text);
    }

    protected static String getContent(String file) throws Exception {
        byte[] encoded = Files.readAllBytes(Paths.get(file));
        return new String(encoded, ENCODING);
    }
}

package io.github.nnovakova.opennlpner;

import opennlp.tools.namefind.*;
import opennlp.tools.util.Span;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class Validate {

    public static void main(String[] args) throws IOException {
        validate();
    }

    private static void validate() throws IOException {
        try (InputStream modelIn = new FileInputStream(Config.onlpModelPath)) {
            TokenNameFinderModel model = new TokenNameFinderModel(modelIn);
            NameFinderME nameFinder = new NameFinderME(model);


            String sentence = "Keine Mittellinienverlagerung.\n" +
                    "Keine intrakranielle Blutung.\n" +
                    "Kein klares fokales, kein generalisiertes Hirnödem.\n" +
                    "Derzeit keine eindeutige frische, keine grobe alte Ischämie.";;
            sentence = sentence.replaceAll("(?!\\s)\\W", " $0");
            String[] s = sentence.split(" ");

            // Get NE in sentence and positions of found NE

            Span[] nameSpans = nameFinder.find(s);
            System.out.println(Arrays.toString(nameSpans));
            String ent;

            for (Span nameSpan : nameSpans) {
                int entityLength = nameSpan.getEnd() - nameSpan.getStart();
                for (int i = 1; i <= entityLength; i++) {
                    ent = sentence.split(" ")[nameSpan.getStart()];
                    System.out.println(ent);
                }
            }

        }
    }
}

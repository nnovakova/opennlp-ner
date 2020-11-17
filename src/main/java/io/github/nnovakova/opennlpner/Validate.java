package io.github.nnovakova.opennlpner;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
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
            var model = new TokenNameFinderModel(modelIn);
            var nameFinder = new NameFinderME(model);
            var sentence = "Keine Mittellinienverlagerung.\n" +
                    "Keine intrakranielle Blutung.\n" +
                    "Kein klares fokales, kein generalisiertes Hirnödem.\n" +
                    "Derzeit keine eindeutige frische, keine grobe alte Ischämie.";
            ;
            sentence = sentence.replaceAll("(?!\\s)\\W", " $0");
            var words = sentence.split(" ");

            // Get NE in sentence and positions of found NE
            var nameSpans = nameFinder.find(words);
            System.out.println(Arrays.toString(nameSpans));

            for (Span span : nameSpans) {
                var ent = words[span.getStart()];
                System.out.println(ent);
            }
        }
    }
}

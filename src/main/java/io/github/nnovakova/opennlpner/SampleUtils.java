package io.github.nnovakova.opennlpner;

import opennlp.tools.namefind.NameSample;
import opennlp.tools.namefind.NameSampleDataStream;
import opennlp.tools.util.MarkableFileInputStreamFactory;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

class SampleUtils {

    static ObjectStream<NameSample> loadSampleStream() throws IOException {
        return loadSampleStreamFrom(Config.trainingDataFilePath);
    }

    private static ObjectStream<NameSample> loadSampleStreamFrom(String filePath) throws IOException {
        final var in = new MarkableFileInputStreamFactory(new File(filePath));
        return new NameSampleDataStream(new PlainTextByLineStream(in, StandardCharsets.UTF_8));
    }
}

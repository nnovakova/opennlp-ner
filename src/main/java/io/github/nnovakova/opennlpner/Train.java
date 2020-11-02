package io.github.nnovakova.opennlpner;

import opennlp.tools.namefind.*;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.TrainingParameters;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;

import static io.github.nnovakova.opennlpner.Config.onlpModelPath;

public class Train {

    public static void main(String[] args) throws IOException {
        TokenNameFinderModel model = trainModel();
        saveModel(model);
    }

    //Training Customization and training process

    private static TokenNameFinderModel trainModel() throws IOException {
        TokenNameFinderModel model;
        try (ObjectStream<NameSample> sampleStream = SampleUtils.loadSampleStream()) {
            final TrainingParameters params = new TrainingParameters();
            params.put(TrainingParameters.ITERATIONS_PARAM, 100);// number of training iterations
            params.put(TrainingParameters.CUTOFF_PARAM, 3);//minimal number of times a feature must be seen
            //params.put(TrainingParameters.ALGORITHM_PARAM, ModelType.MAXENT.toString());

            TokenNameFinderFactory nameFinderFactory = TokenNameFinderFactory.create(null, null, Collections.emptyMap(), new BioCodec());
            String type = null; // null value - to use names from corpus.
            model = NameFinderME.train("de", type, sampleStream, params, nameFinderFactory);
        }

        return model;
    }

    private static void saveModel(TokenNameFinderModel model) throws IOException {
        try (BufferedOutputStream modelOut = new BufferedOutputStream(new FileOutputStream(onlpModelPath))) {
            model.serialize(modelOut);
        }
    }
}
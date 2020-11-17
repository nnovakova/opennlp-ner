# OpenNLP NER

Learn and validate/test OpenNLP model to find named entities based on annotated corpus.

## Prerequisites

- Java 11
- Gradle

## Configuration

Add text file with annotated entities in OpenNLP at `src/main/resources/test_corpus.txt` path 
As an example you can use my project to create such file (annotated corpus):
[https://github.com/nnovakova/opennlp-annotator](https://github.com/nnovakova/opennlp-annotator)  

## Run

Run the following command to train machine learning model:

```bash
gradle -PmainClass=io.github.nnovakova.opennlpner.Train execute
```

Validate machine learning model:

```bash
gradle -PmainClass=io.github.nnovakova.opennlpner.Validate execute
```

It will create result file as `model.bin`.


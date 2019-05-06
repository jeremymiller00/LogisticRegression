# Differentiating Body Text from Non-Body Text to Enhance Key Statement and Key Word extraction

## Overview
Key Statement (sentence) and Key Phrase (word) endpoints are currently deployed for the ScholarOne manuscript submission process. The RAKE algorithm is used for Key Phrase extraction and the TexRank algorithm is used for Key Statement extraction. 

## Problem Context
Key Statements and Words should be derived from only the body text of a manuscript. Currently there is no differentiation between body text and non-body text in the input data. Identifying body text with a high degree of confidence, and providing a boost to the unsupervised algorithm score of a word or phrase in that case, would significantly improve the output of the both the RAKE and TextRank algorithms.

## Goals and Metrics
* Goals: produce a supervised model with can classify a sentence as body text or non-body text with a high degree of success.
* Metric: In this binary classification task, the class "body text" is defined as the positive class, since that is the class in which some further action will be take. Since the implementation will boost the scores of sentences classified as body text, two metrics will be key: Recall / Sensitivity in the positive class (of all actual body text sentences, what proportion are correctly identified by the model), and Specificity / True Negative Rate (of all sentences which are not actually body text, what proportion are classified). 
* Precision also provides a good summary metric in this case. Of all sentences classified as positive (and which will receive a boost), what proportion are actually positive. 
* Target Metrics: Recall 0.8, Specificity 0.8, Precision 0.8

## Non-goals
Since the implemented algorithms are unsupervised, there will be no reasonable metric by which to measure the change in performance if the body text classifier is implemented.

## Milestones
* Finish planning doc
* Extract labeled data
 * Body, title, abstract, citations
 * Body, not-body
* Featurize data
 * 
* Train Model
* Evaluate Model

## Testing
The model should function with text data (txt) in any state of organization (or lack of).

## Impact on Other Teams and Team Members
If successful, the algorithm will need to be implemented by Bihn Nguyen. The ScholarOne team may need to execute some changes to the UI. Hopefully users of the Key Extraction services will receive more useful and specific results.

## Open Questions
* How best to featurize the data?
* Will I be able to successfully train a model which meets the target metrics?
* Jury duty may delay deadlines

## Detailed Scoping and Timeline
* April 26, 2019: Finish planning document v1
* April 28, 2019: Extract Labeled data 
* April 29, 2019: Featurize data
* April 29, 2019: Train and evaluate model
--------
* May 1, 2019: Extract features and coefficients from Databricks model
* Get local service setup
* Write core algorithm in Java
* Integrate into existing service
* Test locally
* Submit pull request

## Code Planning and Pseudocode
Use databricks and labeled XML sample articles provided by Kay Shen.

## First Model
Used TF-IDF and dumped the data into a Random Forest and Logistic Regression. Both provided F1 scores >0.9, too good to be true. Realized I hadn't split the sections of text into sentences.

## Second Model
Split into sentences using regex rules from implemented TextRank model ("\\.|[!?]+"). Used TF-IDF featurization. Scores back down to Earth, but still good. Logistic Regression notably better with F1 score of 0.865 at a threshold of 0.421. I'm realizing that the TF-IDF featurization is going to be heavily dependant on the vocabulary being used, and hence a LOT of training data will be required to make this approach viable. Also, I'm not sure how this would get implemented with 10,000 or so features.

## Next: Train Logistic Regression Model and Add it to Java Code
* Will be a step in the middle of pre-processing

* Sentences will be tokenized, but not lemmatized, stop words should be intact

* TF provides same performance as TF-IDF

* How do I get the features (vocab words) out of Databricks?
 * Need vocab, and coefficients

## Algorithm
* For tokenized sentence, create a TF vector for that sentence
 * Based on extracted vocab

* Take dot product of TF vector and logit model coeficients
 * Add intercept

* Pass into logit function
 * Multiply the textrank score by this probability

## Steps
* Write simple version which compiles at the command line on my machine

## Tests
* Class variables are the correct type of object
* Scoring function works as expected
* SetCoeffs and SetVocab works as expected

## Code outline
* get model coefficients, intercept
* get POS tag list
* tag tokenized sentence
* vectorize tagged sentence 
* calculate score
* pass score through logit function to calcualate probability
* probability gets multiplied by TextRank score
 * for words, word score mutiplied by probability of origin sentence



import pandas as pd
from sklearn import feature_extraction, linear_model, model_selection, preprocessing
from sklearn.linear_model import LogisticRegression
import re
import nltk
import numpy
from nltk.corpus import stopwords
from nltk.stem import WordNetLemmatizer
import pickle

# Read in the train and test datasets
train_df = pd.read_csv("./dataset/train.csv")
test_df = pd.read_csv("./dataset/test.csv")
# Initialize a CountVectorizer object
count_vectorizer = feature_extraction.text.CountVectorizer()
# Preprocess the tweet text
def preprocess_tweet(tweet):
    # Remove hashtags, @ mentions, and URLs
    tweet = re.sub(r'#\S+', '', tweet)
    tweet = re.sub(r'@\S+', '', tweet)
    tweet = re.sub(r'https?://\S+', '', tweet)
    
    # Convert to lowercase and remove non-alphabetic characters
    tweet = tweet.lower()
    tweet = re.sub(r'[^a-z]', ' ', tweet)
    
    # Tokenize and remove stop words
    tokens = nltk.word_tokenize(tweet)
    stop_words = set(stopwords.words('english'))
    tokens = [token for token in tokens if token not in stop_words]
    
    # Lemmatize
    lemmatizer = WordNetLemmatizer()
    tokens = [lemmatizer.lemmatize(token) for token in tokens]
    
    return tokens
# Transform the training and test sets into matrices of token counts
train_vectors = count_vectorizer.fit_transform(train_df["text"])
test_vectors = count_vectorizer.transform(test_df["text"])
# Initialize a LogisticRegression Classifier object
clf = LogisticRegression()
# Fit the classifier on the training data
clf.fit(train_vectors, train_df["target"]) 
# Save the model to a file
with open('model.pkl', 'wb') as f:
    pickle.dump(clf, f)
scores = model_selection.cross_val_score(clf, train_vectors, train_df["target"], cv=3, scoring="accuracy")
scores = model_selection.cross_val_score(clf, train_vectors, train_df["target"], cv=3, scoring="f1")
print("Accuracy scores: ",scores)
print("F1 scores: ",scores)
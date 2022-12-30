from flask import Flask, request
from flask import jsonify
import pickle
from model import preprocess_tweet, count_vectorizer

app = Flask(__name__)

# Load the model from the file
with open('model.pkl', 'rb') as f:
    model = pickle.load(f)

@app.route('/predict', methods=['POST'])
def predict():
    # Get the tweet text from the request body
    tweet = request.json['tweet']
    
    # Preprocess the tweet text
    preprocessed_tweet = preprocess_tweet(tweet)

    # Convert the list of tokens into a single string
    preprocessed_tweet_string = " ".join(preprocessed_tweet)

    # Convert the preprocessed tweet into a matrix of token counts
    tweet_vector = count_vectorizer.transform([preprocessed_tweet_string]).toarray()
    
    # Make a prediction for the tweet
    prediction = model.predict(tweet_vector)
    
    # Convert the prediction to a Boolean value
    if prediction[0] == 1:
        relevant = True
    else:
        relevant = False
    
    # Return the prediction as a JSON response
    return jsonify({'relevant': relevant})
    
@app.route('/', methods=['GET'])
def default_route():
    return "Hello MQL, I'm a trained model"

if __name__ == '__main__':
    app.run(host='0.0.0.0', debug=True)
FROM continuumio/anaconda3

# install dependencies
RUN conda install -y scikit-learn pandas flask numpy nltk
RUN python -m nltk.downloader punkt
RUN python3 -m nltk.downloader wordnet
RUN python3 -m nltk.downloader stopwords
RUN python3 -m nltk.downloader omw-1.4

# Set the working directory to /app
WORKDIR /app

# copy model code to the container
COPY . /app

# run the model when the container is launched
CMD ["python", "app.py"]

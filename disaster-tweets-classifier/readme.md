1. 
docker build -t tweet-disaster-api .

2.
docker run -p 5000:5000 -d tweet-disaster-api

3. default route via :
curl http://localhost:5000/

4. prediction route via :
curl -X POST -H "Content-Type: application/json" -d '{"tweet": "RT @user: A major earthquake has struck the coast of Indonesia. Many people are injured  and buildings have been damaged. Please stay safe and follow the advice of local authorities."}' http:/localhost:5000/predict

5.

ip adress : 20.122.203.176

currentely deployed image :

curl -X POST -H "Content-Type: application/json" -d '{"tweet": "RT @user: A major earthquake has struck the coast of Indonesia. Many people are injured 
 and buildings have been damaged. Please stay safe and follow the advice of local authorities."}' http:/20.122.203.176:5000/predict
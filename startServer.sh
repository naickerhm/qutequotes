#!/bin/sh

echo "Welcome to QuteQuotes"

echo "Packaging server into Docker image"
cd server
mvn clean
mvn compile
mvn package
cd ..
docker build -t quotes .
docker run -p 5000:5000 quotes


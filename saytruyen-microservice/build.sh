#!/bin/bash

set -e

VERSION="v1"
REPO="buianhtai"

echo "=== Building admin-service ==="
cd ./admin-service
mvn clean install -DskipTests
docker build -t $REPO/saytruyen_admin-service:$VERSION .
cd ../

echo "=== Building user-service ==="
cd ./user-service
mvn clean install -DskipTests
docker build -t $REPO/saytruyen_user-service:$VERSION .
cd ../

echo "=== Building story-service ==="
cd ./story-service
mvn clean install -DskipTests
docker build -t $REPO/saytruyen_story-service:$VERSION .
cd ../

echo "=== Building discovery-server ==="
cd ./discovery-server
mvn clean install -DskipTests
docker build -t $REPO/saytruyen_discovery-server:$VERSION .
cd ../

echo "=== Building api-gateway ==="
cd ./api-gateway
mvn clean install -DskipTests
docker build -t $REPO/saytruyen_api-gateway:$VERSION .
cd ../

echo "✅ All services built and Docker images created successfully!"
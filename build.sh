#!/bin/sh
cd ./Game
./gradlew fatJar
cd ../
cp ./Game/build/libs/Game-1.1-RELEASE-all.jar ./game-1.1.jar


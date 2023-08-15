# DAX Technical Test

The technical test starting point for all engineers applying for a role within the DAX Exchange teams.

# Table of Contents

1. [The Task](#the-task)
2. [Prerequisites](#prerequisites)
3. [Building the project](#building-the-project)
4. [Running Each Component](#running-each-component)

## The Task

TODO: explain the problem

## Prerequisites

You need at-least JDK 17 installed on your local machine. Recommendation: [sdkman](https://sdkman.io/)

## Building the project

Run the following command from the root directory to build all projects.

```bash
./gradlew build
``` 

## Running each component

Both the dax-client and dax-server projects build executable JARs. To run each of them respectively:

```bash
./gradlew build
java -jar ./dax-server/build/libs/dax-server-0.0.1.jar
java -jar ./dax-client/build/libs/dax-client-0.0.1.jar 
```

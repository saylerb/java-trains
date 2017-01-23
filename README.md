# Java Trains

This is an problem involving a weighted directed graph. The example graph edges and weights are: `AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7`.

![graph](http://i.imgur.com/TMMGXpi.png)

## Instructions

Make sure you have Java 8 and Gradle installed.
Exercism.io has a [good page on how to install these across platforms](http://exercism.io/languages/java/installing).

1. Clone this repo and change into the directory:

    ```sh
    git clone git@github.com:saylerb/java-trains.git
    cd java-trains
    ```

1. Build the project using Gradle. This should create a directory called `build`.

    ```sh
    gradle build
    ```

1. Use the `run` Gradle task to generate output. This task will run a main function in `src/main/java/Runner.java` file. The program reads from `input.txt` and write to `output.txt` in the project root directory.

  ```sh
  gradle run
  ```

1. Run tests:
  * Run `gradle test` to run all the tests.
  * Run `gradle cleanTest test` run tests even if they are up-to-date.

1. Run [checkstyle](http://checkstyle.sourceforge.net/)
  * To run checkstyle, use the `check` task: `gradle check`.

## Description

The program runner file is currently set up to run the following
calculations on the example graph:

1. The distance of the route A-B-C.
2. The distance of the route A-D.
3. The distance of the route A-D-C.
4. The distance of the route A-E-B-C-D.
5. The distance of the route A-E-D.
6. The number of trips starting at C and ending at C with a maximum of 3 stops.
   In the sample data below, there are two such trips:
	C-D-C (2 stops). and C-E-B-C (3 stops).
7. The number of trips starting at A and ending at C with exactly 4 stops.  In
   the sample data below, there are three such trips:
	A to C (via B,C,D); A to C (via D,C,D); and A to C (via D,E,B).
8. The length of the shortest route (in terms of distance to travel) from A to C.
9. The length of the shortest route (in terms of distance to travel) from B to B.
10. The number of different routes from C to C with a distance of less than 30.
	In the sample data, the trips are:
	CDC, CEBC, CEBCDC, CDCEBC, CDEBC, CEBCEBC, CEBCEBCEBC.

The main classes are below:
```
src/main
└── java
    ├── DistanceCalculator.java
    ├── FileReader.java
    ├── FileWriter.java
    ├── Graph.java
    ├── GraphBuilder.java
    ├── PathFinder.java
    ├── Route.java
    ├── Runner.java
    └── ShortestPathFinder.java
```
* DistanceCalculator - calculates the distance between two nodes.
* FileReader - reads the input from a text file into a string.
* FileWriter - takes a list of strings and writes them to an output file line-by-line.
* Graph - encapsulates the graph data structure and provides methods to interact with it
* GraphBuilder - takes a string of graph edges and weights and builds a HashMap representing the graph.
* PathFinder - uses depth-first search to calculate paths with a maximum number of stops, an exact number of stops, or up to a certain distance.
* Route - contains the parameters used in the PathFinder class' recursive search methods
* Runner - runs the program with the calculations listed above. Reads from the`input.txt` file, instantiates the correct objects, and runs 10 calculation methods, writing the output to `output.txt`.
* ShortestPathFinder - uses a modified breath-first search to find the shortest path between two nodes.

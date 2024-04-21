
------------------------------------------------------------------------------------------------------------------------
TASKS IN SHORT
------------------------------------------------------------------------------------------------------------------------
1) Graph implementation
2) BFS and DFS implementation
3) Kruskal's algorithm implementation
4) Bellman Ford's and Dijkstra's algorithm implementation
5) Topological sort implementation
6) Bridge finder implementation (finds the edge in a graph that is a bridge edge, if there is one)

------------------------------------------------------------------------------------------------------------------------
GENERAL NOTES
------------------------------------------------------------------------------------------------------------------------
I use JDK 21

For everything to work as expected, every graph should be filled with vertices up to the max number of allowed vertices
(can also be filled with vertices up to a lower nr than the max number, but not more than the max number) in increasing order.

That's why there is a function "fillGraph(int limit)" that should be used whenever you want to
create/test a graph. The function will fill the Graph with vertices up to the specified limit. (The limit can't exceed
the max number of vertices otherwise an exception will be thrown). The number of Vertices will therefore be
equal to the number used in "FillGraph(int limit)" - 1.

There is a version of fillGraph() in UndirectedGraph and DirectedGraph. It will enable the vertex
degrees according to the type of graph (degree for Undirected, in|out degree for Directed).

------------------------------------------------------------------------------------------------------------------------
Helper Classes
------------------------------------------------------------------------------------------------------------------------
CSVReaderWriter, InfoList, Timer and Functions are all used in the experiment to find times and write them to the files.

CSVReaderWriter is also used in GraphBuilder (Task5) to read from the source file and create the vertices.

GraphPrints is used by the Graph class for printouts, and it uses the iterators of said class.

MyPriorityQueue is my implementation of the priority queue that I use in Kruskal(Task 3).
UnionFind is the implementation of UnionFind from Morgans slides (also used in Task 3).

------------------------------------------------------------------------------------------------------------------------
Tasks
------------------------------------------------------------------------------------------------------------------------
---- Task 1) ----
Has a test class where all the implementations are tested.
NrE (number of edges) is both in Directed and Undirected Graph, because the formula is different for each.
The iterator for AdjacencyNodes is in the AdjacencyList class.
The iterator for Edges is in the Vertex class.
The iterator for Vertices is in the Graph class.

---- Task 2) ----
The main functions are inside the classes.
DFS and BFS can be called from the Graph classes (this is what I understood should be done). They can also be called
independently just by passing the desired graph as argument.
There is a method called FindAll which is not required in the assignment, but it turns out to be useful in other tasks
such as Task6

---- Task 3) ----
The main functions are inside the classes.

---- Task 4) ----
The main functions are inside the classes and the "Experiment" class is where I set up the experiment for the report.
I talked to some other students and apparently Morgan said that we are allowed to use the inbuilt PriorityQueue. So
I used the inbuilt version for Dijkstra. Additionally, there is a class called Pair that contains weight and Vertex number
to be used in the PriorityQueue.

---- Task 5) ----
The main function is in the TopSort class.

---- Task 6) ----
The main function is in the class. I added a picture to show the graph that I used to test the function.

--------------------------------------------------------------------------
REMEMBER TO FILL THE GRAPH BEFORE YOU TEST IT!! otherwise it will crash :)
--------------------------------------------------------------------------
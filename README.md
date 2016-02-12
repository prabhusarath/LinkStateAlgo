A Dijkstra’s algorithm should be applied to find shortest path between two entities: source and destination nodes and the minimum cost to travel from source to destination. Link state routing has been implemented in JAVA using NetBeans IDE.


# LinkStateAlgo
Dijkstra's Algorithm written in java.

With Input as Any File Location with Network Cost
(Example is Added).

Input file containing the network information will be loaded, Shortest path is calculated from the router , Implemented code finds out all those possibilities for a given network and will display only last calculated path for the connection table interfaces and then finds out the optimal path between two selected routers (source and destination nodes) .

Cost from the Network Topology File are loaded into Dimensional Network Array. The Path, Cost of the travel and Visited Status are Stored in another 2-Dimensional Array based on Computations done on the Values of Network Array, these values are printed to show the path of travel and cost of travel.


Sample Cost Mathrix:

0 -1 5 1 -1
-1 0 -1 7 9
5 -1 0 -1 4
1 7 -1 0 2
-1 9 4 2 0

The Above Matrix is a 5 Node Network ,Which are Connected.
0 -> Self , -1 -> Not Connected and Other Values are Cost

Options like Creating Connection Table,Shortest Path, Deleting Node from Network has Been Added.

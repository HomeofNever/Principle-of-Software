# Problem 3

There are three kinds of implementation. Here I have chosen adjancent list. The pros and cons are listed below.

## Collection of edges

### Advantages

- Less complexity  
  The ADT of the graph will be a Set. No need for more complex data structure
- The data structure inituitively listed all edges in one set, fulfilled the interface requirement
- The rep of this implementation will be true  
   As long as the edge rep kept, this ADT keeps rep when all the time (with Set implementation, kept graph identical edge free)

### Disadvantages

- Unable to efficiently listed all nodes in the graph  
  Have to go through all edges each time and create a temperory set to avoid dumplicates
- Unable to efficiently locate all edges of a parent node: looks up requireds to loop through each edge
- Unable to represent 
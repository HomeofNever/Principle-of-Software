# Problem 2

## Write up

The strategy when doing blackbox testing is to analysis different path the methods possibly go through.

### Edge

There are several differnt kind of edges:

- reflective/normal
- label with different lengths

When writing the test cases, I have 

- Mixed comparison between reflective and normal edge
- Compare edge with different label length only
- Compare edge with different node and label

### Graph

For graph, I have also concluded several different conditions:

- Add same edge
- Add same edge with different label
- Add different edge with same label
- Add reflective edge

I have checked if the rep keep true after these condition in the test case by constructing its desired result directly and compared them with the method output



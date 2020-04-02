# Problem 2

## Write up

The strategy when doing blackbox testing is to analysis different path the methods possibly go through.

The basic idea here I do is to constructing and cross testing objects with mixed criterias.

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

### GraphWrapper

Since Graph has nearly identical test case with Graph, The strategy I use is nearly the same, except that in order to test iterator, I create a custom method to go through the iterator so that I can compare the result with the answer set.

### Additional test

I have added some test case over the `hashCode`, which is required when using Set.


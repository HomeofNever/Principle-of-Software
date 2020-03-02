# Problem 2

## Question 1

- Array Representation

  - Advantages

    - It does not need to have a specific field to represent each coefficient with its degree. The array index implies the exponents
    - It initializes all degree before each action, so there is less edge condition

  - Disadvantages
    - Zero poly is a special case under such representation. Each time we need to check both array and the degree field.
    - Not resizeable: if we need to decrease/increase degree, we need to rebuild the whole array be copying each object into the new array
    - Overhead of high degrees: we need to fill 0 for non-existing degree. i.e `x^100` will create array size of 101 and only the last one will be filled

- List Representation

  - Advantages

    - List is resizeable: we don't need to copy each element when increasing/decreasing degree
    - Zero is not special case: Zero can be identified by only look at the size of the list only

  - Disadvantages

    - Extra overhead: each item is an object that has its own fields
    - Extra access time: exponents may not be listed in order, or not created
    - Extra edge conditions when doing operation since objects are not created for non-existing exponents

## Question 2

- Add the end of each constructors
  - it is the only place where a RatPoly may be invaild (there is no mutator in the class)
  - Each operations that creates new RatPoly will still go through constructors, which is rep checked as well

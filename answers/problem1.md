# Answer 1

## Problem 1

- creator

  - `RatNum(int n)`
  - `RatNum(int n, int d)`

- observer

  - `isNaN()`
  - `isNegative()`
  - `isPositive()`
  - `compareTo(RatNum rn)`
  - `doubleValue()`
  - `intValue()`
  - `floatValue()`
  - `long longValue()`
  - `hashCode()`
  - `equals(/*@Nullable*/ Object obj)`
  - `toString()`
  - `valueOf(String ratStr)`

- producer

  - `negate()`
  - `add(RatNum arg)`
  - `sub(RatNum arg)`
  - `mul(RatNum arg)`
  - `div(RatNum arg)`

- mutator
  None

## Problem 2

`checkrep()`[check rap invariant] is called in every constructor, which means that `this` object will only be created when it is vaild, so `this` won't be null at any time.

## Problem 3

- Because it is a factory method that creates the object itself.
- We can have a constructor that accepts a string and we can also create object as the same.

## Problem 4

The @modified is none in all these methods, so the method should not change the object fields.

## Problem 5

Since there is no mutator in the class, the only way rep invariant can be violated is when the object is created, which is done by the constructors.

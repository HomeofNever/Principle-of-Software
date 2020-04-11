# Problem 1

## Question 1

```
class Y extends X
class Z extends Y

class A {
        Object m(X y, String s);
}
```

### `X m(X y, String s)`

It is function subtypes of `A.m`, and this should be a override in Java.

### `Y m(Object y, Object s)`

It is a function subtype of `A.m`, and this should be a overload in Java.

### `Z m(Y y, String s)`

It is not a function subtype of `A.m`, and this should be a overload in Java

## Question 2

### `Triangle` and `IsoscelesTriangle`

It is not the true subtype of `Triangle` because `setSides` in `IsoscelesTriangle` cannot substitute the one in Triangle as it does not really make use of `int c`.

### `Vertebrate` and `Squid`

It is not the true subtype of `Vertebrate`, since the post-condition of method `int neckBones()` in class `Squid` does not stronger than `Vertebrate`. The client would be suprise by return value `0`.

### `Vertebrate` and `Human`

It is the true subtype of `Vertebrate` as the post-condition is stronger in `Human` while others remain the same. The overall specs is stronger comparing with `Vertebrate`.

### `Bicycle` and `MountainBike`

It is the true subtype of `Bicycle` as `MountainBike` keeps all original methods' specs the same and adding new method/field. The `MountainBike` can be safely treated as `Bicycle`.

### `Account` and `ConcurrentAccount`

It is not the true subtype of `Account` as `ConcurrentAccount` throws exception which will suprise client. As the fact that it cannot use as substitution, `ConcurrentAccount` is not the true subtype.
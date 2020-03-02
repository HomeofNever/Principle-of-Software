# Problem 3

Write a pseudocode algorithm for division. State the loop invariant for the main loop and prove partial correctness. Write your answer in the file answers/problem3.pdf

When writing pseudocode use symbols +,-,* and / to express rational number and polynomial arithmetic. You may also use u[i] to retrieve the coefficient at power i of polynomial u, as well as c*x^i to denote the single-term polynomial of degree i and coefficient c. Important: write your pseudocode, invariants and proofs first, then write the Java code. Going backwards will be harder.

Use the following definition of polynomial division:

    Given two polynomials u and v, with v != "0", we can divide u by v to obtain a quotient polynomial q and a remainder polynomial r satisfying the condition u = "q * v + r", where the degree of r is strictly less than the degree of v, the degree of q is no greater than the degree of u, and r and q have no negative exponents.

    For the purposes of this class, the operation "u / v" returns q as defined above.

    The following are examples of division's behavior:

        (x^3-2*x+3) / (3*x^2) = 1/3*x (with r = "-2*x+3").
        (x^2+2*x+15) / (2*x^3) = 0 (with r = "x^2+2*x+15").
        (x^3+x-1) / (x+1) = x^2-x+2 (with r = "-3").

    Note that this truncating behavior is similar to the behavior of integer division on computers.

## Pseudocode

let u, v as polynomials, goal u/v

```


```

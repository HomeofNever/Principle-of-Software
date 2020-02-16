function Factorial(n: int): int
  requires n >= 0
{
  if n == 0 then 1 else n * Factorial(n-1)
}

method LoopyFactorial(n: int) returns (u: int)
  requires n >= 0
  ensures u == Factorial(n)
  {
    u := 1;
    var r := 0;
    while (r < n) 
      invariant 0 <= r <= n
      invariant u == Factorial(r)
    {
      var v := u;
      var s := 1;
      while (s <= r) 
        invariant s <= r + 1
        invariant u == v * s
      {
        u := u + v;
        s := s + 1;
      }
      r := r + 1;
      assert u == v * r;
    }
  }
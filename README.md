# Gaussian-Jordan Elimination
A program that takes in an augmented matrix and solves it by putting it into RREF (Reduced Row-Echelon Form).
It tells whether the matrix can be solved or not and the solutions if possible.

## Disclaimer
This is meant to be used as a coding practice for me. ***DO NOT USE THIS PROGRAM TO CHEAT IN ANY EVALUATIONS!!***
**I am NOT responsible for any of your academic misconduct charges because of using this program.**

## How to Use
This program can solve systems of equations like this.
```
(x1) + 2(x2) + 3(x3) = 5
7(x1) + 4(x2) + 8(x3) = 16
2(x1) + 4(x2) + 6(x3) = 20
```
1. Specify the number of rows and columns. Let *m* and *n* be the number of rows and columns respectively.
E.g. `3 3` for 3 columns and 3 rows, and `2 4` for 2 columns and 4 rows.
2. For each *m* rows, insert the coefficients of the variables that belong in that rows and *nth* column. There should be *n + 1* columns in each row, as the element in the *(n + 1)th* column is one of the constants. **0's must be included as coefficients!**
E.g. `1 2 4 8 16` will make the row in the augmented matrix `[1 2 4 8 | 16]` with 16 being the constant. This example row represents the equation `(x1) + 2(x2) + 4(x3) + 8(x4) = 16`. 
3. Watch the magic happen! You will see the matrix you initially built and the result of the operation.
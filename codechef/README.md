## CodeChef solutions

CodeChef typically feeds inputs to the program via standard input and checks the expected results via standard output. To make it easier and relatively faster to read inputs, the [new_solution](../scripts/new_solution.py) script generates a `Solution` class for CodeChef that provides a `FastReader` utility.

To verify a solution, I typically create `input.in` and `expected.out` to contain test inputs and the expected results, respectively. Then I execute this command to check if the expectations match the actual results:

```bash
java -ea Solution < input.in | diff expected.out - 
```

This would not print anything if the results match.

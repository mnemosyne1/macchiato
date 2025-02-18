*Full text of assignment is available (in Polish) under these links: [part 1](assignment1.md), [part 2](assignment2.md).*

# General idea
Create an implementation of Macchiato language, which would support:
- instructions such as: block creation, for loop, if (condition), variable declaration, variable assignment, variable printing
- expressions such as: literals, integer variables, sum, difference, product, division or modulo of two expressions
- procedures, consisting of header (including procedure's name and parameters) and content (procedure's definition)
- debugger allowing user to: move forward a given number of steps, display current variables values, continue/exit, dump program memory (currently visible procedures declaration and current values of variables)

In addition to that, provide a SDK for Macchiato, so that programs can be created easily by calling a sequence of methods of Block Builder (see usage in [Main file](src/Main.java)).

The project consisted of two phases, therefore in some places there are references to Macchiato 1.0 (first phase). They shall be considered obsolete and would be removed if I were to do it as a project for myself.
# MazeSolver

Program that generates mazes, solves them, and provides examples of how printing functionality would be added.

The most recent and primary new feature of this code is the ability to have users write a step by step maze solving algorithm.  This could be used as a coding exercise for personal gain or as an assignment for a class.

Please note that much of this code is messy and in need of refactoring; however, the functionality is there.

Please feel free to submit any issues, requested features, pull requests, or sample maze solving algorithms.

This project is licensed under the MIT License.

# Building and Running

1. Open a shell and navigate to the root directory of this repository (the directory containing this file, `README.md`)

2. Compile all files in the source package: `javac src/coplan/mazes/*.java`

3. Run the project's main class (Driver): `java -cp ./src coplan.mazes.Driver`

4. Once the UI loads, generate a maze using the button on the right side.  **Important Note:** Do not choose a complex maze!  Complex maze generation is not fully stable.

# Writing a Solving Algorithm

Users may put their solution in the [UserSolution.java] file.  They may create any other files as necessary for their solution, however, the code that is called by the user interface is the `public static void run(MoveAPI moveAPI)` method found in [UserSolution.java], as denoted by the existing comment.  Users have full access to the methods provided by the [MoveAPI.java] interface through the MoveAPI object that is passed into the run method as a parameter.

[UserSolution.java]: https://github.com/AaronCoplan/MazeSolver/blob/master/src/coplan/mazes/UserSolution.java
[MoveAPI.java]: https://github.com/AaronCoplan/MazeSolver/blob/master/src/coplan/mazes/MoveAPI.java

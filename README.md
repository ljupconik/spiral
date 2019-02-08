# spiral

## Use Scala to create a console program in Scala that will answer the following question: 

Fill a two-dimensional NxN grid with NxN numbers (N is odd and the numbers are monotone increasing). 
Start in the middle/center  and walk counter-clockwise to the outside. The middle square starts with 1.
```
17  16  15  14  13  .
18   5   4   3  12  .
19   6   1   2  11  .
20   7   8   9  10  27
21  22  23  24  25  26
```
Now given a location (one of the cell-values), calculate the Manhattan-Distance to the center (how many steps are needed to get to the center with coordinates Point(0,0)) 

## App Requirement

How many steps are required to go from location 368078 to the center?

### Prerequisites & bulding the example
The programing language used is Scala 2.12, and uses the build tool sbt 1.2.8
The only library dependency outside the standard Scala libraries is ScalaTest 3.0.5

Clone this repository in a fresh directory:
```
% git clone https://github.com/ljupconik/spiral.git
% cd spiral
```
Compile the project with the following command:
```
% sbt clean compile
```
Then run it by specifying the positive Integer Number from the spiral as an Argument,
to calculate the Manhattan-Distance from it to the center
```
% sbt "run 368078"
-- Output:

The Manhattan distance between 368078 and 1 = 371
```

## How it works

The program uses a Finite State Machine , which has the folowing sequence of states :
```
LowerRightCorner -> RightEdge ->  UpperRightCorner ->  UpperEdge  -> UpperLeftCorner -> LeftEdge -> LowerLeftCorner -> LowerEdge 
     ^                                                                                                                     |
     |											                                   |
     <-------------------------------------------------------<-----------------------------------------------------------<--

     1                  2                3                 4              5                6              7                8

     9              10,11,12            13               14,15,16        17             18,19,20         21           22,23,24

    25              26,27,...
```

which is done using the FsmState abstract class , PointState  which is a subtype of Enumeration,  and the 2 domain model case classes 
Point and PointWithState as a subtype of FsmState.  PointWithState has a companion object that defines the FSM sequence logic.
The important thing to notice here is that every round ends when the number reaches the LowerRightCorner state. At that point there is always a move_right method call which takes the spiral into the next round.

Finally the method manhattan_distance(number:Int ) from CalcUtils which takes the positive Integer number from the spiral as input by using a @tailrec loop method,
finds the coordinates of the NUMBER , and calculates the Manhattan-Distance to the center.
It also guards against non-positive integer input in the function.

The Main object with its main method also guards against not specifying exactly one Argument when running the program , as well as, not specifying a non-integer Argument.

Tests are provided for all the functionality to ensure correctness of the program.

In the FsmStateSpec test suite there is a method - manhattan_distance_seq that is used to verify the correctnes of all Numbers with corresponding Points , PointStates and Manhattan-distances from the center.
The same method is also utilized to print the grid on the console when passed the argument (print_round = true)
The method print_grid ensures the proper alignment and prints the whole NxN grid.


## Testing
### Running the tests
Run the test suite to verify correct behaviour.  From the command line:
```
% sbt test
```
### Test Coverage Report
To measure test coverage, this app uses the 'scoverage' SBT plugin.
To create the report, from the command line:
```
% sbt coverage test coverageReport
```

## Author
* [Lyupcho Nikolov](mailto:ljupconik@gmail.com)


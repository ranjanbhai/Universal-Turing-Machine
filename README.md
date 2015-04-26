# Universal-Turing-Machine
Code for Universal Turing Machine in JAVA

The UTM class loads external machine configurations from the machines folder. A machide description is a txt file with multiple lines. Each line containing 5 space separated values as follows:
  PresentState  InputSymbolFromTape   ReplaceWithTapeSymbol   MoveHead  NextState
  Ex.: 
        0 a b L 1
  Explanation:
  The present state is 0 and 'a' is read from the tape. 'b' is the symbol that replaces 'a'. After writing, the head moves Left   ('L') and the state changes to 1.

The UTMDriver is a Demo class that extends the functionality of the UTM code. Once you understand the UTM.java code, you can write your own Driver class.

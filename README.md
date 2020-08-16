# Decathlon-Calculator
Decathlon is a combined event in athletics with ten events. Events are usually competed on two consecutive days on track or field. Each event is scored according to a point system and points achieved determine the winner. The point system is based on the result not the position in the event. Scoring over 9000 points is really rear and has been done only a few times. The list for the best results all time is available on IAAF's site. 


This calculator allows you to calculate decathlon score.


## Stack 
  1. [Java SE 8]
  1. [Maven]
  1. [JUnit]
  
  ## How  `Build and Run` project
  
   ```jsx
    // Open root directory the project and excute mvn command
    mvn clean install
    
    // After success building   you can run project
    // Go in 'target' folder you will see decathlon-calc-1.0-jar-with-dependencies.jar file , try run it
	// Here test file src\main\resources\data\results.csv you can use it to test program

    java -jar .\decathlon-calc-1.0-jar-with-dependencies.jar ".\results.csv"
    
	// After executing the program, you will see a file with results,   "result.xml"
    
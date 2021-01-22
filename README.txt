README Includes: 1. How to compile the program
                 2. How to run the program in Interactive Mode
                 3. How to run the program in Report Mode
                 4. How to run each UnitTestX.java
                 
**************************** HOW TO COMPILE THE PROGRAM ****************************

- Copy the command below (Line 7) and paste it on the terminal then press enter
alias compile='javac -cp .:junit-4.10.jar:json-20200518.jar *.java'

!! NOTE: This command will create a shortcut key for compilation


- Now the command for compiling is represented by 'compile'

- To compile the program, input 'compile' and press enter on the terminal



********************** HOW TO RUN THE PROGRAM (Interactive Mode) *************************

- Copy the command below (Line 19) and paste it on the terminal then press enter
alias irun='java -Xss512m -cp .:json-20200518.jar cryptoGraph -i'

!! NOTE: This command will create a shortcut key for running the menu with interactive mode


- Now the command for running in interactive mode is represented by 'irun'

- To run the program with interactive mode, input 'irun' and press enter on the terminal

***************************** HOW TO RUN THE PROGRAM (Report Mode) *******************************

- Copy the command below (Line 37) and paste it on the terminal then press enter
alias rrun='java -Xss512m -cp .:json-20200518.jar cryptoGraph -r assetFile.json Trade.json'

!! NOTE: This command will create a shortcut key for running the menu with report mode

- Now the command for running in report mode is represented by 'rrun'

- To run the program with report mode, input 'rrun' and press enter on the terminal



********************************* HOW TO RUN EACH UnitTestX.java *****************************************

- Copy the command below (Line 42) and paste it on the terminal then press enter
alias runtest='java -Xss512m -cp .:junit-4.10.jar:json-20200518.jar org.junit.runner.JUnitCore X'

!! NOTE: * X here refers to the name of the Test Class for that particular .java file

- Now the command for running particular X test class is represented by 'runtest'

- To run the test program, input 'runtest' and press enter on the terminal

- A Report will be shown on the terminal screen.
  If all PASSED, the screen will display Number of Tests with 'OK'
  If FAILED, the screen will indicate which line in the test FAILED

- All classes run in such a way EXCEPT: UnitTestDSALinkedList.java
                                        UnitTestUserInterface.java
                                        UnitTestFileIO.java

                                     ** These classes will run as default method **
                                     ( compile: javac UnitTestX.java, run: java UnitTestX )
                                     Where X is the name of the class
                                        

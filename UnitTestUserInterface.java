/*
Program: UnitTestDSAGraphEdge.java
Author: Calmen Chia Kai Fong
Date: 16 October 2020
Purpose: Test Harness for DSAGraphEdge.java
*/

public class UnitTestUserInterface
{
    public static void main(String[] args)
    {
        String str = UserInterface.userInput("String: ");
        System.out.println(str);
        System.out.println("------------------------------");
        int num = UserInterface.userInput("Integer (1-10): ", 1, 10);
        System.out.println(num);
    }
}


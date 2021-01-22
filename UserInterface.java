/*************************************************
* Program: UserInterface.java                    *
* Author: Calmen Chia Kai Fong                   *
* Purpose: Handle all user input of the program  *
* Date Created: 01-07-2020                       *
**************************************************/

/*                                                 *
    This class comprise externally-obtained code 
*   Obtained from Calmen Chia Kai Fong             *
    (Accessed on 01 July 2020)
    Which is done for the purposes of Workshop 7, 
*   in PDI (Programming Design and Implementation) *
    unit     
*                                                  */

import java.util.*;
import java.lang.*;

public class UserInterface
{
    public static final double TOL = 0.001;

    // SUBMODULE: userInput
    // IMPORT: prompt (String), lower (Integer), upper (Integer)
    // EXPORT: num (Integer)
    // ASSERTION: Take input of user within lower to upper
    public static int userInput( String prompt, int lower, int upper )
    {
        Scanner sc = new Scanner(System.in);
        int num = 0;
        boolean stop = false;

        do
        {
            System.out.print( prompt );
            prompt = "Invalid user input!\nEnter between " + lower + " and " + upper + ": ";
            try
            {
                num = sc.nextInt();
                stop = true;
            }
            catch(InputMismatchException e)
            {
                sc.next();
            }
        }while( (num < lower) || (num > upper) || !stop );
        return num;
    }

    // SUBMODULE: userInput
    // IMPORT: prompt (String)
    // EXPORT: str (String)
    // ASSERTION: Take user input as String
    public static String userInput( String prompt )
    {
        Scanner sc = new Scanner(System.in);
        System.out.print( prompt );
        String str = sc.nextLine();
        return str;
    } 
}


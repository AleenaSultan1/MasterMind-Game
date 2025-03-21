/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Aleena Sultan
 * Date: 20/03/2025
 * Time: 11:27 am
 *
 * Project: csci205_hw
 * Package: PACKAGE_NAME
 * Class: CodeBreaker
 *
 * Description:
 *
 * ****************************************
 */

package org.kcas_mastermind;
import java.util.Scanner;

public class CodeBreaker {
    private String userCode;
    public CodeBreaker() {
        this.userCode = "";
    }

    public String getUserInput(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter user code: ");
        userCode = scanner.nextLine();
        return userCode;
    }

    public CodeValue sendUserCodeToCodeMaker(){
        return CodeValue.parseString(userCode);
    }


}





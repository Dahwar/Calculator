/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.alsace.lacroix.utils;

import fr.alsace.lacroix.analyser.Priority;
import fr.alsace.lacroix.lexer.LexerGrammar;

/**
 *
 * @author Florent
 */
public class Operator {
    public static int getPriority(String operator) {
        switch(operator) {
            case LexerGrammar.PLUS : 
                return Priority.OPERATOR_PLUS;
            case LexerGrammar.MINUS : 
                return Priority.OPERATOR_MINUS;
            case LexerGrammar.MULTIPLY : 
                return Priority.OPERATOR_MULTIPLY;
            case LexerGrammar.DIVIDE : 
                return Priority.OPERATOR_DIVIDE;
            case LexerGrammar.POWER :
                return Priority.OPERATOR_POWER;
            default : 
                return -1;
        }
    }
}

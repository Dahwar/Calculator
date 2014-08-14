package fr.alsace.lacroix.lexer;

import fr.alsace.lacroix.utils.StringUtils;
import fr.alsace.lacroix.utils.Token;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Florent
 */
public class Lexer implements Iterable<Token> {
    
    public static final int PARSE_SUCCESS = 0;
    public static final int BUFFER_IS_NOT_NUMERIC = 1;
    public static final int PLUS_AFTER_ILLEGAL_CHARACTER = 2;
    public static final int MINUS_AFTER_ILLEGAL_CHARACTER = 3;
    public static final int MULTIPLY_AFTER_ILLEGAL_CHARACTER = 4;
    public static final int DIVIDE_AFTER_ILLEGAL_CHARACTER = 5;
    public static final int CLOSING_PARENTHESIS_AFTER_ILLEGAL_CHARACTER = 6;
    public static final int DOT_WITHOUT_A_NUMBER = 7;
    public static final int UNKNOW_PARSING_ERROR = 8;
    public static final int ILLEGAL_NUMBER_OF_PARENTHESIS = 9;
    public static final int ILLEGAL_POSITION_OF_PARENTHESIS = 10;
    public static final int FUNCTION_WITHOUT_PARENTHESIS = 11;
    public static final int ILLEGAL_NUMBER_POSITION = 12;
    public static final int POWER_AFTER_ILLEGAL_CHARACTER = 13;
   
    
    private String originalString;
    private List<Token> tokens = new LinkedList<>();
    
    public Lexer(String originalString) {
        this.originalString = originalString;
    }
    
    public int parse() {
        StringBuilder buffer = new StringBuilder();
        String actualChar;
        int parenthesisCounter = 0;
        
        String workString = "(" + this.originalString + ")";
        
        while(workString.length() > 0) {
            actualChar = workString.substring(0,1);
            
            if(StringUtils.isNumeric(actualChar)) {
                if(buffer.length() == 0) {
                    if(!getLastToken().getOperator().equals(LexerGrammar.CLOSING_PARENTHESIS)) {
                        buffer.append(actualChar);
                    } else {
                        return ILLEGAL_NUMBER_POSITION;
                    }
                } else {
                    buffer.append(actualChar);
                }
                
            } else {
                switch(actualChar) {
                    
                    case LexerGrammar.PLUS:
                        
                        if(buffer.length() == 0) {
                            if(!this.tokens.isEmpty() && getLastToken().getOperator().equals(LexerGrammar.CLOSING_PARENTHESIS)) {
                                 this.tokens.add(new Token(actualChar, LexerType.OPERATOR));
                            } else {
                                return PLUS_AFTER_ILLEGAL_CHARACTER;
                            }
                        } else {
                            if(StringUtils.isNumeric(buffer.toString())) {
                                this.tokens.add(new Token(buffer.toString(), LexerType.VALUE));
                                buffer.delete(0, buffer.length());
                                this.tokens.add(new Token(actualChar, LexerType.OPERATOR));
                            } else {
                                return BUFFER_IS_NOT_NUMERIC;
                            }
                        }
                        break;
                        
                    case LexerGrammar.MINUS:
                        
                        if(buffer.length() == 0) {
                            if(this.tokens.isEmpty()
                                    || getLastToken().getOperator().equals(LexerGrammar.OPENING_PARENTHESIS)) {
                                buffer.append(actualChar);
                            } else if (getLastToken().getOperator().equals(LexerGrammar.CLOSING_PARENTHESIS)){
                                this.tokens.add(new Token(actualChar, LexerType.OPERATOR));
                            } else {
                                return MINUS_AFTER_ILLEGAL_CHARACTER;
                            }
                        } else {
                            if(StringUtils.isNumeric(buffer.toString())) {
                                this.tokens.add(new Token(buffer.toString(), LexerType.VALUE));
                                buffer.delete(0, buffer.length());
                                this.tokens.add(new Token(actualChar, LexerType.OPERATOR));
                            } else {
                                return BUFFER_IS_NOT_NUMERIC;
                            }
                        }
                        break;
                        
                    case LexerGrammar.MULTIPLY:
                        
                        if(buffer.length() == 0) {
                            if(!this.tokens.isEmpty()
                                    && getLastToken().getOperator().equals(LexerGrammar.CLOSING_PARENTHESIS)) {
                                 this.tokens.add(new Token(actualChar, LexerType.OPERATOR));
                            } else {
                                return MULTIPLY_AFTER_ILLEGAL_CHARACTER;
                            }
                        } else {
                            if(StringUtils.isNumeric(buffer.toString())) {
                                this.tokens.add(new Token(buffer.toString(), LexerType.VALUE));
                                buffer.delete(0, buffer.length());
                                this.tokens.add(new Token(actualChar, LexerType.OPERATOR));
                            } else {
                                return BUFFER_IS_NOT_NUMERIC;
                            }
                        }
                        break;
                        
                    case LexerGrammar.DIVIDE:
                        
                        if(buffer.length() == 0) {
                            if(!this.tokens.isEmpty()
                                    && getLastToken().getOperator().equals(LexerGrammar.CLOSING_PARENTHESIS)) {
                                 this.tokens.add(new Token(actualChar, LexerType.OPERATOR));
                            } else {
                                return DIVIDE_AFTER_ILLEGAL_CHARACTER;
                            }
                        } else {
                            if(StringUtils.isNumeric(buffer.toString())) {
                                this.tokens.add(new Token(buffer.toString(), LexerType.VALUE));
                                buffer.delete(0, buffer.length());
                                this.tokens.add(new Token(actualChar, LexerType.OPERATOR));
                            } else {
                                return BUFFER_IS_NOT_NUMERIC;
                            }
                        }
                        break;
                        
                    case LexerGrammar.POWER:
                        
                        if(buffer.length() == 0) {
                            if(!this.tokens.isEmpty()
                                    && getLastToken().getOperator().equals(LexerGrammar.CLOSING_PARENTHESIS)) {
                                 this.tokens.add(new Token(actualChar, LexerType.OPERATOR));
                            } else {
                                return POWER_AFTER_ILLEGAL_CHARACTER;
                            }
                        } else {
                            if(StringUtils.isNumeric(buffer.toString())) {
                                this.tokens.add(new Token(buffer.toString(), LexerType.VALUE));
                                buffer.delete(0, buffer.length());
                                this.tokens.add(new Token(actualChar, LexerType.OPERATOR));
                            } else {
                                return BUFFER_IS_NOT_NUMERIC;
                            }
                        }
                        break;    
                    case LexerGrammar.OPENING_PARENTHESIS:
                        
                        parenthesisCounter++;
                        
                        if(buffer.length() == 0) {
                            this.tokens.add(new Token(actualChar, LexerType.OPENING_PARENTHESIS));
                        } else {
                            if(StringUtils.isNumeric(buffer.toString())) {
                                this.tokens.add(new Token(buffer.toString(), LexerType.VALUE));
                                buffer.delete(0, buffer.length());
                                this.tokens.add(new Token(actualChar, LexerType.OPENING_PARENTHESIS));
                            } else {
                                return BUFFER_IS_NOT_NUMERIC;
                            }
                        }
                        break;
                        
                    case LexerGrammar.CLOSING_PARENTHESIS:
                        
                        parenthesisCounter--;
                        
                        if(parenthesisCounter < 0) {
                            return ILLEGAL_POSITION_OF_PARENTHESIS;
                        }
                        
                        if(buffer.length() == 0) {
                            if(!this.tokens.isEmpty()
                                    && (!getLastToken().getOperator().equals(LexerGrammar.OPENING_PARENTHESIS)
                                            && !getLastToken().getOperator().equals(LexerGrammar.DIVIDE)
                                            && !getLastToken().getOperator().equals(LexerGrammar.MULTIPLY)
                                            && !getLastToken().getOperator().equals(LexerGrammar.MINUS)
                                            && !getLastToken().getOperator().equals(LexerGrammar.PLUS)
                                            && !getLastToken().getOperator().equals(LexerGrammar.SQUARE_ROOT)
                                            && !getLastToken().getOperator().equals(LexerGrammar.POWER))) {
                                this.tokens.add(new Token(actualChar, LexerType.CLOSING_PARENTHESIS));
                            } else {
                                return CLOSING_PARENTHESIS_AFTER_ILLEGAL_CHARACTER;
                            }
                        } else {
                            if(StringUtils.isNumeric(buffer.toString())) {
                                this.tokens.add(new Token(buffer.toString(), LexerType.VALUE));
                                buffer.delete(0, buffer.length());
                                this.tokens.add(new Token(actualChar, LexerType.CLOSING_PARENTHESIS));
                            } else {
                                return BUFFER_IS_NOT_NUMERIC;
                            }
                        }
                        break;
                        
                    case LexerGrammar.DOT:
                        
                        if(buffer.length() == 0) {
                            return DOT_WITHOUT_A_NUMBER;
                        } else {
                            if(StringUtils.isNumeric(buffer.toString())) {
                                buffer.append(actualChar);
                            } else {
                                return BUFFER_IS_NOT_NUMERIC;
                            }
                        }
                        break;
                        
                    default:
                        buffer.append(actualChar);
                }
                
                if(buffer.toString().equals(LexerGrammar.SQUARE_ROOT)) {
                    if(workString.substring(1,2).equals(LexerGrammar.OPENING_PARENTHESIS)) {
                        this.tokens.add(new Token(buffer.toString(), LexerType.FUNCTION));
                        buffer.delete(0, buffer.length());
                    } else {
                        return FUNCTION_WITHOUT_PARENTHESIS;
                    }
                }
            }
            
            workString = workString.substring(1);
        }
        
        if(buffer.length() > 0) {
            this.tokens.add(new Token(buffer.toString(), LexerType.VALUE));
            buffer.delete(0, buffer.length());
        }
        
        if(parenthesisCounter != 0) {
            return ILLEGAL_NUMBER_OF_PARENTHESIS;
        }
        
        return PARSE_SUCCESS;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Parse result : ");
        for(Token t : tokens) {
            sb.append("[");
            sb.append(t.getOperator());
            sb.append("] ");
        }
        return sb.toString();
    }
    
    private Token getLastToken() {
        return this.tokens.get(this.tokens.size()-1);
    }

    @Override
    public Iterator<Token> iterator() {
        return tokens.iterator();
    }
}

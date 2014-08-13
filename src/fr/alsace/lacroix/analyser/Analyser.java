package fr.alsace.lacroix.analyser;

import fr.alsace.lacroix.lexer.Lexer;
import fr.alsace.lacroix.lexer.LexerGrammar;
import fr.alsace.lacroix.lexer.LexerType;
import fr.alsace.lacroix.tree.Node;
import fr.alsace.lacroix.tree.leaf.SquareRoot;
import fr.alsace.lacroix.tree.leaf.Value;
import fr.alsace.lacroix.tree.node.Divide;
import fr.alsace.lacroix.tree.node.Minus;
import fr.alsace.lacroix.tree.node.Multiply;
import fr.alsace.lacroix.tree.node.Plus;
import fr.alsace.lacroix.tree.node.Power;
import fr.alsace.lacroix.utils.Operator;
import fr.alsace.lacroix.utils.Token;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Florent
 */
public class Analyser {

    public Analyser() {
    }
    
    public Node analyse(Lexer lexer) {
        return buildTree(tokensToList(lexer));
    }
    
    public Node buildTree(List<Token> tokens) {
        if(!tokens.isEmpty()) {
            
            LinkedList<List<Token>> functions = new LinkedList<>();
            List<Token> functionTokens = new LinkedList<>();
            List<Token> newTokens = new LinkedList<>();
            int parenthesisCounter = 0;
            
            boolean functionDetected = false;
            
            // detect and remove function from the tokens list
            for(Token token : tokens) {
                if(functionDetected) {                    
                    if(token.getSecond().equals(LexerType.OPENING_PARENTHESIS)) {
                        parenthesisCounter++;
                    } else if(token.getSecond().equals(LexerType.CLOSING_PARENTHESIS)) {
                        parenthesisCounter--;
                    }
                    
                    functionTokens.add(token);
                    
                    if(parenthesisCounter == 0) {
                        List<Token> temp = new LinkedList<>();
                        temp.addAll(functionTokens);
                        functions.add(temp);
                        functionTokens.clear();
                        functionDetected = false;
                    }
                } else {
                    if(token.getSecond().equals(LexerType.FUNCTION)) {
                        functionDetected = true;
                    }                
                    newTokens.add(token);
                }
            }
            
            Collections.reverse(newTokens);
            
            List<Token> prefixTokens = new LinkedList();
            LinkedList<Token> stack = new LinkedList();
            
            // apply the algo to have a postfix expression
            for(Token token : newTokens) {
                if(token.getSecond().equals(LexerType.VALUE)
                        || token.getSecond().equals(LexerType.FUNCTION)) {
                    prefixTokens.add(token);
                } else if(token.getSecond().equals(LexerType.CLOSING_PARENTHESIS)) {
                    stack.add(token);
                } else if(token.getSecond().equals(LexerType.OPERATOR)) {
                    while(Operator.getPriority(stack.getLast().getFirst()) != -1
                            && (Operator.getPriority(token.getFirst()) > Operator.getPriority(stack.getLast().getFirst()))) {
                        prefixTokens.add(stack.removeLast());
                    }
                    stack.add(token);
                } else if(token.getSecond().equals(LexerType.OPENING_PARENTHESIS)) {
                    while(!stack.getLast().getSecond().equals(LexerType.CLOSING_PARENTHESIS)) {
                        prefixTokens.add(stack.removeLast());
                    }
                    stack.removeLast();
                }
            }
            
            while(!stack.isEmpty()) {
                prefixTokens.add(stack.removeLast());
            }
            
            Collections.reverse(prefixTokens);
            
            System.out.println(prefixTokens.toString());
            
            // build the tree with the prefix expression
            Node node = null;
            Node tree = null;
            
            for(Token token : prefixTokens) {
                Node currentNode = null;
                
                switch(token.getSecond()) {
                    case LexerType.VALUE : 
                        currentNode = new Value(null, token.getFirst());
                        
                        if(node == null) {
                            node = currentNode;
                            tree = node;
                        } else {
                            if(node.getLeft() == null) {
                                currentNode.setParent(node);
                                node.setLeft(currentNode);
                            } else if(node.getRight() == null) {
                                currentNode.setParent(node);
                                node.setRight(currentNode);
                                while(node != null && node.getLeft() != null && node.getRight() != null) {
                                    node = node.getParent();
                                }
                            }
                        }
                        break;
                    case LexerType.OPERATOR : 
                        switch(token.getFirst()) {
                            case LexerGrammar.PLUS : 
                                currentNode = new Plus(null, null, null);
                                break;
                            case LexerGrammar.MINUS : 
                                currentNode = new Minus(null, null, null);
                                break;
                            case LexerGrammar.MULTIPLY : 
                                currentNode = new Multiply(null, null, null);
                                break;
                            case LexerGrammar.DIVIDE : 
                                currentNode = new Divide(null, null, null);
                                break;
                            case LexerGrammar.POWER : 
                                currentNode = new Power(null, null, null);
                                break;
                        }
                        
                        if(node == null) {
                            node = currentNode;
                            tree = node;
                        } else {
                            if(node.getLeft() == null) {
                                currentNode.setParent(node);
                                node.setLeft(currentNode);
                                node = node.getLeft();
                            } else if(node.getRight() == null) {
                                currentNode.setParent(node);
                                node.setRight(currentNode);
                                node = node.getRight();
                            }
                        }
                        break;
                    case LexerType.FUNCTION : 
                        if(token.getFirst().equals(LexerGrammar.SQUARE_ROOT)) {
                            currentNode = new SquareRoot(null, this.buildTree(functions.removeFirst()));
                            
                            if(node == null) {
                                node = currentNode;
                                tree = node;
                            } else {
                                if(node.getLeft() == null) {
                                    currentNode.setParent(node);
                                    node.setLeft(currentNode);
                                } else if(node.getRight() == null) {
                                    currentNode.setParent(node);
                                    node.setRight(currentNode);
                                    while(node != null && node.getLeft() != null && node.getRight() != null) {
                                        node = node.getParent();
                                    }
                                }
                            }
                        }
                        break;
                }
            }
            return tree;
        } else {
            return null;
        }
    }
        
    public List<Token> tokensToList(Lexer lexer) {
        Token lastToken = null;
        List<Token> tokens = new LinkedList<>();

        for (Token token : lexer) {
            if(token.getType().equals(LexerType.OPENING_PARENTHESIS)
                    && lastToken != null
                    && (lastToken.getType().equals(LexerType.CLOSING_PARENTHESIS)
                    || lastToken.getType().equals(LexerType.VALUE))) {
                tokens.add(new Token("*", LexerType.OPERATOR));
                tokens.add(new Token(token.getOperator(), token.getType()));
            } else {
                tokens.add(new Token(token.getOperator(), token.getType()));
            }

            lastToken = token;
        }
        
        return tokens;
    }
    
//    public Duo<List<Token>, LinkedList<List<Token>>> extractFunction(List<Token> listOfTokens) {
//        if(!listOfTokens.isEmpty()) {
//            
//            LinkedList<List<Token>> functions = new LinkedList<>();
//            List<Token> functionTokens = new LinkedList<>();
//            List<Token> newTokens = new LinkedList<>();
//            int parenthesisCounter = 0;
//            
//            boolean functionDetected = false;
//            
//            // detect and remove function from the tokens list
//            for(Token token : listOfTokens) {
//                if(functionDetected) {                    
//                    if(token.getType().equals(LexerType.OPENING_PARENTHESIS)) {
//                        parenthesisCounter++;
//                    } else if(token.getType().equals(LexerType.CLOSING_PARENTHESIS)) {
//                        parenthesisCounter--;
//                    }
//                    
//                    functionTokens.add(token);
//                    
//                    if(parenthesisCounter == 0) {
//                        List<Token> temp = new LinkedList<>();
//                        temp.addAll(functionTokens);
//                        functions.add(temp);
//                        functionTokens.clear();
//                        functionDetected = false;
//                    }
//                } else {
//                    if(token.getType().equals(LexerType.FUNCTION)) {
//                        functionDetected = true;
//                    }                
//                    newTokens.add(token);
//                }
//            }
//            
//            return new Duo<>(newTokens, functions);
//        } else {
//            return null;
//        }
//    }
//    
//    public LinkedList<Token> getPrefixExpression(List<Token> tokens) {
//        if(!tokens.isEmpty()) {
//            Collections.reverse(tokens);         
//            LinkedList<Token> prefixTokens = new LinkedList();
//            LinkedList<Token> stack = new LinkedList();
//
//            // apply the algo to have a postfix expression
//            for(Token token : tokens) {
//                if(token.getType().equals(LexerType.VALUE)
//                        || token.getType().equals(LexerType.FUNCTION)) {
//                    prefixTokens.add(token);
//                } else if(token.getType().equals(LexerType.CLOSING_PARENTHESIS)) {
//                    stack.add(token);
//                } else if(token.getType().equals(LexerType.OPERATOR)) {
//                    while(Operator.getPriority(stack.getLast().getOperator()) != -1
//                            && (Operator.getPriority(token.getOperator()) > Operator.getPriority(stack.getLast().getOperator()))) {
//                        prefixTokens.add(stack.removeLast());
//                    }
//                    stack.add(token);
//                } else if(token.getType().equals(LexerType.OPENING_PARENTHESIS)) {
//                    while(!stack.getLast().getType().equals(LexerType.CLOSING_PARENTHESIS)) {
//                        prefixTokens.add(stack.removeLast());
//                    }
//                    stack.removeLast();
//                }
//            }
//
//            while(!stack.isEmpty()) {
//                prefixTokens.add(stack.removeLast());
//            }
//
//            Collections.reverse(prefixTokens);
//
//            System.out.println(prefixTokens.toString());
//            
//            return prefixTokens;
//        } else {
//            return null;
//        }
//    }
//    
//    public String calculate(List<Token> listOfTokens) {
//        Duo<List<Token>, LinkedList<List<Token>>> tokens = extractFunction(listOfTokens);
//        LinkedList<Token> prefixExpression = getPrefixExpression(tokens.getFirst());
//        
//        LinkedList<Token> stack = new LinkedList<>();
//        
//        while(!prefixExpression.isEmpty()) {
//            Token token = prefixExpression.pop();
//            
//            if(token.getType().equals(LexerType.OPERATOR)) {
//                stack.addFirst(token);
//            } else if (token.getType().equals(LexerType.VALUE)) {
//                if(!stack.isEmpty() && stack.getFirst().getType().equals(LexerType.VALUE)) {
//                    String s = "";
//                    while(!stack.isEmpty() && stack.getFirst().getType().equals(LexerType.VALUE)) {
//                        s = doTheOperatorMath(token, stack.pop(), stack.pop());
//                        token = new Token(s, LexerType.VALUE);
//                    }
//                    stack.addFirst(new Token(s, LexerType.VALUE));
//                } else {
//                    stack.addFirst(token);
//                }
//            } else if(token.getType().equals(LexerType.FUNCTION)) {
//                String s = doTheFunctionMath(token, calculate(tokens.getSecond().pop()));
//                Token tmp = new Token(s, LexerType.VALUE);
//                while(!stack.isEmpty() && stack.getFirst().getType().equals(LexerType.VALUE)) {
//                    s = doTheOperatorMath(tmp, stack.pop(), stack.pop());
//                    tmp = new Token(s, LexerType.VALUE);
//                }
//                stack.addFirst(new Token(s, LexerType.VALUE));
//                
//            }
//        }
//        
//        return stack.getFirst().getOperator();
//    }
//    
//    public String doTheOperatorMath(Token value1, Token value2, Token operator) {
//        
//        double d1 = Double.parseDouble(value1.getOperator());
//        double d2 = Double.parseDouble(value2.getOperator());
//        
//        switch(operator.getOperator()) {
//            case LexerGrammar.PLUS: 
//                return Double.toString(d1+d2);
//            case LexerGrammar.MINUS:
//                return Double.toString(d2-d1);
//            case LexerGrammar.MULTIPLY:
//                return Double.toString(d1*d2);
//            case LexerGrammar.DIVIDE:
//                return Double.toString(d1/d2);
//            default:
//                return null;
//        }
//    }
//    
//    public String doTheFunctionMath(Token function, String value) {
//        
//        double d = Double.parseDouble(value);
//        
//        switch(function.getOperator()) {
//            case LexerGrammar.SQUARE_ROOT:
//                return Double.toString(Math.sqrt(d));
//            default: 
//                return null;
//        }
//    }
}

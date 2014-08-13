package fr.alsace.lacroix.calculator;

import fr.alsace.lacroix.analyser.Analyser;
import fr.alsace.lacroix.lexer.Lexer;
import fr.alsace.lacroix.utils.StringUtils;
import fr.alsace.lacroix.utils.Token;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Florent
 */
public class SampleController implements Initializable {
    
    @FXML
    private TextField expression;
    @FXML
    private Button oneButton;
    @FXML
    private Button twoButton;
    @FXML
    private Button threeButton;
    @FXML
    private Button plusButton;
    @FXML
    private Button fourButton;
    @FXML
    private Button fiveButton;
    @FXML
    private Button sixButton;
    @FXML
    private Button minusButton;
    @FXML
    private Button sevenButton;
    @FXML
    private Button eightButton;
    @FXML
    private Button nineButton;
    @FXML
    private Button multiplyButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button zeroButton;
    @FXML
    private Button dotButton;
    @FXML
    private Button divideButton;
    @FXML
    private Button openingParenthesisButton;
    @FXML
    private Button closingParenthesisButton;
    @FXML
    private Button powerButton;
    @FXML
    private Button squareRootButton;
    @FXML
    private Button eraseButton;
    @FXML
    private Button calculateButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Lexer lexer = new Lexer("-365.36+5.2(-8.2-(2+3))-((2+3)*5)((sqrt(9^7)*2+3)-6/(2/3))/(-7.5)^2");
        // Lexer lexer = new Lexer("-365.36+5.2(-8.2-(2+3))-((2+3)*5)");
        // Lexer lexer = new Lexer("3+4(7+2)-3(4+3)");
        // Lexer lexer = new Lexer("3-(4+3)-4*3(2+3)+sqrt(4)");
        // Lexer lexer = new Lexer("-365+5(-8-(2+3))-((2+3)*5)((sqrt(sqrt((2+3)^5)^(7^2))*2+3)-6/(2/3))/(-7)^2");
        // Lexer lexer = new Lexer("3-7-5");
        Lexer lexer = new Lexer("sqrt(sqrt(81))");
        switch(lexer.parse()) {
            case Lexer.PARSE_SUCCESS:
                System.out.println("Parsing success !");
                Analyser analyser = new Analyser();
                List<Token> listOfTokens = analyser.tokensToList(lexer);
                if(!listOfTokens.isEmpty()) {
                    System.out.println(analyser.buildTree(listOfTokens).eval());
                }
               
//                Node tree = analyser.analyse(lexer);
//                if(tree != null) {
//                    System.out.println(tree.eval());
//                }
                break;
            case Lexer.BUFFER_IS_NOT_NUMERIC:
                System.err.println("The buffer is not a numeric.");
                break;
            case Lexer.PLUS_AFTER_ILLEGAL_CHARACTER:
                System.err.println("Find a not allowed caracter '+'.");
                break;
            case Lexer.MINUS_AFTER_ILLEGAL_CHARACTER:
                System.err.println("Find a not allowed caracter '-'.");
                break;
            case Lexer.MULTIPLY_AFTER_ILLEGAL_CHARACTER:
                System.err.println("Find a not allowed caracter '*'.");
                break;
            case Lexer.DIVIDE_AFTER_ILLEGAL_CHARACTER:
                System.err.println("Find a not allowed caracter '/'.");
                break;
            case Lexer.CLOSING_PARENTHESIS_AFTER_ILLEGAL_CHARACTER:
                System.err.println("Find a not allowed caracter ')'.");
                break;
            case Lexer.DOT_WITHOUT_A_NUMBER:
                System.err.println("Find a not allowed caracter '.'.");
                break;
            case Lexer.ILLEGAL_NUMBER_OF_PARENTHESIS:
                System.err.println("Some parenthesis are not closed.");
                break;
            case Lexer.ILLEGAL_POSITION_OF_PARENTHESIS:
                System.err.println("A parenthesis has been closed without been opened.");
                break;
            case Lexer.FUNCTION_WITHOUT_PARENTHESIS:
                System.err.println("A function has been detected without a parenthesis.");
                break;
            case Lexer.ILLEGAL_NUMBER_POSITION:
                System.err.println("A number has been detected to a wrong position.");
                break;
            case Lexer.POWER_AFTER_ILLEGAL_CHARACTER:
                System.err.println("Find a not allowed caracter '^'.");
                break;
            case Lexer.UNKNOW_PARSING_ERROR:
                System.err.println("Unknow parsing error.");
                break;
            default:
                System.err.println("Unknow error.");
                break;
        }
    }    

    @FXML
    private void handleExpressionAction(KeyEvent event) {
        
    }

    @FXML
    private void handleOneAction(MouseEvent event) {
        this.expression.setText(this.expression.getText() + "1");
    }

    @FXML
    private void handleTwoAction(MouseEvent event) {
        this.expression.setText(this.expression.getText() + "2");
    }

    @FXML
    private void handleThreeAction(MouseEvent event) {
        this.expression.setText(this.expression.getText() + "3");
    }

    @FXML
    private void handlePlusAction(MouseEvent event) {
        this.expression.setText(this.expression.getText() + "+");
    }

    @FXML
    private void handleFourAction(MouseEvent event) {
        this.expression.setText(this.expression.getText() + "4");
    }

    @FXML
    private void handleFiveAction(MouseEvent event) {
        this.expression.setText(this.expression.getText() + "5");
    }

    @FXML
    private void handleSixAction(MouseEvent event) {
        this.expression.setText(this.expression.getText() + "6");
    }

    @FXML
    private void handleMinusAction(MouseEvent event) {
        this.expression.setText(this.expression.getText() + "-");
    }

    @FXML
    private void handleSevenAction(MouseEvent event) {
        this.expression.setText(this.expression.getText() + "7");
    }

    @FXML
    private void handleEightAction(MouseEvent event) {
        this.expression.setText(this.expression.getText() + "8");
    }

    @FXML
    private void handleNineAction(MouseEvent event) {
        this.expression.setText(this.expression.getText() + "9");
    }

    @FXML
    private void handleMultiplyAction(MouseEvent event) {
        this.expression.setText(this.expression.getText() + "*");
    }

    @FXML
    private void handleCancelAction(MouseEvent event) {
        this.expression.setText(StringUtils.eraseLastChars(this.expression.getText(), 1));
    }

    @FXML
    private void handleZeroAction(MouseEvent event) {
        this.expression.setText(this.expression.getText() + "0");
    }

    @FXML
    private void handleDotAction(MouseEvent event) {
        this.expression.setText(this.expression.getText() + ".");
    }

    @FXML
    private void handleDivideAction(MouseEvent event) {
        this.expression.setText(this.expression.getText() + "/");
    }

    @FXML
    private void handleOpeningParenthesisAction(MouseEvent event) {
        this.expression.setText(this.expression.getText() + "(");
    }

    @FXML
    private void handleClosingParenthesisAction(MouseEvent event) {
        this.expression.setText(this.expression.getText() + ")");
    }

    @FXML
    private void handlePowerAction(MouseEvent event) {
        this.expression.setText(this.expression.getText() + "^");
    }


    @FXML
    private void handleEraseAction(MouseEvent event) {
        this.expression.setText("");
    }

    @FXML
    private void handleCalculateAction(MouseEvent event) {
    }

    @FXML
    private void handleSquareRootAction(MouseEvent event) {
        this.expression.setText(this.expression.getText() + "sqrt(");
    }
}

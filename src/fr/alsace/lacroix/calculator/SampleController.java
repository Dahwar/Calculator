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
import javafx.scene.input.KeyCode;
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
    @FXML
    private TextField answer;
    
    private int getInsertionPosition;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.getInsertionPosition = 0;
    }    

    public String calculate(String s) {
        Lexer lexer = new Lexer(s);
        switch(lexer.parse()) {
            case Lexer.PARSE_SUCCESS:
                Analyser analyser = new Analyser();
                List<Token> listOfTokens = analyser.tokensToList(lexer);
                if(!listOfTokens.isEmpty()) {
                    return analyser.buildTree(listOfTokens).eval().toString();
                }
                return "Empty string.";
            case Lexer.BUFFER_IS_NOT_NUMERIC:
                return "The buffer is not a numeric.";
            case Lexer.PLUS_AFTER_ILLEGAL_CHARACTER:
                return "Find a not allowed caracter '+'.";
            case Lexer.MINUS_AFTER_ILLEGAL_CHARACTER:
                return "Find a not allowed caracter '-'.";
            case Lexer.MULTIPLY_AFTER_ILLEGAL_CHARACTER:
                return "Find a not allowed caracter '*'.";
            case Lexer.DIVIDE_AFTER_ILLEGAL_CHARACTER:
                return "Find a not allowed caracter '/'.";
            case Lexer.CLOSING_PARENTHESIS_AFTER_ILLEGAL_CHARACTER:
                return "Find a not allowed caracter ')'.";
            case Lexer.DOT_WITHOUT_A_NUMBER:
                return "Find a not allowed caracter '.'.";
            case Lexer.ILLEGAL_NUMBER_OF_PARENTHESIS:
                return "Some parenthesis are not closed.";
            case Lexer.ILLEGAL_POSITION_OF_PARENTHESIS:
                return "A parenthesis has been closed without been opened.";
            case Lexer.FUNCTION_WITHOUT_PARENTHESIS:
                return "A function has been detected without a parenthesis.";
            case Lexer.ILLEGAL_NUMBER_POSITION:
                return "A number has been detected to a wrong position.";
            case Lexer.POWER_AFTER_ILLEGAL_CHARACTER:
                return "Find a not allowed caracter '^'.";
            case Lexer.UNKNOW_PARSING_ERROR:
                return "Unknow parsing error.";
            default:
                return "Unknow error.";
        }
    }
    
    @FXML
    private void handleExpressionAction(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)) {
            this.answer.setText(this.calculate(this.expression.getText()));
        }
    }

    private void addText(String s) {
        this.expression.setText(StringUtils.insertInSpecialPosition(this.expression.getText(), s, this.getInsertionPosition));
    }
    
    @FXML
    private void handleOneAction(MouseEvent event) {
        this.addText("1");
        this.getInsertionPosition++;
    }

    @FXML
    private void handleTwoAction(MouseEvent event) {
        this.addText("2");
        this.getInsertionPosition++;
    }

    @FXML
    private void handleThreeAction(MouseEvent event) {
        this.addText("3");
        this.getInsertionPosition++;
    }

    @FXML
    private void handlePlusAction(MouseEvent event) {
        this.addText("+");
        this.getInsertionPosition++;
    }

    @FXML
    private void handleFourAction(MouseEvent event) {
        this.addText("4");
        this.getInsertionPosition++;
    }

    @FXML
    private void handleFiveAction(MouseEvent event) {
        this.addText("5");
        this.getInsertionPosition++;
    }

    @FXML
    private void handleSixAction(MouseEvent event) {
        this.addText("6");
        this.getInsertionPosition++;
    }

    @FXML
    private void handleMinusAction(MouseEvent event) {
        this.addText("-");
        this.getInsertionPosition++;
    }

    @FXML
    private void handleSevenAction(MouseEvent event) {
        this.addText("7");
        this.getInsertionPosition++;
    }

    @FXML
    private void handleEightAction(MouseEvent event) {
        this.addText("8");
        this.getInsertionPosition++;
    }

    @FXML
    private void handleNineAction(MouseEvent event) {
        this.addText("9");
        this.getInsertionPosition++;
    }

    @FXML
    private void handleMultiplyAction(MouseEvent event) {
        this.addText("*");
        this.getInsertionPosition++;
    }

    @FXML
    private void handleCancelAction(MouseEvent event) {
        this.expression.setText(StringUtils.eraseAtSpecialPosition(this.expression.getText(), this.getInsertionPosition, 1));
        if(this.getInsertionPosition > 0) {
            this.getInsertionPosition--;
        }
    }

    @FXML
    private void handleZeroAction(MouseEvent event) {
        this.addText("0");
        this.getInsertionPosition++;
    }

    @FXML
    private void handleDotAction(MouseEvent event) {
        this.addText(".");
        this.getInsertionPosition++;
    }

    @FXML
    private void handleDivideAction(MouseEvent event) {
        this.addText("/");
        this.getInsertionPosition++;
    }

    @FXML
    private void handleOpeningParenthesisAction(MouseEvent event) {
        this.addText("(");
        this.getInsertionPosition++;
    }

    @FXML
    private void handleClosingParenthesisAction(MouseEvent event) {
        this.addText(")");
        this.getInsertionPosition++;
    }

    @FXML
    private void handlePowerAction(MouseEvent event) {
        this.addText("^");
        this.getInsertionPosition++;
    }

    @FXML
    private void handleEraseAction(MouseEvent event) {
        this.expression.setText("");
        this.getInsertionPosition = 0;
    }

    @FXML
    private void handleCalculateAction(MouseEvent event) {
        this.answer.setText(this.calculate(this.expression.getText()));
    }

    @FXML
    private void handleSquareRootAction(MouseEvent event) {
        this.addText("sqrt(");
        this.getInsertionPosition+=5;
    }

    @FXML
    private void handleGetCaret(MouseEvent event) {
        this.getInsertionPosition = this.expression.getCaretPosition();
    }
}

package fr.alsace.lacroix.calculator;

import fr.alsace.lacroix.analyser.Analyser;
import fr.alsace.lacroix.lexer.Lexer;
import fr.alsace.lacroix.utils.StringUtils;
import fr.alsace.lacroix.utils.Token;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private Label answer;
    @FXML
    private Button answerButton;
    
    private int getInsertionPosition;
    private boolean eraseExpression;
    private boolean error;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.getInsertionPosition = 0;
        this.eraseExpression = false;
        this.error = false;
    }    

    public String calculate(String s) {
        this.error = true;
        Lexer lexer = new Lexer(s);
        switch(lexer.parse()) {
            case Lexer.PARSE_SUCCESS:
                Analyser analyser = new Analyser();
                List<Token> listOfTokens = analyser.tokensToList(lexer);
                if(!listOfTokens.isEmpty()) {
                    this.error = false;
                    return analyser.buildTree(listOfTokens).eval().toString();
                }
                return "Empty string.";
            case Lexer.BUFFER_IS_NOT_NUMERIC:
                this.error = true;
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
    
    private boolean doErase() {
        if(this.eraseExpression) {
            this.expression.setText("");
            this.getInsertionPosition = 0;
            return true;
        } else {
            return false;
        }
    }
    
    private void addText(String s) {
        this.expression.setText(StringUtils.insertInSpecialPosition(this.expression.getText(), s, this.getInsertionPosition));
        this.getInsertionPosition+=s.length();
    }
    
    private void getResult() {
        
        DecimalFormat df = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        df.setMaximumFractionDigits(340);
        
        String s = this.expression.getText();
        
        if(!this.answer.getText().isEmpty()) {
            s = s.replace("Ans", this.answer.getText());
        } else {
            s = s.replace("Ans", "0");
        }
        
        String r = this.calculate(s);
        
        if(!this.error) {
            this.answer.setText(df.format(Double.parseDouble(r)));
        } else {
            this.answer.setText(r);
        }
        
        this.eraseExpression = true;
    }
    
    @FXML
    private void handleExpressionAction(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)) {
            this.getResult();
        }
    }

    
    
    @FXML
    private void handleOneAction(MouseEvent event) {
        this.doErase();
        this.addText("1");
        this.eraseExpression = false;
    }

    @FXML
    private void handleTwoAction(MouseEvent event) {
        this.doErase();
        this.addText("2");
        this.eraseExpression = false;
    }

    @FXML
    private void handleThreeAction(MouseEvent event) {
        this.doErase();
        this.addText("3");
        this.eraseExpression = false;
    }

    @FXML
    private void handlePlusAction(MouseEvent event) {
        if(this.doErase()) {
             this.addText("Ans");
        }
        this.addText("+");
        this.eraseExpression = false;
    }

    @FXML
    private void handleFourAction(MouseEvent event) {
        this.doErase();
        this.addText("4");
        this.eraseExpression = false;
    }

    @FXML
    private void handleFiveAction(MouseEvent event) {
        this.doErase();
        this.addText("5");
        this.eraseExpression = false;
    }

    @FXML
    private void handleSixAction(MouseEvent event) {
        this.doErase();
        this.addText("6");
        this.eraseExpression = false;
    }

    @FXML
    private void handleMinusAction(MouseEvent event) {
        if(this.doErase()) {
             this.addText("Ans");
        }
        this.addText("-");
        this.eraseExpression = false;
    }

    @FXML
    private void handleSevenAction(MouseEvent event) {
        this.doErase();
        this.addText("7");
        this.eraseExpression = false;
    }

    @FXML
    private void handleEightAction(MouseEvent event) {
        this.doErase();
        this.addText("8");
        this.eraseExpression = false;
    }

    @FXML
    private void handleNineAction(MouseEvent event) {
        this.doErase();
        this.addText("9");
        this.eraseExpression = false;
    }

    @FXML
    private void handleMultiplyAction(MouseEvent event) {
        if(this.doErase()) {
             this.addText("Ans");
        }
        this.addText("*");
        this.eraseExpression = false;
    }

    @FXML
    private void handleCancelAction(MouseEvent event) {
        this.expression.setText(StringUtils.eraseAtSpecialPosition(this.expression.getText(), this.getInsertionPosition, 1));
        if(this.getInsertionPosition > 0) {
            this.getInsertionPosition--;
        }
        this.eraseExpression = false;
    }

    @FXML
    private void handleZeroAction(MouseEvent event) {
        this.doErase();
        this.addText("0");
        this.eraseExpression = false;
    }

    @FXML
    private void handleDotAction(MouseEvent event) {
        this.doErase();
        this.addText(".");
        this.eraseExpression = false;
    }

    @FXML
    private void handleDivideAction(MouseEvent event) {
        if(this.doErase()) {
             this.addText("Ans");
        }
        this.addText("/");
        this.eraseExpression = false;
    }

    @FXML
    private void handleOpeningParenthesisAction(MouseEvent event) {
        this.doErase();
        this.addText("(");
        this.eraseExpression = false;
    }

    @FXML
    private void handleClosingParenthesisAction(MouseEvent event) {
        this.doErase();
        this.addText(")");
        this.eraseExpression = false;
    }

    @FXML
    private void handlePowerAction(MouseEvent event) {
        if(this.doErase()) {
             this.addText("Ans");
        }
        this.addText("^");
        this.eraseExpression = false;
    }

    @FXML
    private void handleEraseAction(MouseEvent event) {
        this.expression.setText("");
        this.getInsertionPosition = 0;
        this.eraseExpression = false;
    }

    @FXML
    private void handleCalculateAction(MouseEvent event) {
        this.getResult();
    }

    @FXML
    private void handleSquareRootAction(MouseEvent event) {
        this.doErase();
        this.addText("sqrt(");
        this.eraseExpression = false;
    }

    @FXML
    private void handleGetCaret(MouseEvent event) {
        this.getInsertionPosition = this.expression.getCaretPosition();
        this.eraseExpression = false;
    }

    @FXML
    private void handleAnswerAction(MouseEvent event) {
        this.doErase();
        this.addText("Ans");
        this.eraseExpression = false;
    }
}

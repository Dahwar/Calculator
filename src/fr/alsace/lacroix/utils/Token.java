package fr.alsace.lacroix.utils;

/**
 *
 * @author Florent
 */
public class Token extends Duo<String, Integer> {
    
    public Token(String operator, Integer type) {
        super(operator, type);
    }

    public String getOperator() {
        return this.first;
    }

    public void setOperator(String operator) {
        this.first = operator;
    }

    public Integer getType() {
        return this.second;
    }

    public void setType(Integer type) {
        this.second = type;
    }   
}

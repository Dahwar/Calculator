package fr.alsace.lacroix.utils;

/**
 *
 * @author Florent
 */
public class Duo<T,U> {
    
    protected T first;
    protected U second;
    
    public Duo(T first,U second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public U getSecond() {
        return second;
    }

    public void setSecond(U second) {
        this.second = second;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(this.first.toString());
        sb.append(",");
        sb.append(this.second.toString());
        sb.append("]");
        return sb.toString();
    }
}

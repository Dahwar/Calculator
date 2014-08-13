/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.alsace.lacroix.tree;

/**
 *
 * @author Florent
 */
public abstract class Leaf extends Node {

    public Leaf(Node left, Node right, Node parent) {
        super(left, right, parent);
    }
    
}

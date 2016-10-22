package br.edu.insper.morsesms;

/**
 * Created by Lucas Scarlato Astur on 17/10/2016.
 */

public class CharacterNode {
    private char character;


    private CharacterNode left;
    private CharacterNode right;



    public char getCharacter() {
        return this.character;
    }
    public void setCharacter(char character) {
        this.character = character;
    }



    public CharacterNode getRight() {
        return this.right;
    }

    public void setRight(CharacterNode characterNode) {
        this.right = characterNode;
    }



    public CharacterNode getLeft() {
        return this.left;
    }

    public void setLeft(CharacterNode characterNode) {
        this.left = characterNode;
    }



}

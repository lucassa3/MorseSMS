package br.edu.insper.morsesms;

/**
 * Created by Lucas Scarlato Astur on 17/10/2016.
 */

public class Characters {
    private char character;


    private Characters left;
    private Characters right;



    public char getCharacter() {
        return this.character;
    }
    public void setCharacter(char character) {
        this.character = character;
    }



    public Characters getRight() {
        return this.right;
    }

    public void setRight(Characters characters) {
        this.right = characters;
    }



    public Characters getLeft() {
        return this.left;
    }

    public void setLeft(Characters characters) {
        this.left = characters;
    }



}

package br.edu.insper.morsesms;

/**
 * Created by lucas on 22/10/2016.
 */

public class Msg {
    private String morse_message =  "";
    public void addChar (char char1) {
        morse_message += char1;
    }

    public void removeChar () {
        this.morse_message = morse_message.substring(0, morse_message.length()-1);
    }

    public String getMorse_message() {
        return this.morse_message;
    }
    public void eraseMorse_message () {
        this.morse_message = "";
    }

}

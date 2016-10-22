package br.edu.insper.morsesms;

/**
 * Created by Lucas Scarlato Astur on 18/10/2016.
 */

public class CharacterNodeTree {

    CharacterNode[] Tree = new CharacterNode[40];

    public CharacterNodeTree() {
        for(int i =0; i < Tree.length; i++ ) {
            Tree[i] = new CharacterNode();
        }

        Tree[0].setLeft(Tree[1]);
        Tree[0].setRight(Tree[2]);

        Tree[1].setCharacter('e');
        Tree[1].setLeft(Tree[3]);
        Tree[1].setRight(Tree[4]);

        Tree[2].setCharacter('t');
        Tree[2].setLeft(Tree[5]);
        Tree[2].setRight(Tree[6]);

        Tree[3].setCharacter('i');
        Tree[3].setLeft(Tree[7]);
        Tree[3].setRight(Tree[8]);

        Tree[4].setCharacter('a');
        Tree[4].setLeft(Tree[9]);
        Tree[4].setRight(Tree[10]);

        Tree[5].setCharacter('n');
        Tree[5].setLeft(Tree[11]);
        Tree[5].setRight(Tree[12]);

        Tree[6].setCharacter('m');
        Tree[6].setLeft(Tree[13]);
        Tree[6].setRight(Tree[14]);

        Tree[7].setCharacter('s');
        Tree[7].setLeft(Tree[15]);
        Tree[7].setRight(Tree[16]);

        Tree[8].setCharacter('u');
        Tree[8].setLeft(Tree[17]);
        Tree[8].setRight(Tree[18]);

        Tree[9].setCharacter('r');
        Tree[9].setLeft(Tree[19]);

        Tree[10].setCharacter('w');
        Tree[10].setLeft(Tree[20]);
        Tree[10].setRight(Tree[21]);

        Tree[11].setCharacter('d');
        Tree[11].setLeft(Tree[22]);
        Tree[11].setRight(Tree[23]);

        Tree[12].setCharacter('k');
        Tree[12].setLeft(Tree[24]);
        Tree[12].setRight(Tree[25]);

        Tree[13].setCharacter('g');
        Tree[13].setLeft(Tree[26]);
        Tree[13].setRight(Tree[27]);

        Tree[14].setCharacter('o');
        Tree[14].setLeft(Tree[28]);
        Tree[14].setRight(Tree[29]);

        Tree[15].setCharacter('h');
        Tree[15].setLeft(Tree[30]);
        Tree[15].setRight(Tree[31]);

        Tree[16].setCharacter('v');
        Tree[16].setRight(Tree[32]);

        Tree[17].setCharacter('f');

        Tree[18].setRight(Tree[33]);

        Tree[19].setCharacter('l');

        Tree[20].setCharacter('p');

        Tree[21].setCharacter('j');
        Tree[21].setRight(Tree[34]);

        Tree[22].setCharacter('b');
        Tree[22].setLeft(Tree[35]);

        Tree[23].setCharacter('x');

        Tree[24].setCharacter('c');

        Tree[25].setCharacter('y');

        Tree[26].setCharacter('z');
        Tree[26].setLeft(Tree[36]);

        Tree[27].setCharacter('q');

        Tree[28].setLeft(Tree[37]);

        Tree[29].setLeft(Tree[38]);
        Tree[29].setRight(Tree[39]);

        Tree[30].setCharacter('5');
        Tree[31].setCharacter('4');
        Tree[32].setCharacter('3');
        Tree[33].setCharacter('2');
        Tree[34].setCharacter('1');
        Tree[35].setCharacter('6');
        Tree[36].setCharacter('7');
        Tree[37].setCharacter('8');
        Tree[38].setCharacter('9');
        Tree[39].setCharacter('0');

    }



}

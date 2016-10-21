package br.edu.insper.morsesms;

/**
 * Created by Lucas Scarlato Astur on 18/10/2016.
 */

public class CharactersTree {

    Characters[] charactersTree = new Characters[40];

    public CharactersTree() {
        for(int i =0; i < charactersTree.length; i++ ) {
            charactersTree[i] = new Characters();
        }

        charactersTree[0].setLeft(charactersTree[1]);
        charactersTree[0].setRight(charactersTree[2]);

        charactersTree[1].setCharacter('e');
        charactersTree[1].setLeft(charactersTree[3]);
        charactersTree[1].setRight(charactersTree[4]);

        charactersTree[2].setCharacter('t');
        charactersTree[2].setLeft(charactersTree[5]);
        charactersTree[2].setRight(charactersTree[6]);

        charactersTree[3].setCharacter('i');
        charactersTree[3].setLeft(charactersTree[7]);
        charactersTree[3].setRight(charactersTree[8]);

        charactersTree[4].setCharacter('a');
        charactersTree[4].setLeft(charactersTree[9]);
        charactersTree[4].setRight(charactersTree[10]);

        charactersTree[5].setCharacter('n');
        charactersTree[5].setLeft(charactersTree[11]);
        charactersTree[5].setRight(charactersTree[12]);

        charactersTree[6].setCharacter('m');
        charactersTree[6].setLeft(charactersTree[13]);
        charactersTree[6].setRight(charactersTree[14]);

        charactersTree[7].setCharacter('s');
        charactersTree[7].setLeft(charactersTree[15]);
        charactersTree[7].setRight(charactersTree[16]);

        charactersTree[8].setCharacter('u');
        charactersTree[8].setLeft(charactersTree[17]);
        charactersTree[8].setRight(charactersTree[18]);

        charactersTree[9].setCharacter('r');
        charactersTree[9].setLeft(charactersTree[19]);

        charactersTree[10].setCharacter('w');
        charactersTree[10].setLeft(charactersTree[20]);
        charactersTree[10].setRight(charactersTree[21]);

        charactersTree[11].setCharacter('d');
        charactersTree[11].setLeft(charactersTree[22]);
        charactersTree[11].setRight(charactersTree[23]);

        charactersTree[12].setCharacter('k');
        charactersTree[12].setLeft(charactersTree[24]);
        charactersTree[12].setRight(charactersTree[25]);

        charactersTree[13].setCharacter('g');
        charactersTree[13].setLeft(charactersTree[26]);
        charactersTree[13].setRight(charactersTree[27]);

        charactersTree[14].setCharacter('o');
        charactersTree[14].setLeft(charactersTree[28]);
        charactersTree[14].setRight(charactersTree[29]);

        charactersTree[15].setCharacter('h');
        charactersTree[15].setLeft(charactersTree[30]);
        charactersTree[15].setRight(charactersTree[31]);

        charactersTree[16].setCharacter('v');
        charactersTree[16].setRight(charactersTree[32]);

        charactersTree[17].setCharacter('f');

        charactersTree[18].setRight(charactersTree[33]);

        charactersTree[19].setCharacter('l');

        charactersTree[20].setCharacter('p');

        charactersTree[21].setCharacter('j');
        charactersTree[21].setRight(charactersTree[34]);

        charactersTree[22].setCharacter('b');
        charactersTree[22].setLeft(charactersTree[35]);

        charactersTree[23].setCharacter('x');

        charactersTree[24].setCharacter('c');

        charactersTree[25].setCharacter('y');

        charactersTree[26].setCharacter('z');
        charactersTree[26].setLeft(charactersTree[36]);

        charactersTree[27].setCharacter('q');

        charactersTree[28].setLeft(charactersTree[37]);

        charactersTree[29].setLeft(charactersTree[38]);
        charactersTree[29].setRight(charactersTree[39]);

        charactersTree[30].setCharacter('5');
        charactersTree[31].setCharacter('4');
        charactersTree[32].setCharacter('3');
        charactersTree[33].setCharacter('2');
        charactersTree[34].setCharacter('1');
        charactersTree[35].setCharacter('6');
        charactersTree[36].setCharacter('7');
        charactersTree[37].setCharacter('8');
        charactersTree[38].setCharacter('9');
        charactersTree[39].setCharacter('0');

    }



}

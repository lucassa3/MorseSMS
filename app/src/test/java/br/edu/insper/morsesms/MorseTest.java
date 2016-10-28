package br.edu.insper.morsesms;

/**
 * Created by joaopedro on 27/10/16.
 */

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class MorseTest {
    CharacterNodeTree chartree;
    CharacterNode node;


    @Before
    public void initObjects(){
        chartree = new CharacterNodeTree();
        node = chartree.Tree[0];
    }
    @Test
    public void letterA() {
        node = node.getLeft();
        node = node.getRight();

        Assert.assertEquals(node.getCharacter(),'a');

    }
    @Test
    public void letterB() {
        node = node.getRight();
        node = node.getLeft();
        node = node.getLeft();
        node = node.getLeft();

        Assert.assertEquals(node.getCharacter(),'b');
    }
    @Test
    public void letterC() {
        node = node.getRight();
        node = node.getLeft();
        node = node.getRight();
        node = node.getLeft();
        Assert.assertEquals(node.getCharacter(),'c');

    }
    @Test
    public void letterD() {
        node = node.getRight();
        node = node.getLeft();
        node = node.getLeft();
        Assert.assertEquals(node.getCharacter(),'d');
    }
    @Test
    public void letterE() {
        node = node.getLeft();
        Assert.assertEquals(node.getCharacter(),'e');
    }
    @Test
    public void letterF() {
        node = node.getLeft();
        node = node.getLeft();
        node = node.getRight();
        node = node.getLeft();
        Assert.assertEquals(node.getCharacter(),'f');
    }
    @Test
    public void letterG() {
        node = node.getRight();
        node = node.getRight();
        node = node.getLeft();
        Assert.assertEquals(node.getCharacter(),'g');
    }
    @Test
    public void letterH() {
        node = node.getLeft();
        node = node.getLeft();
        node = node.getLeft();
        node = node.getLeft();
        Assert.assertEquals(node.getCharacter(),'h');
    }
    @Test
    public void letterI() {
        node = node.getLeft();
        node = node.getLeft();
        Assert.assertEquals(node.getCharacter(),'i');
    }
    @Test
    public void letterJ() {
        node = node.getLeft();
        node = node.getRight();
        node = node.getRight();
        node = node.getRight();
        Assert.assertEquals(node.getCharacter(),'j');
    }
    @Test
    public void letterK() {
        node = node.getRight();
        node = node.getLeft();
        node = node.getRight();
        Assert.assertEquals(node.getCharacter(),'k');
    }
    @Test
    public void letterL() {
        node = node.getLeft();
        node = node.getRight();
        node = node.getLeft();
        node = node.getLeft();
        Assert.assertEquals(node.getCharacter(),'l');
    }
    @Test
    public void letterM() {
        node = node.getRight();
        node = node.getRight();
        Assert.assertEquals(node.getCharacter(),'m');
    }
    @Test
    public void letterN() {
        node = node.getRight();
        node = node.getLeft();
        Assert.assertEquals(node.getCharacter(),'n');
    }
    @Test
    public void letterO() {
        node = node.getRight();
        node = node.getRight();
        node = node.getRight();
        Assert.assertEquals(node.getCharacter(),'o');
    }
    @Test
    public void letterP() {
        node = node.getLeft();
        node = node.getRight();
        node = node.getRight();
        node = node.getLeft();
        Assert.assertEquals(node.getCharacter(),'p');
    }
    @Test
    public void letterQ() {
        node = node.getRight();
        node = node.getRight();
        node = node.getLeft();
        node = node.getRight();
        Assert.assertEquals(node.getCharacter(),'q');

    }
    @Test
    public void letterR() {
        node = node.getLeft();
        node = node.getRight();
        node = node.getLeft();
        Assert.assertEquals(node.getCharacter(),'r');
    }
    @Test
    public void letterS() {
        node = node.getLeft();
        node = node.getLeft();
        node = node.getLeft();
        Assert.assertEquals(node.getCharacter(),'s');
    }
    @Test
    public void letterT() {
        node = node.getRight();
        Assert.assertEquals(node.getCharacter(),'t');
    }
    @Test
    public void letterU() {
        node = node.getLeft();
        node = node.getLeft();
        node = node.getRight();
        Assert.assertEquals(node.getCharacter(),'u');
    }
    @Test
    public void letterV() {
        node = node.getLeft();
        node = node.getLeft();
        node = node.getLeft();
        node = node.getRight();
        Assert.assertEquals(node.getCharacter(),'v');

    }
    @Test
    public void letterW() {
        node = node.getLeft();
        node = node.getRight();
        node = node.getRight();
        Assert.assertEquals(node.getCharacter(),'w');

    }
    @Test
    public void letterX() {
        node = node.getRight();
        node = node.getLeft();
        node = node.getLeft();
        node = node.getRight();
        Assert.assertEquals(node.getCharacter(),'x');

    }
    @Test
    public void letterY() {
        node = node.getRight();
        node = node.getLeft();
        node = node.getRight();
        node = node.getRight();
        Assert.assertEquals(node.getCharacter(),'y');

    }
    @Test
    public void letterZ() {
        node = node.getRight();
        node = node.getRight();
        node = node.getLeft();
        node = node.getLeft();
        Assert.assertEquals(node.getCharacter(),'z');

    }
    @Test
    public void numberOne() {
        node = node.getLeft();
        node = node.getRight();
        node = node.getRight();
        node = node.getRight();
        node = node.getRight();
        Assert.assertEquals(node.getCharacter(),'1');


    }
    @Test
    public void numberTwo() {
        node = node.getLeft();
        node = node.getLeft();
        node = node.getRight();
        node = node.getRight();
        node = node.getRight();
        Assert.assertEquals(node.getCharacter(),'2');

    }
    @Test
    public void numberThree() {
        node = node.getLeft();
        node = node.getLeft();
        node = node.getLeft();
        node = node.getRight();
        node = node.getRight();
        Assert.assertEquals(node.getCharacter(),'3');

    }
    @Test
    public void numberFour() {
        node = node.getLeft();
        node = node.getLeft();
        node = node.getLeft();
        node = node.getLeft();
        node = node.getRight();
        Assert.assertEquals(node.getCharacter(),'4');

    }
    @Test
    public void numberFive() {
        node = node.getLeft();
        node = node.getLeft();
        node = node.getLeft();
        node = node.getLeft();
        node = node.getLeft();
        Assert.assertEquals(node.getCharacter(),'5');

    }
    @Test
    public void numberSix() {
        node = node.getRight();
        node = node.getLeft();
        node = node.getLeft();
        node = node.getLeft();
        node = node.getLeft();
        Assert.assertEquals(node.getCharacter(),'6');

    }
    @Test
    public void numberSeven() {
        node = node.getRight();
        node = node.getRight();
        node = node.getLeft();
        node = node.getLeft();
        node = node.getLeft();
        Assert.assertEquals(node.getCharacter(),'7');

    }
    @Test
    public void numberEight() {

        node = node.getRight();
        node = node.getRight();
        node = node.getRight();
        node = node.getLeft();
        node = node.getLeft();
        Assert.assertEquals(node.getCharacter(),'8');
    }
    @Test
    public void numberNine() {
        node = node.getRight();
        node = node.getRight();
        node = node.getRight();
        node = node.getRight();
        node = node.getLeft();
        Assert.assertEquals(node.getCharacter(),'9');

    }
    @Test
    public void numberZero() {
        node = node.getRight();
        node = node.getRight();
        node = node.getRight();
        node = node.getRight();
        node = node.getRight();
        Assert.assertEquals(node.getCharacter(),'0');

    }

}

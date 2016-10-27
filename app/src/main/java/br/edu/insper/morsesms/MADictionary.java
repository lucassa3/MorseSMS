package br.edu.insper.morsesms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Lucas Scarlato Astur on 25/10/2016.
 */

public class MADictionary {
    CharacterNodeTree charTree;
    CharacterNode characterNode;
    Queue<CharacterNode> fila;
    NodeMapper nm;


    public MADictionary (CharacterNodeTree charTree) {
        fila = new LinkedList<>();
        this.charTree = charTree;
        nm = new NodeMapper(charTree);
        characterNode = charTree.Tree[0];

        }

    public void create () {
        fila.add(characterNode);
        while (!fila.isEmpty()) {
            CharacterNode node = fila.remove();

            if (node.getLeft() != null) {
                fila.add(node.getLeft());
            }

            if (node.getRight() != null) {
                fila.add(node.getRight());
            }

            System.out.println(node.getCharacter());
            System.out.println(node.getPath());

        }

    }}



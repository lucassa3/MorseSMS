package br.edu.insper.morsesms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Lucas Scarlato Astur on 27/10/2016.
 */

public class AMDictionary {
    CharacterNodeTree charTree;
    CharacterNode characterNode;
    NodeMapper nm;
    char[] alfabeto = new char[]{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','1','2','3','4','5','6','7','8','9','0'};
    ArrayList<String> MAList;



    public AMDictionary (CharacterNodeTree charTree) {
        this.charTree = charTree;
        characterNode = charTree.Tree[0];
        nm = new NodeMapper(charTree);
        MAList = new ArrayList<>();

        for(char i: alfabeto) {
            for (CharacterNode charnode : charTree.Tree ) {
                if(charnode.getCharacter() == i) {

                    String character = "" + charnode.getCharacter();
                    String path = "" + charnode.getPath();
                    MAList.add(character + "   " + path);

                }
            }

        }

    }}


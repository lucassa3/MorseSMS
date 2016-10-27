package br.edu.insper.morsesms;

import java.util.Stack;

/**
 * Created by Lucas Scarlato Astur on 26/10/2016.
 */

public class NodeMapper {
    CharacterNodeTree charTree;
    CharacterNode characterNode;
    Stack<CharacterNode> pilha = new Stack<>();
    
    public NodeMapper (CharacterNodeTree charTree) {
        this.charTree = charTree;
        characterNode = charTree.Tree[0];
        pilha.push(characterNode);

        String path = "";
        
        while(!pilha.isEmpty()) {
            characterNode = pilha.peek();

            if(characterNode.getCount() == 0) {
                if (characterNode.getLeft()!=null) {
                    pilha.push(characterNode.getLeft());
                    path += '.';
                }
                characterNode.setCount(characterNode.getCount()+1);

            }

            else if(characterNode.getCount() == 1) {
                if (characterNode.getRight()!= null) {
                    pilha.push(characterNode.getRight());
                    path +='-';
                }
                characterNode.setCount(characterNode.getCount()+1);

            }
            
            else {
                characterNode.setPath(path);
                System.out.println(characterNode.getPath());
                System.out.println(characterNode.getCharacter());
                characterNode.setCount(0);
                pilha.pop();
                if (path.length()!=0) {
                    path = path.substring(0, path.length()-1);
                }

                
            }
            
            
        }
    }
}

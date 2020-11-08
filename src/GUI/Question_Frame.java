/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javax.swing.*;

public class Question_Frame {

    private String answer;
    private boolean toRemove;

    public boolean isToRemove() {
        return toRemove;
    }
        
    public String getAnswer() {
        return answer;
    }

    public Question_Frame(String msg) {
        JFrame frame = new JFrame("Seleccio");

        // prompt the user to enter their name
        this.answer = JOptionPane.showInputDialog(frame, msg);

    }
    
    public Question_Frame(String msg, String ipPreSelected) {
        JFrame frame = new JFrame("Seleccio");
        // prompt the user to enter their name
        this.answer = JOptionPane.showInputDialog(frame, msg, ipPreSelected);

    }
    
    public Question_Frame(String msg, boolean isQuestion, String IP){
        
        String[] options = {"Si", "No"};

        int x = JOptionPane.showOptionDialog(null, msg ,
                "Confirma accio",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        
        if(x == 0){
            this.toRemove = true;
        }else{
            this.toRemove = false;
        }
        

    }
}

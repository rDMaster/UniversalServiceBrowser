/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package universalservicebrowser;

import javax.swing.*;

/**
 *
 * @author ryan
 */
public class DiceService implements Service{
    
    JLabel label;
    JComboBox numOfDice;
    
    @Override
    public JPanel getGuiPanel()
    {
        JPanel panel = new JPanel();
        JButton button = new JButton("Roll `em!");
        String[] choices = {"1","2","3","4","5"};
        numOfDice = new JComboBox(choices);
        label = new JLabel("Dices values here");
        // button lambda
        button.addActionListener(e -> {
            // roll the dice
            String diceOutput = "";
            String selection = (String) numOfDice.getSelectedItem();
            int numOfDiceToRoll = Integer.parseInt(selection);
            for(int i = 0; i < numOfDiceToRoll; i++)
            {
                int r = (int) ((Math.random() * 6) + 1);
                diceOutput += (" " + r);
            }
            label.setText(diceOutput);
        });
        
        panel.add(numOfDice);
        panel.add(button);
        panel.add(label);
        return panel;
                
    }
    

}


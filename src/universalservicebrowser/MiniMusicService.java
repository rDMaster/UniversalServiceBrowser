/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package universalservicebrowser;

import java.awt.*;
import javax.sound.midi.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;

/**
 *
 * @author ryan
 */
public class MiniMusicService implements Service{
    
    MyDrawPanel myPanel;
    
    public JPanel getGuiPanel()
    {
        JPanel mainPanel = new JPanel();
        myPanel = new MyDrawPanel();
        
        JButton playItButton = new JButton("Play It");
        playItButton.addActionListener(e -> {
            
            try
            {
                Sequencer sequencer = MidiSystem.getSequencer();
                sequencer.open();
                
                sequencer.addControllerEventListener(myPanel, new int[] {127});
                Sequence seq = new Sequence(Sequence.PPQ, 4);
                Track track = seq.createTrack();
                
                for(int i = 0; i< 100; i++)
                {
                    int rNum = (int) ((Math.random() * 50) + 1);
                    if (rNum < 38) // so now only do it if num < 38 (75% of the time)
                    {
                        track.add(makeEvent(144,1,rNum,100,i));
                        track.add(makeEvent(176,1,127,0,i));
                        track.add(makeEvent(128,1,rNum,100,i + 2));
                    }
                } // close loop
                
                sequencer.setSequence(seq);
                sequencer.start();
                sequencer.setTempoInBPM(220);
                
            } catch (Exception ex)
            {
                ex.printStackTrace();
            }
            
        }); // close lambda
        
        mainPanel.add(myPanel);
        mainPanel.add(playItButton);
        return mainPanel;
    } // close method
    
    public MidiEvent makeEvent(int comd, int chan, int one, int two, int tick)
    {
        MidiEvent event = null;
        try
        {
            ShortMessage a = new ShortMessage();
            a.setMessage(comd, chan, one, two);
            event = new MidiEvent(a, tick);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return event;
    } // close method
    
    class MyDrawPanel extends JPanel implements ControllerEventListener
    {
        // only if we got an event do we want to paint
        boolean msg = false;
        
        @Override
         public void controlChange(ShortMessage event)
         {
             msg = true;
             repaint();
         }// close method
         
        @Override
         public Dimension getPreferredSize()
         {
             return new Dimension(300,300);
         } // close method
         
        @Override
         public void paintComponent(Graphics g)
         {
             if(msg)
             {
                 Graphics2D g2 = (Graphics2D) g;
                 
                 int r = (int) (Math.random() * 250);
                 int gr = (int) (Math.random() * 250);
                 int b = (int) (Math.random() * 250);
                 
                 g.setColor(new Color(r,gr,b));
                 
                 int ht = (int) ((Math.random() * 120) + 10);
                 int width = (int) ((Math.random() * 120) + 10);
                 
                 int x = (int) ((Math.random() * 40) + 10);
                 int y = (int) ((Math.random() * 40) + 10);
                 
                 g.fillRect(x, y, ht, width);
                 msg = false;
             } // close if
         } // close method
         
    } // close inner class

} // close class


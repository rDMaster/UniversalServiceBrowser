/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package universalservicebrowser;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.text.*;
import sun.util.spi.CalendarProvider;
/**
 *
 * @author ryan
 */
public class DayOfTheWeekService  implements Service{

    JLabel outputLabel;
    JComboBox month;
    JTextField day;
    JTextField year;

    @Override
    public JPanel getGuiPanel()
    {
        JPanel pane1 = new JPanel();
        JButton button = new JButton("Do It!");
        button.addActionListener(event -> {
             int monthNum = month.getSelectedIndex();
             int dayNum = Integer.parseInt(day.getText());
             int yearNum = Integer.parseInt(year.getText());
             Calendar c = Calendar.getInstance();
             c.set(Calendar.MONTH, monthNum);
             c.set(Calendar.DAY_OF_MONTH, dayNum);
             c.set(Calendar.YEAR, yearNum);
             Date date = c.getTime();
             String dayOfWeek = (new SimpleDateFormat("EEEE")).format(date);
             outputLabel.setText(dayOfWeek);
        }); // close lambda
        outputLabel = new JLabel("date appears here");
        DateFormatSymbols dateStuff = new DateFormatSymbols();
        month = new JComboBox(dateStuff.getMonths());
        day = new JTextField(8);
        year = new JTextField(8);
        JPanel inputPanel = new JPanel(new GridLayout(3,2));
        inputPanel.add(new JLabel("Month"));
        inputPanel.add(month);
        inputPanel.add(new JLabel("Day"));
        inputPanel.add(day);
        inputPanel.add(new JLabel("Year"));
        inputPanel.add(year);
        pane1.add(inputPanel);
        pane1.add(button);
        pane1.add(outputLabel);
        return pane1;
        
        
    }
    
}


/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package universalservicebrowser;

import java.awt.*;
import java.rmi.*;
import javax.swing.*;

/**
 *
 * @author ryan
 */
public class ServiceBrowser {

    JPanel mainPanel;
    JComboBox serviceList;
    ServiceServer server;
    
    public void buildGUI()
    {
        JFrame frame = new JFrame("RMI Browser");
        mainPanel = new JPanel();
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        
        // RMI Registry lookup
        Object[] services = getServicesList();
        
        // JComboBox can make a displayable string out of each object on the 
        serviceList = new JComboBox(services);
        
        frame.getContentPane().add(BorderLayout.NORTH, serviceList);
        
        serviceList.addActionListener(e -> {
            Object selection = serviceList.getSelectedItem();
            loadService(selection);
        }); // close lambda
    } // close method
    
    void loadService(Object serviceSelection)
    {
        try
        {
            Service svc = server.getService(serviceSelection);
            
            mainPanel.removeAll();
            mainPanel.add(svc.getGuiPanel());
            mainPanel.validate();
            mainPanel.repaint();
                    
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    } // close method
    
    Object[] getServicesList()
    {
        Object obj = null;
        Object[] services = null;
        
        try
        {
            // do rmi lookup and get the stub
            obj = Naming.lookup("rmi://127.0.0.1/ServiceServer");
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        //cast the stub to the remote interface type
        server = (ServiceServer) obj;
        
        try
        {
            services = server.getServiceList();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return services;
    } // close method
    
    public static void main(String[] args)
    {
        new ServiceBrowser().buildGUI();
    }
}


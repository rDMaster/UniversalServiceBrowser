/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universalservicebrowser;

import javax.swing.*;
import java.io.*;

/**
 *
 * @author ryan
 */
public interface Service extends Serializable
{
    public JPanel getGuiPanel();
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universalservicebrowser;

import java.rmi.*;

/**
 *
 * @author ryan
 */
public interface ServiceServer extends Remote
{
    Object[] getServiceList() throws RemoteException;
    Service getService(Object servicekey) throws RemoteException;
}

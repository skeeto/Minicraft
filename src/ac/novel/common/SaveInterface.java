package ac.novel.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SaveInterface extends Remote {
	
	public Save getSave() throws RemoteException;
	
}

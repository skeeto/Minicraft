package ac.novel.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public interface InitInterface extends Remote {
	
	public ArrayList<String> getSavedState() throws RemoteException;
	
}

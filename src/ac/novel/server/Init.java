package ac.novel.server;

import java.rmi.RemoteException;
import java.util.ArrayList;

import ac.novel.common.InitInterface;

public class Init implements InitInterface {

	@Override
	public ArrayList<String> getSavedState() throws RemoteException {
		System.out.println("Saved data requeted");
		ArrayList<String> result = new ArrayList<String>();
		// TODO Auto-generated method stub
		return result;
	}
	

}

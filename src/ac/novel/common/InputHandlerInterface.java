package ac.novel.common;

import java.awt.event.KeyEvent;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;

public interface InputHandlerInterface extends Remote {

	public void releaseAll() throws RemoteException;

	public void tick() throws RemoteException;

	public HashMap<Integer, Integer> getPressesByKey() throws RemoteException;

	public void keyPressed(int keyCode) throws RemoteException;

    public void keyDown(int keyCode) throws RemoteException;

	public void keyReleased(int keyCode) throws RemoteException;
}

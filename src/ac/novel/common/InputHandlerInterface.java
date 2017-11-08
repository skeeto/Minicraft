package ac.novel.common;

import java.awt.event.KeyEvent;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InputHandlerInterface extends Remote {

	public void releaseAll() throws RemoteException;

	public void tick() throws RemoteException;

	public void keyPressed(KeyEvent ke) throws RemoteException;

	public void keyReleased(KeyEvent ke) throws RemoteException;

	public void keyTyped(KeyEvent ke) throws RemoteException;
}
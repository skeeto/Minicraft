package com.mojang.ld22;

import java.awt.event.KeyEvent;
import java.rmi.Remote;

public interface InputHandlerInterface extends Remote {

	public void releaseAll();

	public void tick();

	public void keyPressed(KeyEvent ke);

	public void keyReleased(KeyEvent ke);

	public void keyTyped(KeyEvent ke);
}
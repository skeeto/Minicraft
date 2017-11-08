package ac.novel.client;

import ac.novel.common.InputHandler;
import ac.novel.common.InputHandlerInterface;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class InputHandlerClient extends InputHandler {
	private InputHandlerInterface remoteInputHandler;

	public class Key {
		public int presses, absorbs;
		public boolean down, clicked;

		public Key() {
			keys.add(this);
		}
		public void toggle(boolean pressed) {
			if (pressed != down) {
				down = pressed;
			}
			if (pressed) {
				presses++;
			}
		}

		public void tick() {
			if (absorbs < presses) {
				absorbs++;
				clicked = true;
			} else {
				clicked = false;
			}
		}
	}

	public List<Key> keys = new ArrayList<Key>();

	public Key up = new Key();
	public Key down = new Key();
	public Key left = new Key();
	public Key right = new Key();
	public Key attack = new Key();
	public Key menu = new Key();

	@Override
	public void releaseAll() {
	}

	@Override
	public void tick() {
	}

	public InputHandlerClient(InputHandlerInterface remoteInputHandler) {
		this.remoteInputHandler = remoteInputHandler;
	}

	@Override
	public void keyPressed(KeyEvent ke) {
		System.out.println("Sending keypressed event to server");
		try {
			remoteInputHandler.keyPressed(ke.getKeyCode());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void keyReleased(KeyEvent ke) {
	}

	@Override
	public void keyTyped(KeyEvent ke) {
	}
}

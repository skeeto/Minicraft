package ac.novel.client;

import ac.novel.common.InputHandler;
import ac.novel.common.InputHandlerInterface;

import java.awt.event.KeyEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InputHandlerClient extends InputHandler {
	private InputHandlerInterface remoteInputHandler;

	public class Key {
		public int presses, absorbs, clients;
		public boolean down, clicked;
        private int keycode;

		public Key(int keycode) {
			keys.add(this);
            this.keycode = keycode;
		}
		public void toggle(boolean pressed) {
            if (!down && pressed) {
                try {
                	updateClients();
                    remoteInputHandler.keyPressed(keycode);
                } catch (RemoteException e) {
                    System.out.println("Failure on keyPressed");
                    e.printStackTrace();
                }
            } else if (down && pressed) {
                try {
                    remoteInputHandler.keyDown(keycode);
                } catch (RemoteException e) {
                    System.out.println("Failure on keyDown");
                    e.printStackTrace();
                }
            } else {
                try {
                    remoteInputHandler.keyReleased(keycode);
                } catch (RemoteException e) {
                    System.out.println("Failure on keyReleased");
                    e.printStackTrace();
                }
            }

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

	public Key up = new Key(KeyEvent.VK_W);
	public Key localDown = new Key(KeyEvent.VK_S);
	public Key localLeft = new Key(KeyEvent.VK_A);
	public Key localRight = new Key(KeyEvent.VK_D);
	public Key localAttack = new Key(KeyEvent.VK_SPACE);
	public Key localMenu = new Key(KeyEvent.VK_ENTER);

	public void updateClients(){
		HashMap<Integer, Integer> pressedByKey;
		try {
			pressedByKey = remoteInputHandler.getPressesByKey();
			System.out.println("Received presses info from server");
			System.out.println(pressedByKey.toString());
		} catch (RemoteException e) {
			e.printStackTrace();
			return;
		}
		for (Integer keyCode:
			 pressedByKey.keySet()) {
			Key currentKey = this.getKey(keyCode);
			if (currentKey != null) {
				System.out.println(String.format("Updating client list for key with client num %s", currentKey.toString()));
				System.out.println(pressedByKey.get(keyCode));
				currentKey.clients = pressedByKey.get(keyCode);
				System.out.println(String.format("Updated client list with num %s", currentKey.clients));
			}
		}
	}

	private Key getKey(int keyCode) {
		if (keyCode == KeyEvent.VK_UP) return up;
		if (keyCode == KeyEvent.VK_DOWN) return localDown;
		if (keyCode == KeyEvent.VK_LEFT) return localLeft;
		if (keyCode == KeyEvent.VK_RIGHT) return localRight;
		if (keyCode == KeyEvent.VK_X) return localMenu;
		if (keyCode == KeyEvent.VK_C) return localAttack;
		return null;
	}

	@Override
	public void releaseAll() {
        for (Key key : keys) {
        	if (key.down) {
	            key.toggle(false);
        	}
        }
	}

	@Override
	public void tick() {
	}

	public InputHandlerClient(InputHandlerInterface remoteInputHandler) {
		this.remoteInputHandler = remoteInputHandler;
	}

	@Override
	public void keyPressed(KeyEvent ke) {
        toggle(ke.getKeyCode(), true);
	}

    private void toggle(int keyCode, boolean pressed) {
		if (keyCode == KeyEvent.VK_NUMPAD8) up.toggle(pressed);
		if (keyCode == KeyEvent.VK_NUMPAD2) localDown.toggle(pressed);
		if (keyCode == KeyEvent.VK_NUMPAD4) localLeft.toggle(pressed);
		if (keyCode == KeyEvent.VK_NUMPAD6) localRight.toggle(pressed);
		if (keyCode == KeyEvent.VK_W) up.toggle(pressed);
		if (keyCode == KeyEvent.VK_S) localDown.toggle(pressed);
		if (keyCode == KeyEvent.VK_A) localLeft.toggle(pressed);
		if (keyCode == KeyEvent.VK_D) localRight.toggle(pressed);
		if (keyCode == KeyEvent.VK_UP) up.toggle(pressed);
		if (keyCode == KeyEvent.VK_DOWN) localDown.toggle(pressed);
		if (keyCode == KeyEvent.VK_LEFT) localLeft.toggle(pressed);
		if (keyCode == KeyEvent.VK_RIGHT) localRight.toggle(pressed);
		if (keyCode == KeyEvent.VK_TAB) localMenu.toggle(pressed);
		if (keyCode == KeyEvent.VK_ALT) localMenu.toggle(pressed);
		if (keyCode == KeyEvent.VK_ALT_GRAPH) localMenu.toggle(pressed);
		if (keyCode == KeyEvent.VK_SPACE) localAttack.toggle(pressed);
		if (keyCode == KeyEvent.VK_CONTROL) localAttack.toggle(pressed);
		if (keyCode == KeyEvent.VK_NUMPAD0) localAttack.toggle(pressed);
		if (keyCode == KeyEvent.VK_INSERT) localAttack.toggle(pressed);
		if (keyCode == KeyEvent.VK_ENTER) localMenu.toggle(pressed);
		if (keyCode == KeyEvent.VK_X) localMenu.toggle(pressed);
		if (keyCode == KeyEvent.VK_C) localAttack.toggle(pressed);
    }

	@Override
	public void keyReleased(KeyEvent ke) {
        toggle(ke.getKeyCode(), false);
	}

	@Override
	public void keyTyped(KeyEvent ke) {
	}
}

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

	public Key up = new Key(KeyEvent.VK_UP);
	public Key down = new Key(KeyEvent.VK_DOWN);
	public Key left = new Key(KeyEvent.VK_LEFT);
	public Key right = new Key(KeyEvent.VK_RIGHT);
	public Key attack = new Key(KeyEvent.VK_C);
	public Key menu = new Key(KeyEvent.VK_X);
	
	@Override
	public int getUpClients() {
		return up.clients;
	}
	
	@Override
	public int getDownClients() {
		return down.clients;
	}
	
	@Override
	public int getLeftClients() {
		return left.clients;
	}
	
	@Override
	public int getRightClients() {
		return right.clients;
	}
	
	@Override
	public int getAttackClients() {
		return attack.clients;
	}
	
	@Override
	public int getMenuClients() {
		return menu.clients;
	}

	@Override
	public void updateClients(){
		HashMap<Integer, Integer> pressedByKey;
		try {
			pressedByKey = remoteInputHandler.getPressesByKey();
		} catch (RemoteException e) {
			e.printStackTrace();
			return;
		}
		for (Integer keyCode : pressedByKey.keySet()) {
			Key currentKey = this.getKey(keyCode);
			if (currentKey != null) {
				currentKey.clients = pressedByKey.get(keyCode);
			}
		}
	}

	private Key getKey(int keyCode) {
		if (keyCode == KeyEvent.VK_UP) return up;
		if (keyCode == KeyEvent.VK_DOWN) return down;
		if (keyCode == KeyEvent.VK_LEFT) return left;
		if (keyCode == KeyEvent.VK_RIGHT) return right;
		if (keyCode == KeyEvent.VK_X) return menu;
		if (keyCode == KeyEvent.VK_C) return attack;
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
		if (keyCode == KeyEvent.VK_NUMPAD2) down.toggle(pressed);
		if (keyCode == KeyEvent.VK_NUMPAD4) left.toggle(pressed);
		if (keyCode == KeyEvent.VK_NUMPAD6) right.toggle(pressed);
		if (keyCode == KeyEvent.VK_W) up.toggle(pressed);
		if (keyCode == KeyEvent.VK_S) down.toggle(pressed);
		if (keyCode == KeyEvent.VK_A) left.toggle(pressed);
		if (keyCode == KeyEvent.VK_D) right.toggle(pressed);
		if (keyCode == KeyEvent.VK_UP) up.toggle(pressed);
		if (keyCode == KeyEvent.VK_DOWN) down.toggle(pressed);
		if (keyCode == KeyEvent.VK_LEFT) left.toggle(pressed);
		if (keyCode == KeyEvent.VK_RIGHT) right.toggle(pressed);
		if (keyCode == KeyEvent.VK_TAB) menu.toggle(pressed);
		if (keyCode == KeyEvent.VK_ALT) menu.toggle(pressed);
		if (keyCode == KeyEvent.VK_ALT_GRAPH) menu.toggle(pressed);
		if (keyCode == KeyEvent.VK_SPACE) attack.toggle(pressed);
		if (keyCode == KeyEvent.VK_CONTROL) attack.toggle(pressed);
		if (keyCode == KeyEvent.VK_NUMPAD0) attack.toggle(pressed);
		if (keyCode == KeyEvent.VK_INSERT) attack.toggle(pressed);
		if (keyCode == KeyEvent.VK_ENTER) menu.toggle(pressed);
		if (keyCode == KeyEvent.VK_X) menu.toggle(pressed);
		if (keyCode == KeyEvent.VK_C) attack.toggle(pressed);
    }

	@Override
	public void keyReleased(KeyEvent ke) {
        toggle(ke.getKeyCode(), false);
	}

	@Override
	public void keyTyped(KeyEvent ke) {
	}
}

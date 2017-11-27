package ac.novel.common;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InputHandler implements KeyListener, InputHandlerInterface {
	public class Key {
		public int presses, absorbs, clients;
		public boolean down, clicked;

		public Key() {
			clients = 0;
			keys.add(this);
		}

        public void clientToggle(boolean pressed) {
            if (pressed) {
                clients++;
            } else {
                clients--;
            }

            assert(clients >= 0);

            toggle(pressed);
        }

		public void toggle(boolean pressed) {
            down = (clients > 0);
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

	public HashMap<Integer, Integer> getPressesByKey() {
		HashMap<Integer, Integer> result = new HashMap<Integer,Integer>();
		System.out.println("Get Presses requested from client on server");
		result.put(KeyEvent.VK_UP, up.clients);
		result.put(KeyEvent.VK_DOWN, down.clients);
		result.put(KeyEvent.VK_LEFT, left.clients);
		result.put(KeyEvent.VK_RIGHT, right.clients);
		result.put(KeyEvent.VK_C, attack.clients);
		result.put(KeyEvent.VK_X, menu.clients);
		System.out.println(result.toString());
		return result;
	}

	public void releaseAll() {
		for (int i = 0; i < keys.size(); i++) {
			keys.get(i).down = false;
		}
	}

	public void tick() {
		for (int i = 0; i < keys.size(); i++) {
			keys.get(i).tick();
		}
	}

	public InputHandler(){}

	public void keyPressed(int keyCode) {
		clientToggle(keyCode, true);
	}

	public void keyDown(int keyCode) {
		toggle(keyCode, true);
	}

	public void keyReleased(int keyCode) {
		clientToggle(keyCode, false);
	}

	public void keyReleased(KeyEvent ke) {
		toggle(ke.getKeyCode(), false);
	}

	private Key getKey(int keyCode) {
		if (keyCode == KeyEvent.VK_NUMPAD8) return up;
		if (keyCode == KeyEvent.VK_NUMPAD2) return down;
		if (keyCode == KeyEvent.VK_NUMPAD4) return left;
		if (keyCode == KeyEvent.VK_NUMPAD6) return right;
		if (keyCode == KeyEvent.VK_W) return up;
		if (keyCode == KeyEvent.VK_S) return down;
		if (keyCode == KeyEvent.VK_A) return left;
		if (keyCode == KeyEvent.VK_D) return right;
		if (keyCode == KeyEvent.VK_UP) return up;
		if (keyCode == KeyEvent.VK_DOWN) return down;
		if (keyCode == KeyEvent.VK_LEFT) return left;
		if (keyCode == KeyEvent.VK_RIGHT) return right;
		if (keyCode == KeyEvent.VK_TAB) return menu;
		if (keyCode == KeyEvent.VK_ALT) return menu;
		if (keyCode == KeyEvent.VK_ALT_GRAPH) return menu;
		if (keyCode == KeyEvent.VK_SPACE) return attack;
		if (keyCode == KeyEvent.VK_CONTROL) return attack;
		if (keyCode == KeyEvent.VK_NUMPAD0) return attack;
		if (keyCode == KeyEvent.VK_INSERT) return attack;
		if (keyCode == KeyEvent.VK_ENTER) return menu;
		if (keyCode == KeyEvent.VK_X) return menu;
		if (keyCode == KeyEvent.VK_C) return attack;
        return null;
	}

	private void toggle(int keyCode, boolean pressed) {
        Key key = getKey(keyCode);
        if (key != null) {
            key.toggle(pressed);
        }
	}

	private void clientToggle(int keyCode, boolean pressed) {
        Key key = getKey(keyCode);
        if (key != null) {
            key.clientToggle(pressed);
        }
	}

	public void keyTyped(KeyEvent ke) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		toggle(e.getKeyCode(), true);
	}
}

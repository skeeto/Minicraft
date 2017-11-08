package ac.novel.common;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class InputHandler implements KeyListener, InputHandlerInterface {
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
		System.out.println("keypressed on server");
		toggle(keyCode, true);
	}

	public void keyReleased(KeyEvent ke) {
		toggle(ke.getKeyCode(), false);
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

	public void keyTyped(KeyEvent ke) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		toggle(e.getKeyCode(), true);
	}
}

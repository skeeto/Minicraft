package ac.novel.server;

import java.rmi.RemoteException;
import ac.novel.common.Save;
import ac.novel.common.SaveInterface;

public class GetSave implements SaveInterface {
	private Game game;
	
	public GetSave(Game game) {
		this.game = game;
	}

	@Override
	public Save getSave() throws RemoteException {
		System.err.println("Getting save from server");
		Save result = new Save(this.game);
		System.err.println("Sending save from server");
        System.err.println(String.format("Save dump: %d %d %d", result.currentLevel, result.player.x, result.player.y));
		return result;
	}
	
}

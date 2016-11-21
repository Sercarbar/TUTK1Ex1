package com.tu.tk1.rmi.minionsServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.tu.tk1.rmi.minionsClient.IMinionClient;

public class MinionServer extends UnicastRemoteObject implements IMinionServer{

	private static final long serialVersionUID = 1L;
	Map<String, IMinionClient> players;

	protected MinionServer() throws RemoteException {
		players= new HashMap<String, IMinionClient>();
	}

	@Override
	public synchronized void registerPlayerToGame(IMinionClient player) throws RemoteException {
		this.players.put( player.getPlayerInfo().getName(), player);
	}

	@Override
	public synchronized void broadcastPlayerStatus( IMinionClient newplayerStatus) throws RemoteException {
		try{		
		players.put(newplayerStatus.getPlayerInfo().getName(),newplayerStatus);				
		
		Iterator<Entry<String, IMinionClient>> it = players.entrySet().iterator();
	    while (it.hasNext()) {
	        Entry<String, IMinionClient> pair = it.next();
	        ((IMinionClient)pair.getValue()).retrieveGameStatus(players);	        
	    }
		
		}catch(Exception e){
			e.printStackTrace();
		}		
	}
}

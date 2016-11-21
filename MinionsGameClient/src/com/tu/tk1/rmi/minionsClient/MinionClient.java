package com.tu.tk1.rmi.minionsClient;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;

import com.tu.tk1.rmi.common.player;
import com.tu.tk1.rmi.minionsServer.IMinionServer;

public class MinionClient  extends UnicastRemoteObject implements IMinionClient,  java.io.Serializable{
	
	private IMinionServer minionServer;
	private player infoPlayer;
	
	protected MinionClient(player infoP, IMinionServer minionServ) throws RemoteException {
		infoPlayer=infoP ;
		this.minionServer=minionServ;
		minionServer.registerPlayerToGame(this);
	}

	private static final long serialVersionUID = 1L;

	@Override
	public player getPlayerInfo() throws RemoteException {
		return infoPlayer;
	}	

	@Override
	public void retrieveGameStatus(Map<String, IMinionClient> playersStatus) throws RemoteException {
		for (Map.Entry<String, IMinionClient> entry : playersStatus.entrySet())
		{
		    System.out.println(entry.getKey() + ":" + entry.getValue().getPlayerInfo().getScore());
		}		
	}	
		
	@Override
	public void fedMinion() {
		this.infoPlayer.setScore(this.infoPlayer.getScore()+1);
		try {
			minionServer.broadcastPlayerStatus(this);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

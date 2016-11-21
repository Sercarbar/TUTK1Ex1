package com.tu.tk1.rmi.minionsClient;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

import com.tu.tk1.rmi.common.player;

public interface IMinionClient extends Remote{
	public player getPlayerInfo() throws RemoteException;	
	public void retrieveGameStatus(Map<String, IMinionClient> playersStatus)throws RemoteException;	
	public void fedMinion()throws RemoteException;
}

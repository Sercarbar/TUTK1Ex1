package com.tu.tk1.rmi.minionsServer;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.tu.tk1.rmi.minionsClient.IMinionClient;

public interface IMinionServer extends Remote{
	void registerPlayerToGame(IMinionClient player) throws RemoteException;
	void broadcastPlayerStatus(IMinionClient newplayerStatus)throws RemoteException;
}
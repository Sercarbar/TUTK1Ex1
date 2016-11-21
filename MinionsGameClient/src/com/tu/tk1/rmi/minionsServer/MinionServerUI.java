package com.tu.tk1.rmi.minionsServer;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class MinionServerUI {

	public static void main(String[] args) throws MalformedURLException,RemoteException,NotBoundException {
		try
		{
			LocateRegistry.createRegistry(1099);
			IMinionServer iMinionServer=new MinionServer();
			Naming.rebind("rmi://localhost:1099/MinionServer", iMinionServer);
			System.out.println("Server Started");			
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
}
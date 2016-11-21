package com.tu.tk1.rmi.minionsClient;

import java.awt.EventQueue;

import javax.swing.JFrame;

import com.tu.tk1.rmi.common.player;
import com.tu.tk1.rmi.minionsServer.IMinionServer;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class minionClientUI {

	private JFrame frame;
	private JTextField txtName;
	MinionClient minClient;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					minionClientUI window = new minionClientUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public minionClientUI() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnFed = new JButton("Fed");
		btnFed.setEnabled(false);
		btnFed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
				minClient.fedMinion();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnFed.setBounds(10, 93, 89, 23);
		frame.getContentPane().add(btnFed);
		
		txtName = new JTextField();
		txtName.setBounds(10, 28, 173, 20);
		frame.getContentPane().add(txtName);
		txtName.setColumns(10);
		
		JLabel lblPlayerName = new JLabel("Player Name");
		lblPlayerName.setBounds(10, 11, 101, 14);
		frame.getContentPane().add(lblPlayerName);
		
		JButton btnStartPlaying = new JButton("Start Playing");
		btnStartPlaying.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IMinionServer iMinionServer;
				try {
					String namePlayer=txtName.getText();
					if(namePlayer.isEmpty())
						JOptionPane.showMessageDialog(null,"Please type your nickname");
					else{
						iMinionServer = (IMinionServer)Naming.lookup("rmi://localhost:1099/MinionServer");				
						minClient =new MinionClient(new player(txtName.getText(),0), iMinionServer);
						btnFed.setEnabled(true);
						btnStartPlaying.setEnabled(false);
					}
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnStartPlaying.setBounds(10, 59, 125, 23);
		frame.getContentPane().add(btnStartPlaying);
	}
}

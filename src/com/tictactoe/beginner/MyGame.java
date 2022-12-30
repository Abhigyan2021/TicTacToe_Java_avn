package com.tictactoe.beginner;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class MyGame extends JFrame implements ActionListener
{
	
	// Creating Variables to access various GUI methods
		
		JLabel heading, clockLabel ;
		Font font = new Font("Rockwell", Font.PLAIN, 40);
		JPanel mainPanel;
		
		JButton [] btns = new JButton[9];
		
		
	// game instance variable...
		
		int gameChances[] = {2,2,2,2,2,2,2,2,2};
		int activePlayer = 0;
		
   // Array for winning chances
		
		int wps[][] = {
				{0,1,2},
				{3,4,5},
				{6,7,8},
				{0,3,6},
				{1,4,7},
				{2,5,8},
				{0,4,8},
				{2,4,6}
		};
		
		int  winner = 2;
	
	// variables for draw logic
		
		boolean gameOver = false;
		
	// constructor
	MyGame()
	{
		System.out.println("Creating Instance of Game ///");
		
		//title bar
		super.setTitle("TIC TAC TOE GAME");
		super.setSize(550,550);
		// setting icon in title bar
		ImageIcon icon = new ImageIcon("src/img/tttS.png");
		setIconImage(icon.getImage());
		
		//calling the GUI function
		this.createGUI();
		
		//making the frame visible
		super.setVisible(true);
		
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	
	private void createGUI()
	{
		//setting color of GUI
		this.getContentPane().setBackground(Color.decode("#2196f3"));
		
		// setting layout of GUI
		this.setLayout(new BorderLayout());
		
		// north section -> heading
		heading = new JLabel("TIC TAC TOE by ABHIGYAN");
//		heading.setIcon(new ImageIcon("src/img/tttS.png"));
		heading.setFont(font);
		heading.setHorizontalAlignment(SwingConstants.CENTER);
		heading.setForeground(Color.white);
//		heading.setHorizontalTextPosition(SwingConstants.CENTER);
//		heading.setVerticalTextPosition(SwingConstants.BOTTOM);
		
		this.add(heading, BorderLayout.NORTH);
		
		// south section -> clock
		
		clockLabel = new JLabel("Clock");;
		
		clockLabel.setFont(font);
		clockLabel.setHorizontalAlignment(SwingConstants.CENTER);
		clockLabel.setForeground(Color.white);
		
		this.add(clockLabel, BorderLayout.SOUTH);
		
		
		// creating clock using threads -> anonymous class used
		
		Thread t = new Thread()
				{
					public void run()
					{
					    try
					    {
					    	
							while(true)
							{
								@SuppressWarnings("deprecation")
								String datetime = new Date().toLocaleString();
								
								clockLabel.setText(datetime);
								
								Thread.sleep(1000);
							}
					    }
					    catch (Exception e)
					    {
					    	e.printStackTrace();
					    }
					}
				};
		
		  t.start();		
		  
		  //// Panel Section...
		  mainPanel = new JPanel();
		  
		  mainPanel.setLayout(new GridLayout(3,3));
		  
		  for(int i = 1; i <= 9; i++)
		  {
//			  JButton btns = new JButton(i + " ");
			  
			  JButton btn =  new JButton();
//			  btn.setIcon(new ImageIcon("src/img/0.png"));
			  
			  
			  btn.setBackground(Color.decode("#90caf9"));
			  btn.setFont(font);
			  mainPanel.add(btn);
			  btns[i-1] = btn;
			  btn.addActionListener(this);
//			  btn.setName(i-1 + " "); //Appending a string at the end converts the integer into string
		     // or we can do
			 btn.setName(String.valueOf(i-1));
		  
		  }
		  
		  this.add(mainPanel, BorderLayout.CENTER);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
//		System.out.println("Clicked"); // To check if button is being clicked
		
		JButton currentButton = (JButton)e.getSource();
		
		String nameStr = currentButton.getName();
//		System.out.println(nameStr);
		
		int name = Integer.parseInt(nameStr.trim());
		
		if(gameOver == true)
		{
			JOptionPane.showMessageDialog(this, "Game Already Over");
			return;
			
			
		}
		
		if(gameChances[name] == 2)
		{
			if(activePlayer == 1)
			{
				currentButton.setIcon(new ImageIcon("src/img/cross.png"));
				 
				gameChances[name] = activePlayer;
				activePlayer = 0;
			}
			else
			{
				currentButton.setIcon(new ImageIcon("src/img/circle.png"));
				gameChances[name] = activePlayer;
				activePlayer = 1;
			}
			// find the winner...
				
			for(int [] temp: wps)
			{
				if((gameChances[temp[0]] == gameChances[temp[1]]) && (gameChances[temp[1]] == gameChances[temp[2]]) && (gameChances[temp[2]] != 2))
				{
					winner = gameChances[temp[0]];
					gameOver = true;
					JOptionPane.showMessageDialog(null, "Player" + winner + "has won the game ");
					int i = JOptionPane.showConfirmDialog(this, "do you want to play more ??");
					if(i == 0)
					{
						this.setVisible(false);
						new MyGame();
						
					}
					else if (i == 1)
					{
						System.exit(242);
						
					}
					else
					{
						
					}
					
					System.out.println(i);
					break;
				}
			}
				
			// checking draw logic
			
			int c = 0;
			for(int x : gameChances)
			{
				if(x == 2)
				{
					c++;
					break;
				}
			}
			
			if(c == 0 && gameOver == false)
			{
				JOptionPane.showMessageDialog(null, "This Game has been a draw..");
				
				int i = JOptionPane.showConfirmDialog(this, "Play Again??");
				if(i == 0)
				{
					this.setVisible(false);
					
					new MyGame();
				}
				else if(i == 1)
				{
					System.exit(1212);
				}
				else
				{
					
				}
				
				gameOver = true;
			}
		}
		else
		{	
			JOptionPane.showMessageDialog(this, "Position Already Occupied..");
		}
		
		
		
		
	}
	
}

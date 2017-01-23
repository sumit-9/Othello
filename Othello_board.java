package Othello;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

import java.awt.event.MouseEvent;

public class Board extends JFrame implements MouseListener{
	
	private static final long serialVersionUID = 1L;
	JPanel[] row = new JPanel[8];
    JButton[] button = new JButton[64];
    String[] buttonString = {"0,0", "0,1", "0,2", "0,3", "0,4","0,5","0,6","0,7",
    		"1,0", "1,1", "1,2", "1,3", "1,4","1,5","1,6","1,7",
    		"2,0", "2,1", "2,2", "2,3", "2,4","2,5","2,6","2,7",
    		"3,0", "3,1", "3,2", "3,3", "3,4","3,5","3,6","3,7",
    		"4,0", "4,1", "4,2", "4,3", "4,4","4,5","4,6","4,7",
    		"5,0", "5,1", "5,2", "5,3", "5,4","5,5","5,6","5,7",
    		"6,0", "6,1", "6,2", "6,3", "6,4","6,5","6,6","6,7",
    		"7,0", "7,1", "7,2", "7,3", "7,4","7,5","7,6","7,7"
    };
    int[] dimW = {300,45,100,90};
    int[] dimH = {35, 40};

    Dimension regularDimension = new Dimension(dimW[1], dimH[1]);

    Font font = new Font("Times new Roman", Font.BOLD, 14);
    
	char[][] board;
	
	Player p1 = new Player('a');
	Player p2 = new Player('b');
	
	public Board(){
		board = new char[8][8];
		board[3][3] = p1.symbol;
		board[4][4] = p1.symbol;
		board[3][4] = p2.symbol;
		board[4][3] = p2.symbol;
		setDesign();
        setSize(400, 400);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        GridLayout grid = new GridLayout(8,8);
        setLayout(grid);
        FlowLayout f1 = new FlowLayout(FlowLayout.CENTER);

        for(int i = 0; i < 8; i++)
            row[i] = new JPanel();
        for(int i = 0; i < 8; i++)
            row[i].setLayout(f1);
        
        for(int i = 0; i < 64; i++) {
            button[i] = new JButton();
            button[i].setBackground(Color.white);
            button[i].addMouseListener(this);
        }
        
        button[27].setBackground(Color.red);
        button[36].setBackground(Color.red);
        button[28].setBackground(Color.blue);
        button[35].setBackground(Color.blue);
        
        for(int i = 0; i < 64; i++)
            button[i].setPreferredSize(regularDimension);
        
        for(int i = 0; i < 8; i++)
            row[0].add(button[i]);
        add(row[0]);
        for(int i = 8; i < 16; i++)
            row[1].add(button[i]);
        add(row[1]);
        for(int i = 16; i < 24; i++)
            row[2].add(button[i]);
        add(row[2]);
        for(int i =24; i < 32; i++)
            row[3].add(button[i]);
        add(row[3]);
        for(int i = 32; i < 40; i++)
            row[4].add(button[i]);
        add(row[4]);
        for(int i = 40; i < 48; i++)
            row[5].add(button[i]);
        add(row[5]);
        for(int i = 48; i < 56; i++)
            row[6].add(button[i]);
        add(row[6]);
        for(int i = 56; i < 64; i++)
            row[7].add(button[i]);
        add(row[7]);
        
        setVisible(true);     
	}

	public final void setDesign() {
        try {
            UIManager.setLookAndFeel(
                    "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            UIManager.put("nimbusBase", Color.black);
            UIManager.put("nimbusBlueGrey", Color.black);
            UIManager.put("control", Color.gray);
        } catch(Exception e) {   
        }
    }
	
	public boolean boardStatus(Player p1,Player p2)
	{
		boolean ans = false;
		for (int i=0;i<8;i++)
		{
			for (int j=0;j<8;j++)
			{
				if (board[i][j]=='\0' && checkpossible(p1, p2, i, j))
				{
					ans = true;
					break;
				}
			}
		}
		return ans;
	}
	
	int chance1 = 0;
	
	public void finish()
	{
		char ans = whowon();
		if (ans==p1.symbol)
			setp1();
		else if (ans==p2.symbol)
			setp2();
		else 
			setp3();
	}
	
	private void setp3() {
		// TODO Auto-generated method stub
		for(int i = 0; i < 64; i++)
		{
            button[i].setBackground(Color.YELLOW);
            button[i].setText("");
		}
		button[0].setText("D");
		button[1].setText("R");
		button[2].setText("A");
		button[3].setText("W");
	}

	private void setp2() {
		// TODO Auto-generated method stub
		for(int i = 0; i < 64; i++)
		{
            button[i].setBackground(Color.YELLOW);
            button[i].setText("");
		}
		button[0].setText("P");
		button[1].setText("L");
		button[2].setText("A");
		button[3].setText("Y");
		button[4].setText("E");
		button[5].setText("R");
		button[8].setText("B");
		button[9].setText("L");
		button[10].setText("U");
		button[11].setText("E");
		button[16].setText("W");
		button[17].setText("O");
		button[18].setText("N");
	}

	private void setp1() {
		// TODO Auto-generated method stub
		for(int i = 0; i < 64; i++)
		{
            button[i].setBackground(Color.YELLOW);
            button[i].setText("");
		}
		button[0].setText("P");
		button[1].setText("L");
		button[2].setText("A");
		button[3].setText("Y");
		button[4].setText("E");
		button[5].setText("R");
		button[8].setText("R");
		button[9].setText("E");
		button[10].setText("D");
		button[16].setText("W");
		button[17].setText("O");
		button[18].setText("N");
		
	}

	public char whowon(){
		int p1count=0;
		int p2count=0;
		for (int i=0;i<8;i++)
		{
			for (int j=0;j<8;j++)
			{
				if (board[i][j]==p1.symbol)
					p1count++;
				else if (board[i][j]==p2.symbol)
					p2count++;
			}
		}
		if (p1count>p2count)
			return p1.symbol;
		else if (p2count>p1count)
			return p2.symbol;
		return 'z';
	}
	
	int[] arrx = {0,1,1,1,0,-1,-1,-1};
	int[] arry = {1,1,0,-1,-1,-1,0,1};
	
	public void makeMove(Player p1, Player p2,int x,int y)
	{
		int majorFlag=0;
		for (int curr=0;curr<8;curr++)
		{
			int xpos =x+arrx[curr];
			int ypos =y+arry[curr];
			if (!(xpos<=7 && ypos <=7 && xpos>=0 && ypos>=0))
			{
				continue;
			}
			int minorFlag1=0,minorFlag2=0,helperFlag=0;
			while (xpos<=7 && ypos <=7 && xpos>=0 && ypos>=0 && board[xpos][ypos]!='\0')
			{
				if (board[xpos][ypos]==p2.symbol)
				{
					minorFlag2=1;
				}
				if (board[xpos][ypos]==p1.symbol)
				{
					minorFlag1=1;
					if (minorFlag2==1)
					{
						helperFlag=1;
						break;
					}
					else
						break;
				}
				xpos += arrx[curr];
				ypos += arry[curr];
			}
			if (minorFlag1==1 && helperFlag==1)
			{
				majorFlag=1;
				while (xpos!=x || ypos!=y)
				{
					board[xpos][ypos]=p1.symbol;
					if (p1.symbol=='a')
						button[(8*xpos)+ypos].setBackground(Color.red);
					else
						button[(8*xpos)+ypos].setBackground(Color.blue);
					xpos -=(arrx[curr]);
					ypos -=(arry[curr]);
				}
				board[xpos][ypos]=p1.symbol;
				if (p1.symbol=='a')
					button[(8*xpos)+ypos].setBackground(Color.red);
				else
					button[(8*xpos)+ypos].setBackground(Color.blue);
			}
		}
		if (majorFlag==0)
		{
			if (chance1==1)
				chance1 = 0;
			else if (chance1==0)
				chance1 = 1;
		}
	}
	public boolean checkpossible(Player p1, Player p2,int x,int y)
	{
		int majorFlag=0;
		for (int curr=0;curr<8;curr++)
		{
			int xpos =x+arrx[curr];
			int ypos =y+arry[curr];
			if (!(xpos<=7 && ypos <=7 && xpos>=0 && ypos>=0))
				continue;
			int minorFlag1=0,minorFlag2=0,helperFlag=0;
			while (xpos<=7 && ypos <=7 && xpos>=0 && ypos>=0 && board[xpos][ypos]!='\0')
			{
				if (board[xpos][ypos]==p2.symbol)
					minorFlag2=1;
				if (board[xpos][ypos]==p1.symbol)
				{
					minorFlag1=1;
					if (minorFlag2==1)
						helperFlag=1;
					break;
				}
				xpos += arrx[curr];
				ypos += arry[curr];
			}
			if (minorFlag1==1 && helperFlag==1)
				majorFlag=1;
		}
		if (majorFlag==0)
			return false;
		return true;
	}

	@Override
	public void mouseClicked(MouseEvent ae) {
		// TODO Auto-generated method stub
		int k=0;
		for (int i=0;i<8;i++)
		{
			for (int j=0;j<8;j++)
			{
				if(ae.getSource() == button[k] && board[i][j]=='\0')
				{
					if (chance1==0)
					{
						chance1 = 1;
						makeMove(p1, p2, i, j);
					}
					else
					{
						chance1 = 0;
						makeMove(p2, p1, i, j);
					}
				}
			++k;
			}
		}
		boolean moremoves = false;
		if (chance1 == 0 && boardStatus(p1, p2)==true)
			moremoves = true;
		if (chance1 == 1 && boardStatus(p2, p1)==true)
			moremoves = true;
		if (moremoves == false)
		{
			finish();
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		int k=0;
		for (int i=0;i<8;i++)
		{
			for (int j=0;j<8;j++)
			{
				if(e.getSource() == button[k] && board[i][j]=='\0')
				{
					if (chance1==0 && checkpossible(p1, p2, i, j))
						button[k].setBackground(Color.red);
					else if (chance1==1 && checkpossible(p2, p1, i, j))
						button[k].setBackground(Color.blue);
				}
			++k;
			}
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		int k=0;
		for (int i=0;i<8;i++)
		{
			for (int j=0;j<8;j++)
			{
				if(e.getSource() == button[k] && board[i][j]=='\0')
					button[k].setBackground(Color.white);
				++k;
			}
		}
	}
	
}
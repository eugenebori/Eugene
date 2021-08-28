import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
public class Day3 extends JFrame{
	
	// constant. ��, ���, ��, â ũ�� ����.
	static int BALL_WIDTH = 20;
	static int BALL_HEIGHT = 20;
	static int BLOCK_ROWS = 5;
	static int BLOCK_COLUMNS = 10;
	static int BLOCK_WIDTH = 40;
	static int BLOCK_HEIGHT = 20;
	static int BLOCK_GAP = 3;
	static int BAR_WIDTH = 40;
	static int BAR_HEIGHT = 20;
	static int CANVAS_WIDTH = 400 + (BLOCK_GAP * BLOCK_COLUMNS) - BLOCK_GAP + 15; //442
	static int CANVAS_HEIGHT = 600;
	
	// variable ���� ����.
	static Day3Panel day3panel = null; 
	Day3Clear day3clear;
	Day3Fail day3fail;
	static Image backgroundImage;
	static Color color1 = new Color(0xFFE4B5); //moccasin
	static Color color2 = new Color(0x556B2F); //darkolivegreen
	static Color color3 = new Color(0xCD853F); //peru
	static Color color4 = new Color(0xF4A460); //sandybrown
	static Color color5 = new Color(0xFF4500); //orangered
	static int score = 0;
	static Timer timer = null;
	static Block[][] blocks = new Block[BLOCK_ROWS][BLOCK_COLUMNS];
	static Bar bar = new Bar();
	static Ball ball = new Ball();
	static int barXTarget = bar.x; //������. ������ �Է¹޾� Ÿ�̸Ӱ� ����Ǹ鼭 �� ĭ�� �����̵��� ��.
	static int dir = 0; //0: Up-Right 1 : Down-Right 2 : Up-Left 3 : Down-Left
	static int ballSpeed = 6;

	
	static class Ball {   //���� ���� Ŭ����.
		int x = CANVAS_WIDTH/2 - BALL_WIDTH/2; //��ǥ ����. ���� ó���� ȭ���� �߽ɿ� ��ġ��.
		int y = CANVAS_HEIGHT/2 - BALL_HEIGHT/2; 
		int width = BALL_WIDTH; //�ʺ�� ���� ����.
		int height = BALL_HEIGHT;
		
		//������ ���� ���� �߽�, ���� �ʺ��� �߽�, ���ʺ��� �߽�, ���� ������ �߽�, ������ ������ �߽ɰ��� �����.
		Point getCenter() { 
			return new Point( x +(BALL_WIDTH/2), y+(BALL_HEIGHT/2));
		}
		Point getBottomCenter() {
			return new Point( x +(BALL_WIDTH/2), y+(BALL_HEIGHT));
		}
		Point getTopCenter() {
			return new Point( x +(BALL_WIDTH/2), y);
		}
		Point getLeftCenter() {
			return new Point( x, y+(BALL_HEIGHT/2));
		}
		Point getRightCenter() {
			return new Point( x +(BALL_WIDTH), y+(BALL_HEIGHT/2));
		}	
	}
	
	static class Bar { //�ٿ� ���� Ŭ����.
		int x =  CANVAS_WIDTH/2 - BAR_WIDTH/2; //��ǥ ����. ���������� �ٵ� ȭ���� �߽ɿ� ��ġ��.
		int y = CANVAS_HEIGHT - 100;
		int width = BAR_WIDTH; //�ʺ�� ���� ����.
		int height = BAR_HEIGHT;
	}
	
	static class Block { //��Ͽ� ���� Ŭ����.
		int x = 0; //��ǥ ����.
		int y = 0;
		int width =  BLOCK_WIDTH; //�ʺ�� ���� ����.
		int height =  BLOCK_HEIGHT;
		int color = 0; //0: moccasin 1: darkolivegreen 2: peru 3: sandybrown 4: orangered ����� �� ����.
		boolean isHidden = false; //�浹 �Ŀ� ����� ������� �� ��.
	}
	
	static class Day3Panel extends JPanel {//�г� Ŭ����.
		public Day3Panel() { 
			this.setSize(CANVAS_WIDTH, CANVAS_HEIGHT); //�г� ũ��� ��� �־���.
			try {  
				backgroundImage = ImageIO.read(new File("day3.png")); //��� �̹��� �ҷ���.
			} catch (IOException e) {
				e.printStackTrace(); //���ܰ� �߻��� �� �ڼ��� ��θ� ����Ʈ�ϵ��� ��.
			}
		}
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			g.drawImage(backgroundImage, 0, 0, 442, 600, null); //��� �̹��� �׸�.
			Graphics2D g2d = (Graphics2D)g; 
			
			drawUI( g2d );
		}
		private void drawUI(Graphics2D g2d) {//��� �׸���.
			for(int i = 0; i<BLOCK_ROWS; i++) {      //���� for������ �� �پ� ����� �׸�.
				for(int j=0; j<BLOCK_COLUMNS; j++) {
					if(blocks[i][j].isHidden) { //���� isHidden�� true�� �ٲ���ٸ� ��� �ȱ׸��� �Ѿ.
						continue; 
					}
					if(blocks[i][j].color==0) { //color = 0�̸� ������ ������ color1���� �� ����.
						g2d.setColor(color1);
					}
					else if(blocks[i][j].color == 1) { //color = 1�̸� ������ ������ color2���� �� ����.
						g2d.setColor(color2);
					}
					else if(blocks[i][j].color == 2) { //color = 2�̸� ������ ������ color3���� �� ����.
						g2d.setColor(color3);
					}
					else if(blocks[i][j].color == 3) { //color = 3�̸� ������ ������ color4���� �� ����.
						g2d.setColor(color4);
					}
					else if(blocks[i][j].color == 4) { //color = 4�̸� ������ ������ color5���� �� ����.
						g2d.setColor(color5);
					}
					g2d.fillRect(blocks[i][j].x, blocks[i][j].y,
							blocks[i][j].width, blocks[i][j].height); //���簢���� �ش� ��ǥ�� ���� ������ ������ ä�� �׸�.
					g2d.setColor(Color.BLACK); //���� �������� �ٲ�.
					g2d.drawRect(blocks[i][j].x, blocks[i][j].y, blocks[i][j].width, blocks[i][j].height); //�׵θ� �׷���.
				}
				
				//draw score
				g2d.setColor(Color.WHITE); //�Ͼ������ ���� �׷���.
				g2d.setFont(new Font("TimesRoman", Font.BOLD, 20));
				g2d.drawString("score : " + score, CANVAS_WIDTH/2 - 30 , 20);
				
				//draw Ball
				g2d.setColor(Color.WHITE); //�Ͼ������ �� �׷���.
				g2d.fillOval(ball.x, ball.y, BALL_WIDTH, BALL_HEIGHT);
				
				//draw BAR
				g2d.setColor(Color.WHITE); //���������� �� �׷���.
				g2d.fillRect(bar.x, bar.y, bar.width, bar.height);
			}
		}	
	} //�гο� �׸�
	
	public Day3() { //������
		this.setVisible(true); //�������� ���̵��� ��.
		this.setSize(CANVAS_WIDTH, CANVAS_HEIGHT); //������ ũ�� ����.
		this.setLocationRelativeTo(null); //ȭ���� ����� ������ ���.
		this.setLayout(new BorderLayout()); //BorderLayout���� ����.
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //x������ â ����.
		
		initData(); //initData �޼ҵ� ����.
		
		day3panel = new Day3Panel(); //�г� ��ü ����.
		this.add("Center", day3panel); //�߽ɿ� ��ġ.
		
		setKeyListener(); 
		startTimer();
	}
	
	public void initData() {
		for(int i = 0; i<BLOCK_ROWS; i++) {
			for(int j=0; j<BLOCK_COLUMNS; j++) { //��Ͽ� ���� ���� for���� ���� ������ �ʱ�ȭ.
				dir = 0; //Up-Right. ���� ó������ ������ ���� ������ ����.
				blocks[i][j] = new Block(); //��� ��ü ����.
				blocks[i][j].x = BLOCK_WIDTH*j + BLOCK_GAP*j; //����� �ʺ�� ��ϰ� ��� ������ ���̸�ŭ x�� �̵����� ����� �׸����� �ϱ� ����.
				blocks[i][j].y = 100 + BLOCK_HEIGHT*i + BLOCK_GAP*i; //�����ӿ��� 100px��ŭ �Ʒ��� �׸����� ��. ����� ���̿� ��ϰ� ��� ������ ���̸�ŭ y�� �̵����� ����� �׸��� ��.
				blocks[i][j].width = BLOCK_WIDTH; //����� �ʺ�� ���̴� ��� ������ ������ ���.
				blocks[i][j].height = BLOCK_HEIGHT;
				blocks[i][j].color = 4 - i; //0:moccasin 1:darkolivegreen 2:peru 3:sandybrown 4:orangered �׷��� ���� 4���� �׷�������.
				blocks[i][j].isHidden = false; //�浹�� �ֱ� ���̹Ƿ� false.
			}
		}
	}
	public void setKeyListener() { //Ű�� ���� �̺�Ʈ ó��.
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {//Ű�� ������ ��
				if(e.getKeyCode() == KeyEvent.VK_LEFT) {//���� ����Ű�� ������ ��,
					barXTarget -= 20; //�������� -20��ŭ Ÿ�̸ӷ� �ε巴�� �̵��ϰ� �� ��.
					if(bar.x < barXTarget) {//Ű�� �ݺ������� ������ ��,
						barXTarget = bar.x; //���� ���� ������ �ʱ�ȭ�Ͽ� �з����� ���� ������.
					}
				}
				else if(e.getKeyCode() == KeyEvent.VK_RIGHT) { //������ ����Ű�� ������ ��,
					barXTarget += 20; //���������� +20��ŭ Ÿ�̸ӷ� �ε巴�� �̵��ϰ� �� ��.
					if(bar.x > barXTarget) {//�����ʵ� ��������.
						barXTarget = bar.x;
					}
				}
			}
		});
	}
	public void startTimer() {
		timer = new Timer(20, new ActionListener() { //Ÿ�̸� ��ü ����. 20ms���� �߻�.
			@Override
			public void actionPerformed(ActionEvent e) {//Ÿ�̸� �̺�Ʈ.
				movement();       //�����ӿ� ���� �޼ҵ�.
				checkCollision(); //���� �ٿ� ���� �ε����� ���� ���� �޼ҵ�.
				checkCollisionBlock(); //���� ��Ͽ� �ε����� ���� ���� �޼ҵ�.
				checkCollisionBar();   //�ٰ� ���� �ε����� ���� ���� �޼ҵ�.
				day3panel.repaint(); //Ÿ�̸Ӱ� �ݺ��� ������ �ٽ� �׸���.
				
				isGameFinish(); //������ ������ ���� ���� �޼ҵ�.
				
			
			}
		});
		timer.start(); //Start Timer!
	}
	
	public void isGameFinish() {
		int count = 0;
		for(int i=0; i<BLOCK_ROWS; i++) {
			for(int j=0; j<BLOCK_COLUMNS; j++) {
				Block block = blocks[i][j];
				if(block.isHidden) //����� ����� ������ count�� 1�� �÷���.
					count++;
			}
		}
		if(count == BLOCK_ROWS * BLOCK_COLUMNS) { //���� count�� ������ ������ ��ġ�Ѵٸ� �� ��� ������ ������ٸ�.
			//Game Finished! 
			timer.stop();  //Ÿ�̸Ӹ� ����.
			checkClear();  //checkClear �޼ҵ� ȣ��. Ŭ���� �г��� ��쵵�� ��.
		}
	
	}

	public void movement() {
		if(bar.x < barXTarget) { //bar.x�� barXTarget���� �۴ٸ�,
			bar.x += 5; //Ÿ�̸Ӱ� ����� ������ +5�� �̵�. �� ���������� �̵�. 
		}else if(bar.x > barXTarget ) { //bar.x�� barXTarget���� ũ�ٸ�,
			bar.x -= 5; //Ÿ�̸Ӱ� ����� ������ -5�� �̵�. �� �������� �̵�.
		}
		
		if(dir == 0) {//0: Up-Right
			ball.x += ballSpeed; //������ ���̹Ƿ� x�� +(������), y�� -(��) 
			ball.y -= ballSpeed;			
		}else if(dir == 1) {//1. Down-Right
			ball.x += ballSpeed; //������ �Ʒ��̹Ƿ� x�� +(������), y�� +(�Ʒ�)
			ball.y += ballSpeed;
		}else if(dir == 2) {//2: Up-Left
			ball.x -= ballSpeed; //���� ���̹Ƿ� x�� -(����), y�� -(��)
			ball.y -= ballSpeed;
		}else if(dir == 3) {//3: Down-Left
			ball.x -= ballSpeed; //���� �Ʒ��̹Ƿ� x�� -(����), y�� +(�Ʒ�)
			ball.y += ballSpeed;	
		}
		
	}
	public boolean duplRect(Rectangle rect1, Rectangle rect2) {
		return rect1.intersects(rect2); //�� ���簢���� ��ġ�� ���� ���� boolean���� ��������.
	}
	public void checkCollision() { //���� �ٿ� ���� �浹���� �� ó���� ���� �޼ҵ�.		
		if(dir == 0) {//0: Up-Right ������ ���� ���� �̵����� ��,
			//Wall
			if(ball.y<0) { //wall upper ���� ���� �ε����ٸ�,
				dir = 1; //���� �� �°� ������ �Ʒ��� ��.
			}
			if(ball.x > CANVAS_WIDTH - BALL_WIDTH - BALL_WIDTH) {//wall right ������ ���� �ε����ٸ�,
				dir = 2; //������ �� �°� ���� ���� ƨ����.
			}


		}else if(dir == 1) {//1. Down-Right ������ �Ʒ�.
			//Wall
			if(ball.y > CANVAS_HEIGHT-BALL_HEIGHT-BALL_HEIGHT) { //wall bottom �Ʒ����� �ε����ٸ�,
				timer.stop(); //Ÿ�̸� ����.
				checkOut(); //���� �ٴ����� ������ ���̹Ƿ� �ش� �޼ҵ� ����.
				ball.x = CANVAS_WIDTH/2 -  BALL_WIDTH/2; //�̿��ڰ� fail�� �ٽ� ������ �簳�� �� ���� ���� ��ġ�� �������� �߽ɿ� �����ֵ��� ����.
				ball.y = CANVAS_HEIGHT/2 - BALL_HEIGHT/2;
				bar.x =  CANVAS_WIDTH/2 - BAR_WIDTH/2;
				bar.y = CANVAS_HEIGHT - 100;
				score = 0;                               //������ �ٽ� 0������ ����.
				barXTarget = bar.x;                      //barXTarget�� Ű�� �Է°��� �����صΰ� �ֱ� ������ bar.x�� �߽ɿ� �ξ ������ ���������� ��ġ�ߴ�
														 //������ �ٰ� �̵��ϴ� ������ ���� barXTarget�� ���� �ʱ�ȭ��.
			}
			if(ball.x > CANVAS_WIDTH-BALL_WIDTH - BALL_WIDTH) {//wall right ������ ���� �ε����ٸ�,
				dir = 3; //���� �Ʒ� �������� ƨ����.
			}
			//Bar
			if(ball.getBottomCenter().y >= bar.y) { //���� ������ �Ʒ��� �̵��Ͽ� �� ���� �سʺ��� �߽�(y��)�� ���� y������ ũ�ų� ���ٸ�,
				if( duplRect(new Rectangle(ball.x, ball.y, ball.width, ball.height), //�� ���簢���� ���Ͽ� ��ġ�� ���� ���� boolean���� �����Ѵ�. 
							 new Rectangle(bar.x, bar.y, bar.width, bar.height)) ) { 
					dir = 0; //���� �ٿ� ������Ƿ� ������ ���� ƨ����.
				}
			}
		}else if(dir == 2) {//2: Up-Left ���� ���� ���� ƨ������ ��,
			//Wall
			if(ball.y < 0) {//wall upper ���� �� ���� �¾Ҵٸ�,
				dir = 3; //���� �Ʒ��� �ٽ� ƨ����.
			}
			if(ball.x < 0) {//wall left ���� ���� ���� �¾Ҵٸ�,
				dir = 0; //������ ���� ���� ƨ����.
			}
			//Bar-none �Ʒ��� �������� �� �ƴϹǷ� �ٿ��� ���� ����.
			
		}else if(dir == 3) {//3: Down-Left ���� �Ʒ�.
			//Wall
			if(ball.y > CANVAS_HEIGHT-BALL_HEIGHT-BALL_HEIGHT) {//wall bottom �Ʒ����� ���� �¾Ҵٸ� Ż���̹Ƿ�,
				timer.stop(); //Ÿ�̸Ӹ� ������Ŵ.
				checkOut();   //���� �ٴ����� ������ ���̹Ƿ� �ش� �޼ҵ� ����.
				ball.x = CANVAS_WIDTH/2 -  BALL_WIDTH/2;  //�̿��ڰ� fail�� �ٽ� ������ �簳�� �� ���� ���� ��ġ�� �������� �߽ɿ� �����ֵ��� ����.
				ball.y = CANVAS_HEIGHT/2 - BALL_HEIGHT/2;
				bar.x =  CANVAS_WIDTH/2 - BAR_WIDTH/2;
				bar.y = CANVAS_HEIGHT - 100;
				score = 0;                                //������ �ٽ� 0������ ����.
				barXTarget = bar.x;                       //barXTarget�� Ű�� �Է°��� �����صΰ� �ֱ� ������ bar.x�� �߽ɿ� �ξ ������ ���������� ��ġ�ߴ�
				 										  //������ �ٰ� �̵��ϴ� ������ ���� barXTarget�� ���� �ʱ�ȭ��.
			}
			if(ball.x < 0) {//wall left ���� ���� �´´ٸ�, 
				dir = 1; //������ �Ʒ��� ƨ����.
			}
			//Bar
			if(ball.getBottomCenter().y >= bar.y) { //�Ʒ��� ���Ƿ� �ٿ� ���� ���� ����. ���� �سʺ� �߽�(y��)�� ���� y�� �̻��̸� 
				if( duplRect(new Rectangle(ball.x, ball.y, ball.width, ball.height), //�� ���簢���� ��ġ�� �� ��.
							 new Rectangle(bar.x, bar.y, bar.width, bar.height)) ) {
					dir = 2; //��ģ�ٸ� ���� �ٿ� ���� ���̹Ƿ�, ���� ���� �ٽ� ƨ����.
				}
			}
		}
	}
	public void checkCollisionBlock() { //���� ��ϰ� �ε����� �� ó���� ���� �޼ҵ�.
		//0: Up-Right 1 : Down-Right 2 : Up-Left 3 : Down-Left
		for(int i=0; i<BLOCK_ROWS; i++) {
			for(int j=0; j<BLOCK_COLUMNS; j++) {
				Block block = blocks[i][j];
				if(block.isHidden == false) { //����� ���� ������� �ʾ��� ��,
					if(dir == 0) {//0: Up-Right ������ ���� ���� ƨ��ٸ�
						if( duplRect(new Rectangle(ball.x, ball.y, ball.width, ball.height),
								 new Rectangle(block.x, block.y, block.width, block.height)) ) { //��ϰ� ���� ��ġ�� �� ��.
							if( ball.x > block.x +2 &&  
								ball.getRightCenter().x <= block.x + block.width -2) {
								//block bottom collision ����� �� �κп� ���� �ε����ٸ�,
								dir = 1; //������ �Ʒ��� ���� ƨ��.
							}else { //����� ���ʿ� �ε����ٸ� 
								//block left collision 
								dir = 2; //���� ���� ƨ����.
							}
							block.isHidden = true; //����� ���� �¾����Ƿ� ����� ������� ��. true�� �ٲ���.
							if(block.color == 0) { //�׸��� ����� ���� ���� ���� �ο�. 
								score += 10;
							}else if(block.color == 1) {
								score += 20;
							}else if(block.color == 2) {
								score += 30;
							}else if(block.color == 3) {
								score += 40;
							}else if(block.color == 4) {
								score += 50;
							}
						}
					}
					else if(dir ==1) {//1: Down-Right ������ �Ʒ�
						if( duplRect(new Rectangle(ball.x, ball.y, ball.width, ball.height),
								 	new Rectangle(block.x, block.y, block.width, block.height)) ) { //��ϰ� ���� ��ġ�� �� ��.
							if( ball.x > block.x +2 &&       
								ball.getRightCenter().x <= block.x + block.width -2) {
								//block top collision ����� �� �κп� ���� �ε����ٸ�,
								dir = 0; //������ ���� ƨ����.
							}else { //����� ���� �κп� ���� �ε����ٸ�,
								//block left collision
								dir = 3; //���� �Ʒ��� ���� ƨ����.
							}
							block.isHidden = true;  //����� ���� �¾����Ƿ� ����� ������� ��. true�� �ٲ���.
							if(block.color == 0) {  //�׸��� ����� ���� ���� ���� �ο�.
								score += 10;
							}else if(block.color == 1) {
								score += 20;
							}else if(block.color == 2) {
								score += 30;
							}else if(block.color == 3) {
								score += 40;
							}else if(block.color == 4) {
								score += 50;
							}
						}
					}	
					else if(dir ==2) {//2: Up-Left ���� ��
						if( duplRect(new Rectangle(ball.x, ball.y, ball.width, ball.height),
								 new Rectangle(block.x, block.y, block.width, block.height)) ) { //��ϰ� ���� ��ġ�� �� ��.
							if( ball.x > block.x +2 &&
								ball.getRightCenter().x <= block.x + block.width -2) { 
								//block bottom collision ����� �Ʒ� �κп� ���� �ε����ٸ�, 
								dir = 3; //���� �Ʒ��� ���� ƨ����.
							}else { //����� ������ �κп� ���� �ε����ٸ�,
								//block right collision 
								dir = 0; //������ ���� ���� ƨ����.
							}
							block.isHidden = true;      //����� ���� �¾����Ƿ� ����� ������� ��. true�� �ٲ���.
							if(block.color == 0) {      //�׸��� ����� ���� ���� ���� �ο�.
								score += 10;
							}else if(block.color == 1) {
								score += 20;
							}else if(block.color == 2) {
								score += 30;
							}else if(block.color == 3) {
								score += 40;
							}else if(block.color == 4) {
								score += 50;
							}
						}
					}
					else if(dir == 3) {//3: Down-Left ���� �Ʒ�
						if( duplRect(new Rectangle(ball.x, ball.y, ball.width, ball.height),
								 new Rectangle(block.x, block.y, block.width, block.height)) ) { //��ϰ� ���� ��ġ�� �� ��.
							if( ball.x > block.x +2 &&
								ball.getRightCenter().x <= block.x + block.width -2) {
								//block top collision ����� �� �κп� ���� �ε����ٸ�, 
								dir = 2; //���� ���� ƨ����.
							}else { //����� ������ �κп� ���� �ε����ٸ�,
								//block right collision
								dir = 1; //������ �Ʒ��� ���� ƨ����.
							} 
							block.isHidden = true;      //����� ���� �¾����Ƿ� ����� ������� ��. true�� �ٲ���.
							if(block.color == 0) {      //�׸��� ����� ���� ���� ���� �ο�.
								score += 10;
							}else if(block.color == 1) {
								score += 20;
							}else if(block.color == 2) {
								score += 30;
							}else if(block.color == 3) {
								score += 40;
							}else if(block.color == 4) {
								score += 50;
							}
						}
					}			
				}
			}
		}
	}
	
	public void checkCollisionBar() { //�ٰ� ���� �浹���� ��. �ٰ� �������� ����� ���� �ذ�.
		if(bar.x < 0) { //���� X��ǥ�� 0���� �۴ٸ�, �� �������� ��� �� �������� ���ٸ�,
			bar.x = 0; //X��ǥ�� 0���� ����� �� �̻� �������� ���� ���ϵ��� ��. 
		}
		else if(bar.x > CANVAS_WIDTH-BAR_WIDTH) { //���� X��ǥ�� ������ ���� �Ѿ�ٸ�,
			bar.x = CANVAS_WIDTH-BAR_WIDTH;  //X��ǥ�� ������ ���� �Ѿ�� �ʴ� �������� ����.
		}
	}
	
	public void checkOut() { //�ƿ��Ǿ������� Ȯ����. �� ���� ������ �������� ���� �г��� �ߵ��� �ϴ� �޼ҵ�.
			this.remove(day3panel); //day3panel�� ����.
			day3fail = new Day3Fail(this); //day3fail �г� ��ü ����.
			this.add(day3fail); //day3 �����ӿ� day3fail �г��� �߰���.
			this.repaint(); //�����ӿ� �ٽ� �׸�.
			this.revalidate(); //�г��� ����� ������ϴ� �������� ��ȭ�� ������� ���� ���� �ֱ� ������ ����.
	}
	
	public void checkClear() { //Ŭ�����ߴ����� Ȯ��. count�� ���� ����ŭ �Ǿ��� �� �����ϴ� �޼ҵ�.
		this.remove(day3panel); //day3panel�� �����ӿ��� �����,
		this.setVisible(false); //day3 �������� �Ⱥ��̰� ��.
		new Main(4); //�׸��� Main ��ü ����. �μ��� 4�� ��. 
	}
}
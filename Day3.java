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
	
	// constant. 공, 블록, 바, 창 크기 설정.
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
	
	// variable 변수 선언.
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
	static int barXTarget = bar.x; //보간법. 점들을 입력받아 타이머가 진행되면서 한 칸씩 움직이도록 함.
	static int dir = 0; //0: Up-Right 1 : Down-Right 2 : Up-Left 3 : Down-Left
	static int ballSpeed = 6;

	
	static class Ball {   //공에 대한 클래스.
		int x = CANVAS_WIDTH/2 - BALL_WIDTH/2; //좌표 정의. 공은 처음에 화면의 중심에 배치됨.
		int y = CANVAS_HEIGHT/2 - BALL_HEIGHT/2; 
		int width = BALL_WIDTH; //너비와 높이 정의.
		int height = BALL_HEIGHT;
		
		//편리성을 위해 공의 중심, 밑의 너비의 중심, 윗너비의 중심, 왼쪽 높이의 중심, 오른쪽 높이의 중심값을 적어둠.
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
	
	static class Bar { //바에 대한 클래스.
		int x =  CANVAS_WIDTH/2 - BAR_WIDTH/2; //좌표 정의. 마찬가지로 바도 화면의 중심에 배치됨.
		int y = CANVAS_HEIGHT - 100;
		int width = BAR_WIDTH; //너비와 높이 정의.
		int height = BAR_HEIGHT;
	}
	
	static class Block { //블록에 대한 클래스.
		int x = 0; //좌표 정의.
		int y = 0;
		int width =  BLOCK_WIDTH; //너비와 높이 정의.
		int height =  BLOCK_HEIGHT;
		int color = 0; //0: moccasin 1: darkolivegreen 2: peru 3: sandybrown 4: orangered 블록의 색 설정.
		boolean isHidden = false; //충돌 후에 블록을 사라지게 할 것.
	}
	
	static class Day3Panel extends JPanel {//패널 클래스.
		public Day3Panel() { 
			this.setSize(CANVAS_WIDTH, CANVAS_HEIGHT); //패널 크기로 상수 넣어줌.
			try {  
				backgroundImage = ImageIO.read(new File("day3.png")); //배경 이미지 불러옴.
			} catch (IOException e) {
				e.printStackTrace(); //예외가 발생할 시 자세한 경로를 프린트하도록 함.
			}
		}
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			g.drawImage(backgroundImage, 0, 0, 442, 600, null); //배경 이미지 그림.
			Graphics2D g2d = (Graphics2D)g; 
			
			drawUI( g2d );
		}
		private void drawUI(Graphics2D g2d) {//블록 그리기.
			for(int i = 0; i<BLOCK_ROWS; i++) {      //이중 for문으로 한 줄씩 블록을 그림.
				for(int j=0; j<BLOCK_COLUMNS; j++) {
					if(blocks[i][j].isHidden) { //만약 isHidden이 true로 바뀌었다면 블록 안그리고 넘어감.
						continue; 
					}
					if(blocks[i][j].color==0) { //color = 0이면 위에서 선언한 color1으로 색 설정.
						g2d.setColor(color1);
					}
					else if(blocks[i][j].color == 1) { //color = 1이면 위에서 선언한 color2으로 색 설정.
						g2d.setColor(color2);
					}
					else if(blocks[i][j].color == 2) { //color = 2이면 위에서 선언한 color3으로 색 설정.
						g2d.setColor(color3);
					}
					else if(blocks[i][j].color == 3) { //color = 3이면 위에서 선언한 color4으로 색 설정.
						g2d.setColor(color4);
					}
					else if(blocks[i][j].color == 4) { //color = 4이면 위에서 선언한 color5으로 색 설정.
						g2d.setColor(color5);
					}
					g2d.fillRect(blocks[i][j].x, blocks[i][j].y,
							blocks[i][j].width, blocks[i][j].height); //직사각형을 해당 좌표에 위의 설정된 색으로 채워 그림.
					g2d.setColor(Color.BLACK); //색을 검정으로 바꿈.
					g2d.drawRect(blocks[i][j].x, blocks[i][j].y, blocks[i][j].width, blocks[i][j].height); //테두리 그려줌.
				}
				
				//draw score
				g2d.setColor(Color.WHITE); //하얀색으로 점수 그려줌.
				g2d.setFont(new Font("TimesRoman", Font.BOLD, 20));
				g2d.drawString("score : " + score, CANVAS_WIDTH/2 - 30 , 20);
				
				//draw Ball
				g2d.setColor(Color.WHITE); //하얀색으로 공 그려줌.
				g2d.fillOval(ball.x, ball.y, BALL_WIDTH, BALL_HEIGHT);
				
				//draw BAR
				g2d.setColor(Color.WHITE); //마찬가지로 바 그려줌.
				g2d.fillRect(bar.x, bar.y, bar.width, bar.height);
			}
		}	
	} //패널에 그림
	
	public Day3() { //생성자
		this.setVisible(true); //프레임이 보이도록 함.
		this.setSize(CANVAS_WIDTH, CANVAS_HEIGHT); //프레임 크기 설정.
		this.setLocationRelativeTo(null); //화면의 가운데에 프레임 띄움.
		this.setLayout(new BorderLayout()); //BorderLayout으로 설정.
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //x누르면 창 닫힘.
		
		initData(); //initData 메소드 시행.
		
		day3panel = new Day3Panel(); //패널 객체 생성.
		this.add("Center", day3panel); //중심에 배치.
		
		setKeyListener(); 
		startTimer();
	}
	
	public void initData() {
		for(int i = 0; i<BLOCK_ROWS; i++) {
			for(int j=0; j<BLOCK_COLUMNS; j++) { //블록에 대해 이중 for문을 돌며 데이터 초기화.
				dir = 0; //Up-Right. 공이 처음에는 오른쪽 위로 가도록 설정.
				blocks[i][j] = new Block(); //블록 객체 생성.
				blocks[i][j].x = BLOCK_WIDTH*j + BLOCK_GAP*j; //블록의 너비와 블록과 블록 사이의 차이만큼 x를 이동시켜 블록을 그리도록 하기 위함.
				blocks[i][j].y = 100 + BLOCK_HEIGHT*i + BLOCK_GAP*i; //프레임에서 100px만큼 아래에 그리도록 함. 블록의 높이와 블록과 블록 사이의 차이만큼 y를 이동시켜 블록을 그리게 함.
				blocks[i][j].width = BLOCK_WIDTH; //블록의 너비와 높이는 모두 위에서 선언한 상수.
				blocks[i][j].height = BLOCK_HEIGHT;
				blocks[i][j].color = 4 - i; //0:moccasin 1:darkolivegreen 2:peru 3:sandybrown 4:orangered 그려질 때는 4부터 그려지도록.
				blocks[i][j].isHidden = false; //충돌이 있기 전이므로 false.
			}
		}
	}
	public void setKeyListener() { //키에 대한 이벤트 처리.
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {//키가 눌렸을 때
				if(e.getKeyCode() == KeyEvent.VK_LEFT) {//왼쪽 방향키가 눌렸을 때,
					barXTarget -= 20; //왼쪽으로 -20만큼 타이머로 부드럽게 이동하게 할 것.
					if(bar.x < barXTarget) {//키가 반복적으로 눌렸을 때,
						barXTarget = bar.x; //현재 바의 값으로 초기화하여 밀려가는 현상 없애줌.
					}
				}
				else if(e.getKeyCode() == KeyEvent.VK_RIGHT) { //오른쪽 방향키가 눌렸을 때,
					barXTarget += 20; //오른쪽으로 +20만큼 타이머로 부드럽게 이동하게 할 것.
					if(bar.x > barXTarget) {//오른쪽도 마찬가지.
						barXTarget = bar.x;
					}
				}
			}
		});
	}
	public void startTimer() {
		timer = new Timer(20, new ActionListener() { //타이머 객체 생성. 20ms마다 발생.
			@Override
			public void actionPerformed(ActionEvent e) {//타이머 이벤트.
				movement();       //움직임에 대한 메소드.
				checkCollision(); //공이 바와 벽에 부딪혔을 때에 대한 메소드.
				checkCollisionBlock(); //공이 블록에 부딪혔을 때에 대한 메소드.
				checkCollisionBar();   //바가 벽에 부딪혔을 때에 대한 메소드.
				day3panel.repaint(); //타이머가 반복될 때마다 다시 그리기.
				
				isGameFinish(); //게임이 끝났을 때에 대한 메소드.
				
			
			}
		});
		timer.start(); //Start Timer!
	}
	
	public void isGameFinish() {
		int count = 0;
		for(int i=0; i<BLOCK_ROWS; i++) {
			for(int j=0; j<BLOCK_COLUMNS; j++) {
				Block block = blocks[i][j];
				if(block.isHidden) //블록이 사라질 때마다 count를 1씩 늘려줌.
					count++;
			}
		}
		if(count == BLOCK_ROWS * BLOCK_COLUMNS) { //만약 count가 벽돌의 개수와 일치한다면 즉 모든 벽돌이 사라졌다면.
			//Game Finished! 
			timer.stop();  //타이머를 멈춤.
			checkClear();  //checkClear 메소드 호출. 클리어 패널을 띄우도록 함.
		}
	
	}

	public void movement() {
		if(bar.x < barXTarget) { //bar.x가 barXTarget보다 작다면,
			bar.x += 5; //타이머가 진행될 때마다 +5씩 이동. 즉 오른쪽으로 이동. 
		}else if(bar.x > barXTarget ) { //bar.x가 barXTarget보다 크다면,
			bar.x -= 5; //타이머가 진행될 때마다 -5씩 이동. 즉 왼쪽으로 이동.
		}
		
		if(dir == 0) {//0: Up-Right
			ball.x += ballSpeed; //오른쪽 위이므로 x는 +(오른쪽), y는 -(위) 
			ball.y -= ballSpeed;			
		}else if(dir == 1) {//1. Down-Right
			ball.x += ballSpeed; //오른쪽 아래이므로 x는 +(오른쪽), y는 +(아래)
			ball.y += ballSpeed;
		}else if(dir == 2) {//2: Up-Left
			ball.x -= ballSpeed; //왼쪽 위이므로 x는 -(왼쪽), y는 -(위)
			ball.y -= ballSpeed;
		}else if(dir == 3) {//3: Down-Left
			ball.x -= ballSpeed; //왼쪽 아래이므로 x는 -(왼쪽), y는 +(아래)
			ball.y += ballSpeed;	
		}
		
	}
	public boolean duplRect(Rectangle rect1, Rectangle rect2) {
		return rect1.intersects(rect2); //두 직사각형이 겹치는 지에 대한 boolean값을 리턴해줌.
	}
	public void checkCollision() { //벽과 바에 공이 충돌했을 때 처리를 위한 메소드.		
		if(dir == 0) {//0: Up-Right 오른쪽 위로 공이 이동했을 때,
			//Wall
			if(ball.y<0) { //wall upper 위쪽 벽에 부딪힌다면,
				dir = 1; //위쪽 벽 맞고 오른쪽 아래로 감.
			}
			if(ball.x > CANVAS_WIDTH - BALL_WIDTH - BALL_WIDTH) {//wall right 오른쪽 벽에 부딪힌다면,
				dir = 2; //오른쪽 벽 맞고 왼쪽 위로 튕겨짐.
			}


		}else if(dir == 1) {//1. Down-Right 오른쪽 아래.
			//Wall
			if(ball.y > CANVAS_HEIGHT-BALL_HEIGHT-BALL_HEIGHT) { //wall bottom 아랫벽에 부딪힌다면,
				timer.stop(); //타이머 멈춤.
				checkOut(); //공이 바닥으로 떨어진 것이므로 해당 메소드 실행.
				ball.x = CANVAS_WIDTH/2 -  BALL_WIDTH/2; //이용자가 fail후 다시 게임을 재개할 때 공과 바의 위치가 프레임의 중심에 놓여있도록 설정.
				ball.y = CANVAS_HEIGHT/2 - BALL_HEIGHT/2;
				bar.x =  CANVAS_WIDTH/2 - BAR_WIDTH/2;
				bar.y = CANVAS_HEIGHT - 100;
				score = 0;                               //점수도 다시 0점으로 만듦.
				barXTarget = bar.x;                      //barXTarget이 키의 입력값을 저장해두고 있기 때문에 bar.x를 중심에 두어도 이전에 마지막으로 위치했던
														 //곳으로 바가 이동하는 문제가 생겨 barXTarget의 값도 초기화함.
			}
			if(ball.x > CANVAS_WIDTH-BALL_WIDTH - BALL_WIDTH) {//wall right 오른쪽 벽에 부딪힌다면,
				dir = 3; //왼쪽 아래 방향으로 튕겨짐.
			}
			//Bar
			if(ball.getBottomCenter().y >= bar.y) { //공이 오른쪽 아래로 이동하여 이 공의 밑너비의 중심(y값)이 바의 y값보다 크거나 같다면,
				if( duplRect(new Rectangle(ball.x, ball.y, ball.width, ball.height), //두 직사각형을 비교하여 겹치는 지에 대한 boolean값을 리턴한다. 
							 new Rectangle(bar.x, bar.y, bar.width, bar.height)) ) { 
					dir = 0; //공이 바에 닿았으므로 오른쪽 위로 튕겨짐.
				}
			}
		}else if(dir == 2) {//2: Up-Left 왼쪽 위로 공이 튕겨졌을 때,
			//Wall
			if(ball.y < 0) {//wall upper 공이 윗 벽에 맞았다면,
				dir = 3; //왼쪽 아래로 다시 튕겨짐.
			}
			if(ball.x < 0) {//wall left 왼쪽 벽에 공이 맞았다면,
				dir = 0; //오른쪽 위로 공이 튕겨짐.
			}
			//Bar-none 아래로 내려가는 게 아니므로 바에는 닿지 않음.
			
		}else if(dir == 3) {//3: Down-Left 왼쪽 아래.
			//Wall
			if(ball.y > CANVAS_HEIGHT-BALL_HEIGHT-BALL_HEIGHT) {//wall bottom 아랫벽에 공이 맞았다면 탈락이므로,
				timer.stop(); //타이머를 중지시킴.
				checkOut();   //공이 바닥으로 떨어진 것이므로 해당 메소드 실행.
				ball.x = CANVAS_WIDTH/2 -  BALL_WIDTH/2;  //이용자가 fail후 다시 게임을 재개할 때 공과 바의 위치가 프레임의 중심에 놓여있도록 설정.
				ball.y = CANVAS_HEIGHT/2 - BALL_HEIGHT/2;
				bar.x =  CANVAS_WIDTH/2 - BAR_WIDTH/2;
				bar.y = CANVAS_HEIGHT - 100;
				score = 0;                                //점수도 다시 0점으로 만듦.
				barXTarget = bar.x;                       //barXTarget이 키의 입력값을 저장해두고 있기 때문에 bar.x를 중심에 두어도 이전에 마지막으로 위치했던
				 										  //곳으로 바가 이동하는 문제가 생겨 barXTarget의 값도 초기화함.
			}
			if(ball.x < 0) {//wall left 왼쪽 벽에 맞는다면, 
				dir = 1; //오른쪽 아래로 튕겨짐.
			}
			//Bar
			if(ball.getBottomCenter().y >= bar.y) { //아래로 가므로 바에 맞을 수도 있음. 공의 밑너비 중심(y값)이 바의 y값 이상이면 
				if( duplRect(new Rectangle(ball.x, ball.y, ball.width, ball.height), //두 직사각형이 겹치는 지 비교.
							 new Rectangle(bar.x, bar.y, bar.width, bar.height)) ) {
					dir = 2; //겹친다면 공이 바에 닿은 것이므로, 왼쪽 위로 다시 튕겨짐.
				}
			}
		}
	}
	public void checkCollisionBlock() { //공이 블록과 부딪혔을 때 처리에 대한 메소드.
		//0: Up-Right 1 : Down-Right 2 : Up-Left 3 : Down-Left
		for(int i=0; i<BLOCK_ROWS; i++) {
			for(int j=0; j<BLOCK_COLUMNS; j++) {
				Block block = blocks[i][j];
				if(block.isHidden == false) { //블록이 아직 사라지지 않았을 때,
					if(dir == 0) {//0: Up-Right 오른쪽 위로 공이 튕겼다면
						if( duplRect(new Rectangle(ball.x, ball.y, ball.width, ball.height),
								 new Rectangle(block.x, block.y, block.width, block.height)) ) { //블록과 공이 겹치는 지 비교.
							if( ball.x > block.x +2 &&  
								ball.getRightCenter().x <= block.x + block.width -2) {
								//block bottom collision 블록의 밑 부분에 공이 부딪혔다면,
								dir = 1; //오른쪽 아래로 공이 튕김.
							}else { //블록의 왼쪽에 부딪혔다면 
								//block left collision 
								dir = 2; //왼쪽 위로 튕겨짐.
							}
							block.isHidden = true; //블록이 공에 맞았으므로 블록은 사라져야 함. true로 바꿔줌.
							if(block.color == 0) { //그리고 블록의 색에 따라 점수 부여. 
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
					else if(dir ==1) {//1: Down-Right 오른쪽 아래
						if( duplRect(new Rectangle(ball.x, ball.y, ball.width, ball.height),
								 	new Rectangle(block.x, block.y, block.width, block.height)) ) { //블록과 공이 겹치는 지 비교.
							if( ball.x > block.x +2 &&       
								ball.getRightCenter().x <= block.x + block.width -2) {
								//block top collision 블록의 윗 부분에 공이 부딪혔다면,
								dir = 0; //오른쪽 위로 튕겨짐.
							}else { //블록의 왼쪽 부분에 공이 부딪혔다면,
								//block left collision
								dir = 3; //왼쪽 아래로 공이 튕겨짐.
							}
							block.isHidden = true;  //블록이 공에 맞았으므로 블록은 사라져야 함. true로 바꿔줌.
							if(block.color == 0) {  //그리고 블록의 색에 따라 점수 부여.
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
					else if(dir ==2) {//2: Up-Left 왼쪽 위
						if( duplRect(new Rectangle(ball.x, ball.y, ball.width, ball.height),
								 new Rectangle(block.x, block.y, block.width, block.height)) ) { //블록과 공이 겹치는 지 비교.
							if( ball.x > block.x +2 &&
								ball.getRightCenter().x <= block.x + block.width -2) { 
								//block bottom collision 블록의 아랫 부분에 공이 부딪혔다면, 
								dir = 3; //왼쪽 아래로 공이 튕겨짐.
							}else { //블록의 오른쪽 부분에 공이 부딪혔다면,
								//block right collision 
								dir = 0; //오른쪽 위로 공이 튕겨짐.
							}
							block.isHidden = true;      //블록이 공에 맞았으므로 블록은 사라져야 함. true로 바꿔줌.
							if(block.color == 0) {      //그리고 블록의 색에 따라 점수 부여.
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
					else if(dir == 3) {//3: Down-Left 왼쪽 아래
						if( duplRect(new Rectangle(ball.x, ball.y, ball.width, ball.height),
								 new Rectangle(block.x, block.y, block.width, block.height)) ) { //블록과 공이 겹치는 지 비교.
							if( ball.x > block.x +2 &&
								ball.getRightCenter().x <= block.x + block.width -2) {
								//block top collision 블록의 윗 부분에 공이 부딪혔다면, 
								dir = 2; //왼쪽 위로 튕겨짐.
							}else { //블록의 오른쪽 부분에 공이 부딪혔다면,
								//block right collision
								dir = 1; //오른쪽 아래로 공이 튕겨짐.
							} 
							block.isHidden = true;      //블록이 공에 맞았으므로 블록은 사라져야 함. true로 바꿔줌.
							if(block.color == 0) {      //그리고 블록의 색에 따라 점수 부여.
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
	
	public void checkCollisionBar() { //바가 벽과 충돌했을 때. 바가 프레임을 벗어나는 문제 해결.
		if(bar.x < 0) { //바의 X좌표가 0보다 작다면, 즉 프레임을 벗어나 더 왼쪽으로 갔다면,
			bar.x = 0; //X좌표를 0으로 만들어 더 이상 왼쪽으로 가지 못하도록 함. 
		}
		else if(bar.x > CANVAS_WIDTH-BAR_WIDTH) { //바의 X좌표가 오른쪽 벽을 넘어갔다면,
			bar.x = CANVAS_WIDTH-BAR_WIDTH;  //X좌표를 오른쪽 벽을 넘어가지 않는 지점으로 설정.
		}
	}
	
	public void checkOut() { //아웃되었는지를 확인함. 즉 공이 밑으로 떨어져서 실패 패널이 뜨도록 하는 메소드.
			this.remove(day3panel); //day3panel을 지움.
			day3fail = new Day3Fail(this); //day3fail 패널 객체 생성.
			this.add(day3fail); //day3 프레임에 day3fail 패널을 추가함.
			this.repaint(); //프레임에 다시 그림.
			this.revalidate(); //패널을 지우고 재생성하는 과정에서 변화가 적용되지 않을 수도 있기 때문에 적음.
	}
	
	public void checkClear() { //클리어했는지를 확인. count가 벽돌 수만큼 되었을 때 실행하는 메소드.
		this.remove(day3panel); //day3panel을 프레임에서 지우고,
		this.setVisible(false); //day3 프레임을 안보이게 함.
		new Main(4); //그리고 Main 객체 생성. 인수로 4를 줌. 
	}
}
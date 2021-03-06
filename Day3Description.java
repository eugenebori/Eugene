import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Day3Description extends JPanel implements ActionListener{ //Day3(세번째 스테이지)가 시작되기 전 보여지는 배경 설명. Day2Clear 패널에서 다음으로 버튼을 누르면 나오는 패널.
	Main main;
	Image backgroundImage;
	JButton gamestart;
	
	public Day3Description(Main main) { //생성자에서 Main을 인수로 받음.
		this.main = main; //이 패널의 main은 입력받은 Main임. 나중에 이벤트 발생시 Main의 함수로 이동하기 위함.  
		this.setSize(442, 600); //패널의 크기 설정. 너비 높이 각각 442, 600.
		this.setLayout(null); //레이아웃 설정 안함.
		
		try { 
			backgroundImage = ImageIO.read(new File("day3story.png")); //배경 이미지 불러옴.
		} catch (IOException e) {
			e.printStackTrace(); //예외가 발생할 시 자세한 경로를 프린트하도록 함.
		}
		
		makeButton(); //makeButton 메소드 호출.
	}
	
	public void makeButton() {
		gamestart = new JButton(); //gamestart 버튼 객체 생성. 시작하기 버튼.
		
		//투명버튼 만들기. 이미지에 버튼 모양이 함께 그려져 있기 때문에 그 버튼 이미지 위에 투명버튼을 붙일 것임.
		gamestart.setBounds(137, 491, 164, 51); //외곽선 설정. x,y좌표와 너비, 높이를 적어줌.
		gamestart.setBorderPainted(false); //외곽선 없애줌.
		gamestart.setFocusPainted(false); ///선택(focus)되었을 때 생기는 테두리 사용 안함.
		gamestart.setContentAreaFilled(false); //내용 영역 채우기 안함.
		gamestart.addActionListener(this); //버튼 클릭시 이벤트 처리.
		this.add(gamestart); //패널에 버튼 추가.
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, 442, 600, null); //배경 이미지를 패널에 그림.
	}
	
	public void actionPerformed(ActionEvent e) {
		main.Day3DescriptionToNext(); //버튼 클릭시 Main의 Day3DescriptionToNext 함수로 이동. 
	}

}

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class HowToPlay extends JPanel implements ActionListener{ //게임 방법을 알려줌. maindescription 패널에서 다음 버튼을 누르면 나오는 패널.
	Main main;
	Image backgroundImage;
	JButton back, next;
	
	public HowToPlay(Main main) { //생성자에서 Main을 인수로 받음.
		this.main = main; //이 패널의 main은 입력받은 Main임. 나중에 이벤트 발생시 Main의 함수로 이동하기 위함. 
		this.setSize(442, 600); //패널의 크기 설정. 너비 높이 각각 442, 600.
		this.setLayout(null); //레이아웃 설정 안함.
		
		try {
			backgroundImage = ImageIO.read(new File("howtoplay.png")); //배경 이미지 불러옴.
		} catch (IOException e) {
			e.printStackTrace(); //예외가 발생할 시 자세한 경로를 프린트하도록 함.
		}
		
		makeButton(); //makeButton 메소드 호출.
	}
	
	public void makeButton() {
		back = new JButton(); //back 버튼 객체 생성. 뒤로 가기 버튼.
		next = new JButton(); //next 버튼 객체 생성. 다음 버튼.
		
		//투명버튼 만들기. 이미지에 버튼 모양이 함께 그려져 있기 때문에 그 버튼 이미지 위에 투명버튼을 붙일 것임.
		back.setBounds(25,507,39,51);  //back 버튼의 외곽선 설정. x,y좌표와 너비, 높이를 적어줌.
		back.setBorderPainted(false);  //외곽선 없애줌.
		back.setFocusPainted(false);   //버튼이 클릭되었을시 생기는 테두리 설정 안함.
		back.setContentAreaFilled(false); //버튼 내부도 채우지 않음.
		back.addActionListener(this);     //버튼을 클릭시 이벤트 처리.
		
		next.setBounds(376, 511, 46, 59); //next 버튼의 외곽선 설정. x,y좌표와 너비, 높이를 적어줌.
		next.setBorderPainted(false); //외곽선 없애줌.
		next.setFocusPainted(false); //버튼이 클릭되었을시 생기는 테두리도 설정 안함.		
		next.setContentAreaFilled(false); //버튼 내부도 채우지 않음.
		next.addActionListener(this); //버튼을 클릭시 이벤트 처리.
		
		this.add(back); //패널에 버튼들 추가.
		this.add(next);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, 442, 600, null); //배경 이미지를 패널에 그림.
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == back) { //back 버튼을 클릭했을 시,
			main.HowToPlayToNext(1); //Main의 HowToPlayToNext 함수로 이동. 인수로 1을 넣음.
		} else if (e.getSource() == next) { //next 버튼을 클릭했을 시,
			main.HowToPlayToNext(2); //Main의 HowToPlayToNext 함수로 이동. 인수로 2를 넣음.
		}
	}
}

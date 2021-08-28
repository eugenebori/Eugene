import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Day2Fail extends JPanel implements ActionListener{ //Day2(두번째 스테이지) 실패시 나오는 패널.
	Day2 main;
	Image backgroundImage;
	JButton retry;
	
	public Day2Fail(Day2 main) { //생성자에서 Day2을 인수로 받음.
		this.main = main; //이 패널의 main은 입력받은 Day2임. 나중에 이벤트 발생시 Day2 프레임을 보이지 않도록 설정하기 위함.
		this.setSize(442, 600); //패널의 크기 설정. 너비 높이 각각 442, 600.
		this.setLayout(null); //레이아웃 설정 안함.
		
		try {
			backgroundImage = ImageIO.read(new File("day2fail.png")); //배경 이미지 불러옴.
		} catch (IOException e) {
			e.printStackTrace(); //예외가 발생할 시 자세한 경로를 프린트하도록 함.
		}
		
		makeButton(); //makeButton 메소드 호출.
	}
	
	public void makeButton() {
		retry = new JButton(); //retry 버튼 객체 생성. 다시 시작 버튼.
		
		//투명버튼 만들기. 이미지에 버튼 모양이 함께 그려져 있기 때문에 그 버튼 이미지 위에 투명버튼을 붙일 것임.
		retry.setBounds(143, 309, 163, 59); //외곽선 설정. x,y좌표와 너비, 높이를 적어줌.
		retry.setBorderPainted(false); //외곽선 없애줌.
		retry.setFocusPainted(false); //선택(focus)되었을 때 생기는 테두리 사용 안함.
		retry.setContentAreaFilled(false); //내용 영역 채우기 안함.
		retry.addActionListener(this); //버튼 클릭시 이벤트 처리.
		this.add(retry); //패널에 버튼 추가.

	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, 442, 600, null); //배경 이미지를 패널에 그림.
	}
	
	public void actionPerformed(ActionEvent e) {
		main.setVisible(false); //Day2 프레임을 보이지 않도록 설정.
		new Day2(); //다시 Day2 클래스 객체 생성하여 게임을 재개함.
	}
}	
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MainDescription extends JPanel implements ActionListener{ //게임 배경 설명 패널. 이 패널은 mainpane 패널에서 게임 시작 버튼을 누르면 보여지는 패널임. 
	Main main;
	Image backgroundImage;
	JButton next;
	
	public MainDescription(Main main) { //생성자에서 Main을 인수로 받음.
		this.main = main; //이 패널의 main은 입력받은 Main임. 나중에 이벤트 발생시 Main의 함수로 이동하기 위함. 
		this.setSize(442, 600); //패널의 크기 설정. 너비 높이 각각 442, 600.
		this.setLayout(null); //레이아웃 설정 안함.
		
		try {
			backgroundImage = ImageIO.read(new File("gamedescription.png")); //배경 이미지 불러옴.
		} catch (IOException e) {
			e.printStackTrace(); //예외가 발생할 시 자세한 경로를 프린트하도록 함.
		}
		
		makeButton(); //makeButton 메소드 호출.
	}
	
	public void makeButton() {
		next = new JButton(); //next 버튼 객체 생성.
										  //투명버튼 만들기. 이미지에 버튼 모양이 함께 그려져 있기 때문에 그 버튼 이미지 위에 투명버튼을 붙일 것임.
		next.setBounds(379, 519, 37, 49); //버튼의 외곽선 설정. x,y좌표와 너비, 높이를 각각 적어줌.
		next.setBorderPainted(false);     //외곽선을 없애줌.
		next.setFocusPainted(false);      //버튼이 클릭되었을시 생기는 테두리도 설정 안함.
		next.setContentAreaFilled(false); //버튼 내부도 채우지 않음.
		next.addActionListener(this);     //버튼을 클릭시 이벤트 처리.
		this.add(next);                   //버튼을 패널에 추가.
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, 442, 600, null); //배경 이미지를 패널에 그림.
	}
 
	public void actionPerformed(ActionEvent e) {
		main.MainDescriptionToNext(); //버튼을 클릭시 Main의 MainDescriptionToNext 함수로 이동.
	}
}

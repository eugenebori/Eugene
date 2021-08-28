import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MainPane extends JPanel implements ActionListener{ //mainpane 패널. 이 패널은 게임시작시 맨 처음으로 뜨는 화면임.
	Main main; 
	Image backgroundImage;
	JButton start;
	
	public MainPane(Main main) { //생성자의 인수로 Main을 넣음.
		this.main = main;          //입력받은 main이 이 패널의 main. 나중에 이벤트 발생시 Main의 함수로 이동하기 위함.
		this.setSize(442, 600);    //패널 사이즈 설정. 너비 높이 각각 442, 600px.
		this.setLayout(null);      //레이아웃 설정 안함.
		
		try {
			backgroundImage = ImageIO.read(new File("main.png")); //배경 이미지를 불러옴.
		} catch(IOException e) {
			e.printStackTrace(); //예외가 발생할 시 자세한 경로를 프린트하도록 함.
		}
		
		makeButton(); //makeButton 메소드 호출.
	}
	
	public void makeButton() { 
		start = new JButton(); //start 버튼 객체 생성.
											//투명버튼 만들기. 이미지에 버튼 모양이 함께 그려져 있기 때문에 그 버튼 이미지 위에 투명버튼을 붙일 것임.
		start.setBounds(151, 270, 150, 57); //버튼의 외곽선 설정. x,y좌표와 너비, 높이 입력.
		start.setBorderPainted(false);      //외곽선을 없애줌. 
		start.setFocusPainted(false);		//버튼이 클릭되었을 시 생기는 테두리도 설정 안함.
		start.setContentAreaFilled(false);  //버튼에 색 채우기도 하지 않음.
		start.addActionListener(this);      //버튼이 클릭되었을시 이벤트 처리.
		this.add(start);                    //패널에 추가.
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);    
		g.drawImage(backgroundImage, 0, 0, 442, 600, null); //배경 이미지를 패널에 그림.
	}
	public void actionPerformed(ActionEvent e) {
		main.MainPaneToNext(); //버튼을 클릭하면 main 즉 메인 프레임의 MainPaneToNext함수로 이동.
	}
	

}

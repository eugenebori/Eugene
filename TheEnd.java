import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class TheEnd extends JPanel implements ActionListener{ //theend 패널. 게임의 가장 마지막에 뜨는 패널임.
	Main main;
	Image backgroundImage;
	JButton end;
	
	public TheEnd(Main main) { //생성자의 인수로 Main을 넣음.
		this.main = main;        //입력받은 main이 이 패널의 main. 나중에 이벤트 발생시 Main의 함수로 이동하기 위함.
		this.setSize(442, 600);  //패널 사이즈 설정. 너비 높이 각각 442, 600px.
		this.setLayout(null);    //레이아웃 설정 안함.
		
		try { 
			backgroundImage = ImageIO.read(new File("theend.png")); //배경 이미지를 불러옴.
		} catch (IOException e) {
			e.printStackTrace(); //예외가 발생할 시 자세한 경로를 프린트하도록 함.
		}
		
		makeButton(); //makeButton 메소드 호출.
	}
	
	public void makeButton() {
		end = new JButton(); //end 버튼 객체 생성.
		
		//투명버튼 만들기. 이미지에 버튼 모양이 함께 그려져 있기 때문에 그 버튼 이미지 위에 투명버튼을 붙일 것임.
		end.setBounds(135, 437, 172, 64); //버튼의 외곽선 설정. x,y좌표와 너비, 높이 입력.
		end.setBorderPainted(false); //외곽선 없애줌.
		end.setFocusPainted(false); ///선택(focus)되었을 때 생기는 테두리 사용 안함.
		end.setContentAreaFilled(false); //내용 영역 채우기 안함.
		end.addActionListener(this);     //버튼이 클릭되었을시 이벤트 처리.
		this.add(end);                   //패널에 추가.
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, 442, 600, null); //배경 이미지를 패널에 그림.
	}
	
	public void actionPerformed(ActionEvent e) {
		main.TheEndToNext(); //버튼을 클릭하면 main 즉 메인 프레임의 TheEndToNext 메소드로 이동.
	}

}

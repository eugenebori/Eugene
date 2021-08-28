import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Day1Description extends JPanel implements ActionListener{ //Day1(ù��° ��������)�� ���۵Ǳ� �� �������� ��� ����. howtoplay �гο��� ���� ��ư�� ������ ������ �г�.
	Main main;
	Image backgroundImage;
	JButton gamestart;
	
	public Day1Description(Main main) { //�����ڿ��� Main�� �μ��� ����.
		this.main = main; //�� �г��� main�� �Է¹��� Main��. ���߿� �̺�Ʈ �߻��� Main�� �Լ��� �̵��ϱ� ����.  
		this.setSize(442, 600); //�г��� ũ�� ����. �ʺ� ���� ���� 442, 600.
		this.setLayout(null); //���̾ƿ� ���� ����.
		
		try { 
			backgroundImage = ImageIO.read(new File("day1story.png")); //��� �̹��� �ҷ���.
		} catch (IOException e) {
			e.printStackTrace(); //���ܰ� �߻��� �� �ڼ��� ��θ� ����Ʈ�ϵ��� ��.
		}
		
		makeButton(); //makeButton �޼ҵ� ȣ��.
	}
	
	public void makeButton() {
		gamestart = new JButton(); //gamestart ��ư ��ü ����. �����ϱ� ��ư.
		
		//�����ư �����. �̹����� ��ư ����� �Բ� �׷��� �ֱ� ������ �� ��ư �̹��� ���� �����ư�� ���� ����.
		gamestart.setBounds(142, 495, 168, 59); //�ܰ��� ����. x,y��ǥ�� �ʺ�, ���̸� ������.
		gamestart.setBorderPainted(false); //�ܰ��� ������.
		gamestart.setFocusPainted(false); ///����(focus)�Ǿ��� �� ����� �׵θ� ��� ����.
		gamestart.setContentAreaFilled(false); //���� ���� ä��� ����.
		gamestart.addActionListener(this); //��ư Ŭ���� �̺�Ʈ ó��.
		this.add(gamestart); //�гο� ��ư �߰�.
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, 442, 600, null); //��� �̹����� �гο� �׸�.
	}
	
	public void actionPerformed(ActionEvent e) {
		main.Day1DescriptionToNext(); //��ư Ŭ���� MainFr�� Day1DescriptionToNext �Լ��� �̵�. 
	}

}

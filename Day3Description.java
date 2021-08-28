import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Day3Description extends JPanel implements ActionListener{ //Day3(����° ��������)�� ���۵Ǳ� �� �������� ��� ����. Day2Clear �гο��� �������� ��ư�� ������ ������ �г�.
	Main main;
	Image backgroundImage;
	JButton gamestart;
	
	public Day3Description(Main main) { //�����ڿ��� Main�� �μ��� ����.
		this.main = main; //�� �г��� main�� �Է¹��� Main��. ���߿� �̺�Ʈ �߻��� Main�� �Լ��� �̵��ϱ� ����.  
		this.setSize(442, 600); //�г��� ũ�� ����. �ʺ� ���� ���� 442, 600.
		this.setLayout(null); //���̾ƿ� ���� ����.
		
		try { 
			backgroundImage = ImageIO.read(new File("day3story.png")); //��� �̹��� �ҷ���.
		} catch (IOException e) {
			e.printStackTrace(); //���ܰ� �߻��� �� �ڼ��� ��θ� ����Ʈ�ϵ��� ��.
		}
		
		makeButton(); //makeButton �޼ҵ� ȣ��.
	}
	
	public void makeButton() {
		gamestart = new JButton(); //gamestart ��ư ��ü ����. �����ϱ� ��ư.
		
		//�����ư �����. �̹����� ��ư ����� �Բ� �׷��� �ֱ� ������ �� ��ư �̹��� ���� �����ư�� ���� ����.
		gamestart.setBounds(137, 491, 164, 51); //�ܰ��� ����. x,y��ǥ�� �ʺ�, ���̸� ������.
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
		main.Day3DescriptionToNext(); //��ư Ŭ���� Main�� Day3DescriptionToNext �Լ��� �̵�. 
	}

}

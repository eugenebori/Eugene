import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Day2Fail extends JPanel implements ActionListener{ //Day2(�ι�° ��������) ���н� ������ �г�.
	Day2 main;
	Image backgroundImage;
	JButton retry;
	
	public Day2Fail(Day2 main) { //�����ڿ��� Day2�� �μ��� ����.
		this.main = main; //�� �г��� main�� �Է¹��� Day2��. ���߿� �̺�Ʈ �߻��� Day2 �������� ������ �ʵ��� �����ϱ� ����.
		this.setSize(442, 600); //�г��� ũ�� ����. �ʺ� ���� ���� 442, 600.
		this.setLayout(null); //���̾ƿ� ���� ����.
		
		try {
			backgroundImage = ImageIO.read(new File("day2fail.png")); //��� �̹��� �ҷ���.
		} catch (IOException e) {
			e.printStackTrace(); //���ܰ� �߻��� �� �ڼ��� ��θ� ����Ʈ�ϵ��� ��.
		}
		
		makeButton(); //makeButton �޼ҵ� ȣ��.
	}
	
	public void makeButton() {
		retry = new JButton(); //retry ��ư ��ü ����. �ٽ� ���� ��ư.
		
		//�����ư �����. �̹����� ��ư ����� �Բ� �׷��� �ֱ� ������ �� ��ư �̹��� ���� �����ư�� ���� ����.
		retry.setBounds(143, 309, 163, 59); //�ܰ��� ����. x,y��ǥ�� �ʺ�, ���̸� ������.
		retry.setBorderPainted(false); //�ܰ��� ������.
		retry.setFocusPainted(false); //����(focus)�Ǿ��� �� ����� �׵θ� ��� ����.
		retry.setContentAreaFilled(false); //���� ���� ä��� ����.
		retry.addActionListener(this); //��ư Ŭ���� �̺�Ʈ ó��.
		this.add(retry); //�гο� ��ư �߰�.

	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, 442, 600, null); //��� �̹����� �гο� �׸�.
	}
	
	public void actionPerformed(ActionEvent e) {
		main.setVisible(false); //Day2 �������� ������ �ʵ��� ����.
		new Day2(); //�ٽ� Day2 Ŭ���� ��ü �����Ͽ� ������ �簳��.
	}
}	
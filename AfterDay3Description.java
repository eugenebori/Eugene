import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class AfterDay3Description extends JPanel implements ActionListener{ //afterday3description �г�. day3 ���������� ���� ������ �г�.
	Main main;
	Image backgroundImage;
	JButton next;
	
	public AfterDay3Description(Main main) { //�����ڿ��� Main�� �μ��� ����. 
		this.main = main; //�� �г��� main�� �Է¹��� Main��. ���߿� �̺�Ʈ �߻��� Main�� �Լ��� �̵��ϱ� ����.
		this.setSize(442, 600); //�г��� ũ�� ����. �ʺ� ���� ���� 442, 600.
		this.setLayout(null); //���̾ƿ� ���� ����.
		
		try { 
			backgroundImage = ImageIO.read(new File("afterday3story.png")); //��� �̹��� �ҷ���.
		} catch (IOException e) {
			e.printStackTrace(); //���ܰ� �߻��� �� �ڼ��� ��θ� ����Ʈ�ϵ��� ��.
		}
		
		makeButton(); //makeButton �޼ҵ� ȣ��.
	}
	
	public void makeButton() {
		next = new JButton(); //next ��ư ��ü ����. ���� ��ư.
		
		//�����ư �����. �̹����� ��ư ����� �Բ� �׷��� �ֱ� ������ �� ��ư �̹��� ���� �����ư�� ���� ����.
		next.setBounds(365, 500, 67, 64); //�ܰ��� ����. x,y��ǥ�� �ʺ�, ���̸� ������.
		next.setBorderPainted(false); //�ܰ��� ������.
		next.setFocusPainted(false); ///����(focus)�Ǿ��� �� ����� �׵θ� ��� ����.
		next.setContentAreaFilled(false); //���� ���� ä��� ����.
		next.addActionListener(this); //��ư Ŭ���� �̺�Ʈ ó��.
		this.add(next); //�гο� ��ư �߰�.

	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, 442, 580, null); //��� �̹����� �гο� �׸�.
	}
	
	public void actionPerformed(ActionEvent e) {
		main.AfterDay3DescriptionToNext();  //��ư Ŭ���� AfterDay3DescriptionToNext �޼ҵ�� �̵�.
	}

}

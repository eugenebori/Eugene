import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class HowToPlay extends JPanel implements ActionListener{ //���� ����� �˷���. maindescription �гο��� ���� ��ư�� ������ ������ �г�.
	Main main;
	Image backgroundImage;
	JButton back, next;
	
	public HowToPlay(Main main) { //�����ڿ��� Main�� �μ��� ����.
		this.main = main; //�� �г��� main�� �Է¹��� Main��. ���߿� �̺�Ʈ �߻��� Main�� �Լ��� �̵��ϱ� ����. 
		this.setSize(442, 600); //�г��� ũ�� ����. �ʺ� ���� ���� 442, 600.
		this.setLayout(null); //���̾ƿ� ���� ����.
		
		try {
			backgroundImage = ImageIO.read(new File("howtoplay.png")); //��� �̹��� �ҷ���.
		} catch (IOException e) {
			e.printStackTrace(); //���ܰ� �߻��� �� �ڼ��� ��θ� ����Ʈ�ϵ��� ��.
		}
		
		makeButton(); //makeButton �޼ҵ� ȣ��.
	}
	
	public void makeButton() {
		back = new JButton(); //back ��ư ��ü ����. �ڷ� ���� ��ư.
		next = new JButton(); //next ��ư ��ü ����. ���� ��ư.
		
		//�����ư �����. �̹����� ��ư ����� �Բ� �׷��� �ֱ� ������ �� ��ư �̹��� ���� �����ư�� ���� ����.
		back.setBounds(25,507,39,51);  //back ��ư�� �ܰ��� ����. x,y��ǥ�� �ʺ�, ���̸� ������.
		back.setBorderPainted(false);  //�ܰ��� ������.
		back.setFocusPainted(false);   //��ư�� Ŭ���Ǿ����� ����� �׵θ� ���� ����.
		back.setContentAreaFilled(false); //��ư ���ε� ä���� ����.
		back.addActionListener(this);     //��ư�� Ŭ���� �̺�Ʈ ó��.
		
		next.setBounds(376, 511, 46, 59); //next ��ư�� �ܰ��� ����. x,y��ǥ�� �ʺ�, ���̸� ������.
		next.setBorderPainted(false); //�ܰ��� ������.
		next.setFocusPainted(false); //��ư�� Ŭ���Ǿ����� ����� �׵θ��� ���� ����.		
		next.setContentAreaFilled(false); //��ư ���ε� ä���� ����.
		next.addActionListener(this); //��ư�� Ŭ���� �̺�Ʈ ó��.
		
		this.add(back); //�гο� ��ư�� �߰�.
		this.add(next);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, 442, 600, null); //��� �̹����� �гο� �׸�.
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == back) { //back ��ư�� Ŭ������ ��,
			main.HowToPlayToNext(1); //Main�� HowToPlayToNext �Լ��� �̵�. �μ��� 1�� ����.
		} else if (e.getSource() == next) { //next ��ư�� Ŭ������ ��,
			main.HowToPlayToNext(2); //Main�� HowToPlayToNext �Լ��� �̵�. �μ��� 2�� ����.
		}
	}
}

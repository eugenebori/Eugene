import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Day2Clear extends JPanel implements ActionListener{ //Day2(�ι�° ��������) Ŭ����� ������ �г�. 
	Main main; 
	Image backgroundImage;
	JButton nextstage;
	
	public Day2Clear(Main main) { //�����ڿ��� Main�� �μ��� ����. 
		this.main = main; //�� �г��� main�� �Է¹��� Main��. ���߿� �̺�Ʈ �߻��� Main�� �Լ��� �̵��ϱ� ����.
		this.setSize(442, 600); //�г��� ũ�� ����. �ʺ� ���� ���� 442, 600.
		this.setLayout(null); //���̾ƿ� ���� ����.
		
		try {
			backgroundImage = ImageIO.read(new File("day2clear.png")); //��� �̹��� �ҷ���.
		} catch (IOException e) {
			e.printStackTrace(); //���ܰ� �߻��� �� �ڼ��� ��θ� ����Ʈ�ϵ��� ��.
		}
		
		makeButton(); //makeButton �޼ҵ� ȣ��.
	}
	
	public void makeButton() {
		nextstage = new JButton(); //nextstage ��ư ��ü ����. ���� ��ư.
		
		//�����ư �����. �̹����� ��ư ����� �Բ� �׷��� �ֱ� ������ �� ��ư �̹��� ���� �����ư�� ���� ����.
		nextstage.setBounds(143, 309, 163, 59); //�ܰ��� ����. x,y��ǥ�� �ʺ�, ���̸� ������.
		nextstage.setBorderPainted(false); //�ܰ��� ������.
		nextstage.setFocusPainted(false); //����(focus)�Ǿ��� �� ����� �׵θ� ��� ����.
		nextstage.setContentAreaFilled(false); //���� ���� ä��� ����.
		nextstage.addActionListener(this); //��ư Ŭ���� �̺�Ʈ ó��.
		this.add(nextstage); //�гο� ��ư �߰�.

	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, 442, 600, null); //��� �̹����� �гο� �׸�.
	}
	
	public void actionPerformed(ActionEvent e) {
		main.Day2ClearToNext(); //��ư Ŭ���� Day2ClearToNext �Լ��� �̵�.
	}
}
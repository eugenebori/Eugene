import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MainDescription extends JPanel implements ActionListener{ //���� ��� ���� �г�. �� �г��� mainpane �гο��� ���� ���� ��ư�� ������ �������� �г���. 
	Main main;
	Image backgroundImage;
	JButton next;
	
	public MainDescription(Main main) { //�����ڿ��� Main�� �μ��� ����.
		this.main = main; //�� �г��� main�� �Է¹��� Main��. ���߿� �̺�Ʈ �߻��� Main�� �Լ��� �̵��ϱ� ����. 
		this.setSize(442, 600); //�г��� ũ�� ����. �ʺ� ���� ���� 442, 600.
		this.setLayout(null); //���̾ƿ� ���� ����.
		
		try {
			backgroundImage = ImageIO.read(new File("gamedescription.png")); //��� �̹��� �ҷ���.
		} catch (IOException e) {
			e.printStackTrace(); //���ܰ� �߻��� �� �ڼ��� ��θ� ����Ʈ�ϵ��� ��.
		}
		
		makeButton(); //makeButton �޼ҵ� ȣ��.
	}
	
	public void makeButton() {
		next = new JButton(); //next ��ư ��ü ����.
										  //�����ư �����. �̹����� ��ư ����� �Բ� �׷��� �ֱ� ������ �� ��ư �̹��� ���� �����ư�� ���� ����.
		next.setBounds(379, 519, 37, 49); //��ư�� �ܰ��� ����. x,y��ǥ�� �ʺ�, ���̸� ���� ������.
		next.setBorderPainted(false);     //�ܰ����� ������.
		next.setFocusPainted(false);      //��ư�� Ŭ���Ǿ����� ����� �׵θ��� ���� ����.
		next.setContentAreaFilled(false); //��ư ���ε� ä���� ����.
		next.addActionListener(this);     //��ư�� Ŭ���� �̺�Ʈ ó��.
		this.add(next);                   //��ư�� �гο� �߰�.
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, 442, 600, null); //��� �̹����� �гο� �׸�.
	}
 
	public void actionPerformed(ActionEvent e) {
		main.MainDescriptionToNext(); //��ư�� Ŭ���� Main�� MainDescriptionToNext �Լ��� �̵�.
	}
}

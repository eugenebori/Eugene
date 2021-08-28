import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MainPane extends JPanel implements ActionListener{ //mainpane �г�. �� �г��� ���ӽ��۽� �� ó������ �ߴ� ȭ����.
	Main main; 
	Image backgroundImage;
	JButton start;
	
	public MainPane(Main main) { //�������� �μ��� Main�� ����.
		this.main = main;          //�Է¹��� main�� �� �г��� main. ���߿� �̺�Ʈ �߻��� Main�� �Լ��� �̵��ϱ� ����.
		this.setSize(442, 600);    //�г� ������ ����. �ʺ� ���� ���� 442, 600px.
		this.setLayout(null);      //���̾ƿ� ���� ����.
		
		try {
			backgroundImage = ImageIO.read(new File("main.png")); //��� �̹����� �ҷ���.
		} catch(IOException e) {
			e.printStackTrace(); //���ܰ� �߻��� �� �ڼ��� ��θ� ����Ʈ�ϵ��� ��.
		}
		
		makeButton(); //makeButton �޼ҵ� ȣ��.
	}
	
	public void makeButton() { 
		start = new JButton(); //start ��ư ��ü ����.
											//�����ư �����. �̹����� ��ư ����� �Բ� �׷��� �ֱ� ������ �� ��ư �̹��� ���� �����ư�� ���� ����.
		start.setBounds(151, 270, 150, 57); //��ư�� �ܰ��� ����. x,y��ǥ�� �ʺ�, ���� �Է�.
		start.setBorderPainted(false);      //�ܰ����� ������. 
		start.setFocusPainted(false);		//��ư�� Ŭ���Ǿ��� �� ����� �׵θ��� ���� ����.
		start.setContentAreaFilled(false);  //��ư�� �� ä��⵵ ���� ����.
		start.addActionListener(this);      //��ư�� Ŭ���Ǿ����� �̺�Ʈ ó��.
		this.add(start);                    //�гο� �߰�.
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);    
		g.drawImage(backgroundImage, 0, 0, 442, 600, null); //��� �̹����� �гο� �׸�.
	}
	public void actionPerformed(ActionEvent e) {
		main.MainPaneToNext(); //��ư�� Ŭ���ϸ� main �� ���� �������� MainPaneToNext�Լ��� �̵�.
	}
	

}

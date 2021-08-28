import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class TheEnd extends JPanel implements ActionListener{ //theend �г�. ������ ���� �������� �ߴ� �г���.
	Main main;
	Image backgroundImage;
	JButton end;
	
	public TheEnd(Main main) { //�������� �μ��� Main�� ����.
		this.main = main;        //�Է¹��� main�� �� �г��� main. ���߿� �̺�Ʈ �߻��� Main�� �Լ��� �̵��ϱ� ����.
		this.setSize(442, 600);  //�г� ������ ����. �ʺ� ���� ���� 442, 600px.
		this.setLayout(null);    //���̾ƿ� ���� ����.
		
		try { 
			backgroundImage = ImageIO.read(new File("theend.png")); //��� �̹����� �ҷ���.
		} catch (IOException e) {
			e.printStackTrace(); //���ܰ� �߻��� �� �ڼ��� ��θ� ����Ʈ�ϵ��� ��.
		}
		
		makeButton(); //makeButton �޼ҵ� ȣ��.
	}
	
	public void makeButton() {
		end = new JButton(); //end ��ư ��ü ����.
		
		//�����ư �����. �̹����� ��ư ����� �Բ� �׷��� �ֱ� ������ �� ��ư �̹��� ���� �����ư�� ���� ����.
		end.setBounds(135, 437, 172, 64); //��ư�� �ܰ��� ����. x,y��ǥ�� �ʺ�, ���� �Է�.
		end.setBorderPainted(false); //�ܰ��� ������.
		end.setFocusPainted(false); ///����(focus)�Ǿ��� �� ����� �׵θ� ��� ����.
		end.setContentAreaFilled(false); //���� ���� ä��� ����.
		end.addActionListener(this);     //��ư�� Ŭ���Ǿ����� �̺�Ʈ ó��.
		this.add(end);                   //�гο� �߰�.
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, 442, 600, null); //��� �̹����� �гο� �׸�.
	}
	
	public void actionPerformed(ActionEvent e) {
		main.TheEndToNext(); //��ư�� Ŭ���ϸ� main �� ���� �������� TheEndToNext �޼ҵ�� �̵�.
	}

}

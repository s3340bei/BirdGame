package game;

import javax.imageio.ImageIO;
import java.util.*;

import javax.swing.*;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.awt.*;

public class BirdGame extends JPanel {

	Image background;
	Image startImage;
	Image overImage;
	public boolean enlagreStatus = false;
	public boolean enlagreColumn = false;
	Ground ground;
	Column column1, column2;
	Bird bird;
	int score;
	int state;

	public static final int START = 0;
	public static final int RUNNING = 1;
	public static final int GAME_OVER = 2;
	boolean isColumn1Active, isColumn2Active; // 追蹤柱子是否仍然活動

	Slow slow;
	X2 x2;
	int rs;
	Random random = new Random();
	Image sslowImage;
	Image sx2Image;
	Image iiImage;
	large lg;

	public BirdGame() throws Exception {
		background = new ImageIcon("./source/bg.png").getImage();
		startImage = new ImageIcon("./source/start.png").getImage();
		overImage = new ImageIcon("./source/gameover.png").getImage();
		sslowImage = new ImageIcon("./source/slow_small.png").getImage();
		sx2Image = new ImageIcon("./source/x2_small.png").getImage();
		iiImage = new ImageIcon("./source/light.png").getImage();
	
		ground = new Ground();
		column1 = new Column(1);
		column2 = new Column(2);
		isColumn1Active = true; // 初始狀態為活動中
		isColumn2Active = true; // 初始狀態為活動中
		enlagreStatus=false;

		bird = new Bird();
		slow = new Slow();
		x2 = new X2();
		lg=new large();
		score = 0;
		state = 0;
	}

	public void paint(Graphics g) {
	
		g.drawImage(background, 0, 0, null);
		
		g.drawImage(ground.image, ground.x, ground.y, null);

		if (isColumn1Active) {
			g.drawImage(column1.image, column1.x - column1.width / 2, column1.y - column1.height / 2, null);
		}
		if (isColumn2Active) {
			g.drawImage(column2.image, column2.x - column2.width / 2, column2.y - column2.height / 2, null);
		}

		Graphics2D g2 = (Graphics2D) g;
		g2.rotate(-bird.alpha, bird.x, bird.y);
		if (enlagreStatus)
			g2.drawImage(bird.image, bird.x - bird.width / 2, bird.y - bird.height / 2, bird.width * 2, bird.height * 2,
					null);
		else
			g.drawImage(bird.image, bird.x - bird.width / 2, bird.y - bird.height / 2, null);
		g2.rotate(bird.alpha, bird.x, bird.y);

		Font f = new Font(Font.SANS_SERIF, Font.BOLD, 40);
		g.setFont(f);
		g.drawString("" + score, 40, 60);
		g.setColor(Color.WHITE);
		g.drawString("" + score, 40 - 3, 60 - 3);

		switch (state) {
			case START:
				g.drawImage(startImage, 0, 0, null);
				break;
			case GAME_OVER:
				g.drawImage(overImage, 0, 0, null);
				break;
		}
		// 繪製慢速圖示
		if (slow.slowIconActive) {
			g.drawImage(slow.image, slow.x, slow.y, null);
		}  else if (slow.ms == 1200) {
			g.drawImage(sslowImage, bird.x - 30, bird.y - bird.height / 2 + 10, null);
		}
		//繪製放大燈
		if (lg.x2IconActive) {
			g.drawImage(lg.image, lg.x, lg.y, null);
			//enlagreStatus = true;
		}


		// 繪製分數加倍圖示
		if (x2.x2IconActive) {
			g.drawImage(x2.image, x2.x, x2.y, null);
		} else if (x2.score_variable == 1000) {
			g.drawImage(sx2Image, bird.x - 30, bird.y - bird.height / 2 +10 , null);
		}

		if (!isColumn1Active)
			isColumn1Active = true;
		if (!isColumn2Active)
			isColumn2Active = true;
	}

	public void action() throws Exception {
		
		MouseListener l = new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (bird.hitEnlarge(column1) && enlagreStatus) {
					isColumn1Active = false; // 柱子1消失
				}
				if (bird.hitEnlarge(column2) && enlagreStatus) {
					isColumn2Active = false; // 柱子2消失
				}
				try {
					switch (state) {
						case START:
		
							state = RUNNING;
							break;
						case RUNNING:
				
							bird.flappy();
							break;
						case GAME_OVER:
							
							column1 = new Column(1);
							column2 = new Column(2);
							bird = new Bird();
							slow = new Slow();
							x2 = new X2();
							lg=new large();
							score = 0;
							state = START;
							break;
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

		};
		addMouseListener(l);
		while (true) {
			switch (state) {
				case START:
					bird.fly();
					ground.step();
					break;
				case RUNNING:
					ground.step();
					column1.step();
					column2.step();
					bird.fly();
					bird.step();

					if (!slow.slowActive && !slow.slowIconActive)
						slow = new Slow();
					if (bird.hit(slow)) {
						slow.tmpScore = score;
					}
					if (slow.slowActive && slow.tmpScore + 140 >= score)
						slow.ms = 1200;
					else {
						slow.slowIconActive = true;
						slow.ms = 1000;
					}
					if (slow.slowIconActive)
						slow.step();

					if (!x2.x2Active && !x2.x2IconActive)
						x2 = new X2();
					if (bird.hit(x2)) {
						x2.tmpScore = score;
					}
					if (x2.x2Active && x2.tmpScore + 1500 >= score)
						x2.score_variable = 10;
					else {
						x2.x2IconActive = true;
						x2.score_variable = 1;
					}
					if (x2.x2IconActive)
						x2.step();
					// if(bird.x==column1.x||bird.x==column2.x)
					// {
					// score++;
					// }
					score = score + x2.score_variable;
						
					if (!enlagreStatus) {
						if (bird.hit(ground) || bird.hit(column1) || bird.hit(column2)) {
							state = GAME_OVER;
						}
					}
					
					if (!lg.x2Active && !lg.x2IconActive)
						lg = new large();
					if (bird.hit(lg)) {
						lg.tmpScore = score;
					}
					if (lg.x2Active && lg.tmpScore + 900 >= score)
						{
							enlagreStatus=true;
						lg.x2Active=true;
						}
					else {
						lg.x2Active = false;					
						enlagreStatus=false;
					}
					if (lg.x2IconActive)
						lg.step();
					
				
					if(bird.hit(ground)) {state=GAME_OVER;}
					break;
			}
			Thread.sleep(slow.ms / 60);
			repaint();
		}
	}


	public static void main(String[] args) throws Exception {

		JFrame frame = new JFrame();
		BirdGame game = new BirdGame();
		frame.add(game);
		frame.setSize(440, 670);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		game.action();
	}

}

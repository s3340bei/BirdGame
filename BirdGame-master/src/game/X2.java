package game;

import java.util.*;
import java.awt.*;
import javax.swing.*;
public class X2 {
    Image image;
	int	width;	int	height;
    int x = 600;    int y;
    int gap = 20;    int distance = 435;
    boolean x2IconActive = true;  
    boolean x2Active = false;
	int score_variable = 1;// 控制兩倍分數bb
    int rs;int tmpScore;
    Random random = new Random();
    public X2() throws Exception 
    {
        image = new ImageIcon("source/x2.png").getImage();
        width=image.getWidth(null);height=image.getHeight(null);
        y = 90+random.nextInt(218);
        x2IconActive = true;  x2Active = false;
    }
    public void step()
    {
        x-=4;
		if(x<= -width)
		{
            x=distance*2-width/2;
			x2IconActive = false;
		}
    }
}

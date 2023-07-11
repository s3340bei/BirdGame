package game;

import java.util.*;
import java.awt.*;
import javax.swing.*;
public class Slow {
    Image image;
	int	width;	int	height;
    int x = 720;    int y;int tmpScore=0;
    int gap = 20;int distance=500;
    boolean slowIconActive = true; 
    boolean slowActive = false;

    int ms=1000;

    Random random = new Random();
    public Slow() throws Exception 
    {        
        image = new ImageIcon("source/slow.png").getImage();
            width=image.getWidth(null);height=image.getHeight(null);
            y = 90+random.nextInt(218);slowIconActive = true; slowActive = false;
    }
    public void step()
    {        
        x-=4;
		if(x<= -width)
		{
            x=distance*2-width/2;
			slowIconActive = false;
		}
    }
}

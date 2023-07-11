package game;

import java.util.*;
import java.awt.*;
import javax.swing.*;
public class large {
    Image image;
	int	width;	int	height;
    int x = 500;    int y;
    int gap = 20;    int distance = 435;
    boolean x2IconActive = true;  
    boolean x2Active = false;
    int rs;int tmpScore;
    Random random = new Random();
    public large() throws Exception 
    {
        image = new ImageIcon("source/light.png").getImage();
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

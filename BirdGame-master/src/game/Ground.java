package game;

import javax.swing.*;
import java.awt.*;

public class Ground {

	Image image;
	
	int x,y;
	
	int width,height;
	

	public Ground() throws Exception
	{
		image =new ImageIcon("src/ground.png").getImage();
		width=image.getWidth(null);
		height=image.getHeight(null);
		x=0;
		y=500;
	}
	

	public void step()
	{
		x-=4;
		if(x<=-109)
		{
			x=0;
		}
	}
	
	
}

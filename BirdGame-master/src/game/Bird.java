package game;
import java.awt.*;
import javax.swing.*;


public class Bird {

	Image image;
	int x,y;
	int width,height;
	int size;
	double g;
	double t;
	double v0;
	double speed;
	double s;
	double alpha;
	Image[] images;
	int index;
	
	public Bird() throws Exception
	{
		image=new ImageIcon("source/0.png").getImage();
		width = image.getWidth(null);
		height = image.getHeight(null);
		x=132;
		y=280;
		size=40;
		g=4;
		v0=20;
		t=0.25;
		speed=v0;
		s=0;		
		alpha=0;	
		images=new Image[8];
		
		for(int i=0;i<8;i++)
		{
			images[i]=new ImageIcon("source/"+i+".png").getImage();
		}
		index=0;
	}
	

	public void fly()
	{
		index++;
		image=images[(index/12)%8];
	}
	
	
	public void step()
	{
		double v0=speed; 
	
		s=v0*t+g*t*t/2;
	
		y=y-(int)s;
		
		double v=v0-g*t;
		speed =v;
		
		alpha=Math.atan(s/8);
	}
	

	public void flappy()
	{
			speed=v0;
	}
	

	public boolean hit(Ground ground)
	{
		boolean hit =y+size/2>ground.y;
		if(hit)
		{
			y=ground.y-size/2;
			alpha=Math.PI/2;
		}
		return hit;
	}
	

	public boolean hit(Column column)
	{

		if(x>column.x-column.width/2-size/2&&x<column.x+column.width/2+size/2)
		{
			if(y>column.y-column.gap/2+size/2&&y<column.y+column.gap/2-size/2) return false;
			return true;
		}
		return false;
	}
	public boolean hit(large x2) {	
		int birdTop = y - size / 2;
		int birdBottom = y + size / 2;
		int x2Top = x2.y - x2.gap / 2;
		int x2Bottom = x2.y + x2.gap / 2;
	
		if (x > x2.x - x2.width / 2 - size / 7 && x < x2.x + x2.width / 2 + size / 7) {
			if (birdBottom > x2Top && birdTop < x2Bottom) {
				if (x2.x2Active == false && x2.x2IconActive == true) {
					x2.x2Active = true;
				}
				if (x2.x2IconActive == true) {
					x2.x2IconActive = false;}
				return true;
			}
		}

		// 左下角碰撞檢測
		if (x > x2.x - x2.width / 2 - size / 7 && x < x2.x - x2.width / 2 + size / 7) {
			if (birdBottom > x2Bottom && birdTop < x2.y) {
				if (x2.x2Active == false && x2.x2IconActive == true) {
					x2.x2Active = true;
				}
				if (x2.x2IconActive == true) {
					x2.x2IconActive = false;
				}
				return true;
			}
		}
		
		// 左上角碰撞檢測
		if (x > x2.x - x2.width / 2 - size / 7 && x < x2.x - x2.width / 2 + size / 7) {
			if (birdTop < x2Top && birdBottom > x2.y) {
				if (x2.x2Active == false && x2.x2IconActive == true) {
					x2.x2Active = true;
				}
				if (x2.x2IconActive == true) {
					x2.x2IconActive = false;
				}
				return true;
			}
		}
		return false;
	}
	public boolean hitEnlarge(Column column)
	{
		if (x > column.x - column.width / 2 - height && x < column.x + column.width / 2 + height) {
            if (y > column.y - column.gap / 2 +height && y < column.y + column.gap / 2 - height)
                return false;
            return true;
		}
		return false;
	}
	public boolean hit(Slow slow) {
		int birdTop = y - size / 2;
		int birdBottom = y + size / 2;
		int slowTop = slow.y - slow.gap / 2;
		int slowBottom = slow.y + slow.gap / 2;
	
		if (x > slow.x - slow.width / 2 - size / 7 && x < slow.x + slow.width / 2 + size / 7) {
			if (birdBottom > slowTop && birdTop < slowBottom) {
				if (slow.slowActive == false && slow.slowIconActive == true) {
					slow.slowActive = true;
				}
				if (slow.slowIconActive == true) {
					slow.slowIconActive = false;}
				return true;
			}
		}
		// 左下角碰撞檢測
		if (x > slow.x - slow.width / 2 - size / 7 && x < slow.x - slow.width / 2 + size / 7) {
			if (birdBottom > slowBottom && birdTop < slow.y) {
				if (slow.slowActive == false && slow.slowIconActive == true) {
					slow.slowActive = true;
				}
				if (slow.slowIconActive == true) {
					slow.slowIconActive = false;
				}
				return true;
			}
		}
		
		// 左上角碰撞檢測
		if (x > slow.x - slow.width / 2 - size / 7 && x < slow.x - slow.width / 2 + size / 7) {
			if (birdTop < slowTop && birdBottom > slow.y) {
				if (slow.slowActive == false && slow.slowIconActive == true) {
					slow.slowActive = true;
				}
				if (slow.slowIconActive == true) {
					slow.slowIconActive = false;
				}
				return true;
			}
		}
		return false;
	}
	
	public boolean hit(X2 x2) {	
		int birdTop = y - size / 2;
		int birdBottom = y + size / 2;
		int x2Top = x2.y - x2.gap / 2;
		int x2Bottom = x2.y + x2.gap / 2;
	
		if (x > x2.x - x2.width / 2 - size / 7 && x < x2.x + x2.width / 2 + size / 7) {
			if (birdBottom > x2Top && birdTop < x2Bottom) {
				if (x2.x2Active == false && x2.x2IconActive == true) {
					x2.x2Active = true;
				}
				if (x2.x2IconActive == true) {
					x2.x2IconActive = false;}
				return true;
			}
		}

		// 左下角碰撞檢測
		if (x > x2.x - x2.width / 2 - size / 7 && x < x2.x - x2.width / 2 + size / 7) {
			if (birdBottom > x2Bottom && birdTop < x2.y) {
				if (x2.x2Active == false && x2.x2IconActive == true) {
					x2.x2Active = true;
				}
				if (x2.x2IconActive == true) {
					x2.x2IconActive = false;
				}
				return true;
			}
		}
		
		// 左上角碰撞檢測
		if (x > x2.x - x2.width / 2 - size / 7 && x < x2.x - x2.width / 2 + size / 7) {
			if (birdTop < x2Top && birdBottom > x2.y) {
				if (x2.x2Active == false && x2.x2IconActive == true) {
					x2.x2Active = true;
				}
				if (x2.x2IconActive == true) {
					x2.x2IconActive = false;
				}
				return true;
			}
		}
		return false;
	}
}

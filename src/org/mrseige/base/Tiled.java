package org.mrseige.base;

import org.mrseige.common.Pos;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.MotionEvent;

public abstract class Tiled {

	protected int x;
	protected int y;
	protected int width;
	protected int height;
	
	public Tiled() {
		
	}
	
	public Tiled(int x,int y,int width,int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public abstract boolean touch(MotionEvent ev);
	public abstract void draw(Canvas canvas);
	
	public boolean isContains(float curTouchX,float curTouchY){
		RectF rect = new RectF(this.x,this.y,width,height);
		return rect.contains(curTouchX,curTouchY);
	}
	
	public boolean isConlision(Pos pos, int r1,int r2) {
		int x=(int) pos.x;
		int y=(int) pos.y;
		int xx=this.getX()+this.getW()/2;
		int yy=this.getY()+this.getH()/2;
		if(r1+r2>Math.sqrt(((xx-x)*(xx-x)+(yy-y)*(yy-y))))
			return true;
		else
			return false;
	}
	
	public boolean isCollision(int monster_y) {
		return monster_y - this.getY() > 0 ? true : false;
	}
	
	/**  
     * 矩形碰撞检测 参数为x,y,width,height  
     *   
     * @param x1  
     *            第一个矩形的x  
     * @param y1  
     *            第一个矩形的y  
     * @param w1  
     *            第一个矩形的w  
     * @param h1  
     *            第一个矩形的h  
     * @param x2  
     *            第二个矩形的x  
     * @param y2  
     *            第二个矩形的y  
     * @param w2  
     *            第二个矩形的w  
     * @param h2  
     *            第二个矩形的h  
     * @return 是否碰撞  
     */  
    public boolean IsRectCollision(int x1, int y1, int w1, int h1, int x2,   
            int y2, int w2, int h2) {   
        if (x2 > x1 && x2 > x1 + w1) {   
            return false;   
        } else if (x2 < x1 && x2 < x1 - w2) {   
            return false;   
        } else if (y2 > y1 && y2 > y1 + h1) {   
            return false;   
        } else if (y2 < y1 && y2 < y1 - h2) {   
            return false;   
        } else {   
            return true;   
        }   
    }   
  
    /**  
     * 矩形碰撞检测 参数为Rect对象  
     *   
     * @param r1  
     *            第一个Rect对象  
     * @param r2  
     *            第二个Rect对象  
     * @return 是否碰撞  
     */  
    public boolean IsRectCollision(Rect r1, Rect r2) {   
        return IsRectCollision(r1.left, r1.top, r1.right - r1.left, r1.bottom   
                - r1.top, r2.left, r2.top, r2.right - r2.left, r2.bottom   
                - r2.top);   
    }   
  
    public boolean IsConlision(Rect rect, int r1,int r2) {
		int x=rect.left + (rect.right - rect.left)/2;
		int y=rect.top + (rect.bottom - rect.top)/2;
		int xx=this.getX()+this.getW()/2;
		int yy=this.getY()+this.getH()/2;
		if(r1+r2>Math.sqrt(((xx-x)*(xx-x)+(yy-y)*(yy-y))))
			return true;
		else
			return false;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getW() {
		return width-x;
	}
	public int getH() {
		return height-y;
	}
}

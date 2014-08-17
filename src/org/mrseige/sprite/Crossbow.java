package org.mrseige.sprite;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.mrseige.base.Layer;
import org.mrseige.base.TouchAble;
import org.mrseige.common.BitMapManager;
import org.mrseige.common.DensityUtil;
import org.mrseige.common.Pos;
import org.mrseige.game.WeaponConfig;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Shader;
import android.view.MotionEvent;


public class Crossbow extends Layer implements TouchAble,Serializable{
	
	private static final String TAG = "Crossbow";
	
	private final static float MIN_DEGREE = -60f;  
    private final static float MAX_DEGREE = 60f;  
  
    private Matrix matrix;  
    private Matrix matrix2;  
    
    private float saveX; // 当前保存的x  
    private float saveY; // 当前保存的y  
    private float curTouchX; // 当前触屏的x  
    private float curTouchY; // 当前触摸的y  
    private float centerX; // 中心点x  
    private float centerY; // 中心点y  
    private float curDegree; // 当前角度  
    private float changeDegree;  
    
    
    // 贝赛尔曲线成员变量(起始点，控制（操作点），终止点，3点坐标)  
    private float startX, startY, controlX, controlY, endX, endY;  
    // Path  
    private Path path;  
    // 为了不影响主画笔，这里绘制贝赛尔曲线单独用一个新画笔  
    private Paint paintQ;  
    // 随机库（让贝赛尔曲线更明显）  
    private Random random;  
    
    /**当前弦上的矢**/
    private Arrow arrow;
    
    private List<Arrow> arrowList = new ArrayList<Arrow>();
    
    /**弓弩是否空弦**/
    private boolean isBowEmpty = false;
    
    /**弦旋转半径**/
    private double radius;
    private double initQDegreeL;
    private double initQDegreeR;
    /**触摸按下时候y坐标**/
    private float tmpSaveY ;
    private float deltaY;
    
    float tmpControlX ;
    float tmpControlY ;
    
    long beginTime;
    long shootEnhancer;
   
    private Context context ;
    
	public Crossbow(int x, int y, int width, int height, Context context) {
		super(x, y, width, height);
		this.context = context;
		this.centerX = x+(width-x)/2;
		this.centerY = y +(height-y)/4;
		matrix = new Matrix();
		matrix2 = new Matrix();
		
		Shader shader = new LinearGradient(0,0,1,1, 
				new int[]{Color.BLACK,Color.WHITE},null,Shader.TileMode.REPEAT);  
		
		//贝赛尔曲线相关初始化  
        path = new Path();
        
        paintQ = new Paint();  
        float strokeWidth = DensityUtil.dip2px(context, 5);
        paintQ.setAntiAlias(true);  
        paintQ.setStyle(Style.STROKE);  
        paintQ.setStrokeWidth(strokeWidth);  
       // paintQ.setARGB(255, 156, 212, 255); 
        paintQ.setShader(shader);
        random = new Random();
        
        arrowList.add(initArrow());
        
        //弦 的逻辑
        double w = (width-x)/2 - DensityUtil.dip2px(context, 16);
        double h = (height-y)/4 - DensityUtil.dip2px(context, 12);;
        radius = Math.sqrt(Math.pow(w, 2)+Math.pow(h, 2));
        initQDegreeL = Math.PI+Math.atan(h/w);
        initQDegreeR = initQDegreeL+(Math.PI - 2*Math.atan(h/w));
        
        //弦初始化
        double radiansQL =initQDegreeL- Math.toRadians(curDegree);
        double radiansQR = initQDegreeR -Math.toRadians(curDegree);
        startX=(int) (centerX + radius*Math.cos(radiansQL));
        startY = (int) (centerY - radius * Math.sin(radiansQL));
        endX=(int) (centerX + radius*Math.cos(radiansQR));
        endY = (int) (centerY - radius * Math.sin(radiansQR));
        controlX = (startX+endX)/2;
        controlY = (startY+endY)/2;
        
        //武器攻击
        shootEnhancer = (long) (WeaponConfig.getCrossBows().get(0).getShootEnhancer()*1000);
	}
	
	/**制造一只矢**/
	private Arrow initArrow() {
		 //初始化的箭矢
        Bitmap arrowBitmap = BitMapManager.getInstance().arrow[0];
        arrow = new Arrow((int) (centerX-arrowBitmap.getWidth()/2),y-arrowBitmap.getHeight()/2,(int) (this.centerX+arrowBitmap.getWidth()/2),y+arrowBitmap.getHeight()/2, context);
        arrow.setBitmap(arrowBitmap);
        arrow.setMove(false);
        return arrow;
	}
	@Override
	public boolean onTouch(MotionEvent event) {
		
		curTouchX = event.getX();  
        curTouchY = event.getY();  
		if(true) {
			switch (event.getAction()) {  
	        case MotionEvent.ACTION_DOWN:
	        	saveTouchPoint();  
	        	tmpSaveY =  saveY;
	        //	optimize( (float)getActionDegrees(centerX, centerY, centerX, centerY -1 , curTouchX, curTouchY) );
	        //	if(!touch(event)) return false;
	        	/*initArrow();
	        	arrowList.add(arrow);*/
	            break;  
	        case MotionEvent.ACTION_MOVE:  
	            handleTouchMove();
	            rotateBow();
	            
	            //弦旋转
	            double radiansQL =initQDegreeL- Math.toRadians(curDegree);
	            double radiansQR = initQDegreeR -Math.toRadians(curDegree);
	            startX=(int) (centerX + radius*Math.cos(radiansQL));
	            startY = (int) (centerY - radius * Math.sin(radiansQL));
	            endX=(int) (centerX + radius*Math.cos(radiansQR));
	            endY = (int) (centerY - radius * Math.sin(radiansQR));
	            tmpControlX = (startX+endX)/2;
            	tmpControlY = (startY+endY)/2;
	            
	            //弦拉伸
	            calculateStrethPos();
	            
	            //箭随弦拉伸
	            if(!isBowEmpty)rotateArrow(arrow);
	            break;
	        case MotionEvent.ACTION_UP:
	        	if(arrow.getRotatedBitmap() ==null) {
	        		arrow.setDegree(curDegree);
	        		double radians = Math.toRadians(curDegree);
		        	int x1 = arrow.getX()+arrow.getBitmap().getWidth()/2 + (int) ((arrow.getBitmap().getHeight()-this.getBitmap().getHeight()/2)*	Math.sin(radians)) ;
		        	int y1 = (int) (this.centerY-(int) ((arrow.getBitmap().getHeight()-this.getBitmap().getHeight()/2)*	Math.cos(radians)));
		        	
		        	int x2 = x1-(int) (arrow.getBitmap().getHeight()*Math.sin(radians));
		        	int y2 = (int) (y1+arrow.getBitmap().getHeight()*Math.cos(radians));
	        		float centerX = (x1+x2)/2;
	        		float centerY =(y1+y2)/2;
	        		Pos pos = new Pos(centerX, centerY);
	        		arrow.setPos(pos);
	        		
	        		if(deltaY >DensityUtil.dip2px(context, 5)) {
			            //记录弦空闲的起始时间
			            if(!isBowEmpty) {
			            	//弓箭射出后，把弓的状态置为空
			            	isBowEmpty = true;
			            	beginTime = System.currentTimeMillis();
			            }
	        			arrow.setMove(true);
	        		}else {
	        			deltaY = 0;
	        			//弦拉伸但未达到射箭的范围，点击弹起时，恢复弓箭初始化状态
	        			rotateArrow(arrow);
	        		}
	        	}
	        	deltaY = 0;
	        	//重置弦状态
	        	controlX = (startX+endX)/2;
	        	controlY = (startY+endY)/2;
	        	
	        }  
		}
		return true;
	}
	
	/**
	 * 装载弓箭
	 */
	public void loadArrow() {
		long nowTime = System.currentTimeMillis();
		
		if(isBowEmpty && (nowTime - beginTime > shootEnhancer ))  {
	    	arrowList.add(initArrow());
	    	rotateArrow(arrow);
	    	isBowEmpty = false;
		}
	}
	
	public void getNextPos(Pos pos, float speed, float degree) {
	      double radians = Math.toRadians(degree);
	      int xStep = (int) (pos.x + speed * Math.sin(radians));
	      int yStep = (int) (pos.y - speed * Math.cos(radians));
	      pos.x = xStep;
	      pos.y = yStep;
    }
	
    double y_ = BitMapManager.getInstance().arrow[0].getHeight()/4;
    double Y_;
    private void calculateStrethPos() {
		double radians = Math.toRadians(curDegree);
		//弦拉伸状态
		if(curTouchY> tmpSaveY ) {
			float deltaNum = curTouchY - tmpSaveY;
			Y_ = y_ +deltaNum;
			controlX = (float) (-Y_*Math.sin(radians)+centerX);
			controlY = (float) (Y_*Math.cos(radians) + centerY);
			//deltaY = (float) (Math.sqrt(Math.pow(controlX-centerX, 2) + Math.pow(controlY-centerY, 2))-y_);
			deltaY = (float) (deltaNum/2);//?为什么/2
		}else {
			float deltaNum = curTouchY - tmpSaveY;
			Y_ = y_ +deltaNum;
			if(Y_<y_) return;
			controlX = (float) (-Y_*Math.sin(radians)+centerX);
			controlY = (float) (Y_*Math.cos(radians) + centerY);
			//deltaY = (float) (Math.sqrt(Math.pow(controlX-centerX, 2) + Math.pow(controlY-centerY, 2))-y_);
			deltaY = (float) (deltaNum/2);
		}
    }
    
	private void handleTouchMove() {  
        changeDegree = (float) getActionDegrees(centerX, centerY, saveX, saveY,  
                curTouchX, curTouchY);  
        float tempDegree = (float) curDegree + changeDegree;  
        if (tempDegree >= MIN_DEGREE && tempDegree <= MAX_DEGREE) {  
            optimize(tempDegree);//优化变动  
            /*m.setRotate(curDegree, centerX, centerY);  */
        }  
        saveTouchPoint();  
    }  
	
  
    private void optimize(float tempDegree){  
        if(tempDegree>MAX_DEGREE-1){  
            curDegree=MAX_DEGREE;  
        }else if(tempDegree<MIN_DEGREE+1){  
            curDegree=MIN_DEGREE;  
        }else{  
            this.curDegree = tempDegree;  
        }  
          
    }  
  
    private void saveTouchPoint() {  
        saveX = curTouchX;  
        saveY = curTouchY;  
    }  
    
    //获得旋转角
  	public float getAngle(MotionEvent Event)
  	{
  		double DeltalX=Event.getX(0)-Event.getX(1);
  		double DeltalY=Event.getY(0)-Event.getY(1);
  		return (float)Math.atan2(DeltalX, DeltalY);
  	}
  	
	/** 
     * 获取两点到第三点的夹角。 
     *  
     * @param x 
     * @param y 
     * @param x1 
     * @param y1 
     * @param x2 
     * @param y2 
     * @return 
     */  
    private double getActionDegrees(float x, float y, float x1, float y1,  
            float x2, float y2) {  
  
        double a = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));  
        double b = Math.sqrt((x - x2) * (x - x2) + (y - y2) * (y - y2));  
        double c = Math.sqrt((x1 - x) * (x1 - x) + (y1 - y) * (y1 - y));  
        // 余弦定理  
        double cosA = (b * b + c * c - a * a) / (2 * b * c);  
        // 返回余弦值为指定数字的角度，Math函数为我们提供的方法  
        double arcA = Math.acos(cosA);  
        double degree = arcA * 180 / Math.PI;  
  
        // 接下来我们要讨论正负值的关系了，也就是求出是顺时针还是逆时针。  
        // 第1、2象限  
        if (y1 < y && y2 < y) {  
            if (x1 < x && x2 > x) {// 由2象限向1象限滑动  
                return degree;  
            }  
            // 由1象限向2象限滑动  
            else if (x1 >= x && x2 <= x) {  
                return -degree;  
            }  
        }  
        // 第3、4象限  
        if (y1 > y && y2 > y) {  
            // 由3象限向4象限滑动  
            if (x1 < x && x2 > x) {  
                return -degree;  
            }  
            // 由4象限向3象限滑动  
            else if (x1 > x && x2 < x) {  
                return degree;  
            }  
  
        }  
        // 第2、3象限  
        if (x1 < x && x2 < x) {  
            // 由2象限向3象限滑动  
            if (y1 < y && y2 > y) {  
                return -degree;  
            }  
            // 由3象限向2象限滑动  
            else if (y1 > y && y2 < y) {  
                return degree;  
            }  
        }  
        // 第1、4象限  
        if (x1 > x && x2 > x) {  
            // 由4向1滑动  
            if (y1 > y && y2 < y) {  
                return -degree;  
            }  
            // 由1向4滑动  
            else if (y1 < y && y2 > y) {  
                return degree;  
            }  
        }  
  
        // 在特定的象限内  
        float tanB = (y1 - y) / (x1 - x);  
        float tanC = (y2 - y) / (x2 - x);  
        if ((x1 > x && y1 > y && x2 > x && y2 > y && tanB > tanC)// 第一象限  
                || (x1 > x && y1 < y && x2 > x && y2 < y && tanB > tanC)// 第四象限  
                || (x1 < x && y1 < y && x2 < x && y2 < y && tanB > tanC)// 第三象限  
                || (x1 < x && y1 > y && x2 < x && y2 > y && tanB > tanC))// 第二象限  
            return -degree;  
        return degree;  
    }
    
    /*public  Bitmap rotate() {  
    	Bitmap b = this.getBitmap();
        if (curDegree != 0 && b != null) {  
            m.setRotate(curDegree,    
                    (float) b.getWidth() / 2, (float) b.getHeight() / 2);
            
            Log.v(TAG, "degree="+curDegree);
            Log.v(TAG, "width="+b.getWidth()/2+" height="+b.getHeight()/2);
            
            try {    
                Bitmap b2 = Bitmap.createBitmap(    
                        b, 0, 0, b.getWidth(), b.getHeight(), m, true);   
                
                if (b != b2 && !b.isRecycled()) {    
                    b.recycle();  
                    b = b2;    
                }    
            } catch (OutOfMemoryError ex) {    
                // 建议大家如何出现了内存不足异常，最好return 原始的bitmap对象。.    
            }    
        }    
        return b;    
    } */
    public void rotateBow(){	
    	Bitmap b = this.getBitmap();
    	if (curDegree != 0 && b != null) {  
    	    matrix.reset();
    	    matrix.postTranslate(this.x, this.y);
            matrix.postRotate(curDegree,centerX,centerY);
    	}
       //   return Bitmap.createBitmap(b, 0, 0, b.getWidth(), b.getHeight(), matrix, true);
    }
    
    /**矢的旋转，transient status**/
    public void rotateArrow(Arrow arrow){	
    	Bitmap b = arrow.getBitmap();
    	if (curDegree != 0 && b != null) {  
    	    matrix2.reset();
    	    matrix2.postTranslate(arrow.getX(), arrow.getY()+deltaY);
            matrix2.postRotate(curDegree,centerX,centerY);
    	}
        /*return Bitmap.createBitmap(b, 0, 0, b.getWidth(), b.getHeight(), matrix2, true);*/
    }
    public void drawSelf(Canvas canvas) {
    	Bitmap bitmap = this.getBitmap();
		canvas.save();
		if(bitmap != null) {
			if(!matrix.isIdentity()){
				canvas.drawBitmap(bitmap, matrix, null);
				canvas.restore();
			}
			else{
				canvas.restore();
				draw(canvas);
			}
		}
		
	}
    
    /** 
     * 绘制贝赛尔曲线 
     *  
     * @param canvas 主画布 
     */  
    public void drawQpath(Canvas canvas) {
    	/*Bitmap bitmap = this.getBitmap();
    	startX = x;
    	startY = height;
    	endX = width;
    	endY = height;
    	controlX = x + bitmap.getWidth()/2;
    	controlY = height;*/
        path.reset();// 重置path  
        // 贝赛尔曲线的起始点  
        path.moveTo(startX, startY);  
        // 设置贝赛尔曲线的操作点以及终止点  
        path.quadTo(controlX, controlY, endX, endY);  
        // 绘制贝赛尔曲线（Path）  
        canvas.drawPath(path, paintQ);  
    }
    
    /**
     * 绘制矢
     */
    public void drawArrow(Canvas canvas) {
    	for(int i=0;i<arrowList.size();i++) {
    		Arrow arrow = arrowList.get(i);
    		if(!arrow.isMove()) {
        		Bitmap bitmap = arrow.getBitmap();
        		canvas.save();
        		if(bitmap != null) {
        			//matrix在用
        			if(!matrix2.isIdentity()){
        				//旋转后的bitmap
        				canvas.drawBitmap(bitmap, matrix2, null);
        				canvas.restore();
        			}
        			else{
        				//初始化时的bitmap
        				canvas.restore();
        				arrow.draw(canvas);
        			}
        		}
        	}else {
        		//移动的bitmap
        		arrow.drawRotate(canvas);
        	}
    	}
    }

	public List<Arrow> getArrowList() {
		return arrowList;
	}
    
}


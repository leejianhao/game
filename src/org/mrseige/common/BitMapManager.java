package org.mrseige.common;

import java.io.InputStream;

import org.mrseige.activity.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * 位图工具
 * @author ljh
 *
 */
public class BitMapManager{
	
	public Bitmap[] background = new Bitmap[1];
	public Bitmap[] frozenWeapon = new Bitmap[1];
	public Bitmap[] fixTool = new Bitmap[1];
	
	public Bitmap[] obstacle = new Bitmap[6];
	public Bitmap[] bow = new Bitmap[2];
	public Bitmap[] bow2 = new Bitmap[2];
	public Bitmap[] arrow = new Bitmap[2];
	
	public Bitmap[] A = new Bitmap[5];
	public Bitmap[] A_DEAD = new Bitmap[4];
	public Bitmap[] A_ATTACK = new Bitmap[3];
	
	public Bitmap[] B = new Bitmap[5];
	public Bitmap[] B_DEAD = new Bitmap[4];
	public Bitmap[] B_ATTACK = new Bitmap[3];
	
	public Bitmap[] BB = new Bitmap[3];
	public Bitmap[] BB_DEAD = new Bitmap[4];
	public Bitmap[] BB_ATTACK = new Bitmap[3];
	
	public Bitmap[] BB_ = new Bitmap[3];
	public Bitmap[] BB__ATTACK = new Bitmap[3];
	
	public Bitmap[] C = new Bitmap[5];
	public Bitmap[] C_DEAD = new Bitmap[4];
	public Bitmap[] C_ATTACK = new Bitmap[3];
	
	public Bitmap[] D = new Bitmap[5];
	public Bitmap[] D_DEAD = new Bitmap[4];
	public Bitmap[] D_ATTACK = new Bitmap[4];
	
	public Bitmap[] E = new Bitmap[5];
	public Bitmap[] E_DEAD = new Bitmap[4];
	public Bitmap[] E_ATTACK = new Bitmap[3];
	
	public Bitmap[] F = new Bitmap[10];
	public Bitmap[] F_DEAD = new Bitmap[3];
	public Bitmap[] F_ATTACK = new Bitmap[4];
	
	
	
	public Bitmap[] level = new Bitmap[6];
	
	
	public Bitmap[] attackedHint = new Bitmap[3];
	private static final BitMapManager bmm = new BitMapManager();
	private BitMapManager(){}
	
	public static BitMapManager getInstance() {
		return bmm;
	}
	public void loadResource2(Context context) {
		for(int i=0,size=level.length;i<size;i++) {
			Bitmap b = readBitMap(context, R.drawable.level_1_1+i);
			level[i] = Bitmap.createScaledBitmap(b, DensityUtil.dip2px(context, b.getWidth()), DensityUtil.dip2px(context,b.getHeight()), true);
		}
	}
	
	public void loadResource(Context context) {
		for(int i=0,size=attackedHint.length;i<size;i++) {
			attackedHint[0] = readBitMap(context, R.drawable.attacked);
		}
		
		for(int i=0,size=background.length;i<size;i++) {
			background[i] = readBitMap(context, R.drawable.bg+i);
		}
		for(int i=0,size=obstacle.length;i<size;i++) {
			obstacle[i] = readBitMap(context, R.drawable.obstacle1+i);
		}
		for(int i=0,size=frozenWeapon.length;i<size;i++) {
			Bitmap b = readBitMap(context, R.drawable.frozen_btn+i);
			frozenWeapon[i] = Bitmap.createScaledBitmap(b, DensityUtil.dip2px(context, b.getWidth()), DensityUtil.dip2px(context,b.getHeight()), true);
		}
		for(int i=0,size=fixTool.length;i<size;i++) {
			Bitmap b = readBitMap(context, R.drawable.fix_btn+i);
			fixTool[i] = Bitmap.createScaledBitmap(b, DensityUtil.dip2px(context, b.getWidth()), DensityUtil.dip2px(context,b.getHeight()), true);
		}
		for(int i=0,size=bow.length;i<size;i++) {
			Bitmap b = readBitMap(context, R.drawable.crossbow01+i);
			bow[i] = Bitmap.createScaledBitmap(b, DensityUtil.dip2px(context, b.getWidth()), DensityUtil.dip2px(context,b.getHeight()), true);
		}
		for(int i=0,size=arrow.length;i<size;i++) {
			Bitmap b = readBitMap(context, R.drawable.arrow+i);
			arrow[i] = Bitmap.createScaledBitmap(b, DensityUtil.dip2px(context, b.getWidth()), DensityUtil.dip2px(context,b.getHeight()), true);
		}
		
		for(int i=0,size=D.length;i<size;i++) {
			D[i] = readBitMap(context, R.drawable.d1+i);
		}
		
		for(int i=0,size=A.length;i<size;i++) {
			Bitmap b = readBitMap(context, R.drawable.a1+i);
			A[i] = Bitmap.createScaledBitmap(b, DensityUtil.dip2px(context, b.getWidth()*1.5f), DensityUtil.dip2px(context,b.getHeight()*1.5f), true);
		}
		for(int i=0,size=A_DEAD.length;i<size;i++) {
			Bitmap b = readBitMap(context, R.drawable.a_dead_1+i);
			A_DEAD[i] = Bitmap.createScaledBitmap(b, DensityUtil.dip2px(context, b.getWidth()), DensityUtil.dip2px(context,b.getHeight()), true);
		}
		for(int i=0,size=A_ATTACK.length;i<size;i++) {
			Bitmap b = readBitMap(context, R.drawable.a_attack_1+i);
			A_ATTACK[i] = Bitmap.createScaledBitmap(b, DensityUtil.dip2px(context, b.getWidth()), DensityUtil.dip2px(context,b.getHeight()), true);
		}
		
		for(int i=0,size=B.length;i<size;i++) {
			Bitmap b = readBitMap(context, R.drawable.crawl01+i);
			B[i] = Bitmap.createScaledBitmap(b, DensityUtil.dip2px(context, b.getWidth()), DensityUtil.dip2px(context,b.getHeight()), true);
		}
		
		for(int i=0,size=B_DEAD.length;i<size;i++) {
			Bitmap b = readBitMap(context, R.drawable.crawl_dead_01+i);
			B_DEAD[i] = Bitmap.createScaledBitmap(b, DensityUtil.dip2px(context, b.getWidth()), DensityUtil.dip2px(context,b.getHeight()), true);
		}
		
		for(int i=0,size=B_ATTACK.length;i<size;i++) {
			Bitmap b = readBitMap(context, R.drawable.crawl_attack_1+i);
			B_ATTACK[i] = Bitmap.createScaledBitmap(b, DensityUtil.dip2px(context, b.getWidth()), DensityUtil.dip2px(context,b.getHeight()), true);
		}
		
		for(int i=0,size=BB.length;i<size;i++) {
			Bitmap b = readBitMap(context, R.drawable.bb1+i);
			BB[i] = Bitmap.createScaledBitmap(b, DensityUtil.dip2px(context, b.getWidth()), DensityUtil.dip2px(context,b.getHeight()), true);
		}
		
		for(int i=0,size=BB_DEAD.length;i<size;i++) {
			Bitmap b = readBitMap(context, R.drawable.bb_dead_1+i);
			BB_DEAD[i] = Bitmap.createScaledBitmap(b, DensityUtil.dip2px(context, b.getWidth()), DensityUtil.dip2px(context,b.getHeight()), true);
		}
		
		for(int i=0,size=BB_ATTACK.length;i<size;i++) {
			Bitmap b = readBitMap(context, R.drawable.bb_attack_1+i);
			BB_ATTACK[i] = Bitmap.createScaledBitmap(b, DensityUtil.dip2px(context, b.getWidth()), DensityUtil.dip2px(context,b.getHeight()), true);
		}
		
		for(int i=0,size=BB_.length;i<size;i++) {
			Bitmap b = readBitMap(context, R.drawable.bb1_1+i);
			BB_[i] = Bitmap.createScaledBitmap(b, DensityUtil.dip2px(context, b.getWidth()), DensityUtil.dip2px(context,b.getHeight()), true);
		}
		
		for(int i=0,size=BB__ATTACK.length;i<size;i++) {
			Bitmap b = readBitMap(context, R.drawable.bb_attack_1+i);
			BB__ATTACK[i] = Bitmap.createScaledBitmap(b, DensityUtil.dip2px(context, b.getWidth()), DensityUtil.dip2px(context,b.getHeight()), true);
		}
		
		for(int i=0,size=C.length;i<size;i++) {
			Bitmap b = readBitMap(context, R.drawable.c1+i);
			C[i] = Bitmap.createScaledBitmap(b, DensityUtil.dip2px(context, b.getWidth()), DensityUtil.dip2px(context,b.getHeight()), true);
		}
		
		for(int i=0,size=C_DEAD.length;i<size;i++) {
			Bitmap b = readBitMap(context, R.drawable.c_dead_1+i);
			C_DEAD[i] = Bitmap.createScaledBitmap(b, DensityUtil.dip2px(context, b.getWidth()), DensityUtil.dip2px(context,b.getHeight()), true);
		}
		
		for(int i=0,size=C_ATTACK.length;i<size;i++) {
			Bitmap b = readBitMap(context, R.drawable.c_attack_1+i);
			C_ATTACK[i] = Bitmap.createScaledBitmap(b, DensityUtil.dip2px(context, b.getWidth()), DensityUtil.dip2px(context,b.getHeight()), true);
		}
		
		for(int i=0,size=D.length;i<size;i++) {
			Bitmap b = readBitMap(context, R.drawable.d1+i);
			D[i] = Bitmap.createScaledBitmap(b, DensityUtil.dip2px(context, b.getWidth()), DensityUtil.dip2px(context,b.getHeight()), true);
		}
		
		for(int i=0,size=D_DEAD.length;i<size;i++) {
			Bitmap b = readBitMap(context, R.drawable.d_dead_1+i);
			D_DEAD[i] = Bitmap.createScaledBitmap(b, DensityUtil.dip2px(context, b.getWidth()), DensityUtil.dip2px(context,b.getHeight()), true);
		}
		
		for(int i=0,size=D_ATTACK.length;i<size;i++) {
			Bitmap b = readBitMap(context, R.drawable.d_attack_1+i);
			D_ATTACK[i] = Bitmap.createScaledBitmap(b, DensityUtil.dip2px(context, b.getWidth()), DensityUtil.dip2px(context,b.getHeight()), true);
		}
		
		
		for(int i=0,size=E.length;i<size;i++) {
			Bitmap b = readBitMap(context, R.drawable.e1+i);
			E[i] = Bitmap.createScaledBitmap(b, DensityUtil.dip2px(context, b.getWidth()), DensityUtil.dip2px(context,b.getHeight()), true);
		}
		
		for(int i=0,size=E_DEAD.length;i<size;i++) {
			Bitmap b = readBitMap(context, R.drawable.a_dead_1+i);
			E_DEAD[i] = Bitmap.createScaledBitmap(b, DensityUtil.dip2px(context, b.getWidth()), DensityUtil.dip2px(context,b.getHeight()), true);
		}
		
		for(int i=0,size=E_ATTACK.length;i<size;i++) {
			Bitmap b = readBitMap(context, R.drawable.e_attack_1+i);
			E_ATTACK[i] = Bitmap.createScaledBitmap(b, DensityUtil.dip2px(context, b.getWidth()), DensityUtil.dip2px(context,b.getHeight()), true);
		}
		
		for(int i=0,size=F.length;i<size;i++) {
			Bitmap b = readBitMap(context, R.drawable.f1+i);
			F[i] = Bitmap.createScaledBitmap(b, DensityUtil.dip2px(context, b.getWidth()), DensityUtil.dip2px(context,b.getHeight()), true);
		}
		
		for(int i=0,size=F_DEAD.length;i<size;i++) {
			Bitmap b = readBitMap(context, R.drawable.f_dead_1+i);
			F_DEAD[i] = Bitmap.createScaledBitmap(b, DensityUtil.dip2px(context, b.getWidth()), DensityUtil.dip2px(context,b.getHeight()), true);
		}
		
		for(int i=0,size=F_ATTACK.length;i<size;i++) {
			Bitmap b = readBitMap(context, R.drawable.f_attack_1+i);
			F_ATTACK[i] = Bitmap.createScaledBitmap(b, DensityUtil.dip2px(context, b.getWidth()), DensityUtil.dip2px(context,b.getHeight()), true);
		}
		
		
	}
	
	/**
	 * 读取图片资源
	 * @param context
	 * @param resId
	 * 
	 * @return he decoded bitmap, or null if the image could not be decode.
	 */
	public Bitmap readBitMap(Context context, int resId) {
		// TODO Auto-generated method stub
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inPreferredConfig = Bitmap.Config.RGB_565;
		opts.inPurgeable = true;
		opts.inInputShareable = true;
		InputStream in = context.getResources().openRawResource(resId);
		return BitmapFactory.decodeStream(in, null, opts);
	}
	
   /**
    * 释放内存空间
    */
   public void clear() {
	   for(int i=0,size=background.length;i<size;i++) {
		   	   recycleBitmap(background[i]);
		}
		for(int i=0,size=obstacle.length;i<size;i++) {
			   recycleBitmap(obstacle[i]);	
		}
		for(int i=0,size=bow.length;i<size;i++) {
			   recycleBitmap(bow[i]);
		}
		for(int i=0,size=arrow.length;i<size;i++) {
			   recycleBitmap(arrow[i]);
		}
		
		for(int i=0,size=bow2.length;i<size;i++) {
			   recycleBitmap(bow2[i]);
		}
		for(int i=0,size=A.length;i<size;i++) {
			   recycleBitmap(A[i]);
		}
		for(int i=0,size=B.length;i<size;i++) {
			   recycleBitmap(B[i]);
		}
		for(int i=0,size=D.length;i<size;i++) {
			   recycleBitmap(D[i]);
		}
   }
   
   public static void recycleBitmap(Bitmap bitmap) {
      if (bitmap != null) {
         bitmap.recycle();
         bitmap = null;
      }
   }
}



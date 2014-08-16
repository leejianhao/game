package org.mrseige.common;

import android.graphics.Bitmap;
import android.graphics.Matrix;

public class ImageUtils {
	/**
     * 放大缩小图片
     * @param bitmap
     * @param w
     * @param h
     * @return
     */
    public static Bitmap zoomBitmap(Bitmap bitmap, int w, int h) {
        Bitmap newbmp = null;
        if (bitmap != null) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            Matrix matrix = new Matrix();
            float scaleWidht = ((float) w / width);
            float scaleHeight = ((float) h / height);
            matrix.postScale(scaleWidht, scaleHeight);
            newbmp = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix,
                    true);
        }
        return newbmp;
    }
    
    /** 
     * 将Bitmap转换成指定大小 
     *  
     * @param bitmap 
     * @param width 
     * @param height 
     * @return 
     */  
    public static Bitmap createBitmapBySize(Bitmap bitmap, int width, int height) {  
        return Bitmap.createScaledBitmap(bitmap, width, height, true);  
    }  
}

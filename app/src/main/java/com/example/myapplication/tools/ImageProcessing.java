package tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;


public class ImageProcessing {

    private int zoom = 90;

    public void scaleImageRelative(ImageView imageView, Context c) {

        Drawable drawing = imageView.getDrawable();
        if (drawing == null) {
        }
        Bitmap bitmap = ((BitmapDrawable) drawing).getBitmap();

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int bounding = dpToPx(50, c);

        float xScale = ((float) bounding) / width;
        float yScale = ((float) bounding) / height;
        float scale = (xScale <= yScale) ? xScale : yScale;
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        Bitmap scaledBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height,
                matrix, true);
        width = scaledBitmap.getWidth(); // re-use
        height = scaledBitmap.getHeight(); // re-use
        BitmapDrawable result = new BitmapDrawable(scaledBitmap);

        imageView.setImageDrawable(result);

        RelativeLayout.LayoutParams params    = (RelativeLayout.LayoutParams) imageView
                .getLayoutParams();

/*        GridLayout.LayoutParams params = (GridLayout.LayoutParams) imageView
                .getLayoutParams();*/
        params.width = width + zoom ;
        params.height = height + zoom ;
        imageView.setLayoutParams(params);

    }

    public void scaleImageConstraint(ImageView imageView, Context c) {

        Drawable drawing = imageView.getDrawable();
        if (drawing == null) {
        }
        Bitmap bitmap = ((BitmapDrawable) drawing).getBitmap();

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int bounding = dpToPx(50, c);

        float xScale = ((float) bounding) / width;
        float yScale = ((float) bounding) / height;
        float scale = (xScale <= yScale) ? xScale : yScale;
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        Bitmap scaledBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height,
                matrix, true);
        width = scaledBitmap.getWidth(); // re-use
        height = scaledBitmap.getHeight(); // re-use
        BitmapDrawable result = new BitmapDrawable(scaledBitmap);

        imageView.setImageDrawable(result);

        ConstraintLayout.LayoutParams params    = (ConstraintLayout.LayoutParams) imageView
                .getLayoutParams();

/*        GridLayout.LayoutParams params = (GridLayout.LayoutParams) imageView
                .getLayoutParams();*/
        params.width = width + zoom ;
        params.height = height + zoom ;
        imageView.setLayoutParams(params);

    }









    public int dpToPx(int dp, Context c) {
        float density = c.getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }
}

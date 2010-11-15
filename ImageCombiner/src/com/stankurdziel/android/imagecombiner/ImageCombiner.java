
package com.stankurdziel.android.imagecombiner;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

/**
 * A simple runnable Android program that demonstrates how to combine two drawables into one.
 */
public class ImageCombiner extends Activity {
    ImageState imageState = ImageState.BACKGROUND;
    private View button;
    private View image;
    private TextView label;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        button = findViewById(R.id.button);
        image = findViewById(R.id.image);
        label = (TextView) findViewById(R.id.image_label);
        updateImage();

        button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                imageState = imageState.next();
                updateImage();
            }
        });
    }

    protected void updateImage() {
        label.setText(imageState.toString());
        image.setBackgroundDrawable(imageState.getDrawable(getResources()));
    }

    enum ImageState {
        BACKGROUND, OVERLAY, COMBINED;
        private Drawable drawable;

        public ImageState next() {
            if (ordinal() == values().length - 1) {
                return values()[0];
            }
            return values()[ordinal() + 1];
        }

        public Drawable getDrawable(Resources resources) {
            if (drawable != null) return drawable;
            return drawable = makeDrawable(resources, this);
        }
    }

    private static Drawable makeDrawable(Resources resources, ImageState state) {
        switch (state) {
            case BACKGROUND:
                return resources.getDrawable(R.drawable.background);
            case OVERLAY:
                return resources.getDrawable(R.drawable.overlay);
            case COMBINED:
                return combineDrawables(ImageState.BACKGROUND.getDrawable(resources),
                        ImageState.OVERLAY.getDrawable(resources));
        }
        return null;
    }

    /**
     * Utility function to combine two Drawables
     *
     * @param background the Drawable that goes underneath
     * @param overlay the Drawable that goes on top (should probably have some high transparency
     *            alpha set on at least part of it)
     * @return a combined Drawable
     */
    public static Drawable combineDrawables(Drawable background, Drawable overlay) {
        Bitmap combined = Bitmap.createBitmap(300, 300, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(combined);
        background.draw(canvas);
        overlay.draw(canvas);
        return new BitmapDrawable(combined);
    }
}


package com.stankurdziel.android.rotation;

import android.app.Activity;
import android.os.Bundle;
import android.view.Surface;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class RotationDisplay extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        updateRotationValue();
        findViewById(R.id.rotate_button).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                updateRotationValue();
            }
        });
    }

    private void updateRotationValue() {
        TextView rotateText = (TextView) findViewById(R.id.rotate_text);
        rotateText.setText("Rotation: "
                + Rotation.fromValue(getWindowManager().getDefaultDisplay().getRotation()));
    }

    public enum Rotation {
        ROTATION_0, ROTATION_90, ROTATION_180, ROTATION_270;
        public static Rotation fromValue(int value) {
            switch (value) {
                case Surface.ROTATION_0:
                    return ROTATION_0;
                case Surface.ROTATION_90:
                    return ROTATION_90;
                case Surface.ROTATION_180:
                    return ROTATION_180;
                case Surface.ROTATION_270:
                    return ROTATION_270;
                default:
                    throw new IllegalArgumentException("Unknown rotation value: " + value);
            }
        }
    }
}

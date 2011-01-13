
package com.stankurdziel.android.shareview;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ShareViewer extends Activity {
    private static final String TAG = ShareViewer.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Intent intent = getIntent();
        Log.d(TAG, intent.toString());

        if (intent.getCategories() == null
                || !intent.getCategories().contains(Intent.CATEGORY_LAUNCHER)) {
            ((TextView) findViewById(R.id.text)).setText(R.string.alternate);
        }
        ((TextView) findViewById(R.id.intent_details)).setText(describeIntent(intent));
    }

    private CharSequence describeIntent(Intent intent) {
        StringBuffer desc = new StringBuffer();
        desc.append("displayId: " + getWindowManager().getDefaultDisplay().getDisplayId() + "\n");
        desc.append("ACTION: " + intent.getAction() + "\n");
        desc.append("mime type: " + intent.getType() + "\n");
        desc.append("component: " + intent.getComponent() + "\n");
        desc.append("flags: " + intent.getFlags() + "\n");
        desc.append(describeExtras(intent.getExtras()));
        return desc.toString();
    }

    private String describeExtras(Bundle extras) {
        StringBuffer desc = new StringBuffer();
        if (extras == null) return "0 extras\n";
        desc.append(extras.size() + " extras:\n");
        int i = 0;
        for (String key : extras.keySet()) {
            Object value = extras.get(key);
            desc.append("  " + i + ": key=" + key + ", value=" + value + "\n");
            if (value instanceof Bitmap) {
                Bitmap bitmap = (Bitmap) value;
                ImageView view = new ImageView(this);
                view.setImageBitmap(bitmap);
                ((ViewGroup) findViewById(R.id.layout)).addView(view);
            }
            i++;
        }
        return desc.toString();
    }
}

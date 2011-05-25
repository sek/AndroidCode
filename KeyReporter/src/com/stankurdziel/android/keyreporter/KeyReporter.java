
package com.stankurdziel.android.keyreporter;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MultiDisplayManager;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

public class KeyReporter extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        String message = "keyCode: " + keyCode + ", event: " + event;
        TextView textView = (TextView) findViewById(R.id.text);
        textView.setText(textView.getText() + "\n" + message);
        ((ScrollView) findViewById(R.id.scroll)).fullScroll(View.FOCUS_DOWN);
        return true;
    }
}


package com.stankurdziel.android.keyreporter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;

public class EditTextRepeater extends EditText {
    public EditTextRepeater(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        super.onKeyDown(keyCode, event);
        return false;
    }
}

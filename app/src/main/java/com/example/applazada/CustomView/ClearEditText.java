package com.example.applazada.CustomView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import androidx.core.content.ContextCompat;

import com.example.applazada.R;

@SuppressLint("AppCompatCustomView")
public class ClearEditText extends EditText {
    Drawable crossx, noncrossx;
    Boolean visible = false;
    Drawable drawable1;

    public ClearEditText(Context context) {
        super(context);
        khoitao();
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        khoitao();
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        khoitao();
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        khoitao();
    }

    private void khoitao() {

        crossx = ContextCompat.getDrawable(getContext(), R.drawable.ic_clear_black_24dp);
        noncrossx = ContextCompat.getDrawable(getContext(), android.R.drawable.screen_background_light_transparent);
        cauhinh();
    }

    private void cauhinh() {

        setInputType(InputType.TYPE_CLASS_TEXT);
        Drawable[] drawable = getCompoundDrawables();
        drawable1 = (visible) ? crossx : noncrossx;
        setCompoundDrawablesWithIntrinsicBounds(drawable[0], drawable[1], drawable1, drawable[3]);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN && event.getX() >= (getRight() - drawable1.getBounds().width())) {
            setText("");

        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        if (lengthAfter == 0 && start == 0) {
            visible = false;
            cauhinh();
        } else {
            visible = true;
            cauhinh();
        }
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
    }
}

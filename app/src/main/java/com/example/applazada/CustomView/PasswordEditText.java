package com.example.applazada.CustomView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import androidx.core.content.ContextCompat;

import com.example.applazada.R;
import com.google.android.material.textfield.TextInputLayout;


@SuppressLint("AppCompatCustomView")
public class PasswordEditText extends EditText {

    Drawable eye, eyeStrike;
    Boolean visible = false;
    Boolean useStrike = false;
    Drawable drawable1;
    Boolean useValidate = false;
    int ALPHA = (int) (255 * .55f);

    public PasswordEditText(Context context) {
        super(context);

        khoitao(null);
    }

    public PasswordEditText(Context context, AttributeSet attrs) {

        super(context, attrs);
        khoitao(attrs);
    }

    public PasswordEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        khoitao(attrs);
    }

    public PasswordEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        khoitao(attrs);
    }

    private void khoitao(AttributeSet attrs) {


        if (attrs != null) {
            TypedArray array = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.PasswordEditText, 0, 0);
            this.useStrike = array.getBoolean(R.styleable.PasswordEditText_useStrike, false);
            this.useValidate = array.getBoolean(R.styleable.PasswordEditText_useValidate, false);
        }

        eye = ContextCompat.getDrawable(getContext(), R.drawable.ic_visibility_black_24dp);
        eyeStrike = ContextCompat.getDrawable(getContext(), R.drawable.ic_visibility_off_black_24dp);

        caidat();
    }

    private void caidat() {
        setInputType(InputType.TYPE_CLASS_TEXT | (visible ? InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD : InputType.TYPE_TEXT_VARIATION_PASSWORD));
        Drawable[] drawable = getCompoundDrawables();
        drawable1 = (useStrike && !visible) ? eyeStrike : eye;
        drawable1.setAlpha(ALPHA);
        setCompoundDrawablesWithIntrinsicBounds(drawable[0], drawable[1], drawable1, drawable[3]);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP && event.getX() >= (getRight() - drawable1.getBounds().width())) {
            visible = !visible;
            caidat();
            invalidate();
        }
        return super.onTouchEvent(event);
    }
}

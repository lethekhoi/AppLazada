package com.example.applazada.View.DangNhap.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.applazada.CustomView.PasswordEditText;
import com.example.applazada.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FragmentDangKy extends Fragment {
    TextInputLayout textInputLayout;
    PasswordEditText passwordEditText;
    String MATCHER_PATTERN = "((?=.*\\d)(?=.*[A-Z])(?=.*[a-z]).{6,20})"; // (?=.*\d)
    Pattern pattern;
    Matcher matcher;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_fragment_dang_ky, container, false);
        passwordEditText = view.findViewById(R.id.edtPasswordDangKy);
        textInputLayout = view.findViewById(R.id.input_edtPasswordDangKy);
        pattern = Pattern.compile(MATCHER_PATTERN);
        passwordEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    String chuoi = passwordEditText.getText().toString();
                    matcher = pattern.matcher(chuoi);
                    if(!matcher.matches()){
                        textInputLayout.setErrorEnabled(true);
                        textInputLayout.setError("Mật khẩu phải bao gồm 6 ký tự và một chữ hoa");
                    }else{
                        textInputLayout.setErrorEnabled(false);
                        textInputLayout.setError("");
                    }

                }
            }
        });

        return view;
    }
}

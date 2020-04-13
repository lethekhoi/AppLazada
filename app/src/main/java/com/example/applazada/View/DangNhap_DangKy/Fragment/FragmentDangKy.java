package com.example.applazada.View.DangNhap_DangKy.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import com.example.applazada.CustomView.PasswordEditText;
import com.example.applazada.Model.ObjectClass.NhanVien;
import com.example.applazada.Presenter.DangKy.PresenterLogicDangKy;
import com.example.applazada.R;
import com.example.applazada.View.DangNhap_DangKy.ViewDangKy;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FragmentDangKy extends Fragment implements ViewDangKy {

    PresenterLogicDangKy presenterLogicDangKy;
    TextInputLayout textInputLayoutHoTen, textInputLayoutPassword, textInputLayoutNhapLaiMK, textInputLayoutEmail;
    PasswordEditText edtpassword, edtnhaplaipassword;
    Button btnDangKy;
    EditText edtHoTen, editTextEmail;
    SwitchCompat sEmailDocQuyen;
    String MATCHER_PATTERN = "((?=.*\\d)(?=.*[A-Z])(?=.*[a-z]).{6,20})"; // (?=.*\d)
    Pattern pattern;
    Matcher matcher;
    Boolean kiemtrathongtin = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_fragment_dang_ky, container, false);
        edtpassword = view.findViewById(R.id.edtPasswordDangKy);
        textInputLayoutPassword = view.findViewById(R.id.input_edtPasswordDangKy);
        edtnhaplaipassword = view.findViewById(R.id.edtNhapLaiPasswordDangKy);
        btnDangKy = view.findViewById(R.id.btnDangKy);
        edtHoTen = view.findViewById(R.id.edtHoTenDangKy);
        sEmailDocQuyen = view.findViewById(R.id.sEmailDocQuyen);
        editTextEmail = view.findViewById(R.id.edtEmailDangKy);
        textInputLayoutHoTen = view.findViewById(R.id.input_edtHoTenDangKy);
        textInputLayoutNhapLaiMK = view.findViewById(R.id.input_edtNhapLaiPasswordDangKy);
        textInputLayoutEmail = view.findViewById(R.id.input_edtEmailDangKy);


        pattern = Pattern.compile(MATCHER_PATTERN);
        edtpassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    String chuoi = edtpassword.getText().toString();
                    matcher = pattern.matcher(chuoi);
                    if (!matcher.matches()) {
                        textInputLayoutPassword.setErrorEnabled(true);
                        textInputLayoutPassword.setError("Mật khẩu phải bao gồm 6 ký tự và một chữ hoa");
                        kiemtrathongtin = false;
                    } else {
                        textInputLayoutPassword.setErrorEnabled(false);
                        textInputLayoutPassword.setError("");
                        kiemtrathongtin = true;
                    }

                }
            }
        });
        edtHoTen.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    String chuoi = edtHoTen.getText().toString();
                    if (chuoi.trim().equals("") || chuoi.equals(null)) {
                        textInputLayoutHoTen.setError("Bạn chưa nhập mục này");
                        textInputLayoutHoTen.setErrorEnabled(true);
                        kiemtrathongtin = false;
                    } else {
                        textInputLayoutHoTen.setError("");
                        textInputLayoutHoTen.setErrorEnabled(false);
                        kiemtrathongtin = true;
                    }
                }
            }
        });

        edtnhaplaipassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    String chuoi = edtnhaplaipassword.getText().toString().trim();
                    String mk = edtpassword.getText().toString().trim();
                    if (chuoi.equals(mk)) {
                        textInputLayoutNhapLaiMK.setErrorEnabled(false);
                        textInputLayoutNhapLaiMK.setError("");
                        kiemtrathongtin = true;
                    } else {
                        textInputLayoutNhapLaiMK.setErrorEnabled(true);
                        textInputLayoutNhapLaiMK.setError("Mật khẩu không trùng khớp");
                        kiemtrathongtin = false;
                    }


                }
            }
        });

        editTextEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if (!b) {

                    String chuoi = editTextEmail.getText().toString();

                    if (chuoi.trim().equals("") || chuoi.equals(null)) {
                        textInputLayoutEmail.setError("Bạn chưa nhập mục này");
                        textInputLayoutEmail.setEnabled(true);
                        kiemtrathongtin = false;

                    } else {

                        Boolean kiemtraemail = Patterns.EMAIL_ADDRESS.matcher(chuoi).matches();
                        if (!kiemtraemail) {
                            textInputLayoutEmail.setError("Đây không phải địa chỉ Email");
                            textInputLayoutEmail.setErrorEnabled(true);
                            kiemtrathongtin = false;


                        } else {
                            textInputLayoutEmail.setError("");
                            textInputLayoutEmail.setErrorEnabled(false);
                            kiemtrathongtin = true;

                        }
                    }
                }
            }
        });

        presenterLogicDangKy = new PresenterLogicDangKy(this);
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BtnDangKy();
            }
        });


        return view;
    }


    @Override
    public void DangKyThanhCong() {
        Toast.makeText(getActivity(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void DangKyThatBai() {
        Toast.makeText(getActivity(), "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
    }


    String emaildocquyen = "";

    private void BtnDangKy() {

        String hoten = edtHoTen.getText().toString();
        final String email = editTextEmail.getText().toString();
        String matkhau = edtpassword.getText().toString();
        String nhaplaimk = edtnhaplaipassword.getText().toString();

        sEmailDocQuyen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                emaildocquyen = b + "";
            }
        });


        if (kiemtrathongtin) {
            NhanVien nhanVien = new NhanVien();
            nhanVien.setTenNV(hoten);
            nhanVien.setTenDN(email);
            nhanVien.setMatKhau(matkhau);
            nhanVien.setEmailDocQuyen(emaildocquyen);
            nhanVien.setMaNV(2);
            presenterLogicDangKy.ThucHienDangKy(nhanVien);
            Log.d("kiemtra", "đăng ký thành công");

        } else {
            Log.d("kiemtra", "đăng ký thất bại");
        }


    }


}

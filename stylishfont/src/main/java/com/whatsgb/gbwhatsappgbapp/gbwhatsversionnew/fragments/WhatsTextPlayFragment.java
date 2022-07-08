package com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.PrefManagersd;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.R;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.util.CopyHandlersds;

public class WhatsTextPlayFragment extends Fragment {

    //Prefrance
    public static PrefManagersd prf;

    ImageView close;
    Activity context;
    TextView Result_reverse;
    ImageView btncopy;
    ImageView btncopy_flip;
    ImageView btncopy_reverseflip;
    ImageView btnshare;
    ImageView btnshare_flip;
    ImageView btnshare_reverseflip;
    EditText inputtext;
    TextView txt_flip_text;
    TextView txt_reverseflip_text;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.whatsfragment_text_play, container, false);


        close = view.findViewById(R.id.close22);
        inputtext = view.findViewById(R.id.inputtext);
        Result_reverse = view.findViewById(R.id.result_reverse);
        txt_reverseflip_text = view.findViewById(R.id.txt_reverseflip_text);
        txt_flip_text = view.findViewById(R.id.txt_flip_text);
        btncopy = view.findViewById(R.id.btncopy);
        btncopy_flip = view.findViewById(R.id.btncopy_flip);
        btncopy_reverseflip = view.findViewById(R.id.btncopy_reverseflip);
        btnshare = view.findViewById(R.id.btnshare);
        btnshare_flip = view.findViewById(R.id.btnshare_flip);
        btnshare_reverseflip = view.findViewById(R.id.btnshare_revrerseflip);
        final CopyHandlersds copyHandlersds = new CopyHandlersds(context);
        inputtext.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                Result_reverse.setText(revrsetext(inputtext.getText().toString()));
                txt_reverseflip_text.setText(convertString(inputtext.getText().toString()));
                txt_flip_text.setText(revrsetext(convertString(inputtext.getText().toString())));
            }
        });
        //Copy Button
        btncopy.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                copyHandlersds.copy(Result_reverse.getText().toString());
            }
        });
        //Copy Button
        btncopy_flip.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                StringBuilder sb = new StringBuilder();
                sb.append("txt_flip_text: ");
                sb.append(txt_flip_text.getText().toString());
                copyHandlersds.copy(txt_flip_text.getText().toString());
            }
        });
        //Copy Button
       btncopy_reverseflip.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                String data1 = txt_reverseflip_text.getText().toString();
                copyHandlersds.copy(data1);

            }
        });
       //Share Button
        btnshare.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                copyHandlersds.Share(Result_reverse.getText().toString());
            }
        });
        //Share Button
        btnshare_flip.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                copyHandlersds.Share(txt_flip_text.getText().toString());
            }
        });
        //Share Button
       btnshare_reverseflip.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                copyHandlersds.Share(txt_reverseflip_text.getText().toString());
            }
        });

        close.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int length = inputtext.getText().length();
                if (length > 0) {
                    inputtext.getText().delete(length - 1, length);
                }
            }
        });
        close.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                inputtext.getText().clear();
                return false;
            }
        });
        return view;

    }
    //Text Reverse
    public String revrsetext(String str) {
        StringBuffer stringBuffer = new StringBuffer(str);
        stringBuffer.reverse();
        return String.valueOf(stringBuffer);
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = (Activity) context;
        this.prf = new PrefManagersd(context);
    }
    //Text Flip and Reverse Flip
    public String convertString(String str) {
        String string = getString(R.string.normal_text);
        String string2 = getString(R.string.fliped_text);
        String str2 = "";
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            int indexOf = string.indexOf(charAt);
            StringBuilder sb = new StringBuilder();
            sb.append(str2);
            if (indexOf != -1) {
                charAt = string2.charAt(indexOf);
            }
            sb.append(charAt);
            str2 = sb.toString();
        }
        return new StringBuilder(str2).reverse().toString();
    }


}
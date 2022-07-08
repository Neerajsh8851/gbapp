
package com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.PrefManagersd;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.R;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.util.CopyHandlersds;

import es.dmoral.toasty.Toasty;


public class WhatsRepeatFragmentdsfdsf extends Fragment {

    //Prefrance
    public static PrefManagersd prf;

    public WhatsRepeatFragmentdsfdsf() {
    }

    EditText input, number;
    String text = "";
    Activity activity;
    TextView outputt;
    SwitchCompat switchCompat;
    ImageView close;

    //ads

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.whatsfragment_repeat, container, false);


        close = view.findViewById(R.id.close22);
        input =  view.findViewById(R.id.editText);
        number =  view.findViewById(R.id.number);
        switchCompat = view.findViewById(R.id.nLine);

        outputt =  view.findViewById(R.id.preview_text);
        ImageButton copy = view.findViewById(R.id.copy);
        ImageButton share = view.findViewById(R.id.share);
        Button button = view.findViewById(R.id.button);
        final CopyHandlersds copyHandlersds = new CopyHandlersds(activity);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                outputt.setText(text);
            }
        });
        //Copy Button
        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String value = outputt.getText().toString();
                copyHandlersds.copysi(value);
            }
        });
        //Share Button
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String value = outputt.getText().toString();
                copyHandlersds.Share(value);
            }
        });

        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
               textRepeat();
            }
        });
        //Text Delete From EditText
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int length = input.getText().length();
                if (length > 0) {
                    input.getText().delete(length - 1, length);
                }
            }
        });
        //All Text Delete From EditText
        close.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                input.getText().clear();
                return false;
            }
        });


        return view;
    }
      // Text Repeater System
    private void textRepeat() {
        String inputtext = input.getText().toString();
        String tempNumber = number.getText().toString();
        if (input.getText().toString().trim().isEmpty() || inputtext.isEmpty()) {
            Toasty.info(activity, R.string.toast, Toasty.LENGTH_LONG).show();
        } else {

            if (!tempNumber.equals("")) {
                int num = Integer.parseInt(number.getText().toString());
                text = "";
                for (int i = 0; i < num; i++) {
                    if (switchCompat.isChecked()) {
                        text = text + inputtext + "\n";
                    } else {
                        text = text + inputtext;
                    }
                }
            }
            outputt.setText(text);
        }
    }



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.activity = (Activity) context;
        this.prf = new PrefManagersd(context);
    }
}

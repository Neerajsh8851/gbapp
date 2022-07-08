package com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.PrefManagersd;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.R;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.Zalgoasd;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.util.CopyHandlersds;


public class GiltchFragmentsad extends Fragment {

    //Prefrance
    public static PrefManagersd prf;

    public GiltchFragmentsad() {
    }
    private EditText testTextField;
    private TextView previewText;
    private SeekBar intensitySlider;
    private Activity activity;
    private CheckBox upCheck;
    private CheckBox midCheck;
    private CheckBox downCheck;
    ImageView close;
    ImageButton copy,share;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.whatsfragment_glitch, container, false);

        testTextField = view.findViewById(R.id.editText);
        close = view.findViewById(R.id.close33);
        previewText = view.findViewById(R.id.preview_text);

        intensitySlider = view.findViewById(R.id.seekBar);
        copy = view.findViewById(R.id.copy);
        share = view.findViewById(R.id.share);

        upCheck = view.findViewById(R.id.upwards);
        midCheck = view.findViewById(R.id.middle);
        downCheck = view.findViewById(R.id.downwards);
        upCheck.setChecked(true);
        downCheck.setChecked(true);
        final CopyHandlersds copyHandlersds = new CopyHandlersds(activity);
        //Set listeners
        testTextField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                updatePreview();
            }
        });


        intensitySlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updatePreview();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        // Copy Button
        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copyHandlersds.copysi(makeZalgo());
            }
        });
        // Share Button
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copyHandlersds.Share(makeZalgo());
            }
        });
        upCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                updatePreview();
            }
        });
        midCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                updatePreview();
            }
        });
        downCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                updatePreview();
            }
        });
        //Text Delete from EditText
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int length = testTextField.getText().length();
                if (length > 0) {
                    testTextField.getText().delete(length - 1, length);
                }
            }
        });
        //All Text Delete from EditText
        close.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                testTextField.getText().clear();
                return false;
            }
        });

        return view;


    }

    private void updatePreview() {
        previewText.setText(getString(R.string.preview_text, makeZalgo()));
    }

    // Zalgo Text Maker
    private String makeZalgo() {
        String text = testTextField.getText().toString();

            int intUp = 0;
            int intMid = 0;
            int intDown = 0;

            int intensity = intensitySlider.getProgress() + 2;

            if (upCheck.isChecked()) {
                intUp = intensity;
            }
            if (midCheck.isChecked()) {
                intMid = intensity;
            }
            if (downCheck.isChecked()) {
                intDown = intensity;
            }

            return Zalgoasd.generate(text, intUp, intMid, intDown);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.activity = (Activity) context;
        this.prf = new PrefManagersd(context);
    }
}

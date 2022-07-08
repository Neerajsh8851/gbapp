package com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.stylish;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.FontsGeneratorsad;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.R;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.adapter.FloatingAdaptersad;

import java.util.ArrayList;


public class FloatingStylishService extends FloatingView implements TextWatcher {
    FontsGeneratorsad mGenerator;
    EditText mInput;
    RecyclerView mListResult;
    FloatingAdaptersad mAdapter;


    @NonNull
    @Override
    protected View inflateButton(@NonNull ViewGroup parent) {
        return LayoutInflater.from(getContext()).inflate(R.layout.whatsfloating_stylish_icon, parent, false);
    }

    @NonNull
    @Override
    protected View onCreateView(@NonNull ViewGroup parent) {
        mGenerator = new FontsGeneratorsad(getContext());

        View view = LayoutInflater.from(getContext()).inflate(R.layout.whatsfloating_stylish, parent, false);
        mInput = view.findViewById(R.id.edit_inputjj);
        mAdapter = new FloatingAdaptersad(getContext());
        mListResult = view.findViewById(R.id.recycler_viewjj);
        mListResult.setLayoutManager(new LinearLayoutManager(getContext()));
        mListResult.setAdapter(mAdapter);
        mInput.addTextChangedListener(this);
        convertText(mInput.getText().toString());
        return view;
    }

    @NonNull
    @Override
    protected Notification createNotification(String notificationChannel) {
        Intent intent = new Intent(this, FloatingStylishService.class).setAction(ACTION_OPEN);
        return new NotificationCompat.Builder(this, notificationChannel)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(getString(R.string.app_name))
                .setContentText(getString(R.string.tap))
                .setContentIntent(PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE))
                .setPriority(NotificationCompat.PRIORITY_MIN)
                .build();
    }


    private void convertText(String inp) {
        if (inp.isEmpty()) inp = "Fancy Text";
        ArrayList<String> translate = mGenerator.generate(inp);
        mAdapter.setData(translate);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        convertText(mInput.getText().toString());
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

}

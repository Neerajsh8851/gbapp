package com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.PrefManagersd;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.R;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.util.CopyHandlersds;

import java.io.IOException;
import java.io.InputStream;

import es.dmoral.toasty.Toasty;


public class WhatsEmojiFragmentasdsad extends Fragment implements View.OnClickListener {

    //Prefrance
    public static PrefManagersd prf;

    public WhatsEmojiFragmentasdsad() {
    }

    ImageView close;
    private Activity activity;
    public TextView text_TV;
    public EditText inputEditText;
    public String string;
    public String quo;
    public EditText pickemo;
    public TextView pickedemo;
    Button setEmo,Art;
    public int num;
    ImageButton share,copy;
    public boolean aBoolean;
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.whatsfragment_emoji, container, false);



        Art = view.findViewById(R.id.Art);
        close = view.findViewById(R.id.close22);
        text_TV = view.findViewById(R.id.text_TV);
        inputEditText = view.findViewById(R.id.inputEditText);
        pickemo = view.findViewById(R.id.pickemo);
        pickedemo = view.findViewById(R.id.pickedemo);
        setEmo = view.findViewById(R.id.setEmo);
        copy = view.findViewById(R.id.copyemo);
        copy.setOnClickListener(this);
        share = view.findViewById(R.id.shareemo);
        share.setOnClickListener(this);
        aBoolean = false;
        //Text Delete from edittext
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int length = inputEditText.getText().length();
                if (length > 0) {
                    inputEditText.getText().delete(length - 1, length);
                }
            }
        });
        //All Text Delete from edittext
        close.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                inputEditText.getText().clear();
                return false;
            }
        });
        //Text To Emoji Convert
        Art.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                num = 0;
                string = inputEditText.getText().toString();
                if (string.isEmpty()) {
                    Toasty.info(activity, "Please input some text", Toast.LENGTH_LONG).show();
                    return;
                }
                text_TV.setText(".\n");
                char[] charArray =string.toCharArray();
                for (char c : charArray) {
                    num = num + 1;
                    String str = "";
                    quo = "";
                    if (aBoolean) {
                        aBoolean = false;
                    } else {
                        if (c == '?') {
                            try {
                                InputStream open = activity.getAssets().open("ques.txt");
                                byte[] bArr = new byte[open.available()];
                                open.read(bArr);
                                open.close();
                                quo = new String(bArr).replaceAll("[*]",pickedemo.getText().toString());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            text_TV.append(quo + "\n\n");
                        }
                        if (c == '$') {
                            try {
                                InputStream open2 = activity.getAssets().open("dollar.txt");
                                byte[] bArr2 = new byte[open2.available()];
                                open2.read(bArr2);
                                open2.close();
                                quo = new String(bArr2).replaceAll("[*]",pickedemo.getText().toString());
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                            text_TV.append(quo + "\n\n");
                            quo = "";
                            aBoolean = true;
                        }
                        if (c == ',') {
                            try {
                                InputStream open3 = activity.getAssets().open("comma.txt");
                                byte[] bArr2 = new byte[open3.available()];
                                open3.read(bArr2);
                                open3.close();
                                quo = new String(bArr2).replaceAll("[*]",pickedemo.getText().toString());
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                            text_TV.append(quo + "\n\n");
                            quo = "";
                            aBoolean = true;
                        }
                        if (c == '=') {
                            try {
                                InputStream open4 = activity.getAssets().open("equals.txt");
                                byte[] bArr2 = new byte[open4.available()];
                                open4.read(bArr2);
                                open4.close();
                                quo = new String(bArr2).replaceAll("[*]",pickedemo.getText().toString());
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                            text_TV.append(quo + "\n\n");
                            quo = "";
                            aBoolean = true;
                        }
                        if (c == '!') {
                            try {
                                InputStream open5 = activity.getAssets().open("exclamation.txt");
                                byte[] bArr2 = new byte[open5.available()];
                                open5.read(bArr2);
                                open5.close();
                                quo = new String(bArr2).replaceAll("[*]",pickedemo.getText().toString());
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                            text_TV.append(quo + "\n\n");
                            quo = "";
                            aBoolean = true;
                        }
                        if (c == '-') {
                            try {
                                InputStream open6 = activity.getAssets().open("minus.txt");
                                byte[] bArr2 = new byte[open6.available()];
                                open6.read(bArr2);
                                open6.close();
                                quo = new String(bArr2).replaceAll("[*]",pickedemo.getText().toString());
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                            text_TV.append(quo + "\n\n");
                            quo = "";
                            aBoolean = true;
                        }
                        if (c == '"') {
                            try {
                                InputStream open7 = activity.getAssets().open("doublequotes.txt");
                                byte[] bArr2 = new byte[open7.available()];
                                open7.read(bArr2);
                                open7.close();
                                quo = new String(bArr2).replaceAll("[*]",pickedemo.getText().toString());
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                            text_TV.append(quo + "\n\n");
                            quo = "";
                            aBoolean = true;
                        }
                        if (c == ':') {
                            try {
                                InputStream open8 = activity.getAssets().open("colon.txt");
                                byte[] bArr2 = new byte[open8.available()];
                                open8.read(bArr2);
                                open8.close();
                                quo = new String(bArr2).replaceAll("[*]",pickedemo.getText().toString());
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                            text_TV.append(quo + "\n\n");
                            quo = "";
                            aBoolean = true;
                        }
                        if (c == '+') {
                            try {
                                InputStream open9 = activity.getAssets().open("plus.txt");
                                byte[] bArr2 = new byte[open9.available()];
                                open9.read(bArr2);
                                open9.close();
                                quo = new String(bArr2).replaceAll("[*]",pickedemo.getText().toString());
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                            text_TV.append(quo + "\n\n");
                            quo = "";
                            aBoolean = true;
                        }
                        if (c == '_') {
                            try {
                                InputStream open10 = activity.getAssets().open("underscore.txt");
                                byte[] bArr2 = new byte[open10.available()];
                                open10.read(bArr2);
                                open10.close();
                                quo = new String(bArr2).replaceAll("[*]",pickedemo.getText().toString());
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                            text_TV.append(quo + "\n\n");
                            quo = "";
                            aBoolean = true;
                        }
                        if (c == '*') {
                            try {
                                InputStream open11 = activity.getAssets().open("star.txt");
                                byte[] bArr2 = new byte[open11.available()];
                                open11.read(bArr2);
                                open11.close();
                                quo = new String(bArr2).replaceAll("[*]",pickedemo.getText().toString());
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                            text_TV.append(quo + "\n\n");
                            quo = "";
                            aBoolean = true;
                        }
                        if (c == '#') {
                            try {
                                InputStream open12 = activity.getAssets().open("hash.txt");
                                byte[] bArr2 = new byte[open12.available()];
                                open12.read(bArr2);
                                open12.close();
                                quo = new String(bArr2).replaceAll("[*]",pickedemo.getText().toString());
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                            text_TV.append(quo + "\n\n");
                            quo = "";
                            aBoolean = true;
                        }
                        if (c == '%') {
                            try {
                                InputStream open13 = activity.getAssets().open("modulo.txt");
                                byte[] bArr2 = new byte[open13.available()];
                                open13.read(bArr2);
                                open13.close();
                                quo = new String(bArr2).replaceAll("[*]",pickedemo.getText().toString());
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                            text_TV.append(quo + "\n\n");
                            quo = "";
                            aBoolean = true;
                        }
                        if (c == '&') {
                            try {
                                InputStream open14 = activity.getAssets().open("ampersand.txt");
                                byte[] bArr2 = new byte[open14.available()];
                                open14.read(bArr2);
                                open14.close();
                                quo = new String(bArr2).replaceAll("[*]",pickedemo.getText().toString());
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                            text_TV.append(quo + "\n\n");
                            quo = "";
                            aBoolean = true;
                        }
                        else if (c == ((char) (c & '_')) || Character.isDigit(c)) {
                            try {
                                InputStream open15 = activity.getAssets().open(c + ".txt");
                                byte[] bArr3 = new byte[open15.available()];
                                open15.read(bArr3);
                                open15.close();
                                quo = new String(bArr3).replaceAll("[*]",pickedemo.getText().toString());
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                            text_TV.append(quo + "\n\n");
                        } else {
                            try {
                                InputStream open16 = activity.getAssets().open("sml" + c + ".txt");
                                byte[] bArr4 = new byte[open16.available()];
                                open16.read(bArr4);
                                open16.close();
                                quo = new String(bArr4).replaceAll("[*]",pickedemo.getText().toString());
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                            text_TV.append(quo + "\n\n");
                        }
                    }
                }

            }
        });


         //Emoji Check or Change Emoji
        setEmo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String obj = pickemo.getText().toString();
                if (obj.isEmpty() || obj.matches("[\\x00-\\x7F]+")) {
                    Toasty.info(activity, "Please choose a emoji", Toast.LENGTH_LONG).show();
                } else {
                    pickedemo.setText(obj);
                }

            }
        });

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.activity = (Activity) context;
        this.prf = new PrefManagersd(context);
    }

    @Override
    public void onClick(View v) {
        final CopyHandlersds copyHandlersds = new CopyHandlersds(activity);
        final String data = text_TV.getText().toString();
        int id = v.getId();// Copy Button
        if (id == R.id.copyemo) {
            copyHandlersds.copysi(data);
            // Share Button
        } else if (id == R.id.shareemo) {
            copyHandlersds.Share(data);
        }
    }
}

package com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.PrefManagersd;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.R;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.util.BottomSheetdsfs;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.util.CopyHandlersds;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.util.NumStylesds;

import org.jetbrains.annotations.NotNull;

public class NumberAdapterdsf extends RecyclerView.Adapter<NumberAdapterdsf.MyViewHolder> {

    //Prefrance
    public static PrefManagersd prf;

    private Context context;
    private String name;
    String[][] number_style_arr;
    private String[] numberArr;
    int type;
    int value;


    public NumberAdapterdsf(Context context, String[] strArr, String[][] strArr2, String str, int i) {
        this.prf = new PrefManagersd(context);
        this.context = context;
        this.numberArr = strArr;
        this.number_style_arr = strArr2;
        this.name = str;
        this.type = i;
    }

    public void setName(String str, int i) {
        this.name = str;
        this.type = i;
        notifyDataSetChanged();
    }

    @NotNull
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(this, LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.whatsnum_item, viewGroup, false));
    }

    public void onBindViewHolder(@NotNull MyViewHolder myViewHolder, final int i) {
        myViewHolder.number.setText(String.valueOf(i + 1));
        value = i;
        final CopyHandlersds copyHandlersds = new CopyHandlersds(context);
        //Check which number is enter
        if (name.equalsIgnoreCase(" ")) {
            myViewHolder.numberStylish.setText(StyleMaker("0123456789".toLowerCase().toCharArray(), NumStylesds.numberStyle[i]));
            StringBuilder sb = new StringBuilder();
            sb.append(i + 1);
            sb.append("");
        } else if (!name.isEmpty()) {
            myViewHolder.numberStylish.setText(StyleMaker(this.name.toLowerCase().toCharArray(), NumStylesds.numberStyle[i]));
            StringBuilder sb2 = new StringBuilder();
            sb2.append(i + 1);
            sb2.append("");
        }
        final String data =StyleMaker(name.toLowerCase().toCharArray(), NumStylesds.numberStyle[i]);
        // Share Button
        myViewHolder.share_number.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                copyHandlersds.Share(data);
            }
        });
        // Copy Button
        myViewHolder.copy_number.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                copyHandlersds.copy(data);
            }
        });
        // Preview text Bottomsheet
        myViewHolder.layout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetdsfs bottomSheetdsfs = new BottomSheetdsfs();
                bottomSheetdsfs.styleBottom(context,data);
            }
        });
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView copy_number;
        TextView numberStylish,number;
        ImageView share_number;
        LinearLayout layout;

        public MyViewHolder(NumberAdapterdsf numberAdapterdsf, View view) {
            super(view);
            numberStylish = view.findViewById(R.id.textView);
            number = view.findViewById(R.id.txt_number);
            share_number = view.findViewById(R.id.share);
            copy_number = view.findViewById(R.id.Copy_stylish);
            layout = itemView.findViewById(R.id.linearclick);

        }
    }

    public int getItemCount() {
        return this.numberArr.length;
    }

    public long getItemId(int i) {
        return Long.parseLong(this.numberArr[i]);
    }
    // Stylish Number Maker
    private String StyleMaker(char[] cArr, String[] strArr) {
        StringBuilder stringBuffer = new StringBuilder();
        for (char c : cArr) {
            if (c - '0' >= 0 && c - '9' <= 10) {
                stringBuffer.append(strArr[c - '0']);
            }
        }
        return stringBuffer.toString();
    }
}

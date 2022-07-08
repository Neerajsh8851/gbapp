package com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.PrefManagersd;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.R;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.adapter.SheetAdapterdsaf;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.interfaces.RecyclerViewItemcc;

import java.util.ArrayList;
import java.util.Objects;

public  class BottomSheetdsfs {

    //Prefrance
    public static PrefManagersd prf;

    BottomSheetDialog bottomSheetDialog;


    public static void showDialogbox(final Context context, final EditText editText){

        prf = new PrefManagersd(context);

        BottomSheetDialog bottomSheetDialog;
        ImageButton delete, back;
        LayoutInflater inflater = LayoutInflater.from(context);
        @SuppressLint("InflateParams") final View view = inflater.inflate(R.layout.sheet_symbols, null);
        bottomSheetDialog = new BottomSheetDialog(context, R.style.BottomSheetDialog);
        bottomSheetDialog.setContentView(view);
        back = view.findViewById(R.id.back_delete);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int length = editText.getText().length();
                if (length > 0) {
                    editText.getText().delete(length - 1, length);
                }
            }
        });
        delete = view.findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.getText().clear();
            }
        });
        RadioGroup group = view.findViewById(R.id.radio_group_sym);
        group.check(R.id.rd1);
        final RecyclerView recyclerView = view.findViewById(R.id.recyclerviewSheet);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 5));
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rd1){
                    SheetAdapterdsaf sheetAdapterdsaf = new SheetAdapterdsaf(context, getSymbols(context.getString(R.string.SYM1)), new RecyclerViewItemcc() {
                        @Override
                        public void onItemClick(int position, String sym) {
                            editText.getEditableText().insert(Math.max(editText.getSelectionStart(), 0), sym);
                        }
                    });
                    recyclerView.setAdapter(sheetAdapterdsaf);
                }
                if (checkedId == R.id.rd2){
                    SheetAdapterdsaf sheet2Adapter = new SheetAdapterdsaf(context, getSymbols(context.getString(R.string.SYM2)), new RecyclerViewItemcc() {
                        @Override
                        public void onItemClick(int position, String sym) {
                            editText.append(sym);
                        }
                    });
                    recyclerView.setAdapter(sheet2Adapter);

                }
                if (checkedId == R.id.rd3){
                    SheetAdapterdsaf sheet2Adapter = new SheetAdapterdsaf(context, getSymbols(context.getString(R.string.SYM3)), new RecyclerViewItemcc() {
                        @Override
                        public void onItemClick(int position, String sym) {
                            editText.append(sym);
                        }
                    });
                    recyclerView.setAdapter(sheet2Adapter);

                }
                if (checkedId == R.id.rd4){
                    SheetAdapterdsaf sheet2Adapter = new SheetAdapterdsaf(context, getSymbols(context.getString(R.string.SYM4)), new RecyclerViewItemcc() {
                        @Override
                        public void onItemClick(int position, String sym) {
                            editText.append(sym);
                        }
                    });
                    recyclerView.setAdapter(sheet2Adapter);

                }
                if (checkedId == R.id.rd5){
                    SheetAdapterdsaf sheet2Adapter = new SheetAdapterdsaf(context, getSymbols(context.getString(R.string.SYM5)), new RecyclerViewItemcc() {
                        @Override
                        public void onItemClick(int position, String sym) {
                            editText.append(sym);
                        }
                    });
                    recyclerView.setAdapter(sheet2Adapter);

                }
                if (checkedId == R.id.rd6){
                    SheetAdapterdsaf sheet2Adapter = new SheetAdapterdsaf(context, getSymbols(context.getString(R.string.SYM6)), new RecyclerViewItemcc() {
                        @Override
                        public void onItemClick(int position, String sym) {
                            editText.append(sym);
                        }
                    });
                    recyclerView.setAdapter(sheet2Adapter);

                }
                if (checkedId == R.id.rd7){
                    SheetAdapterdsaf sheet2Adapter = new SheetAdapterdsaf(context, getSymbols(context.getString(R.string.SYM7)), new RecyclerViewItemcc() {
                        @Override
                        public void onItemClick(int position, String sym) {
                            editText.append(sym);
                        }
                    });
                    recyclerView.setAdapter(sheet2Adapter);

                }
                if (checkedId == R.id.rd8){
                    SheetAdapterdsaf sheet2Adapter = new SheetAdapterdsaf(context, getSymbols(context.getString(R.string.SYM8)), new RecyclerViewItemcc() {
                        @Override
                        public void onItemClick(int position, String sym) {
                            editText.append(sym);
                        }
                    });
                    recyclerView.setAdapter(sheet2Adapter);

                }
                if (checkedId == R.id.rd9){
                    SheetAdapterdsaf sheet2Adapter = new SheetAdapterdsaf(context, getSymbols(context.getString(R.string.SYM9)), new RecyclerViewItemcc() {
                        @Override
                        public void onItemClick(int position, String sym) {
                            editText.append(sym);
                        }
                    });
                    recyclerView.setAdapter(sheet2Adapter);

                }
                if (checkedId == R.id.rd10){
                    SheetAdapterdsaf sheet2Adapter = new SheetAdapterdsaf(context, getSymbols(context.getString(R.string.SYM10)), new RecyclerViewItemcc() {
                        @Override
                        public void onItemClick(int position, String sym) {
                            editText.getEditableText().insert(Math.max(editText.getSelectionStart(), 0), sym);
                        }
                    });
                    recyclerView.setAdapter(sheet2Adapter);

                }
                if (checkedId == R.id.rd11){
                    SheetAdapterdsaf sheet2Adapter = new SheetAdapterdsaf(context, getSymbols(context.getString(R.string.SYM11)), new RecyclerViewItemcc() {
                        @Override
                        public void onItemClick(int position, String sym) {
                            editText.append(sym);
                        }
                    });
                    recyclerView.setAdapter(sheet2Adapter);

                }
                if (checkedId == R.id.rd12){
                    SheetAdapterdsaf sheet2Adapter = new SheetAdapterdsaf(context, getSymbols(context.getString(R.string.SYM12)), new RecyclerViewItemcc() {
                        @Override
                        public void onItemClick(int position, String sym) {
                            editText.append(sym);
                        }
                    });
                    recyclerView.setAdapter(sheet2Adapter);

                }
                if (checkedId == R.id.rd13){
                    SheetAdapterdsaf sheet2Adapter = new SheetAdapterdsaf(context, getSymbols(context.getString(R.string.SYM13)), new RecyclerViewItemcc() {
                        @Override
                        public void onItemClick(int position, String sym) {
                            editText.append(sym);
                        }
                    });
                    recyclerView.setAdapter(sheet2Adapter);

                }
            }
        });

        SheetAdapterdsaf sheetAdapterdsaf = new SheetAdapterdsaf(context, getSymbols(context.getString(R.string.SYM1)), new RecyclerViewItemcc() {
            @Override
            public void onItemClick(int position, String sym) {
                editText.append(sym);
            }
        });
        recyclerView.setAdapter(sheetAdapterdsaf);
        Objects.requireNonNull(bottomSheetDialog.getWindow()).setDimAmount(0);
        bottomSheetDialog.show();

    }
    private static ArrayList<String> getSymbols(String ss)
    {
        String[] sym = ss.split(" ");
        ArrayList<String> list = new ArrayList<>();
        for (String s : sym) {
            if (s != null && !s.trim().equals(""))
                list.add(s);
        }

        return list;
    }
    public  void styleBottom(Context context, final String text){

        prf = new PrefManagersd(context);

        ImageButton back,share,copy;
        final CopyHandlersds copyHandlersds = new CopyHandlersds(context);
        bottomSheetDialog = new BottomSheetDialog(context, R.style.BottomSheetDialog);
        LayoutInflater inflater = LayoutInflater.from(context);
        @SuppressLint("InflateParams") View view=inflater.inflate(R.layout.whatsbottom_preview,null);
        TextView preview = view.findViewById(R.id.preview_font) ;
        back = view.findViewById(R.id.backbtn);
        share = view.findViewById(R.id.sharebtn);
        copy = view.findViewById(R.id.copybtn);
        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copyHandlersds.copy(text);
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copyHandlersds.Share(text);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.hide();
            }
        });
        preview.setText(text);
        bottomSheetDialog = new BottomSheetDialog(context);
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();
    }


}

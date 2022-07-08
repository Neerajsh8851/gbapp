package com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.PrefManagersd;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.R;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.adapter.StylerAdaptersd;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.interfaces.RecyclerViewItemcc;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.util.CopyHandlersds;

import java.util.Objects;

public class WhatsEmojiSheetFragmentdefd extends Fragment implements RecyclerViewItemcc {

    //Prefrance
    public static PrefManagersd prf;

  private Context context;
  private TabLayout layout;
  private RecyclerView recyclerView;
  private StylerAdaptersd sheetAdapter;
  private EditText editText;
  private String[] strings;
  ImageView close;
  FloatingActionButton button;

    //ads

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.whatsfragment_emoji_sheet, container, false);


        recyclerView = view.findViewById(R.id.rv_styles);
        editText = view.findViewById(R.id.edittx);
        layout = view.findViewById(R.id.tabScrollable);
        close = view.findViewById(R.id.close22);
        button = view.findViewById(R.id.copy);
        strings =getResources().getStringArray(R.array.emoji_activity);
        recyclerView.setLayoutManager(new GridLayoutManager(context,5));
        sheetAdapter = new StylerAdaptersd(context,1,strings,this);
        recyclerView.setAdapter(sheetAdapter);
        //Tab Change to Emoji Change
        layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (Objects.requireNonNull(layout.getTabAt(0)).isSelected()){
                    strings =getResources().getStringArray(R.array.emoji_activity);
                    sheetAdapter = new StylerAdaptersd(context, 1, strings, new RecyclerViewItemcc() {
                        @Override
                        public void onItemClick(int position, String sym) {
                               editText.getEditableText().append(sym);
                        }
                    });
                    recyclerView.setAdapter(sheetAdapter);
                }
                if (Objects.requireNonNull(layout.getTabAt(1)).isSelected()){
                    strings =getResources().getStringArray(R.array.emoji_animals);
                    sheetAdapter = new StylerAdaptersd(context, 1, strings, new RecyclerViewItemcc() {
                        @Override
                        public void onItemClick(int position, String sym) {
                            editText.getEditableText().append(sym);
                        }
                    });
                    recyclerView.setAdapter(sheetAdapter);
                }
                if (Objects.requireNonNull(layout.getTabAt(2)).isSelected()){
                    strings =getResources().getStringArray(R.array.characters);
                    sheetAdapter = new StylerAdaptersd(context, 1, strings, new RecyclerViewItemcc() {
                        @Override
                        public void onItemClick(int position, String sym) {
                            editText.getEditableText().append(sym);
                        }
                    });
                    recyclerView.setAdapter(sheetAdapter);
                }
                if (Objects.requireNonNull(layout.getTabAt(3)).isSelected()){
                    strings =getResources().getStringArray(R.array.emoji_food);
                    sheetAdapter = new StylerAdaptersd(context, 1, strings, new RecyclerViewItemcc() {
                        @Override
                        public void onItemClick(int position, String sym) {
                            editText.getEditableText().append(sym);
                        }
                    });
                    recyclerView.setAdapter(sheetAdapter);
                }
                if (Objects.requireNonNull(layout.getTabAt(4)).isSelected()){
                    strings =getResources().getStringArray(R.array.emoji_object);
                    sheetAdapter = new StylerAdaptersd(context, 1, strings, new RecyclerViewItemcc() {
                        @Override
                        public void onItemClick(int position, String sym) {
                            editText.getEditableText().append(sym);
                        }
                    });
                    recyclerView.setAdapter(sheetAdapter);
                }
                if (Objects.requireNonNull(layout.getTabAt(5)).isSelected()){
                    strings =getResources().getStringArray(R.array.emoji_people);
                    sheetAdapter = new StylerAdaptersd(context, 1, strings, new RecyclerViewItemcc() {
                        @Override
                        public void onItemClick(int position, String sym) {
                            editText.getEditableText().append(sym);
                        }
                    });
                    recyclerView.setAdapter(sheetAdapter);
                }
                if (Objects.requireNonNull(layout.getTabAt(6)).isSelected()){
                    strings =getResources().getStringArray(R.array.symbols);
                    sheetAdapter = new StylerAdaptersd(context, 1, strings, new RecyclerViewItemcc() {
                        @Override
                        public void onItemClick(int position, String sym) {
                            editText.getEditableText().append(sym);
                        }
                    });
                    recyclerView.setAdapter(sheetAdapter);
                }
                if (Objects.requireNonNull(layout.getTabAt(7)).isSelected()){
                    strings =getResources().getStringArray(R.array.emoji_travel);
                    sheetAdapter = new StylerAdaptersd(context, 1, strings, new RecyclerViewItemcc() {
                        @Override
                        public void onItemClick(int position, String sym) {
                            editText.getEditableText().append(sym);
                        }
                    });
                    recyclerView.setAdapter(sheetAdapter);
                }
                if (Objects.requireNonNull(layout.getTabAt(8)).isSelected()){
                    strings =getResources().getStringArray(R.array.emoji_flag);
                    sheetAdapter = new StylerAdaptersd(context, 1, strings, new RecyclerViewItemcc() {
                        @Override
                        public void onItemClick(int position, String sym) {
                            editText.getEditableText().append(sym);
                        }
                    });
                    recyclerView.setAdapter(sheetAdapter);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        //Fab Copy Button
       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               final String data = editText.getText().toString();
               final CopyHandlersds copyHandlersds = new CopyHandlersds(context);
               copyHandlersds.copy(data);
           }
       });

        //Text Delete from edittext
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int length = editText.getText().length();
                if (length > 0) {
                    editText.getText().delete(length - 1, length);
                }
            }
        });
        //All Text Delete from edittext
        close.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                editText.getText().clear();
                return false;
            }
        });


        return view;
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        this.prf = new PrefManagersd(context);
    }


    @Override
    public void onItemClick(int position, String sym) {
        editText.getEditableText().append(sym);
    }
}

package com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.FontsGeneratorsad;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.PrefManagersd;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.R;
import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.adapter.FontAdapterasd;

import java.util.ArrayList;


public class ProNameFragmentsadfsa extends Fragment implements TextWatcher, AdapterView.OnItemSelectedListener  {

    //Prefrance
    public static PrefManagersd prf;

    public ProNameFragmentsadfsa() {
    }

    private Context context;
    private FontsGeneratorsad mGenerator;
    private FontAdapterasd mAdapter;
    private EditText editText;
    RecyclerView fontsRV;
    ImageView close;
    AppCompatSpinner left,center,right;
    String leftem,centerem,rightem;
    String[] leftSymbol = {"","꧁ঔৣ☠︎", "꧁§༺⚔", "☆꧁༒☬", "꧁☆$", "༺࿕༒", "꧁ঔ", "༺✮•°◤", "꧁☬☠", "꧁༺۝༒♛", "3✞ঔৣ۝", "•༒꧂꧁༒•", "《《☆", "꧁༒•⪨", "꧁✨", "[ஜ۩۞۩ஜ", "꧁☬☆", "꧁☬༺", "꧁᭄", "🔫~", "꧁꧅", "꧁ད", "꧁༒❦​ေလး", "꧁༒•", "✘•☬★", "꧁༒☬❤️", "︻╦̵̵͇̿̿̿̿╤─", "꧁¢༒", "꧁༒☬★", "꧁༺", "꧁༒", "꧁༒☬☆•", "꧁☆☬", "◥꧁", "꧁༒☆•", "꧁༒☆", "˙·٠•●۩۩ஜ♦", "༒༺", "༒•༺", "꧁✭̶", "°•°《♡$", "★꧁༒☆•", "꧁ঔৣ✞", "◤◥༒☬", "꧁✿", "◥࿐", "●▬▬▬▬๑۩", "꧁༒☬⫷", "ஜ۞ஜ[", "➶☤༆✰", "꧁ঔৣ☬✞", "◦•●◉✿[", "●☆■°", "€»—————", "★꧁༒☆•", "꧁༒✮۝✰", "꧁༆", "˙·٠•●۩۩ஜ♦", "꧁☬༒", "۩༒•☬☆༻༺♛", "༺Ꭶॐ乂✰༒", "❣༺", "༼℘ᖫ", "乂✰", "☆꧁༒ ☬", "༺", "꧁༒☬", "☬", "◥ὦɧ◤", "ᴳᵒᵈ乡", "ᵈʳᵉᵃᵐ乡", "๖ۣۜҨž乡", "ƒさ→", "ᎮᏁ丶", "ɱ√ρ︵", "๖ACE✪", "ღᏠᎮღ", "꧁༺", "࿐", "ঔ", "ঌ", "♚", "♕", "✿", "❄", "❤", "ツ", "ッ", "☆", "➻❥", "❖︵", "‿✶", "︵✰", "✎﹏", "﹏✍", "︵❣", "﹏❣", "ミ★", "★彡", "๖", "▲", "◇", "◆", "☠", "❣", "➻", "✎", "☓", "ღ", "☂", "❦", "☗", "﹏", "❖", "۶", "✚", "ʚɞ", "╰☜", "☞╯", "✔", "✾", "♥", "♪", "●", "■", "✌", "☝", "☥", "ッ", "♜", "✟", "❖", "๖ۣۜ", "✦", "✼", "⊹⊱", "⊰⊹", "╰❥", "↭", "❛❜", "₆₇₈₉", "︵❻❾", "︵⁹", "︵¹⁰", "︵¹¹", "︵¹²", "︵¹⁶", "︵¹⁹", "︵²⁰", "︵³⁴", "︵³⁶", "︵⁸⁸", "︵⁹⁰", "︵⁹¹", "︵⁹²", "︵⁹³", "︵⁹⁴", "︵⁹⁵", "︵⁹⁶", "︵⁹⁷", "︵⁹⁸", "︵⁹⁹", "︵²⁰⁰⁰", "︵²⁰⁰¹", "︵²⁰⁰²", "︵²⁰⁰⁴", "︵²⁰⁰⁵", "₠", "™", "℠", "ᴾᴿᴼシ", "ᵛᶰシ", "๖²⁴ʱ", "『ᴹᵛᴸ"};
    String[] rightSymbol = {"","☠︎ঔৣ꧂™", "⚔༻§꧂", "☬༒꧂☆", "$☆꧂", "༒࿖༻", "ঔৣ꧂", "◥°•✮༻", "☠☬꧂", "♛༒۝༻꧂", "۝ঔৣ✞", "༒꧂꧁༒•", "☆》》", "⪩•༒꧂", "✨꧂", "ஜ۩۞۩ஜ]", "☆☬꧂", "༻☬꧂", "ℚ᭄꧂", "~🔫", "꧅꧂", "ཌ꧂", "​ေလး❦༒꧂", "•༒꧂", "★☬༻", "❤️☬༒꧂", "︻╦̵̵͇̿̿̿̿╤─", "༒¢꧂", "★ ☬༒꧂", "༻ᴳᵒᵈ꧂", "༒꧂", "•☆☬༒꧂", "☬☆꧂", "꧂◤", "•☆༒꧂", "★☬꧂", "♦ஜ۩۩●•٠·˙", "༻༒", "༻•༒", "✬꧂", "$♡》°•°", "•☆༒꧂★", "☬ঔৣ꧂", "☬༒◤◥", "✿꧂", "࿐◤", "۩๑▬▬▬▬●", "⫸☬༒꧂", "]ஜ۞ஜ", "✰༆☤➶", "✞☬ঔৣ꧂", "]✿◉●•◦", "°■☆●", "—————«€", "•☆༒꧂★", "✰۝✮༒꧂", "༆꧂", "♦ஜ۩۩●•٠·˙", "༒ ☬꧂", "♛༻༺☆☬•༒۩", "༒✰乂ॐ༻", "༻❣", "ᖭ℘༽", "✰乂", "☬༒꧂☆", "༻ᴳᵒᵈ", "☬༒꧂", "☬", "༻꧂", "࿐", "ঔ", "ঌ", "♚", "♕", "✿", "❄", "❤", "ツ", "ッ", "☆", "➻❥", "❖︵", "‿✶", "︵✰", "✎﹏", "﹏✍", "︵❣", "﹏❣", "ミ★", "★彡", "๖", "▲", "◇", "◆", "☠", "❣", "➻", "✎", "☓", "ღ", "☂", "❦", "☗", "﹏", "❖", "۶", "✚", "ʚɞ", "╰☜", "☞╯", "✔", "✾", "♥", "♪", "●", "■", "✌", "☝", "☥", "ッ", "♜", "✟", "❖", "๖ۣۜ", "✦", "✼", "⊹⊱", "⊰⊹", "╰❥", "↭", "❛❜", "₆₇₈₉", "︵❻❾", "︵⁹", "︵¹⁰", "︵¹¹", "︵¹²", "︵¹⁶", "︵¹⁹", "︵²⁰", "︵³⁴", "︵³⁶", "︵⁸⁸", "︵⁹⁰", "︵⁹¹", "︵⁹²", "︵⁹³", "︵⁹⁴", "︵⁹⁵", "︵⁹⁶", "︵⁹⁷", "︵⁹⁸", "︵⁹⁹", "︵²⁰⁰⁰", "︵²⁰⁰¹", "︵²⁰⁰²", "︵²⁰⁰⁴", "︵²⁰⁰⁵", "₠", "™", "℠", "ᴾᴿᴼシ", "ᵛᶰシ", "๖²⁴ʱ"};
    String[] centerSymbol = {"", "࿐", "ঔ", "ঌ", "♚", "♕", "✿", "❄", "❤", "ツ", "ッ", "☆", "➻❥", "❖︵", "‿✶", "︵✰", "✎﹏", "﹏✍", "︵❣", "﹏❣", "ミ★", "★彡", "๖", "▲", "◇", "◆", "☠", "❣", "➻", "✎", "☓", "ღ", "☂", "❦", "☗", "﹏", "❖", "۶", "✚", "ʚɞ", "╰☜", "☞╯", "✔", "✾", "♥", "♪", "●", "■", "✌", "☝", "☥", "ッ", "♜", "✟", "❖", "๖ۣۜ", "✦", "✼", "⊹⊱", "⊰⊹", "╰❥", "↭", "❛❜", "₆₇₈₉", "︵❻❾", "︵⁹", "︵¹⁰", "︵¹¹", "︵¹²", "︵¹⁶", "︵¹⁹", "︵²⁰", "︵³⁴", "︵³⁶", "︵⁸⁸", "︵⁹⁰", "︵⁹¹", "︵⁹²", "︵⁹³", "︵⁹⁴", "︵⁹⁵", "︵⁹⁶", "︵⁹⁷", "︵⁹⁸", "︵⁹⁹", "︵²⁰⁰⁰", "︵²⁰⁰¹", "︵²⁰⁰²", "︵²⁰⁰⁴", "︵²⁰⁰⁵", "₠", "™", "℠", "ᴾᴿᴼシ", "ᵛᶰシ", "๖²⁴ʱ"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.whatsfragment_pro_name, container, false);


        mGenerator = new FontsGeneratorsad(context);
        mAdapter = new FontAdapterasd(context);
        close = view.findViewById(R.id.closebtn);
        left = view.findViewById(R.id.spLeft);
        center = view.findViewById(R.id.spCenter);
        right = view.findViewById(R.id.spRight);
        leftem = "";
        centerem = "";
        rightem ="";
        fontsRV = view.findViewById(R.id.recycle_view_FF);
        fontsRV.setLayoutManager(new LinearLayoutManager(context));
        editText = view.findViewById(R.id.edit_text_FF);
        fontsRV.setHasFixedSize(true);
        fontsRV.setAdapter(mAdapter);

        editText.addTextChangedListener(this);
        //Text Delete from EditText
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int length = editText.getText().length();
                if (length > 0) {
                    editText.getText().delete(length - 1, length);
                }
            }
        });
        //All Text Delete from EditText
        close.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                editText.getText().clear();
                return false;
            }
        });
        left.setOnItemSelectedListener(this);
        right.setOnItemSelectedListener(this);
        center.setOnItemSelectedListener(this);
        ArrayAdapter<String> aa1 = new ArrayAdapter<>(context,android.R.layout.simple_spinner_item,leftSymbol);
        aa1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        left.setAdapter(aa1);
        ArrayAdapter<String> aa2 = new ArrayAdapter<>(context,android.R.layout.simple_spinner_item,rightSymbol);
        aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        right.setAdapter(aa2);
        ArrayAdapter<String> aa3 = new ArrayAdapter<>(context,android.R.layout.simple_spinner_item,centerSymbol);
        aa3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        center.setAdapter(aa3);
        left.setSelection(0);
        center.setSelection(0);
        right.setSelection(0);

        return view;
    }
    // Symbol Adder Left and Right
    private String adder(String str) {
        String d = merge(str);
        StringBuilder sb = new StringBuilder();
        sb.append(leftem);
        sb.append(d);
        sb.append(rightem);
        return sb.toString();
    }
    // Symbol Adder Center and Merge
    private String merge(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                sb.append("");
                sb.append(centerem);
            } else {
                sb.append(str.charAt(i));
            }
        }
        return sb.toString();
    }
     //Stylish Text Converter
    private void convertText(String inp) {
        if (inp.isEmpty()) inp = "Fancy Text";
        ArrayList<String> translate = mGenerator.generate(adder(inp));
        mAdapter.setData(translate);

    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        convertText(editText.getText().toString());
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        this.prf = new PrefManagersd(context);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
       if (parent == left){
           leftem = leftSymbol[position];
       }
       else if (parent == center){
           centerem = centerSymbol[position];
       }
       else if (parent == right){
           rightem = rightSymbol[position];
       }
       convertText(editText.getText().toString());

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
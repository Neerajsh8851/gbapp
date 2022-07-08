package com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew;

import android.content.Context;
import android.database.SQLException;

import com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.fragments.Constnbgf;

import java.io.IOException;

public class Fontsad {
	
	public static String[] rajan;
	public static String output = "R";

	public String[] init(Context context, int no) {
		
		// database handler
				Databasehelperfdsf myDbHelper = new Databasehelperfdsf(context);

				try {
					myDbHelper.createDatabase();
				} catch (IOException ioe) {
					throw new Error("Unable to create database");
				}
				try {
					myDbHelper.openDatabase();
				} catch (SQLException sqle) {
					throw sqle;
				}

				// Spinner Drop down elements
				rajan = myDbHelper.setfont(no);
				
				return rajan;
	}
	
	public String convertText(String inputtext, String[] myArray) {
		char[] inputtextarray = inputtext.toCharArray();
		String[] outputarray = new String[inputtextarray.length];
		String outputString = Constnbgf.styleable.FLAVOR;
		for (int i = 0; i < inputtextarray.length; i++) {
			outputarray[i] = replace(inputtextarray[i] + Constnbgf.styleable.FLAVOR, myArray);
		}
		for (String str : outputarray) {
			outputString = outputString + str;
		}
		return outputString;
	}
	
	private String replace(String c, String[] myArray_Rounded2) {
		int i = -1;

		System.out.println(c.hashCode() + "hash");
		switch (c.hashCode()) {
		case Constnbgf.styleable.Style_A /* 65 */:
			if (c.equals("A")) {
				i = 1;
				break;
			}
			break;
		case Constnbgf.styleable.Style_B /* 66 */:
			if (c.equals("B")) {
				i = 2;
				break;
			}
			break;
		case Constnbgf.styleable.Style_C /* 67 */:
			if (c.equals("C")) {
				i = 3;
				break;
			}
			break;
		case Constnbgf.styleable.Style_D /* 68 */:
			if (c.equals("D")) {
				i = 4;
				break;
			}
			break;
		case Constnbgf.styleable.Style_E /* 69 */:
			if (c.equals("E")) {
				i = 5;
				break;
			}
			break;
		case Constnbgf.styleable.Style_F /* 70 */:
			if (c.equals("F")) {
				i = 6;
				break;
			}
			break;
		case Constnbgf.styleable.Style_G /* 71 */:
			if (c.equals("G")) {
				i = 7;
				break;
			}
			break;
		case Constnbgf.styleable.Style_H /* 72 */:
			if (c.equals("H")) {
				i = 8;
				break;
			}
			break;
		case Constnbgf.styleable.Style_I /* 73 */:
			if (c.equals("I")) {
				i = 9;
				break;
			}
			break;
		case Constnbgf.styleable.Style_J /* 74 */:
			if (c.equals("J")) {
				i = 10;
				break;
			}
			break;
		case Constnbgf.styleable.Style_K /* 75 */:
			if (c.equals("K")) {
				i = 11;
				break;
			}
			break;
		case Constnbgf.styleable.Style_L /* 76 */:
			if (c.equals("L")) {
				i = 12;
				break;
			}
			break;
		case Constnbgf.styleable.Style_M /* 77 */:
			if (c.equals("M")) {
				i = 13;
				break;
			}
			break;
		case Constnbgf.styleable.Style_N /* 78 */:
			if (c.equals("N")) {
				i = 14;
				break;
			}
			break;
		case Constnbgf.styleable.Style_O /* 79 */:
			if (c.equals("O")) {
				i = 15;
				break;
			}
			break;
		case Constnbgf.styleable.Style_P /* 80 */:
			if (c.equals("P")) {
				i = 16;
				break;
			}
			break;
		case Constnbgf.styleable.Style_Q /* 81 */:
			if (c.equals("Q")) {
				i = 17;
				break;
			}
			break;
		case Constnbgf.styleable.Style_R /* 82 */:
			if (c.equals("R")) {
				i = 18;
				break;
			}
			break;
		case Constnbgf.styleable.Style_S /* 83 */:
			if (c.equals("S")) {
				i = 19;
				break;
			}
			break;
		case Constnbgf.styleable.Style_T /* 84 */:
			if (c.equals("T")) {
				i = 20;
				break;
			}
			break;
		case Constnbgf.styleable.Style_U /* 85 */:
			if (c.equals("U")) {
				i = 21;
				break;
			}
			break;
		case Constnbgf.styleable.Style_V /* 86 */:
			if (c.equals("V")) {
				i = 22;
				break;
			}
			break;
		case Constnbgf.styleable.Style_W /* 87 */:
			if (c.equals("W")) {
				i = 23;
				break;
			}
			break;
		case Constnbgf.styleable.Style_X /* 88 */:
			if (c.equals("X")) {
				i = 24;
				break;
			}
			break;
		case Constnbgf.styleable.Style_Y /* 89 */:
			if (c.equals("Y")) {
				i = 25;
				break;
			}
			break;
		case Constnbgf.styleable.Style_Z /* 90 */:
			if (c.equals("Z")) {
				i = 26;
				break;
			}
			break;
		case Constnbgf.styleable.Style_a /* 97 */:
			if (c.equals("a")) {
				i = 27;
				break;
			}
			break;
		case Constnbgf.styleable.Style_b /* 98 */:
			if (c.equals("b")) {
				i = 28;
				break;
			}
			break;
		case Constnbgf.styleable.Style_c /* 99 */:
			if (c.equals("c")) {
				i = 29;
				break;
			}
			break;
		case Constnbgf.styleable.Style_d /* 100 */:
			if (c.equals("d")) {
				i = 30;
				break;
			}
			break;
		case Constnbgf.styleable.Style_e /* 101 */:
			if (c.equals("e")) {
				i = 31;
				break;
			}
			break;
		case Constnbgf.styleable.Style_f /* 102 */:
			if (c.equals("f")) {
				i = 32;
				break;
			}
			break;
		case Constnbgf.styleable.Style_g /* 103 */:
			if (c.equals("g")) {
				i = 33;
				break;
			}
			break;
		case Constnbgf.styleable.Style_h /* 104 */:
			if (c.equals("h")) {
				i = 34;
				break;
			}
			break;
		case Constnbgf.styleable.Style_i /* 105 */:
			if (c.equals("i")) {
				i = 35;
				break;
			}
			break;
		case Constnbgf.styleable.Style_j /* 106 */:
			if (c.equals("j")) {
				i = 36;
				break;
			}
			break;
		case Constnbgf.styleable.Style_k /* 107 */:
			if (c.equals("k")) {
				i = 37;
				break;
			}
			break;
		case Constnbgf.styleable.Style_l /* 108 */:
			if (c.equals("l")) {
				i = 38;
				break;
			}
			break;
		case Constnbgf.styleable.Style_m /* 109 */:
			if (c.equals("m")) {
				i = 39;
				break;
			}
			break;
		case Constnbgf.styleable.Style_n /* 110 */:
			if (c.equals("n")) {
				i = 40;
				break;
			}
			break;
		case Constnbgf.styleable.Style_o /* 111 */:
			if (c.equals("o")) {
				i = 41;
				break;
			}
			break;
		case Constnbgf.styleable.Style_p /* 112 */:
			if (c.equals("p")) {
				i = 42;
				break;
			}
			break;
		case Constnbgf.styleable.Style_q /* 113 */:
			if (c.equals("q")) {
				i = 43;
				break;
			}
			break;
		case Constnbgf.styleable.Style_r /* 114 */:
			if (c.equals("r")) {
				i = 44;
				break;
			}
			break;
		case Constnbgf.styleable.Style_s /* 115 */:
			if (c.equals("s")) {
				i = 45;
				break;
			}
			break;
		case Constnbgf.styleable.Style_t /* 116 */:
			if (c.equals("t")) {
				i = 46;
				break;
			}
			break;
		case Constnbgf.styleable.Style_u /* 117 */:
			if (c.equals("u")) {
				i = 47;
				break;
			}
			break;
		case Constnbgf.styleable.Style_v /* 118 */:
			if (c.equals("v")) {
				i = 48;
				break;
			}
			break;
		case Constnbgf.styleable.Style_w /* 119 */:
			if (c.equals("w")) {
				i = 49;
				break;
			}
			break;
		case Constnbgf.styleable.Style_x /* 120 */:
			if (c.equals("x")) {
				i = 50;
				break;
			}
			break;
		case Constnbgf.styleable.Style_y /* 121 */:
			if (c.equals("y")) {
				i = 51;
				break;
			}
			break;
		case Constnbgf.styleable.Style_z /* 122 */:
			if (c.equals("z")) {
				i = 52;
				break;
			}
			break;
		case Constnbgf.styleable.Style_0 /* 48 */:
			if (c.equals("0")) {
				i = 53;
				break;
			}
			break;
		case Constnbgf.styleable.Style_1 /* 49 */:
			if (c.equals("1")) {
				i = 54;
				break;
			}
			break;
		case Constnbgf.styleable.Style_2 /* 50 */:
			if (c.equals("2")) {
				i = 55;
				break;
			}
			break;
		case Constnbgf.styleable.Style_3 /* 51 */:
			if (c.equals("3")) {
				i = 56;
				break;
			}
			break;
		case Constnbgf.styleable.Style_4 /* 52 */:
			if (c.equals("4")) {
				i = 57;
				break;
			}
			break;
		case Constnbgf.styleable.Style_5 /* 53 */:
			if (c.equals("5")) {
				i = 58;
				break;
			}
			break;
		case Constnbgf.styleable.Style_6 /* 54 */:
			if (c.equals("6")) {
				i = 59;
				break;
			}
			break;
		case Constnbgf.styleable.Style_7 /* 55 */:
			if (c.equals("7")) {
				i = 60;
				break;
			}
			break;
		case Constnbgf.styleable.Style_8 /* 56 */:
			if (c.equals("8")) {
				i = 61;
				break;
			}
			break;
		case Constnbgf.styleable.Style_9 /* 57 */:
			if (c.equals("9")) {
				i = 62;
				break;
			}
			break;
		}
		System.out.println(i + "IIIIIII");

		switch (i) {
		case 0 /* 0 */:
		case 1 /* 1 */:
			return myArray_Rounded2[0];
		case 2 /* 2 */:
			return myArray_Rounded2[1];
		case 3 /* 3 */:
			return myArray_Rounded2[2];
		case 4 /* 4 */:
			return myArray_Rounded2[3];
		case 5 /* 5 */:
			return myArray_Rounded2[4];
		case 6 /* 6 */:
			return myArray_Rounded2[5];
		case 7 /* 7 */:
			return myArray_Rounded2[6];
		case 8 /* 8 */:
			return myArray_Rounded2[7];
		case 9 /* 9 */:
			return myArray_Rounded2[8];
		case 10 /* 10 */:
			return myArray_Rounded2[9];
		case 11 /* 11 */:
			return myArray_Rounded2[10];
		case 12 /* 12 */:
			return myArray_Rounded2[11];
		case 13 /* 13 */:
			return myArray_Rounded2[12];
		case 14 /* 14 */:
			return myArray_Rounded2[13];
		case 15 /* 15 */:
			return myArray_Rounded2[14];
		case 16 /* 16 */:
			return myArray_Rounded2[15];
		case 17 /* 17 */:
			return myArray_Rounded2[16];
		case 18 /* 18 */:
			return myArray_Rounded2[17];
		case 19 /* 19 */:
			return myArray_Rounded2[18];
		case 20 /* 20 */:
			return myArray_Rounded2[19];
		case 21 /* 21 */:
			return myArray_Rounded2[20];
		case 22 /* 22 */:
			return myArray_Rounded2[21];
		case 23 /* 23 */:
			return myArray_Rounded2[22];
		case 24 /* 24 */:
			return myArray_Rounded2[23];
		case 25 /* 25 */:
			return myArray_Rounded2[24];
		case 26 /* 26 */:
			return myArray_Rounded2[25];
		case 27 /* 27 */:
			return myArray_Rounded2[26];
		case 28 /* 28 */:
			return myArray_Rounded2[27];
		case 29 /* 29 */:
			return myArray_Rounded2[28];
		case 30 /* 30 */:
			return myArray_Rounded2[29];
		case 31 /* 31 */:
			return myArray_Rounded2[30];
		case 32 /* 32 */:
			return myArray_Rounded2[31];
		case 33 /* 33 */:
			return myArray_Rounded2[32];
		case 34 /* 34 */:
			return myArray_Rounded2[33];
		case 35 /* 35 */:
			return myArray_Rounded2[34];
		case 36 /* 36 */:
			return myArray_Rounded2[35];
		case 37 /* 37 */:
			return myArray_Rounded2[36];
		case 38 /* 38 */:
			return myArray_Rounded2[37];
		case 39 /* 39 */:
			return myArray_Rounded2[38];
		case 40 /* 40 */:
			return myArray_Rounded2[39];
		case 41 /* 41 */:
			return myArray_Rounded2[40];
		case 42 /* 42 */:
			return myArray_Rounded2[41];
		case 43 /* 43 */:
			return myArray_Rounded2[42];
		case 44 /* 44 */:
			return myArray_Rounded2[43];
		case 45 /* 45 */:
			return myArray_Rounded2[44];
		case 46 /* 46 */:
			return myArray_Rounded2[45];
		case 47 /* 47 */:
			return myArray_Rounded2[46];
		case 48 /* 48 */:
			return myArray_Rounded2[47];
		case 49 /* 49 */:
			return myArray_Rounded2[48];
		case 50 /* 50 */:
			return myArray_Rounded2[49];
		case 51 /* 51 */:
			return myArray_Rounded2[50];
		case 52 /* 52 */:
			return myArray_Rounded2[51];
		case 53 /* 53 */:
			return myArray_Rounded2[52];
		case 54 /* 54 */:
			return myArray_Rounded2[53];
		case 55 /* 55 */:
			return myArray_Rounded2[54];
		case 56 /* 56 */:
			return myArray_Rounded2[55];
		case 57 /* 57 */:
			return myArray_Rounded2[56];
		case 58 /* 58 */:
			return myArray_Rounded2[57];
		case 59 /* 59 */:
			return myArray_Rounded2[58];
		case 60 /* 60 */:
			return myArray_Rounded2[59];
		case 61 /* 61 */:
			return myArray_Rounded2[60];
		case 62 /* 62 */:
			return myArray_Rounded2[61];
		default:
			return c;
		}
	}

}

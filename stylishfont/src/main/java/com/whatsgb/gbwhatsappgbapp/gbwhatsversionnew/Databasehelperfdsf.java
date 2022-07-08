package com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Databasehelperfdsf extends SQLiteOpenHelper {
	private SQLiteDatabase myDataBase;
	private final Context myContext;
	private static final String DATABASE_NAME = "fontslite.sqlite";
	public static String DATABASE_PATH = "";
	public static final int DATABASE_VERSION = 1;

	// Labels table name
	private static final String TABLE_LABELS = "styletype";
	private static final String TABLE_LABELSD = "decoration";

	// public static final int DATABASE_VERSION_old = 1;
	// Constructor

	public Databasehelperfdsf(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.myContext = context;
		DATABASE_PATH = myContext.getDatabasePath(DATABASE_NAME).toString();
	}

	// Create a empty database on the system
	public void createDatabase() throws IOException {
		boolean dbExist = checkDataBase();
		if (dbExist) {
			Log.v("DB Exists", "db exists");
			// By calling this method here onUpgrade will be called on a
			// writeable database, but only if the version number has been
			// bumped
			// onUpgrade(myDataBase, DATABASE_VERSION_old, DATABASE_VERSION);
		}
		boolean dbExist1 = checkDataBase();
		if (!dbExist1) {
			System.out.println("Rajan_readable" + this.getReadableDatabase());
			this.getReadableDatabase();
			try {
				this.close();
				copyDataBase();
			} catch (IOException e) {
				throw new Error("Error copying database");
			}
		}
	}

	// Check database already exist or not
	private boolean checkDataBase() {
		boolean checkDB = false;
		try {
			String myPath = DATABASE_PATH;
			File dbfile = new File(myPath);
			checkDB = dbfile.exists();
			System.out.println("Rajan_dataexist" + checkDB);
		} catch (SQLiteException e) {
		}
		return checkDB;
	}

	// Copies your database from your local assets-folder to the just created
	// empty database in the system folder

	private void copyDataBase() throws IOException {
		String outFileName = DATABASE_PATH;
		OutputStream myOutput = new FileOutputStream(outFileName);
		InputStream myInput = myContext.getAssets().open(DATABASE_NAME);
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer)) > 0) {
			myOutput.write(buffer, 0, length);
		}
		myInput.close();
		myOutput.flush();
		myOutput.close();
	}

	// delete database
	public void db_delete() {
		File file = new File(DATABASE_PATH);
		if (file.exists()) {
			file.delete();
			System.out.println("delete database file.");
		}
	}

	/**
	 * Getting all labels returns list of labels
	 */
	public ArrayList<String> getAllLabels() {
		String myPath = DATABASE_PATH;
		ArrayList<String> labels = new ArrayList<>();

		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_LABELS;

		SQLiteDatabase checkDB = null;

		try {
			checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

		} catch (SQLiteException e) {

			// database does't exist yet.
			System.out.println("Rajj");

		}

		// if (checkDB != null) {
		//
		// checkDB.close();
		//
		// }
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = checkDB.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				System.out.println("Raj" + cursor.getString(1));
				labels.add(cursor.getString(2));
			} while (cursor.moveToNext());
		}

		// closing connection
		cursor.close();
		checkDB.close();

		// returning lables
		return labels;
	}
	
	/**
	 * Getting all labels returns list of labels
	 */
	public ArrayList<String> getAllLabelsd() {
		String myPath = DATABASE_PATH;
		ArrayList<String> labels = new ArrayList<String>();

		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_LABELSD;

		SQLiteDatabase checkDB = null;

		try {
			checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

		} catch (SQLiteException e) {

			// database does't exist yet.
			System.out.println("Rajj");

		}

		// if (checkDB != null) {
		//
		// checkDB.close();
		//
		// }
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = checkDB.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				System.out.println("Rajsmy" + cursor.getString(1));
				labels.add(cursor.getString(1));
			} while (cursor.moveToNext());
		}

		// closing connection
		cursor.close();
		checkDB.close();

		// returning lables
		return labels;
	}

	// Open database
	public String[] openDatabase() throws SQLException {
		String myPath = DATABASE_PATH;
		System.out.println("Rajan_datapath" + myPath);
		myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);

		List<String> list = new ArrayList<String>();
		Cursor cursor = myDataBase.rawQuery("SELECT * FROM textstyle", null);

		String[] array = new String[cursor.getCount()];
		int i = 0;

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {

			String str = cursor.getString(2).toString();
			System.out.println("Rajan_data" + str);
			array[i] = str;
			i++;

			int unicode = (int) cursor.getString(2).charAt(0);
			System.out.println("Rajan_char" + unicode);

			list.add(cursor.getString(0));
			cursor.moveToNext();
		}
		System.out.println("Rajan_StringArray" + Arrays.toString(array));
		cursor.close();
		return array;
	}
	
	// Open database
		public String[] setfont(int no) throws SQLException {
			String myPath = DATABASE_PATH;
			myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);

			Cursor cursor = myDataBase.rawQuery("SELECT style"+no+" FROM textstyle", null);
			
			String[] array = new String[cursor.getCount()];
			int i = 0;

			cursor.moveToFirst();
			while (!cursor.isAfterLast()) {

				String str = cursor.getString(0).toString();
				array[i] = str;				
				i++;

				cursor.moveToNext();
			}
			System.out.println("Rajan_StringArray" + Arrays.toString(array));
			cursor.close();
						
			return array;
		}

	public synchronized void closeDataBase() throws SQLException {
		if (myDataBase != null)
			myDataBase.close();
		super.close();
	}

	public void onCreate(SQLiteDatabase db) {
	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (newVersion > oldVersion) {
			Log.v("Database Upgrade", "Database version higher than old.");
			db_delete();
		}
	}
	// add your public methods for insert, get, delete and update data in
	// database.
}

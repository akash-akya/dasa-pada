/*
 * KRUTHI. An application for Android users, it contains kannada KRUTHIs
 * Copyright (c) 2016. akash
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.akash.dasapada.dbUtil;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.firebase.crash.FirebaseCrash;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class MainDbHelper extends SQLiteOpenHelper implements Serializable, DatabaseReadAccess {
    private static final String TAG = "MainDbHelper";
    public static final String DATABASE_NAME = "main.db";
    private static final String ZIP_FILE_NAME = DATABASE_NAME+".zip";
    public static final int MAX_KATHRU = 129;

    private String DB_PATH;
    public static final int DATABASE_VERSION = 2;

    public static final String TABLE_KATHRU = "Kathru";
    public static final String KEY_KATHRU_ID = "Id";
    public static final String KEY_NAME = "Name";
    public static final String KEY_ANKITHA = "Ankitha";
    public static final String KEY_NUMBER  = "Num";
    public static final String KEY_DETAILS = "Details";

    ////// Pada Table
    public static final String TABLE_KRUTHI = "Kruthi";
    public static final String KEY_KRUTHI_ID = "Id";
    public static final String KEY_KRUTHI_TEXT = "Txt";
    public static final String KEY_TITLE = "Title";
    public static final String FOREIGN_KEY_KATHRU_ID = "KathruId";
    public static final String KEY_FAVORITE = "Favorite";

    //// Kathru Details Table0
//    private static final String TABLE_KATHRU_DETAILS = "KathruDetails";
    private static final String KEY_SIBLINGS = "Siblings";
    private static final String KEY_BIRTH_PLACE = "Birth_place";
    private static final String KEY_MOTHER = "Mother";
    private static final String KEY_PROVINCE = "Province";
    private static final String KEY_DEATH = "Death";
    private static final String KEY_VILLAGE = "Village";
    private static final String KEY_FATHER = "Father";
    private static final String KEY_TIME = "Time";
    private static final String KEY_OTHER_WORKS = "Other_works";
    private static final String KEY_CHILDREN = "Children";
    private static final String KEY_WORK = "Work";
    private static final String KEY_SPOUSE = "Spouse";
    private static final String KEY_AVAILABLE_KRUTHI = "Available_KRUTHI";
    private static final String KEY_TALUK = "Taluk";
    private static final String KEY_SPECIALITY = "Speciality";

    private Context mContext;
    private SQLiteDatabase mDataBase;

    public MainDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        if (android.os.Build.VERSION.SDK_INT >= 4.2) {
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        } else {
            DB_PATH =  "/data/data/" + context.getPackageName() + "/databases/";
        }
        mContext = context;
    }


    /**
     * Creates a empty database on the system and rewrites it with your own
     * database.
     */
    public void getDataBase() throws IOException {

        // If database not exists copy it from the assets
        boolean mDataBaseExist = checkDataBase();
        if (!mDataBaseExist) {
            this.getReadableDatabase();
            this.close();
            try {
                // Copy the database from assests
                copyDataBase();
                Log.e("DataBaseHelper", "createDatabase database created");
            } catch (IOException mIOException) {
                throw new Error("Error Copying DataBase");
            }
        }
    }

    /**
     * Check if the database already exist to avoid re-copying the file each
     * time you open the application.
     *
     * @return true if it exists, false if it doesn't
     */

    //Check that the database exists here: /data/data/your package/databases/Da Name
    public boolean checkDataBase() {
        File dbFile = new File(DB_PATH + DATABASE_NAME);
        return dbFile.exists();
    }


    /**
     * Copies your database from your local assets-folder to the just created
     * empty database in the system folder, from where it can be accessed and
     * handled. This is done by transfering bytestream.
     */
    private void copyDataBase() throws IOException {
        InputStream mInput = mContext.getAssets().open(ZIP_FILE_NAME);
        File dbPath = new File(DB_PATH);
        unzip(mInput, dbPath);
    }

    public static void unzip(InputStream zipFileStream, File targetDirectory) throws IOException {
        ZipInputStream zis = new ZipInputStream(
                new BufferedInputStream(zipFileStream));
        try {
            ZipEntry ze;
            int count;
            byte[] buffer = new byte[8192];
            while ((ze = zis.getNextEntry()) != null) {
                File file = new File(targetDirectory, ze.getName());
                File dir = ze.isDirectory() ? file : file.getParentFile();
                if (!dir.isDirectory() && !dir.mkdirs())
                    throw new FileNotFoundException("Failed to ensure directory: " +
                            dir.getAbsolutePath());
                if (ze.isDirectory())
                    continue;
                FileOutputStream fout = new FileOutputStream(file);
                try {
                    while ((count = zis.read(buffer)) != -1)
                        fout.write(buffer, 0, count);
                } finally {
                    fout.close();
                }
            }
        } finally {
            zis.close();
        }
    }

    public void openDataBase() throws SQLException {
        // Open the database
        String myPath = DB_PATH + DATABASE_NAME;
        mDataBase = SQLiteDatabase.openDatabase(myPath, null,
                SQLiteDatabase.OPEN_READONLY);

    }

    public synchronized void close() {

        if (mDataBase != null)
            mDataBase.close();

        super.close();

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
/*
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_KATHRU + "("
                + KEY_KATHRU_ID+ " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_ANKITHA + "TEXT,"
                + KEY_NUMBER + "INT,"
                + KEY_DETAILS + "TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);
*/
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Drop older table if existed
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_KATHRU);
        // Create tables again
        onCreate(sqLiteDatabase);
    }

    @Override
    public KathruMini getKathruMiniById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_KATHRU, new String[] { KEY_KATHRU_ID,
                        KEY_NAME, KEY_ANKITHA, KEY_NUMBER, KEY_FAVORITE },
                KEY_KATHRU_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        assert cursor != null;
        String name = cursor.getString(1);

        KathruMini kathruMini = new KathruMini(Integer.parseInt(cursor.getString(0)), name, cursor.getString(2),
                cursor.getInt(3), cursor.getInt(4));

        cursor.close();

        return kathruMini;

    }

    @Override
    public ArrayList<KathruMini> getAllKathruMinis(){
        ArrayList<KathruMini> contactList = new ArrayList<>();
        String selectQuery = "SELECT " +
                KEY_KATHRU_ID + ", " +
                KEY_NAME + ", " +
                KEY_ANKITHA + ", " +
                KEY_NUMBER + ", " +
                KEY_FAVORITE +
                " FROM " + TABLE_KATHRU + " ORDER BY Name";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                String ankitha = cursor.getString(2);
                int num = cursor.getInt(3);
                int favorite = cursor.getInt(4);

                KathruMini contact = new KathruMini(id, name, ankitha, num, favorite);

                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return contactList;
    }

    /*@Override
    public ArrayList<PadaMini> getKRUTHIMinisByKathruId (int kathruId, String kathruName) {
        ArrayList<PadaMini> KRUTHIMinis = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_KRUTHI, new String[] { KEY_KRUTHI_ID, KEY_TITLE, KEY_FAVORITE},
                FOREIGN_KEY_KATHRU_ID + "=?",
                new String[] { String.valueOf(kathruId) }, null, null, KEY_TITLE, null);

        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String title = cursor.getString(1);
                PadaMini KRUTHIMini = new PadaMini(id, kathruId, kathruName, title, cursor.getInt(2));
                KRUTHIMinis.add(KRUTHIMini);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return KRUTHIMinis;
    }*/

    @Override
    public ArrayList<PadaMini> getPadaMinisByKathruId (int kathruId) {
        String kathruName = getKathruNameById(kathruId);
        ArrayList<PadaMini> padaMinis = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_KRUTHI, new String[] { KEY_KRUTHI_ID, KEY_TITLE, KEY_FAVORITE},
                FOREIGN_KEY_KATHRU_ID + "=?",
                new String[] { String.valueOf(kathruId) }, null, null, KEY_TITLE, null);

        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String title = cursor.getString(1);
                PadaMini padaMini = new PadaMini(id, kathruId, kathruName, title, cursor.getInt(2));
                padaMinis.add(padaMini);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return padaMinis;
    }

    @Override
    public String getKathruNameById(int kathruId) { return getKathruMiniById(kathruId).getName(); }

    @Override
    public ArrayList<PadaMini> searchForPada(String text, String kathruName, boolean isPartialSearch) {
        long startTime = System.currentTimeMillis();
        long t;
        ArrayList<PadaMini> padaMinis = new ArrayList<>();
        String query_text = "SELECT " +
                MainDbHelper.KEY_KRUTHI_ID + ", "+
                MainDbHelper.KEY_TITLE + ", "+
                MainDbHelper.FOREIGN_KEY_KATHRU_ID + ", "+
                MainDbHelper.KEY_FAVORITE;

        String[] parameters;

        query_text += " FROM " + MainDbHelper.TABLE_KRUTHI;
        query_text += " WHERE " +
                MainDbHelper.KEY_KRUTHI_TEXT + " LIKE ? "; // + "%"+query+"%";

        String query_text_parameter = isPartialSearch? "%"+text+"%" : text;
        if (!kathruName.isEmpty()) {
            query_text += " AND " +
                    MainDbHelper.FOREIGN_KEY_KATHRU_ID + " IN " +
                    " ( SELECT " + MainDbHelper.KEY_KATHRU_ID +
                    " FROM " + MainDbHelper.TABLE_KATHRU +
                    " WHERE " + MainDbHelper.KEY_NAME + " LIKE ? )"; // + "%"+kathruString+"% ) ";
            parameters = new  String[]{query_text_parameter, kathruName};
        } else {
            parameters = new  String[]{query_text_parameter};
        }

        query_text += " = 1 ORDER BY "+MainDbHelper.KEY_TITLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query_text, parameters);

        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String title = cursor.getString(1);
                int kathruId = cursor.getInt(2);
                PadaMini padaMini = new PadaMini(id, kathruId, getKathruNameById(kathruId),
                        title, cursor.getInt(3));
                padaMinis.add(padaMini);
            } while (cursor.moveToNext());
        }
        cursor.close();

        t = System.currentTimeMillis();
        FirebaseCrash.log("searchForKRUTHI: String - "+ text + " Time - "+(t-startTime) + " Records - "+cursor.getCount());
        return padaMinis;
    }

    @Override
    public Pada getPada(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_KRUTHI, new String[] { KEY_KRUTHI_ID, KEY_KRUTHI_TEXT, FOREIGN_KEY_KATHRU_ID,
                        KEY_FAVORITE},
                KEY_KRUTHI_ID+ "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        assert cursor != null;
        String text = cursor.getString(1);
        int kathruId = Integer.parseInt(cursor.getString(2));
        Pada pada = new Pada(id, text, getKathruNameById(kathruId), cursor.getInt(3) == 1, kathruId);
        cursor.close();
        return pada;
    }

    public void addPadaToFavorite(int KRUTHIId){
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues newValues = new ContentValues();
        newValues.put(KEY_FAVORITE, 1);

        String[] args = new String[]{String.valueOf(KRUTHIId)};
        db.update(TABLE_KRUTHI, newValues, KEY_KRUTHI_ID+"=?", args);
    }

    public void removePadaFromFavorite(int KRUTHIId){
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues newValues = new ContentValues();
        newValues.put(KEY_FAVORITE, 0);

        String[] args = new String[]{String.valueOf(KRUTHIId)};
        db.update(TABLE_KRUTHI, newValues, KEY_KRUTHI_ID+"=?", args);
    }

    @Override
    public ArrayList<PadaMini> getFavoritePadaMinis() {
        ArrayList<PadaMini> padaMinis = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_KRUTHI, new String[] { KEY_KRUTHI_ID, KEY_TITLE, FOREIGN_KEY_KATHRU_ID},
                KEY_FAVORITE + "=?",
                new String[] { String.valueOf(1) }, null, null, KEY_TITLE, null);

        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String title = cursor.getString(1);
                int kathruId = cursor.getInt(2);
                PadaMini padaMini = new PadaMini(id, kathruId, getKathruNameById(kathruId),
                        title, 1);
                padaMinis.add(padaMini);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return padaMinis;
    }

    @Override
    public ArrayList<KathruMini> getFavoriteKathruMinis() {
        ArrayList<KathruMini> kathruMinis = new ArrayList<>();
        String selectQuery = "SELECT " +
                KEY_KATHRU_ID + ", " +
                KEY_NAME + ", " +
                KEY_ANKITHA + ", " +
                KEY_NUMBER + ", " +
                KEY_FAVORITE +
                " FROM " + TABLE_KATHRU + " WHERE " + KEY_FAVORITE +
                " = 1 ORDER BY Name";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                String ankitha = cursor.getString(2);
                int num = cursor.getInt(3);
                int favorite = cursor.getInt(4);

                KathruMini contact = new KathruMini(id, name, ankitha, num, favorite);

                kathruMinis.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return kathruMinis;
    }

    public void addKathruToFavorite(int kathruId){
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues newValues = new ContentValues();
        newValues.put(KEY_FAVORITE, 1);

        String[] args = new String[]{String.valueOf(kathruId)};
        db.update(TABLE_KATHRU, newValues, KEY_KATHRU_ID+"=?", args);
    }

    public void removeKathruFromFavorite(int KRUTHIId){
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues newValues = new ContentValues();
        newValues.put(KEY_FAVORITE, 0);

        String[] args = new String[]{String.valueOf(KRUTHIId)};
        db.update(TABLE_KATHRU, newValues, KEY_KATHRU_ID+"=?", args);
    }

    @Override
    public KathruDetails getKathruDetails (int kathruId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_KATHRU,
                new String[] { KEY_SIBLINGS, KEY_BIRTH_PLACE, KEY_MOTHER, KEY_PROVINCE, KEY_DEATH,
                        KEY_VILLAGE, KEY_FATHER, KEY_TIME, KEY_OTHER_WORKS, KEY_CHILDREN, KEY_WORK, KEY_SPOUSE,
                        KEY_AVAILABLE_KRUTHI, KEY_ANKITHA, KEY_TALUK, KEY_SPECIALITY, KEY_NAME },
                KEY_KATHRU_ID+ "=?",
                new String[] { String.valueOf(kathruId) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        assert cursor != null;
        EnumMap<KathruDetails.KEYS, String> kathruDetailsMap = new EnumMap<>(KathruDetails.KEYS.class);

        kathruDetailsMap.put(KathruDetails.KEYS.SIBLINGS , cursor.getString(0));
        kathruDetailsMap.put(KathruDetails.KEYS.BIRTH_PLACE , cursor.getString(1));
        kathruDetailsMap.put(KathruDetails.KEYS.MOTHER , cursor.getString(2));
        kathruDetailsMap.put(KathruDetails.KEYS.PROVINCE , cursor.getString(3));
        kathruDetailsMap.put(KathruDetails.KEYS.DEATH , cursor.getString(4));
        kathruDetailsMap.put(KathruDetails.KEYS.VILLAGE , cursor.getString(5));
        kathruDetailsMap.put(KathruDetails.KEYS.FATHER , cursor.getString(6));
        kathruDetailsMap.put(KathruDetails.KEYS.TIME , cursor.getString(7));
        kathruDetailsMap.put(KathruDetails.KEYS.OTHER_WORKS , cursor.getString(8));
        kathruDetailsMap.put(KathruDetails.KEYS.CHILDREN , cursor.getString(9));
        kathruDetailsMap.put(KathruDetails.KEYS.WORK , cursor.getString(10));
        kathruDetailsMap.put(KathruDetails.KEYS.SPOUSE , cursor.getString(11));
        kathruDetailsMap.put(KathruDetails.KEYS.AVAILABLE_KRUTHI , cursor.getString(12));
        kathruDetailsMap.put(KathruDetails.KEYS.ANKITHA , cursor.getString(13));
        kathruDetailsMap.put(KathruDetails.KEYS.TALUK , cursor.getString(14));
        kathruDetailsMap.put(KathruDetails.KEYS.SPECIALITY , cursor.getString(15));
        kathruDetailsMap.put(KathruDetails.KEYS.NAME , cursor.getString(16));

        KathruDetails kathruDetails = new KathruDetails(kathruId, kathruDetailsMap);

        cursor.close();
        return kathruDetails;
    }
}

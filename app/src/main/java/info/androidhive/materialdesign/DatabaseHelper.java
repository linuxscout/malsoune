package info.androidhive.materialdesign;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by condor on 25/04/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Name
    public static String DATABASE_NAME = "expression_database";

    // Current version of database
    private static final int DATABASE_VERSION = 1;

    // Name of table
    private static final String TABLE_STUDENTS = "expressions";

    // All Keys used in table
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
   // private static final String KEY_PHONENUMBER = "phone_number";

    public static String TAG = "tag";

    // Students Table Create Query
    /**
     * CREATE TABLE students ( id INTEGER PRIMARY KEY AUTOINCREMENT, name
     * TEXT,phone_number TEXT);
     */

    private static final String CREATE_TABLE_STUDENTS = "CREATE TABLE "
            + TABLE_STUDENTS + "(" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT );";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This method is called by system if the database is accessed but not yet
     * created.
     */

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_STUDENTS); // create students table

    }

    /**
     * This method is called when any modifications in database are done like
     * version is updated or database schema is changed
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_STUDENTS); // drop table if exists

        onCreate(db);
    }

    /**
     *
     * This method is used to add students detail in students Table
     *
     * @param expression
     * @return
     */

    public long addStudentDetail(Expression expression) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Creating content values
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, expression.name);
      //  values.put(KEY_PHONENUMBER, student.phone_number);

        // insert row in students table

        long insert = db.insert(TABLE_STUDENTS, null, values);

        return insert;
    }

    /**
     * This method is used to update particular student entry
     *
     * @param expression
     * @return
     */
    public int updateEntry(Expression expression, String Nom) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Creating content values
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, Nom);
      //  values.put(KEY_PHONENUMBER, Num);

        // update row in students table base on students.is value
        return db.update(TABLE_STUDENTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(expression.id) });
    }

    /**
     * Used to delete particular student entry
     *
     * @param id
     */
    public void deleteEntry(long id) {

        // delete row in students table based on id
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STUDENTS, KEY_ID + " = ?",
                new String[] { String.valueOf(id) });

    }

    /**
     * Used to get particular student details
     *
     * @param id
     * @return
     */

    public Expression getStudent(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        // SELECT * FROM students WHERE id = ?;
        String selectQuery = "SELECT  * FROM " + TABLE_STUDENTS + " WHERE "
                + KEY_ID + " = " + id;
        Log.d(TAG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Expression students = new Expression();
        students.id = c.getInt(c.getColumnIndex(KEY_ID));
      //  students.phone_number = c.getString(c.getColumnIndex(KEY_PHONENUMBER));
        students.name = c.getString(c.getColumnIndex(KEY_NAME));

        return students;
    }

    /**
     * Used to get detail of entire database and save in array list of data type
     * StudentsModel
     *
     * @return
     */
    public List<Expression> getAllStudentsList() {
        List<Expression> studentsArrayList = new ArrayList<Expression>();

        String selectQuery = "SELECT  * FROM " + TABLE_STUDENTS;
        Log.d(TAG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {

                Expression students = new Expression();
                students.id = c.getInt(c.getColumnIndex(KEY_ID));
             //   students.phone_number = c.getString(c
               //         .getColumnIndex(KEY_PHONENUMBER));
                students.name = c.getString(c.getColumnIndex(KEY_NAME));

                // adding to Students list
                studentsArrayList.add(students);
            } while (c.moveToNext());
        }

        return studentsArrayList;
    }
}

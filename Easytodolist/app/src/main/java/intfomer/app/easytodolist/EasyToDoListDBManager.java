package intfomer.app.easytodolist;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EasyToDoListDBManager  extends SQLiteOpenHelper{

    private static final String DB_USERDATA = "EasyTodoList.db";
    private static final String TABLE_USERINTEREST = "EasyToDoList";
    private static final int DB_VERSION = 1;

    Context mContext = null;


    private static EasyToDoListDBManager mDbManager = null;

    public static EasyToDoListDBManager getInstance(Context context) {
        if (mDbManager == null) {
            mDbManager = new EasyToDoListDBManager(context,
                    DB_USERDATA,
                    null,
                    DB_VERSION);

            Cursor c = mDbManager.query(null, null, null, null, null, null);

            if (c != null){

                ContentValues contentValues = new ContentValues();

                for(int i = 0; i < c.getColumnCount(); i++){
                    contentValues.put(c.getColumnName(i), 0);
                }
                mDbManager.insert(contentValues);
            }
        }

        return mDbManager;

    }

    private EasyToDoListDBManager(Context context,
                                  String dbname,
                                  SQLiteDatabase.CursorFactory factory,
                                  int version) {
        super(context, dbname, factory, version);
        mContext = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String database_creat = "CREATE TABLE IF NOT EXISTS " + TABLE_USERINTEREST + " (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "todo_list TEXT," +
                "favorite	INTEGER); ";
        db.execSQL(database_creat);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    public long insert(ContentValues addRowValue) {
        return getWritableDatabase().insert(TABLE_USERINTEREST,
                null,
                addRowValue);
    }

    public int insertAll(ContentValues[] values) {
        SQLiteDatabase db = getWritableDatabase();

        db.beginTransaction();

        for (ContentValues contentValues : values) {
            db.insert(TABLE_USERINTEREST, null, contentValues);
        }
        db.setTransactionSuccessful();
        ;
        db.endTransaction();
        return values.length;
    }

    public Cursor query(String[] columns,
                        String selection,
                        String[] selectionArgs,
                        String groupBy,
                        String having,
                        String orderBy) {
        return getReadableDatabase().query(TABLE_USERINTEREST,
                columns,
                selection,
                selectionArgs,
                groupBy,
                having,
                orderBy);
    }

    public int update(ContentValues updateRowValue,
                      String whereClause,
                      String[] whereArgs) {
        return getWritableDatabase().update(TABLE_USERINTEREST,
                updateRowValue,
                whereClause,
                whereArgs);
    }

    public int delete(String whereClause,
                      String[] whereArgs) {
        return getWritableDatabase().delete(TABLE_USERINTEREST,
                whereClause,
                whereArgs);
    }
}

package com.example.read_app.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.read_app.greendao.entity.SearchHistory;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "SEARCH_HISTORY".
*/
public class SearchHistoryDao extends AbstractDao<SearchHistory, String> {

    public static final String TABLENAME = "SEARCH_HISTORY";

    /**
     * Properties of entity SearchHistory.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, String.class, "id", true, "ID");
        public final static Property Content = new Property(1, String.class, "content", false, "CONTENT");
        public final static Property CreateDate = new Property(2, String.class, "createDate", false, "CREATE_DATE");
    }


    public SearchHistoryDao(DaoConfig config) {
        super(config);
    }
    
    public SearchHistoryDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"SEARCH_HISTORY\" (" + //
                "\"ID\" TEXT PRIMARY KEY NOT NULL ," + // 0: id
                "\"CONTENT\" TEXT NOT NULL ," + // 1: content
                "\"CREATE_DATE\" TEXT NOT NULL );"); // 2: createDate
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"SEARCH_HISTORY\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, SearchHistory entity) {
        stmt.clearBindings();
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(1, id);
        }
        stmt.bindString(2, entity.getContent());
        stmt.bindString(3, entity.getCreateDate());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, SearchHistory entity) {
        stmt.clearBindings();
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(1, id);
        }
        stmt.bindString(2, entity.getContent());
        stmt.bindString(3, entity.getCreateDate());
    }

    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    @Override
    public SearchHistory readEntity(Cursor cursor, int offset) {
        SearchHistory entity = new SearchHistory( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // id
            cursor.getString(offset + 1), // content
            cursor.getString(offset + 2) // createDate
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, SearchHistory entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setContent(cursor.getString(offset + 1));
        entity.setCreateDate(cursor.getString(offset + 2));
     }
    
    @Override
    protected final String updateKeyAfterInsert(SearchHistory entity, long rowId) {
        return entity.getId();
    }
    
    @Override
    public String getKey(SearchHistory entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(SearchHistory entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}

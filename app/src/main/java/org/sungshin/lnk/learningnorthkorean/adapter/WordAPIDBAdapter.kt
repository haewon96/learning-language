package org.sungshin.lnk.learningnorthkorean.adapter

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

import org.sungshin.lnk.learningnorthkorean.`object`.Word
import org.sungshin.lnk.learningnorthkorean.adapter.WordAPIDB._TABLENAME

import java.util.ArrayList

class WordAPIDBAdapter (private val mContext: Context) {
    private var mDBHelper: DatabaseHelper? = null

    val isEmpty: Boolean
        get() {
            var notEmpty = 0
            val c = rDB!!.rawQuery("SELECT * FROM wordAPITable", null)

            while (c.moveToNext()) {
                notEmpty++
            }

            return if (notEmpty > 0) {
                false
            } else
                true
        } // DB가 비어있는지 확인하는 함수

    private inner class DatabaseHelper// DB 테이블 생성
    (context: Context, name: String, factory: SQLiteDatabase.CursorFactory?, version: Int) : SQLiteOpenHelper(context, name, factory, version) {

        // 최초 DB를 만들 때 한 번만 호출된다.
        override fun onCreate(db: SQLiteDatabase?) {
            db?.execSQL(WordAPIDB._CREATE) // 테이블 생성
        }

        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            db.execSQL("DROP TABLE IF EXISTS " + WordAPIDB._TABLENAME)
            onCreate(db)
        }
    }

    @Throws(SQLException::class)
    fun open(): WordAPIDBAdapter {
        mDBHelper = DatabaseHelper(mContext, DATABASE_NAME, null, DATABASE_VERSION)
        mDB = mDBHelper!!.writableDatabase // DB를 쓰기 버전으로 데려옴
        rDB = mDBHelper!!.readableDatabase // DB를 읽기 버전으로 데려옴
        return this
    }

    fun close() {
        mDB?.close()
        rDB?.close()
    } // DB 닫기

    fun insertColumn(id: Int, title: String?, ntitle: String?, stitle: String?, semantic: String?, dsemantic: String?, sngram: String?, ngram: String?, sgram: String?): Long {
        val values = ContentValues()
        values.put("id", id)
        values.put("title", title)
        values.put("ntitle", ntitle)
        values.put("stitle", stitle)
        values.put("semantic", semantic)
        values.put("dsemantic", dsemantic)
        values.put("sngram", sngram)
        values.put("ngram", ngram)
        values.put("sgram", sgram)

        return mDB!!.insert(_TABLENAME, null, values)
    } // DB에 내용 추가

    fun deleteTable() {
        mDB!!.execSQL("delete from $_TABLENAME")
    }

    fun getTitle(): Array<String?> {
        val c = mDB!!.rawQuery("Select * from " + _TABLENAME + " ORDER BY id ", null)
        val titles = arrayOfNulls<String>(c.count)
        var i = 0

        while (c.moveToNext()) {
            titles[i] = c.getString(c.getColumnIndex("title"))
            i++
        }

        return titles
    }

    fun displayColumn(): Cursor {
        return mDB!!.rawQuery("SELECT * FROM $_TABLENAME", null)
    } // 커서를 사용해 테이블 내용을 가져옴

    companion object {
        private val DATABASE_NAME = "learning_north_korean_word.db"
        private val DATABASE_VERSION = 1
        var mDB: SQLiteDatabase? = null
        var rDB: SQLiteDatabase? = null
        var list: ArrayList<Word>? = null
    }
}
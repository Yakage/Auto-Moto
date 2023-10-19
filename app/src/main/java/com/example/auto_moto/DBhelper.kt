package com.example.auto_moto

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DBhelper(context: Context) : SQLiteOpenHelper(context, "Userdata", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table Userdatalist(Fullname TEXT, Username TEXT primary key, Contact INT, Password TEXT, ConfirmPassword TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table if exists Userdatalist")
    }

    fun saveuserdata(Fullname: String, Username: String, Contact: String, Password: String, ConfirmPassword: String): Boolean {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put("Fullname", Fullname)
        cv.put("Username", Username)
        cv.put("Contact", Contact)
        cv.put("Password", Password)
        cv.put("ConfirmPassword", ConfirmPassword)
        if (Password == ConfirmPassword) {
            val result = db.insert("Userdatalist", null, cv)
            db.close()
            return result != -1L
        }
        return false
    }

    fun loginUser(Username: String, Password: String): Boolean {
        val db = this.readableDatabase
        val columns = arrayOf("Username")
        val selection = "Username = ? AND Password = ?"
        val selectionArgs = arrayOf(Username, Password)

        val cursor = db.query("Userdatalist", columns, selection, selectionArgs, null, null, null)
        val cursorCount = cursor.count

        cursor.close()
        db.close()

        return cursorCount > 0
    }


    fun resetPassword(Contact: String, newPassword: String, resetCode : String): Boolean {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put("Password", newPassword)
        cv.put("Reset Code", resetCode)

        val whereClause = "Contact = ?"
        val whereArgs = arrayOf(Contact)

        val updatedRows = db.update("Userdatalist", cv, whereClause, whereArgs)

        db.close()

        return updatedRows > 0
    }

    fun updatePassword(Contact: String, newPassword: String): Boolean {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put("Password", newPassword)

        if (Contact != null) {
            val whereClause = "Contact = ?"
            val whereArgs = arrayOf(Contact)

            val rowsUpdated = db.update("Userdatalist", cv, whereClause, whereArgs)
            db.close()

            return rowsUpdated > 0
        }

        return false
    }

}


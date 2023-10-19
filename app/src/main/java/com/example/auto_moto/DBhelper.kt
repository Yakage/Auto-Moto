package com.example.auto_moto

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper



class DBhelper(context: Context) : SQLiteOpenHelper(context, "Userdata", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table Userdatalist(Email TEXT, Username TEXT primary key, Contact INT, Password TEXT, ConfirmPassword TEXT)")
    }
    fun someDatabaseOperation(context: Context) {
        val dbHelper = DBhelper(context)
        val db = dbHelper.writableDatabase

        try {
            // Perform database operations here
            // ...
        } catch (e: Exception) {
            // Handle exceptions or errors
        } finally {
            // Close the database when done
            db.isOpen
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table if exists Userdatalist")
    }

    fun saveuserdata(Email:String,Username: String, Contact: String, Password: String, ConfirmPassword: String): Boolean {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put("Email", Email)
        cv.put("Username", Username)
        cv.put("Contact", Contact)
        cv.put("Password", Password)
        cv.put("ConfirmPassword", ConfirmPassword)
        if (Password == ConfirmPassword) {
            val result = db.insert("Userdatalist", null, cv)
            db.isOpen
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
        db.isOpen

        return cursorCount > 0
    }

    @SuppressLint("Range")
    fun getUserData(username: String): User? {
        val db = this.readableDatabase
        var user: User? = null

        try {
            val query = "SELECT * FROM Userdatalist WHERE Username = ?"
            db.rawQuery(query, arrayOf(username)).use { cursor ->
                if (cursor.moveToFirst()) {
                    user = User(
                        email = cursor.getString(cursor.getColumnIndex("Email")),
                        fullname = cursor.getString(cursor.getColumnIndex("FullName")),
                        username = cursor.getString(cursor.getColumnIndex("Username")),
                        contact = cursor.getInt(cursor.getColumnIndex("Contact")),
                        password = cursor.getString(cursor.getColumnIndex("Password")),
                        confirmPassword = cursor.getString(cursor.getColumnIndex("ConfirmPassword")),
                        resetCode = cursor.getString(cursor.getColumnIndex("Reset Code")),
                        profileImageURL = cursor.getString(cursor.getColumnIndex("ProfileImageURL"))
                    )
                }
            }
        } catch (e: SQLException) {
            // Handle database errors here
        } finally {
            db.isOpen
        }

        return user
    }
    fun updateUserData(context: Context, username: String, newFullName: String, newContact: String, newEmail: String): Boolean {
        val dbHelper = DBhelper(context)
        val db = dbHelper.writableDatabase // Open the database here

        try {
            val cv = ContentValues()
            cv.put("Email", newEmail)
            cv.put("Fullname", newFullName)
            cv.put("Contact", newContact)

            val whereClause = "Username = ?"
            val whereArgs = arrayOf(username)

            val updatedRows = db.update("Userdatalist", cv, whereClause, whereArgs)

            return updatedRows > 0
        } catch (e: Exception) {
            // Handle exceptions or errors
            return false
        } finally {
            db.close() // Close the database when done
        }
    }

    fun updateResetCode(contact: String, resetCode: String): Boolean {
        val db = this.writableDatabase // Open the database here
        val cv = ContentValues()
        cv.put("Reset Code", resetCode)

        val whereClause = "Contact = ?"
        val whereArgs = arrayOf(contact)

        val updatedRows = db.update("Userdatalist", cv, whereClause, whereArgs)

        db.close() // Close the database when done

        return updatedRows > 0
    }

    @SuppressLint("Range")
    fun getUserByContact(contact: String): User? {
        val db = this.readableDatabase
        val user: User? = null
        val selectQuery = "SELECT * FROM Userdatalist WHERE Contact = ?"

        db.rawQuery(selectQuery, arrayOf(contact)).use { cursor ->
            if (cursor.moveToFirst()) {
                val user = User(
                    email = cursor.getString(cursor.getColumnIndex("Email")),
                    fullname = cursor.getString(cursor.getColumnIndex("FullName")),
                    username = cursor.getString(cursor.getColumnIndex("Username")),
                    contact = cursor.getInt(cursor.getColumnIndex("Contact")),
                    password = cursor.getString(cursor.getColumnIndex("Password")),
                    confirmPassword = cursor.getString(cursor.getColumnIndex("ConfirmPassword")),
                    resetCode = cursor.getString(cursor.getColumnIndex("Reset Code")),
                    profileImageURL = cursor.getString(cursor.getColumnIndex("ProfileImageURL"))
                )

            }
        }

        db.isOpen
        return user
    }


    fun resetPassword(contact: String, newPassword: String, resetCode: String): Boolean {
        val db = this.writableDatabase

        // Check if the provided reset code matches the one in the database for the given contact
        val user = getUserByContact(contact)
        if (user != null && user.resetCode == resetCode) {
            val cv = ContentValues()
            cv.put("Password", newPassword)

            val whereClause = "Contact = ?"
            val whereArgs = arrayOf(contact)

            val updatedRows = db.update("Userdatalist", cv, whereClause, whereArgs)

            db.close()

            return updatedRows > 0
        }

        db.isOpen

        return false
    }




}


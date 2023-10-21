package com.example.auto_motov04

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.auto_moto.User
import com.example.auto_moto.UsersCar


class DBhelper(context: Context) : SQLiteOpenHelper(context, "Userdata", null, 2) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table Userdatalist(Email TEXT, Username TEXT primary key, Contact INT, Password TEXT, ConfirmPassword TEXT)")
        db?.execSQL("create table CarList(CarName TEXT, CarModel TEXT, CarBrand TEXT, CarImage BLOB)")

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table if exists Userdatalist")
        db?.execSQL("drop table if exists CarList")
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
    fun updateUserData(context: Context, newUsername: String, newContact: String, newEmail: String): Boolean {
        val dbHelper = DBhelper(context)
        val db = dbHelper.writableDatabase // Open the database here

        try {
            val cv = ContentValues()
            cv.put("Email", newEmail)
            cv.put("Username", newUsername)
            cv.put("Contact", newContact)

            val whereClause = "Username = ?"
            val whereArgs = arrayOf(newUsername)

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
                    username = cursor.getString(cursor.getColumnIndex("Username")),
                    contact = cursor.getInt(cursor.getColumnIndex("Contact")),
                    password = cursor.getString(cursor.getColumnIndex("Password")),
                    confirmPassword = cursor.getString(cursor.getColumnIndex("ConfirmPassword")),
                    resetCode = cursor.getString(cursor.getColumnIndex("Reset Code")),
                    profileImageURL = cursor.getString(cursor.getColumnIndex("ProfileImageURL"))
                )

            }
        }

        db.close()
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

        db.close()

        return false
    }
    fun updatePassword(contact: String, newPassword: String): Boolean {
         return false
    }


    fun saveusercars(carName: String, carModel: String, carBrand: String, carImage: ByteArray): Boolean {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put("CarName", carName)
        cv.put("CarModel", carModel)
        cv.put("CarBrand", carBrand)
        cv.put("CarImage", carImage) // Store the image data as a byte array

        val result = db.insert("CarList", null, cv)
        db.close()

        return result != -1L
    }



    @SuppressLint("Range")
    fun getUserCars(): List<UsersCar> {
        val carList = mutableListOf<UsersCar>()
        val db = this.readableDatabase
        val columns = arrayOf("CarName", "CarModel", "CarBrand", "CarImage") // Include "CarImage"

        val cursor = db.query("CarList", columns, null, null, null, null, null)

        while (cursor.moveToNext()) {
            val carName = cursor.getString(cursor.getColumnIndex("CarName"))
            val carModel = cursor.getString(cursor.getColumnIndex("CarModel"))
            val carBrand = cursor.getString(cursor.getColumnIndex("CarBrand"))
            val carImage = cursor.getBlob(cursor.getColumnIndex("CarImage")) // Retrieve image data as a byte array

            // Create a UsersCar object and add it to the list
            val car = UsersCar(carName, carModel, carBrand, carImage)
            carList.add(car)
        }

        cursor.close()
        db.close()

        return carList
    }



}



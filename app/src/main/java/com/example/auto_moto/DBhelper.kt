package com.example.auto_motov04

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.net.Uri
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.auto_moto.EmailService
import com.example.auto_moto.MyCarList
import com.example.auto_moto.User



class DBhelper(context: Context) : SQLiteOpenHelper(context, "Userdata", null, 2) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table Userdatalist(Email TEXT, Username TEXT primary key, Contact INT, Password TEXT, Name TEXT)")
        db?.execSQL("create table CarList(CarName TEXT, CarModel TEXT, CarBrand TEXT, CarImage BLOB)")

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table if exists Userdatalist")
        db?.execSQL("drop table if exists CarList")
    }

    @Suppress("UNREACHABLE_CODE")
    fun saveuserdata(Email:String, Username: String, Contact: String, Password: String, Name: String): Boolean {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put("Email", Email)
        cv.put("Username", Username)
        cv.put("Contact", Contact)
        cv.put("Password", Password)
        cv.put("Name", Name)
            val result = db.insert("Userdatalist", null, cv)
            db.close()
            return result != -1L

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
        val query = "SELECT * FROM Userdatalist WHERE Username = ?"

        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery(query, arrayOf(username))
            if (cursor.moveToFirst()) {
                user = User(
                    email = cursor.getString(cursor.getColumnIndex("Email")),
                    username = cursor.getString(cursor.getColumnIndex("Username")),
                    contact = cursor.getInt(cursor.getColumnIndex("Contact")),
                    password = cursor.getString(cursor.getColumnIndex("Password")),
                    Name = cursor.getString(cursor.getColumnIndex("Name")),
                )
            }
        } catch (e: SQLException) {
            // Handle database errors here or log them
        } finally {
            cursor?.close()
            db.close()
        }

        return user
    }

    fun getData(){
        return
    }




    fun updateUserData(context: Context, Username: String, newContact: String, newEmail: String): Boolean {
        val dbHelper = DBhelper(context)
        val db = dbHelper.writableDatabase // Open the database here

        try {
            val cv = ContentValues()
            cv.put("Email", newEmail)
            cv.put("Contact", newContact)

            val whereClause = "Username = ?"
            val whereArgs = arrayOf(Username)

            val updatedRows = db.update("Userdatalist", cv, whereClause, whereArgs)

            return updatedRows > 0
        } catch (e: Exception) {
            // Handle exceptions or errors
            return false
        } finally {
            db.close() // Close the database when done
        }
    }

    fun deleteAccount(username: String): Boolean {
        val db = this.writableDatabase

        // Define the WHERE clause to specify which user's data to delete
        val whereClause = "Username = ?"
        val whereArgs = arrayOf(username)

        // Delete the user's data from the Userdatalist table
        val deletedRows = db.delete("Userdatalist", whereClause, whereArgs)


        db.close()

        return deletedRows > 0
    }


    fun sendResetCodeByEmail(context: Context, email: String, resetCode: String): Boolean {
        try {
            val emailIntent = createResetCodeEmail(email, resetCode)
            EmailService.sendEmail(context, emailIntent)
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }






    fun createResetCodeEmail(email: String, resetCode: String): Intent {
        val subject = "Password Reset Code"
        val message = "Your password reset code is: $resetCode"

        val emailIntent = Intent(Intent.ACTION_SENDTO)
        emailIntent.data = Uri.parse("mailto:$email")
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
        emailIntent.putExtra(Intent.EXTRA_TEXT, message)

        return emailIntent
    }




    fun updatePassword(context: Context, email: String, newPassword: String): Boolean {
        try {
            val dbHelper = DBhelper(context) // Initialize your database helper
            val db = dbHelper.writableDatabase // Get a writable database

            val values = ContentValues()
            values.put("Password", newPassword) // Assuming "Password" is the field name

            val whereClause = "Email = ?"
            val whereArgs = arrayOf(email)

            val updatedRows = db.update("Userdatalist", values, whereClause, whereArgs)
            db.close() // Close the database when done

            return updatedRows > 0
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
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
    fun getUserCars(): List<MyCarList> {
        val carList = mutableListOf<MyCarList>() // Change the type to MyCarList
        val db = this.readableDatabase
        val columns = arrayOf("CarName", "CarModel", "CarBrand", "CarImage")

        val cursor = db.query("CarList", columns, null, null, null, null, null)

        while (cursor.moveToNext()) {
            val carName = cursor.getString(cursor.getColumnIndex("CarName"))
            val carModel = cursor.getString(cursor.getColumnIndex("CarModel"))
            val carBrand = cursor.getString(cursor.getColumnIndex("CarBrand"))
            val carImage = cursor.getBlob(cursor.getColumnIndex("CarImage"))

            // Create a MyCarList object and add it to the list
            val car = MyCarList(carImage, carName, carModel, carBrand) // Assuming the constructor of MyCarList accepts a ByteArray as the first parameter
            carList.add(car)
        }

        cursor.close()
        db.close()

        return carList
    }


    fun deleteCar(carName: String, carModel: String, carBrand: String): Boolean {
        val db = this.writableDatabase

        // Define the WHERE clause to specify which car's data to delete
        val whereClause = "CarName = ? AND CarModel = ? AND CarBrand = ?"
        val whereArgs = arrayOf(carName, carModel, carBrand)

        // Delete the car's data from the CarList table
        val deletedRows = db.delete("CarList", whereClause, whereArgs)

        db.close()

        return deletedRows > 0
    }


}



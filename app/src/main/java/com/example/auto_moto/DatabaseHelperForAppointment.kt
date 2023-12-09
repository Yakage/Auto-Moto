package com.example.auto_moto

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelperForAppointment(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "MyAppointmentDatabase.db"
        private const val DATABASE_VERSION = 1

        // Define your table and columns
        private const val TABLE_APPOINTMENT = "appointments"
        private const val COLUMN_ID = "id"
        private const val COLUMN_SERVICES = "services"
        private const val COLUMN_CAR_TYPE = "car_type"
        private const val COLUMN_DATE = "date"
        private const val COLUMN_TIME = "time"
        private const val COLUMN_SERVICE_CHARGE = "service_charge"
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Create the database table
        val createTableSQL = "CREATE TABLE $TABLE_APPOINTMENT (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_SERVICES TEXT," +
                "$COLUMN_CAR_TYPE TEXT," +
                "$COLUMN_DATE TEXT," +
                "$COLUMN_TIME TEXT," +
                "$COLUMN_SERVICE_CHARGE REAL);"
        db.execSQL(createTableSQL)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Handle database upgrades if needed
        db.execSQL("DROP TABLE IF EXISTS $TABLE_APPOINTMENT")
        onCreate(db)
    }

    // Insert a new appointment record
    fun insertAppointment(appointment: AppointmentData) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_SERVICES, appointment.services)
        values.put(COLUMN_CAR_TYPE, appointment.carType)
        values.put(COLUMN_DATE, appointment.date)
        values.put(COLUMN_TIME, appointment.time)
        values.put(COLUMN_SERVICE_CHARGE, appointment.serviceCharge)

        db.insert(TABLE_APPOINTMENT, null, values)
        db.close()
    }

    // Retrieve an appointment record (for example, by ID)
    @SuppressLint("Range")
    fun getAppointmentById(id: Long): AppointmentData? {
        val db = this.readableDatabase
        val cursor = db.query(
            TABLE_APPOINTMENT, null,
            "$COLUMN_ID = ?", arrayOf(id.toString()), null, null, null
        )

        val appointment: AppointmentData?

        if (cursor.moveToFirst()) {
            appointment = AppointmentData(
                cursor.getString(cursor.getColumnIndex(COLUMN_SERVICES)),
                cursor.getString(cursor.getColumnIndex(COLUMN_CAR_TYPE)),
                cursor.getString(cursor.getColumnIndex(COLUMN_DATE)),
                cursor.getString(cursor.getColumnIndex(COLUMN_TIME)),
                cursor.getDouble(cursor.getColumnIndex(COLUMN_SERVICE_CHARGE))
            )
        } else {
            appointment = null
        }

        cursor.close()
        db.close()
        return appointment
    }
    data class AppointmentData(
        val services: String,
        val carType: String,
        val date: String,
        val time: String,
        val serviceCharge: Double
    )
}





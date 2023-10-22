package com.example.auto_moto

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelperForItems (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){
        companion object{
            private const val DATABASE_NAME = "Items.db"
            private const val DATABASE_VERSION = 1
            private const val TABLE_NAME = "Shop_Items"
            private const val COLUMN_ID = "Item_ID"
            private const val COLUMN_NAME = "ITEM_NAME"
            private const val COLUMN_AVAILABILITY = "ITEM_AVAILABILITY"
            private const val COLUMN_IMAGE = "ITEM_IMAGE"
        }
    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE $TABLE_NAME($COLUMN_ID INT PRIMARY KEY, $COLUMN_NAME TEXT, $COLUMN_AVAILABILITY TEXT, $COLUMN_IMAGE BLOB)"
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }

    fun getShopItems() : List<Items> {
        val itemsList = mutableListOf<Items>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(query, null)

        while (cursor.moveToNext()){
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME))
            val availability = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_AVAILABILITY))
            val image = cursor.getBlob(cursor.getColumnIndexOrThrow(COLUMN_IMAGE))

            val items = Items(id, name, availability, image)
            itemsList.add(items)
        }
        cursor.close()
        db.close()
        return itemsList
    }

}
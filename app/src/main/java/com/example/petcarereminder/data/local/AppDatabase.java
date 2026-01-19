package com.example.petcarereminder.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(
        entities = {
                PetEntity.class,
                VaccineEntity.class   // 🩺 Aşı / bakım tablosu
        },
        version = 2
)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    // 🐾 Hayvan DAO
    public abstract PetDao petDao();

    // 🩺 Aşı / bakım DAO
    public abstract VaccineDao vaccineDao();

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "pet_db"
                    )
                    // 🔥 Versiyon arttığı için gerekli
                    .fallbackToDestructiveMigration()

                    // ⚠️ Ders / proje için kabul
                    .allowMainThreadQueries()

                    .build();
        }
        return INSTANCE;
    }
}

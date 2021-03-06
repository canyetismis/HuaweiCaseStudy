package com.canyetismis.football_app.repository.cache;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.canyetismis.football_app.model.Team;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Team.class}, version = 1, exportSchema = false)
public abstract class TeamDatabase extends RoomDatabase {

    public abstract TeamDao teamDao();

    private static TeamDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static TeamDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (TeamDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TeamDatabase.class, "team_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

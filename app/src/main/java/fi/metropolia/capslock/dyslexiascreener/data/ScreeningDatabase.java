package fi.metropolia.capslock.dyslexiascreener.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import fi.metropolia.capslock.dyslexiascreener.data.converter.OffsetDateTimeConverter;
import fi.metropolia.capslock.dyslexiascreener.data.dao.TestDao;
import fi.metropolia.capslock.dyslexiascreener.data.model.Test;

/**
 * Configuration for the application's Room database
 *
 * @author Peetu Saarinen
 */
@Database(entities = {Test.class}, version = 1, exportSchema = false)
@TypeConverters({OffsetDateTimeConverter.class})
public abstract class ScreeningDatabase extends RoomDatabase {
    private static ScreeningDatabase instance;

    public static ScreeningDatabase getDatabase(Context context) {
        if (instance == null)
            instance = Room.databaseBuilder(context, ScreeningDatabase.class, "screening_database").build();
        return instance;
    }

    /**
     * Returns the Data Access Object (DAO) for accessing {@link Test} entities in the database.
     *
     * @return the {@link TestDao} of this database
     */
    public abstract TestDao testDao();
}

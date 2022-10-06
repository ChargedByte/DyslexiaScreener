package fi.metropolia.capslock.dyslexiascreener.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import fi.metropolia.capslock.dyslexiascreener.SharedConstants;
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
    private static volatile ScreeningDatabase instance;

    public static synchronized ScreeningDatabase getInstance(Context context) {
        if (instance == null)
            instance = Room.databaseBuilder(context, ScreeningDatabase.class, SharedConstants.DATABASE_NAME).build();
        return instance;
    }

    /**
     * Returns the Data Access Object (DAO) for accessing {@link Test} entities in the database.
     *
     * @return A {@link TestDao} for this database
     */
    public abstract TestDao testDao();
}

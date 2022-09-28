package fi.metropolia.capslock.dyslexiascreener.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import fi.metropolia.capslock.dyslexiascreener.data.model.Test;

/**
 * Data Access Object (DAO) for interacting with {@link Test} entities.
 *
 * @author Peetu Saarinen
 */
@Dao
public interface TestDao {
    /**
     * Insert the provided entity to the table.
     * In case of a conflict, the row in the database is replaced.
     *
     * @param entity the {@link Test} entity to be inserted
     * @return the id of the inserted entity
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Test entity);

    /**
     * Delete the provided entity from the table.
     * This method matches based on the primary key of the entity, so only the ids have to match.
     *
     * @param entity the {@link Test} entity to be deleted
     */
    @Delete
    void delete(Test entity);

    /**
     * Find the entity that matches the provided id.
     *
     * @param id the id of the desired entity
     * @return the found {@link Test} entity or <code>null</code> if nothing was found
     */
    @Query("SELECT * FROM tests WHERE id = :id")
    Test findById(long id);

    /**
     * Get a list of all entities in the table.
     *
     * @return a list of {@link Test} entities
     */
    @Query("SELECT * FROM tests")
    List<Test> findAll();

    /**
     * Load a list of all entities in the table with reactivity support.
     *
     * @return a list of {@link Test} entities in a {@link LiveData} holder
     */
    @Query("SELECT * FROM tests")
    LiveData<List<Test>> loadAll();
}

package fi.metropolia.capslock.dyslexiascreener.data.converter;

import androidx.room.TypeConverter;

import java.time.OffsetDateTime;

/**
 * Converter-class for storing {@link OffsetDateTime}s in a Room database.
 *
 * @author Peetu Saarinen
 */
public class OffsetDateTimeConverter {
    /**
     * Convert a {@link String} to a {@link OffsetDateTime}.
     *
     * @param value A {@link String} holding a ISO-8601 formatted timestamp
     * @return A {@link OffsetDateTime} or <code>null</code> if <code>value</value> was <code>null</code>.
     * @see OffsetDateTime#parse(CharSequence)
     */
    @TypeConverter
    public static OffsetDateTime fromTimestamp(String value) {
        return value == null ? null : OffsetDateTime.parse(value);
    }

    /**
     * Convert a {@link OffsetDateTime} to a {@link String}.
     *
     * @param value A {@link OffsetDateTime} to written
     * @return A {@link String} holding ISO-8601 formatted timestamp or <code>null</code> if <code>value</code> was <code>null</code>.
     * @see OffsetDateTime#toString()
     */
    @TypeConverter
    public static String offsetDateTimeToTimestamp(OffsetDateTime value) {
        return value == null ? null : value.toString();
    }
}

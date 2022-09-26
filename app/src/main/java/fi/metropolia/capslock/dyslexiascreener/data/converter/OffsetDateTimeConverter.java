package fi.metropolia.capslock.dyslexiascreener.data.converter;

import androidx.room.TypeConverter;

import java.time.OffsetDateTime;

public class OffsetDateTimeConverter {
    @TypeConverter
    public static OffsetDateTime fromTimestamp(String value) {
        return value == null ? null : OffsetDateTime.parse(value);
    }

    @TypeConverter
    public static String offsetDateTimeToTimestamp(OffsetDateTime value) {
        return value == null ? null : value.toString();
    }
}

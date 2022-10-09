package fi.metropolia.capslock.dyslexiascreener.utils;

import androidx.annotation.NonNull;

import java.security.SecureRandom;

/**
 * Utility for dealing with randomness.
 *
 * @author Peetu Saarinen
 */
public final class RandomUtil {
    private static final SecureRandom random = new SecureRandom();

    /**
     * Get a random item from the provided array.
     *
     * @param array An array holding the items
     * @param <T>   Any type that can be made into an array
     * @return A random <code>T</code> item from the provided array
     */
    public static <T> T item(@NonNull T[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("array cannot be empty");
        }
        return array[integer(array.length)];
    }

    /**
     * Generate a random integer within the provided range
     *
     * @param min Minimum value (Inclusive)
     * @param max Maximum value (Exclusive)
     * @return An <code>int</code> between min and max
     */
    public static int integer(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        return random.nextInt((max - min)) + min;
    }

    /**
     * Generate a random integer from 0 to max
     *
     * @param max Maximum value (Exclusive)
     * @return An <code>int</code> between 0 and max
     */
    public static int integer(int max) {
        if (max <= 0) {
            throw new IllegalArgumentException("max must be greater than 0");
        }
        return integer(0, max);
    }
}

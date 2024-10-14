package io.github.buianhtai1205.saytruyen_common_service.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The type Collection utils.
 */
public class CollectionUtils {

    /**
     * Is null or empty boolean.
     *
     * @param collection the collection
     * @return the boolean
     */
// Check if a collection is null or empty
    public static boolean isNullOrEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * Merge and remove duplicates list.
     *
     * @param <T>   the type parameter
     * @param list1 the list 1
     * @param list2 the list 2
     * @return the list
     */
// Merge two lists and remove duplicates
    public static <T> List<T> mergeAndRemoveDuplicates(List<T> list1, List<T> list2) {
        Set<T> set = new HashSet<>(list1);
        set.addAll(list2);
        return new ArrayList<>(set);
    }

    /**
     * Difference list.
     *
     * @param <T>   the type parameter
     * @param list1 the list 1
     * @param list2 the list 2
     * @return the list
     */
// Find the difference between two lists
    public static <T> List<T> difference(List<T> list1, List<T> list2) {
        return list1.stream().filter(item -> !list2.contains(item)).collect(Collectors.toList());
    }
}

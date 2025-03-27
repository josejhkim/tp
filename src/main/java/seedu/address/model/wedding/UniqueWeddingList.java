package seedu.address.model.wedding;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.wedding.exceptions.DuplicateWeddingException;
import seedu.address.model.wedding.exceptions.WeddingNotFoundException;

/**
 * Represents a unique list of weddings in a wedding.
 * <p>
 * This class ensures that weddings remain unique within the list. It provides methods for adding, deleting, searching,
 * and managing guests within weddings.
 * </p>
 * <p>
 * This list does not allow duplicate weddings based on {@code Wedding#isSameWedding(Wedding)}.
 * </p>
 */
public class UniqueWeddingList implements Iterable<Wedding> {

    private final ObservableList<Wedding> internalList = FXCollections.observableArrayList();
    private final ObservableList<Wedding> internalUnmodifiableList = FXCollections
            .unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent wedding as the given argument.
     */
    public boolean contains(Wedding toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(wedding -> wedding.isSameWedding(toCheck));
    }

    /**
     * Returns an iterator over the weddings in the list.
     *
     * @return an Iterator of Wedding.
     */
    public Iterator<Wedding> iterator() {
        return internalList.iterator();
    }

    /**
     * Adds a new wedding to the list. Ensures that the wedding does not already exist in the list.
     *
     * @param toAdd The wedding to add.
     * @throws IllegalArgumentException if a wedding with the same ID already exists.
     */
    public void addWedding(Wedding toAdd) {
        requireNonNull(toAdd);

        if (contains(toAdd)) {
            throw new IllegalArgumentException("Wedding with name " + toAdd.getName() + " already exists.");
        }

        internalList.add(toAdd);
    }

    /**
     * Deletes a wedding from the list by its ID.
     *
     * @param wedding The wedding to be deleted.
     * @throws WeddingNotFoundException if the wedding does not exist.
     */
    public void deleteWedding(Wedding wedding) {
        requireNonNull(wedding);

        if (!internalList.remove(wedding)) {
            throw new WeddingNotFoundException();
        }
    }

    /**
     * Deletes a wedding from the list by its name.
     *
     * @param weddingName The name of the wedding to be deleted.
     * @throws WeddingNotFoundException if the wedding with the given name does not exist.
     */
    public void deleteWeddingByName(String weddingName) {
        requireNonNull(weddingName);

        Wedding wedding = findWeddingByName(weddingName);
        deleteWedding(wedding);
    }

    /**
     * Finds a wedding in the list by its ID.
     *
     * @param weddingName The ID of the wedding to find.
     * @return The wedding from the list if found.
     * @throws WeddingNotFoundException if no matching wedding is found.
     *
     */
    public Wedding findWeddingByName(String weddingName) {
        try {
            return internalList.stream().filter(wedding -> wedding.getName().equals(weddingName)).findFirst().get();
        } catch (NoSuchElementException nsee) {
            throw new WeddingNotFoundException();
        }
    }

    /**
     * Finds a wedding in the list that is equal to the given wedding.
     *
     * @param wedding The wedding to find.
     * @return The wedding from the list if found.
     * @throws WeddingNotFoundException if no matching wedding is found.
     */
    public Wedding findWedding(Wedding wedding) {
        try {
            return internalList.stream().filter(w -> w.equals(wedding)).findFirst().get();
        } catch (NoSuchElementException nsee) {
            throw new WeddingNotFoundException();
        }
    }

    /**
     * Checks whether a wedding with the given ID exists in the list.
     *
     * @param weddingName The ID of the wedding to check.
     * @return {@code true} if the wedding exists, otherwise {@code false}.
     */
    public boolean hasWeddingByName(String weddingName) {
        requireNonNull(weddingName);
        return internalList.stream().anyMatch(wedding -> wedding.getName() == weddingName);
    }

    /**
     * Checks if the given wedding object is in this wedding list
     * @param wedding to check if it exists in this list
     * @return boolean value denoting whether the given wedding exists or not
     */
    public boolean hasWedding(Wedding wedding) {
        requireNonNull(wedding);
        return internalList.contains(wedding);
    }

    /**
     * Replaces the target wedding in the list with the edited wedding.
     *
     * @param target The wedding to be replaced.
     * @param editedWedding The new wedding to replace the target.
     * @throws PersonNotFoundException if the target wedding is not found.
     * @throws DuplicateWeddingException if the edited wedding already exists in the list.
     */
    public void setWedding(Wedding target, Wedding editedWedding) {
        requireAllNonNull(target, editedWedding);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new WeddingNotFoundException();
        }

        if (!target.isSameWedding(editedWedding) && contains(editedWedding)) {
            throw new DuplicateWeddingException();
        }

        internalList.set(index, editedWedding);
    }

    /**
     * Replaces the contents of this list with the weddings from another UniqueWeddingList.
     *
     * @param replacement The list to copy weddings from.
     */
    public void setWeddings(UniqueWeddingList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with the given list of weddings.
     *
     * @param weddings The new list of weddings.
     * @throws DuplicateWeddingException if duplicate weddings are found in the list.
     */
    public void setWeddings(List<Wedding> weddings) {
        requireAllNonNull(weddings);
        if (!weddingsAreUnique(weddings)) {
            throw new DuplicateWeddingException();
        }

        internalList.setAll(weddings);
    }

    /**
     * Assigns a guest to a wedding based on their name.
     * <p>
     * The method ensures that the wedding exists and that the guest is present in the RSVP list. If the wedding is
     * full, an exception is thrown.
     * </p>
     *
     * @param weddingName The ID of the wedding.
     * @param guest The new guest to be added in this wedding.
     * @throws WeddingNotFoundException if the wedding does not exist.
     * @throws IllegalArgumentException if the guest is not found or the wedding is full.
     */
    public void assignGuestToWeddingByName(String weddingName, Person guest) {
        Wedding wedding = findWeddingByName(weddingName);
        if (wedding == null) {
            throw new WeddingNotFoundException();
        }

        wedding.addPerson(guest);
    }

    /**
     * Removes a guest from a wedding.
     * <p>
     * The method finds the specified wedding, removes the guest, and updates the wedding list.
     * </p>
     *
     * @param weddingName The ID of the wedding.
     * @param guest The guest to be added.
     * @throws WeddingNotFoundException if the wedding does not exist.
     */
    public void deleteGuestFromWeddingByName(String weddingName, Person guest) {
        Wedding wedding = findWeddingByName(weddingName);
        if (wedding == null) {
            throw new WeddingNotFoundException();
        }

        wedding.deletePerson(guest);
    }

    /**
     * Returns the list of weddings as an unmodifiable {@code ObservableList}. This ensures that the list cannot be
     * modified externally.
     *
     * @return An unmodifiable list of weddings.
     */
    public ObservableList<Wedding> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /**
     * Returns a list of all weddings.
     *
     * @return A list of all weddings.
     */
    public List<Wedding> getAllWeddings() {
        return internalUnmodifiableList;
    }

    public int size() {
        return internalList.size();
    }

    /**
     * Checks if all weddings in the given list are unique based on isSameWedding().
     *
     * @param weddings The list to check.
     * @return true if all weddings are unique, false otherwise.
     */
    private boolean weddingsAreUnique(List<Wedding> weddings) {
        for (int i = 0; i < weddings.size() - 1; i++) {
            for (int j = i + 1; j < weddings.size(); j++) {
                if (weddings.get(i).isSameWedding(weddings.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueWeddingList)) {
            return false;
        }

        UniqueWeddingList otherWeddingList = (UniqueWeddingList) other;
        return internalList.equals(otherWeddingList.internalList);
    }
}

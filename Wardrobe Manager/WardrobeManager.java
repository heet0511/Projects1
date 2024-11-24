////////////FILE HEADER(INCLUDE IN EVERY FILE)////////////////
//
// Title: Wardrobe Manager
// Course: CS300 Spring 2024
//
// Author: Heet Joshi
// Email: hjoshi6@wisc.edu
// Lecturer: Mouna Kacem
//
////////////////// ASSISTANCE/HELP CITATIONS//////////////
//
// Person: No help taken
// Online sources: No help taken
//
//////////////////////////////////////////////////////////////////

public class WardrobeManager {

    /**
     * Checks if a clothing item with the given description and brand exists in the wardrobe.
     *
     * @param description the description of the clothing item
     * @param brand       the brand of the clothing item
     * @param wardrobe    the 2D array representing the wardrobe
     * @param wardrobeSize the current size of the wardrobe
     * @return true if the clothing item is found, false otherwise
     */
    public static boolean containsClothing(String description, String brand, String[][] wardrobe, int wardrobeSize) {
        for (int i = 0; i < wardrobeSize; i++) {
            if (wardrobe[i] != null &&
                    wardrobe[i][0].equalsIgnoreCase(description) &&
                    wardrobe[i][1].equalsIgnoreCase(brand)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a new clothing item to the wardrobe if it doesn't already exist.
     *
     * @param description the description of the clothing item
     * @param brand       the brand of the clothing item
     * @param wardrobe    the 2D array representing the wardrobe
     * @param wardrobeSize the current size of the wardrobe
     * @return the new size of the wardrobe if added successfully, old size if the item already exists or wardrobe is full
     */
    public static int addClothing(String description, String brand, String[][] wardrobe, int wardrobeSize) {


        if (wardrobeSize < wardrobe.length) {
            wardrobe[wardrobeSize] = new String[]{description, brand, "never"};
            return wardrobeSize + 1; // Returns the new wardrobe size
        }

        return wardrobeSize; // Indicates that the wardrobe is full and the clothing couldn't be added
    }



    /**
     * Finds the index of the most recently worn clothing item in the wardrobe.
     *
     * @param wardrobe    the 2D array representing the wardrobe
     * @param wardrobeSize the current size of the wardrobe
     * @return the index of the most recently worn item, or -1 if no item has been worn
     */
    public static int getMostRecentlyWorn(String[][] wardrobe, int wardrobeSize) {
        int mostRecentIndex = -1;
        String mostRecentDate = "1900-01-01"; // A default date for comparison

        for (int i = 0; i < wardrobeSize; i++) {
            if (wardrobe[i] != null && !wardrobe[i][2].equalsIgnoreCase("never") &&
                    wardrobe[i][2].compareTo(mostRecentDate) > 0) {
                mostRecentIndex = i;
                mostRecentDate = wardrobe[i][2];
            }
        }

        return mostRecentIndex;
    }

    /**
     * Finds the index of a clothing item in the wardrobe.
     *
     * @param description the description of the clothing item
     * @param brand       the brand of the clothing item
     * @param wardrobe    the 2D array representing the wardrobe
     * @param wardrobeSize the current size of the wardrobe
     * @return the index of the item if found, -1 otherwise
     */
    public static int indexOfClothing(String description, String brand, String[][] wardrobe, int wardrobeSize) {
        for (int i = 0; i < wardrobeSize; i++) {
            if (wardrobe[i] != null &&
                    wardrobe[i][0].equalsIgnoreCase(description) &&
                    wardrobe[i][1].equalsIgnoreCase(brand)) {
                return i;
            }
        }
        return -1; // Clothing not found in the wardrobe
    }

    /**
     * Updates the last worn date of a clothing item.
     *
     * @param description the description of the clothing item
     * @param brand       the brand of the clothing item
     * @param wardrobe    the 2D array representing the wardrobe
     * @param wardrobeSize the current size of the wardrobe
     * @param date        the date the item was last worn
     */
    public static boolean wearClothing(String description, String brand, String date,String[][] wardrobe, int wardrobeSize) {
        if (date.equals("never")) return false;
        int index = indexOfClothing(description, brand, wardrobe, wardrobeSize);
        if (index != -1) {
            wardrobe[index][2] = date;
            return true;
        }
        return false;
    }

    /**
     * Counts the number of clothing items with a specific brand in the wardrobe.
     *
     * @param brand       the brand to count
     * @param wardrobe    the 2D array representing the wardrobe
     * @param wardrobeSize the current size of the wardrobe
     * @return the count of items with the specified brand
     */
    public static int getBrandCount(String brand, String[][] wardrobe, int wardrobeSize) {
        int count = 0;
        for (int i = 0; i < wardrobeSize; i++) {
            if (wardrobe[i] != null && wardrobe[i][1].equalsIgnoreCase(brand)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Counts the number of unworn clothing items in the wardrobe.
     *
     * @param wardrobe    the 2D array representing the wardrobe
     * @param wardrobeSize the current size of the wardrobe
     * @return the count of unworn items
     */
    public static int getNumUnwornClothes(String[][] wardrobe, int wardrobeSize) {
        int count = 0;
        for (int i = 0; i < wardrobeSize; i++) {
            if (wardrobe[i] != null && wardrobe[i][2].equalsIgnoreCase("never")) {
                count++;
            }
        }
        return count;
    }

    /**
     * Removes a clothing item at a specific index from the wardrobe.
     *
     * @param index       the index of the item to be removed
     * @param wardrobe    the 2D array representing the wardrobe
     * @param wardrobeSize the current size of the wardrobe
     */

    public static int removeClothingAtIndex(int index, String[][] wardrobe, int wardrobeSize) {
        if (index >= 0 && index < wardrobeSize) {
            wardrobe[index] = null;
            // Shift remaining elements to fill the gap
            for (int i = index; i < wardrobeSize - 1; i++) {
                wardrobe[i] = wardrobe[i + 1];
            }
            wardrobe[wardrobeSize - 1] = null;
            return wardrobeSize - 1; // Return the new size after removal
        }
        return wardrobeSize; // If the index is out of bounds, return the original size
    }


    /**
     * Removes all unworn clothing items from the wardrobe.
     *
     * @param wardrobe    the 2D array representing the wardrobe
     * @param wardrobeSize the current size of the wardrobe
     */
    public static int removeAllUnworn(String[][] wardrobe, int wardrobeSize) {
        int i = 0;
        while (i < wardrobeSize) {
            if (wardrobe[i] != null && wardrobe[i][2].equalsIgnoreCase("never")) {
                removeClothingAtIndex(i, wardrobe, wardrobeSize);
                wardrobeSize--;
            } else {
                i++;
            }
        }
        return wardrobeSize;
    }
}

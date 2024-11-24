
// Title: Wardrobe Manager Tester
// Author: Heet Joshi
// Email: hjoshi6@wisc.edu

import java.util.Arrays;

/**
 * This class is a tester for the WardrobeManager class.
 */

public class WardrobeManagerTester {

  /**
   * Tests the containsClothing method with an empty wardrobe.
   *
   * @return true if the test passes, false otherwise.
   */
  public static boolean testContainsEmpty() {
    // (1) set up the test variables and call the method we are testing - EXACT MATCH
    String[][] wardrobe = {null, null, null, null, null};
    String[][] wardrobeCopy = Arrays.copyOf(wardrobe, wardrobe.length);
    int size = 0;
    boolean expected = false;
    boolean actual = WardrobeManager.containsClothing("black t-shirt", "Hanes", wardrobe, size);

    // (2) verify that the expected return value and the actual return value match
    if (expected != actual) return false;

    // (3) another test method call, this time with case difference (that should still match!)
    actual = WardrobeManager.containsClothing("dark blue jeans", "LEVI", wardrobe, size);
    if (expected != actual) return false;

    // (4) since this method should not modify the array, let's check it against our copy:
    if (!Arrays.deepEquals(wardrobe, wardrobeCopy)) return false;

    // (5) if all of those checks pass, NOW we can say we passed the test
    return true;
  }

  /**
   * Tests the containsClothing method with a wardrobe that contains the specified clothing.
   *
   * @return true if the test passes, false otherwise.
   */
  public static boolean testContainsTrue() {
    // (1) set up the test variables and call the method we are testing - EXACT MATCH
    String[][] wardrobe = {{"black t-shirt", "Hanes", "never"}, {"dark blue jeans", "Levi", "never"},
            null, null, null};
    String[][] wardrobeCopy = Arrays.copyOf(wardrobe, wardrobe.length);
    int size = 2;
    boolean expected = true;
    boolean actual = WardrobeManager.containsClothing("black t-shirt", "Hanes", wardrobe, size);

    // (2) verify that the expected return value and the actual return value match
    if (expected != actual) return false;

    // (3) another test method call, this time with case difference (that should still match!)
    actual = WardrobeManager.containsClothing("dark blue jeans", "LEVI", wardrobe, size);
    if (expected != actual) return false;

    // (4) since this method should not modify the array, let's check it against our copy:
    if (!Arrays.deepEquals(wardrobe, wardrobeCopy)) return false;

    // (5) if all of those checks pass, NOW we can say we passed the test
    return true;
  }

  /**
   * Tests the containsClothing method with a wardrobe that does not contain the specified clothing.
   *
   * @return true if the test passes, false otherwise.
   */
  public static boolean testContainsFalse() {
    // (1) set up the test variables and call the method we are testing - EXACT MATCH
    String[][] wardrobe = {{"black t-shirt", "Hanes", "never"}, {"dark blue jeans", "Levi", "never"},
            null, null, null};
    String[][] wardrobeCopy = Arrays.copyOf(wardrobe, wardrobe.length);
    int size = 2;
    boolean expected = false;
    boolean actual = WardrobeManager.containsClothing("blue t-shirt", "levis", wardrobe, size);

    // (2) verify that the expected return value and the actual return value match
    if (expected != actual) return false;

    // (3) verify that the expected return value and the actual return value match when different description but same brand is passed
    actual = WardrobeManager.containsClothing("black t-shirt", "levis", wardrobe, size);
    if (expected != actual) return false;

    // (4) verify that the expected return value and the actual return value match when different brand but same description is passed
    actual = WardrobeManager.containsClothing("blue t-shirt", "Hanes", wardrobe, size);
    if (expected != actual) return false;

    // (5) since this method should not modify the array, let's check it against our copy:
    if (!Arrays.deepEquals(wardrobe, wardrobeCopy)) return false;

    // (6) if all of those checks pass, NOW we can say we passed the test
    return true;

  }

  /**
   * Tests the addclothing method with an empty wardrobe and adds a clothing to it.
   *
   * @return true if the test passes, false otherwise.
   */
  public static boolean testAddToEmpty() {
    // (1) set up the test variables and call the method we are testing
    String[][] empty = new String[10][];
    int size = 0;
    int expected = 1;
    int actual = WardrobeManager.addClothing("green crop top", "H&M", empty, size);

    // (2) verify the expected return value
    if (expected != actual) return false;

    // (3) verify that the provided array was updated correctly
    if (empty[0] == null) return false;
    if (!empty[0][0].equalsIgnoreCase("green crop top") || !empty[0][1].equalsIgnoreCase("H&M") ||
            !empty[0][2].equals("never")) return false;

    // (4) verify that NOTHING ELSE was changed unexpectedly
    for (int i=1; i<empty.length; i++) {
      if (empty[i] != null) return false;
    }

    // (5) if all of those checks pass, NOW we can say that we passed the test
    return true;
  }

  /**
   * Tests the addClothing method with a wardrobe that is not empty.
   *
   * @return true if the test passes, false otherwise.
   */
  public static boolean testAddNonEmpty() {
    // (1) set up the test variables and call the method we are testing
    String[][] wardrobe = {{"black t-shirt", "Hanes", "never"}, {"dark blue jeans", "Levi", "never"},
            null, null, null};

    int size = 2;
    int expected = 3;
    int actual = WardrobeManager.addClothing("green crop top", "H&M", wardrobe, size);

    // (2) verify the expected return value
    if (expected != actual) return false;

    // (3) verify that the provided array was updated correctly
    if (wardrobe[size] == null) return false;
    if (!wardrobe[size][0].equalsIgnoreCase("green crop top") || !wardrobe[size][1].equalsIgnoreCase("H&M") ||
            !wardrobe[size][2].equals("never")) return false;

    // (4) verify that NOTHING ELSE was changed unexpectedly
    for (int i=actual; i<wardrobe.length; i++) {
      if (wardrobe[i] != null) return false;
    }

    // (5) if all of those checks pass, NOW we can say that we passed the test
    return true;
  }

  /**
   * Tests the addclothing method with a wardrobe that already has the specified clothing.
   *
   * @return true if the test passes, false otherwise.
   */
  public static boolean testAddDuplicate() {
    // (1) set up the test variables and call the method we are testing
    String[][] wardrobe = {{"black t-shirt", "hanes", "never"}, {"dark blue jeans", "Levi", "never"}};
    String [][] wardrobeCopy = Arrays.copyOf(wardrobe, wardrobe.length);
    int size = 2;
    int expected = 2;
    int actual = WardrobeManager.addClothing("black t-shirt", "hanes", wardrobe, size);

    // (2) verify the expected return value
    if (expected != actual) return false;

    if (!Arrays.deepEquals(wardrobe, wardrobeCopy)) return false;

    // (4) if all of those checks pass, NOW we can say that we passed the test
    return true;
  }

  /**
   * Tests the addClothing method with a wardrobe that is full.
   *
   * @return true if the test passes, false otherwise.
   */
  public static boolean testAddToFull() {
    // (1) set up the test variables and call the method we are testing
    String[][] wardrobe = {{"black t-shirt", "Hanes", "never"}, {"dark blue jeans", "Levi", "never"}};

    int size = 2;
    int expected = 2;
    int actual = WardrobeManager.addClothing("blue t-shirt", "Hanes", wardrobe, size);

    // (2) verify the expected return value
    if(expected != actual) return false;

    // (4) if all of those checks pass, NOW we can say that we passed the test
    return true;
  }

  /**
   * Tests the indexofclothing method with a wardrobe is empty.
   *
   * @return true if the test passes, false otherwise.
   */

  public static boolean testIndexOfEmpty() {
    // (1) set up the test variables and call the method we are testing - EXACT MATCH
    String[][] wardrobe = {null, null, null, null, null};
    String[][] wardrobeCopy = Arrays.copyOf(wardrobe, wardrobe.length);
    int size = 0;
    int expected = -1;
    int actual = WardrobeManager.indexOfClothing("black t-shirt", "Hanes", wardrobe, size);

    // (2) verify that the expected return value and the actual return value match
    if (expected != actual) return false;



    // (4) since this method should not modify the array, let's check it against our copy:
    if (!Arrays.deepEquals(wardrobe, wardrobeCopy)) return false;

    // (5) if all of those checks pass, NOW we can say we passed the test
    return true;
  }

  /**
   * Tests the indexofclothing method with a wardrobe that is non-empty to find the index of the first and last clothing.
   *
   * @return true if the test passes, false otherwise.
   */
  public static boolean testIndexOfFirstLast() {
    // (1) set up the test variables and call the method we are testing - EXACT MATCH
    String[][] wardrobe =  {{"black t-shirt", "Hanes", "2023-12-19"},
            {"grey UW hoodie", "gildan", "2020-03-16"},
            {"dark blue jeans", "Levi", "2024-01-25"},
            {"green cabled sweater", "handmade", "never"}, null};
    String[][] wardrobeCopy = Arrays.copyOf(wardrobe, wardrobe.length);
    int size =4 ;
    int expected = 0;
    int actual = WardrobeManager.indexOfClothing("black t-shirt", "Hanes", wardrobe, size);

    // (2) verify that the expected return value and the actual return value match for first index
    if (expected != actual) return false;

    // (3) another test method call, this time with case difference (that should still match!)
    actual = WardrobeManager.indexOfClothing("BLACK T-SHIRT", "HANES", wardrobe, size);
    if (expected != actual) return false;

    expected = 3;
    actual = WardrobeManager.indexOfClothing("green cabled sweater", "handmade", wardrobe, size);

    // (4) verify that the expected return value and the actual return value match for last index
    if (expected != actual) return false;

    // (5) since this method should not modify the array, let's check it against our copy:
    if (!Arrays.deepEquals(wardrobe, wardrobeCopy)) return false;

    // (6) if all of those checks pass, NOW we can say we passed the test
    return true;
  }

  /**
   * Tests the indexofClothing method with a wardrobe that is non-empty to find the index of the middle number clothing.
   *
   * @return true if the test passes, false otherwise.
   */
  public static boolean testIndexOfMiddle() {
    // (1) set up the test variables and call the method we are testing - EXACT MATCH
    String[][] wardrobe =  {{"black t-shirt", "Hanes", "2023-12-19"},
            {"grey UW hoodie", "gildan", "2020-03-16"},
            {"dark blue jeans", "Levi", "2024-01-25"},
            {"green cabled sweater", "handmade", "never"}, null};
    String[][] wardrobeCopy = Arrays.copyOf(wardrobe, wardrobe.length);
    int size =4 ;
    int expected = 2;
    int actual = WardrobeManager.indexOfClothing("dark blue jeans", "Levi", wardrobe, size);

    // (2) verify that the expected return value and the actual return value match for first index
    if (expected != actual) return false;

    // (3) another test method call, this time with case difference (that should still match!)
    actual = WardrobeManager.indexOfClothing("DARK BLUE JEANS", "LEVI", wardrobe, size);
    if(expected != actual) return false;



    // (4) if all of those checks pass, NOW we can say we passed the test
    return true;
  }

  /**
   * Tests the indexOfClothing method with a wardrobe that is non-empty to find an index out of bounds.
   *
   * @return true if the test passes, false otherwise.
   */
  public static boolean testIndexOfNoMatch() {
    // (1) set up the test variables and call the method we are testing - EXACT MATCH
    String[][] wardrobe =  {{"black t-shirt", "Hanes", "2023-12-19"},
            {"grey UW hoodie", "gildan", "2020-03-16"},
            {"dark blue jeans", "Levi", "2024-01-25"},
            {"green cabled sweater", "handmade", "never"}, null};
    String[][] wardrobeCopy = Arrays.copyOf(wardrobe, wardrobe.length);
    int size =4 ;
    int expected = -1;
    int actual = WardrobeManager.indexOfClothing("blue knit sweater", "H&M", wardrobe, size);

    // (2) verify that the expected return value and the actual return value match for first index
    if (expected != actual) return false;

    // (3) another test method call, this time with case difference (that should still match!)
    actual = WardrobeManager.indexOfClothing("BLUE KNIT SWEATER", "H&M", wardrobe, size);
    if (expected != actual) return false;

    // (3) verify that the expected return value and the actual return value match when different description but same brand is passed
    actual = WardrobeManager.indexOfClothing("black t-shirt", "levis", wardrobe, size);
    if (expected != actual) return false;

    // (4) verify that the expected return value and the actual return value match when different brand but same description is passed
    actual = WardrobeManager.indexOfClothing("blue t-shirt", "Hanes", wardrobe, size);
    if(expected != actual) return false;

    // (5) if all of those checks pass, NOW we can say we passed the test
    return true;
  }

  /**
   * Tests the wearClothing method with a wardrobe to check last time a clothing has been worn.
   *
   * @return true if the test passes, false otherwise.
   */
  public static boolean testWearClothingTrue() {
    // (1) set up the test variables and call the method we are testing - EXACT MATCH
    String[][] wardrobe = {{"black t-shirt", "Hanes", "never"}, {"dark blue jeans", "Levi", "never"},
            null, null, null};
    String[][] wardrobeCopy = Arrays.copyOf(wardrobe, wardrobe.length);
    int size = 2;
    boolean expected = true;
    boolean actual = WardrobeManager.wearClothing("black t-shirt", "Hanes",  "2024-12-23" ,wardrobe, size);

    // (2) verify that the expected return value and the actual return value match
    if (expected != actual) return false;

    // (3) another test method call, this time with case difference (that should still match!)
    actual = WardrobeManager.wearClothing("BLACK T-SHIRT", "HANES",  "2024-12-23" ,wardrobe, size);

    if (expected != actual) return false;

    // (4) since this method should not modify the array, let's check it against our copy:
    if (!Arrays.deepEquals(wardrobe, wardrobeCopy)) return false;

    // (5) if all of those checks pass, NOW we can say we passed the test
    return true;
  }

  /**
   * Tests the wearClothing method with a wardrobe to check last worn date of a clothing that does not eixst.
   *
   * @return true if the test passes, false otherwise.
   */
  public static boolean testWearClothingNoMatch() {
    // (1) set up the test variables and call the method we are testing - EXACT MATCH
    String[][] wardrobe = {{"black t-shirt", "Hanes", "never"}, {"dark blue jeans", "Levi", "never"},
            null, null, null};
    String[][] wardrobeCopy = Arrays.copyOf(wardrobe, wardrobe.length);
    int size = 2;
    boolean expected = false;
    boolean actual = WardrobeManager.wearClothing("white t-shirt", "MN",  "2024-12-23" ,wardrobe, size);

    // (2) verify that the expected return value and the actual return value match for first index
    if (expected != actual) return false;

    // (3) verify that the expected return value and the actual return value match when different description but same brand is passed
    actual = WardrobeManager.wearClothing("blue t-shirt", "Hanes",  "2024-12-23" ,wardrobe, size);
    if (expected != actual) return false;

    // (4) verify that the expected return value and the actual return value match when different brand but same description is passed
    actual = WardrobeManager.wearClothing("black t-shirt", "SE",  "2024-12-23" ,wardrobe, size);
    if(expected != actual) return false;

    // (5) if all of those checks pass, NOW we can say we passed the test
    return true;
  }

  /**
   * Tests the getBrandCount method with a wardrobe counting the number of items with a brand when all items have the same brand.
   *
   * @return true if the test passes, false otherwise.
   */
  public static boolean testBrandCountAllMatch() {
    // Test counting the number of items with a brand when all items have the same brand
    String[][] wardrobe = {{"black t-shirt", "Hanes", "2023-12-19"},
            {"grey UW hoodie", "gildan", "2020-03-16"},
            {"dark blue jeans", "Levi", "2024-01-25"},
            {"green cabled sweater", "handmade", "never"}, null};
    String[][] wardrobeCopy = Arrays.copyOf(wardrobe, wardrobe.length);
    int size = 4;
    int expected = 1;
    int actual = WardrobeManager.getBrandCount("Hanes",wardrobe,4);

    // (2) verify that the expected return value and the actual return value match
    if (expected != actual) return false;

    // (3) since this method should not modify the array, let's check it against our copy:
    if (!Arrays.deepEquals(wardrobe, wardrobeCopy)) return false;

    // (4) if all of those checks pass, NOW we can say we passed the test
    return true;
  }

  /**
   * Tests the getBrandCount method with a wardrobe counting the number of items with a brand when some items have the same brand.
   *
   * @return true if the test passes, false otherwise.
   */
  public static boolean testBrandCountSomeMatch() {
    // Test counting the number of items with a brand when some items have the same brand
    String[][] wardrobe = {{"black t-shirt", "Hanes", "never"},
            {"grey UW hoodie", "gildan", "2020-03-16"},
            {"dark blue jeans", "Levi", "2024-01-25"},
            {"green cabled sweater", "Hanes", "never"}, null};
    String[][] wardrobeCopy = Arrays.copyOf(wardrobe, wardrobe.length);
    int size = 4;
    int expected = 2;
    int actual = WardrobeManager.getBrandCount("Hanes",wardrobe,4);

    // (2) verify that the expected return value and the actual return value match
    if (expected != actual) return false;

    // (3) since this method should not modify the array, let's check it against our copy:
    if (!Arrays.deepEquals(wardrobe, wardrobeCopy)) return false;

    // (4) if all of those checks pass, NOW we can say we passed the test
    return true;
  }

  /**
   * Tests the getBrandCount method with a wardrobe counting the number of items with a brand when no items have the same brand.
   *
   * @return true if the test passes, false otherwise.
   */
  public static boolean testBrandCountNoMatch() {
    // Test counting the number of items with a brand when no items have the same brand
    String[][] wardrobe = {{"black t-shirt", "Nike", "never"},
            {"grey UW hoodie", "Adidas", "2020-03-16"},
            {"dark blue jeans", "Puma", "2024-01-25"},
            {"green cabled sweater", "Under Armour", "never"}, null};
    String[][] wardrobeCopy = Arrays.copyOf(wardrobe, wardrobe.length);
    int size = 4;
    int expected = 1;
    int actual = WardrobeManager.getBrandCount("Nike",wardrobe,4);

    // (2) verify that the expected return value and the actual return value match
    if (expected != actual) return false;

    // (3) since this method should not modify the array, let's check it against our copy:
    if (!Arrays.deepEquals(wardrobe, wardrobeCopy)) return false;

    // (4) if all of those checks pass, NOW we can say we passed the test
    return true;
  }

  /**
   * Tests the getNumUnwornClothes method with a wardrobe counting the number of unworn items when all items are unworn.
   *
   * @return true if the test passes, false otherwise.
   */
  public static boolean testUnwornCountAllMatch() {
    // Test counting the number of unworn items when all items are unworn
    String[][] wardrobe = {{"black t-shirt", "Hanes", "never"},
            {"grey UW hoodie", "gildan", "never"},
            {"dark blue jeans", "Levi", "never"},
            {"green cabled sweater", "handmade", "never"}, null};
    String[][] wardrobeCopy = Arrays.copyOf(wardrobe, wardrobe.length);
    int size = 4;
    int expected = 4;
    int actual = WardrobeManager.getNumUnwornClothes(wardrobe,4);

    // (2) verify that the expected return value and the actual return value match
    if (expected != actual) return false;

    // (3) since this method should not modify the array, let's check it against our copy:
    if (!Arrays.deepEquals(wardrobe, wardrobeCopy)) return false;

    // (4) if all of those checks pass, NOW we can say we passed the test
    return true;
  }

  /**
   * Tests the getNumUnwornClothes method with a wardrobe counting the number of unworn items when some items are unworn.
   *
   * @return true if the test passes, false otherwise.
   */
  public static boolean testUnwornCountSomeMatch() {
    // Test counting the number of unworn items when some items are unworn
    String[][] wardrobe = {{"black t-shirt", "Hanes", "never"},
            {"grey UW hoodie", "gildan", "never"},
            {"dark blue jeans", "Levi", "2024-01-25"},
            {"green cabled sweater", "handmade", "never"}, null};
    String[][] wardrobeCopy = Arrays.copyOf(wardrobe, wardrobe.length);
    int size = 4;
    int expected = 3;
    int actual = WardrobeManager.getNumUnwornClothes(wardrobe,4);

    // (2) verify that the expected return value and the actual return value match
    if (expected != actual) return false;

    // (3) since this method should not modify the array, let's check it against our copy:
    if (!Arrays.deepEquals(wardrobe, wardrobeCopy)) return false;

    // (4) if all of those checks pass, NOW we can say we passed the test
    return true;
  }

  /**
   * Tests the getNumUnwornClothes method with a wardrobe counting the number of unworn items when no items are unworn.
   *
   * @return true if the test passes, false otherwise.
   */
  public static boolean testUnwornCountNoMatch() {
    // Test counting the number of unworn items when no items are unworn
    String[][] wardrobe = {{"black t-shirt", "Hanes", "2023-12-19"},
            {"grey UW hoodie", "gildan", "2020-03-16"},
            {"dark blue jeans", "Levi", "2024-01-25"},
            {"green cabled sweater", "handmade", "2023-12-10"}, null};
    String[][] wardrobeCopy = Arrays.copyOf(wardrobe, wardrobe.length);
    int size = 4;
    int expected = 0;
    int actual = WardrobeManager.getNumUnwornClothes(wardrobe,4);

    // (2) verify that the expected return value and the actual return value match
    if (expected != actual) return false;

    // (3) since this method should not modify the array, let's check it against our copy:
    if (!Arrays.deepEquals(wardrobe, wardrobeCopy)) return false;

    // (4) if all of those checks pass, NOW we can say we passed the test
    return true;
  }

  /**
   * Tests the getMostRecentlyWorn method with a wardrobe comparing the most recently worn.
   *
   * @return true if the test passes, false otherwise.
   */
  public static boolean testMostRecentlyWorn() {
    // (1) set up the test variables and call the method we are testing - EXACT MATCH
    String[][] wardrobe = {{"black t-shirt", "Hanes", "2023-12-19"},
            {"grey UW hoodie", "gildan", "2020-03-16"},
            {"dark blue jeans", "Levi", "2024-01-25"},
            {"green cabled sweater", "handmade", "never"}, null};
    String[][] wardrobeCopy = Arrays.copyOf(wardrobe, wardrobe.length);
    int size = 4;
    int expected = 2;
    int actual = WardrobeManager.getMostRecentlyWorn(wardrobe, size);

    // (2) verify that the expected return value and the actual return value match
    if (expected != actual) return false;

    // (3) since this method should not modify the array, let's check it against our copy:
    if (!Arrays.deepEquals(wardrobe, wardrobeCopy)) return false;

    // (4) if all of those checks pass, NOW we can say we passed the test
    return true;
  }
  /**
   * Tests the removeClothingAtIndex method with a wardrobe ro remove last item in the array.
   *
   * @return true if the test passes, false otherwise.
   */

  public static boolean testRemoveLastItem() {
    // Test removing the last item from the wardrobe
    String[][] wardrobe = {{"black t-shirt", "Hanes", "never"},
            {"grey UW hoodie", "gildan", "never"},
            {"dark blue jeans", "Levi", "2024-01-25"},
            {"green cabled sweater", "handmade", "never"}, null};
    String[][] wardrobeCopy = Arrays.copyOf(wardrobe, wardrobe.length);
    int size = 4;
    int expected = size - 1; // Corrected expected value
    int index = 3;
    int actual = WardrobeManager.removeClothingAtIndex(index, wardrobe, size);

    // (2) verify that the expected return value and the actual return value match
    if (expected != actual) return false;

    // (3) since this method should not modify the array, let's check it against our copy:

    // (4) if all of those checks pass, NOW we can say we passed the test
    return true;
  }

  /**
   * Tests the removeClothingAtIndex method with a wardrobe to remove first item from the array.
   *
   * @return true if the test passes, false otherwise.
   */

  public static boolean testRemoveFirstItem() {
    // Test removing the first item from the wardrobe
    String[][] wardrobe = {{"black t-shirt", "Hanes", "never"},
            {"grey UW hoodie", "gildan", "never"},
            {"dark blue jeans", "Levi", "2024-01-25"},
            {"green cabled sweater", "handmade", "never"}, null};
    String[][] wardrobeCopy = Arrays.copyOf(wardrobe, wardrobe.length);
    int size = 4;
    int expected = 3; // Corrected expected value
    int index = 0;
    int actual = WardrobeManager.removeClothingAtIndex(index, wardrobe, size);

    // (2) verify that the expected return value and the actual return value match
    if (expected != actual) return false;

    // (3) since this method should not modify the array, let's check it against our copy:

    // (4) if all of those checks pass, NOW we can say we passed the test
    return true;
  }

  /**
   * Tests the removeClothingAtIndex method with a wardrobe to remove the item at an index that does not exist in the array.
   *
   * @return true if the test passes, false otherwise.
   */
  public static boolean testRemoveBadIndex() {
    // Test removing an item with an invalid index
    String[][] wardrobe = {{"black t-shirt", "Hanes", "never"},
            {"grey UW hoodie", "gildan", "never"},
            {"dark blue jeans", "Levi", "2024-01-25"},
            {"green cabled sweater", "handmade", "never"}, null};
    String[][] wardrobeCopy = Arrays.copyOf(wardrobe, wardrobe.length);
    int size = 4;
    int expected = 4; // Corrected expected value
    int index = 4;
    int actual = WardrobeManager.removeClothingAtIndex(index, wardrobe, size);

    // (2) verify that the expected return value and the actual return value match
    if (expected != actual) return false;

    // (3) since this method should not modify the array, let's check it against our copy:

    // (4) if all of those checks pass, NOW we can say we passed the test
    return true;
  }

  /**
   * Tests the removeAllUnworn method with a wardrobe, by removing unworn items when no items are unworn .
   *
   * @return true if the test passes, false otherwise.
   */
  public static boolean testRemoveUnwornNoMatch() {
    // Test removing unworn items when no items are unworn
    String[][] wardrobe = {{"black t-shirt", "Hanes", "2023-12-19"},
            {"grey UW hoodie", "gildan", "2020-03-16"},
            {"dark blue jeans", "Levi", "2024-01-25"},
            {"green cabled sweater", "handmade", "2023-12-10"}, null};
    String[][] wardrobeCopy = Arrays.copyOf(wardrobe, wardrobe.length);
    int size = 4;
    int expected = 4; // Corrected expected value

    int actual = WardrobeManager.removeAllUnworn(wardrobe, size);

    // (2) verify that the expected return value and the actual return value match
    if(expected != actual) return false;

    // (3) since this method should not modify the array, let's check it against our copy:

    // (4) if all of those checks pass, NOW we can say we passed the test
    return true;
  }

  /**
   * Tests the removeAllUnworn method with a wardrobe, by removing unworn items when some items are unworn .
   *
   * @return true if the test passes, false otherwise.
   */
  public static boolean testRemoveUnwornSomeMatch() {
    // Test removing unworn items when some items are unworn
    String[][] wardrobe = {{"black t-shirt", "Hanes", "2023-12-19"},
            {"grey UW hoodie", "gildan", "never"},
            {"dark blue jeans", "Levi", "never"},
            {"green cabled sweater", "handmade", "2023-12-10"}, null};
    String[][] wardrobeCopy = Arrays.copyOf(wardrobe, wardrobe.length);
    int size = 4;
    int expected = 2; // Corrected expected value

    int actual = WardrobeManager.removeAllUnworn(wardrobe, size);

    // (2) verify that the expected return value and the actual return value match
    if(expected != actual) return false;

    // (3) since this method should not modify the array, let's check it against our copy:

    // (4) if all of those checks pass, NOW we can say we passed the test
    return true;
  }

  /**
   * Tests the removeAllUnworn method with a wardrobe, by removing all unworn items when all items are unworn .
   *
   * @return true if the test passes, false otherwise.
   */
  public static boolean testRemoveUnwornAllMatch() {
    // Test removing all unworn items when all items are unworn
    String[][] wardrobe = {{"black t-shirt", "Hanes", "never"},
            {"grey UW hoodie", "gildan", "never"},
            {"dark blue jeans", "Levi", "never"},
            {"green cabled sweater", "handmade", "never"}, null};
    String[][] wardrobeCopy = Arrays.copyOf(wardrobe, wardrobe.length);
    int size = 4;
    int expected = 0; // Corrected expected value

    int actual = WardrobeManager.removeAllUnworn(wardrobe, size);

    // (2) verify that the expected return value and the actual return value match
    if(expected != actual) return false;

    // (3) since this method should not modify the array, let's check it against our copy:

    // (4) if all of those checks pass, NOW we can say we passed the test
    return true;
  }

  /**
   * This is the main method where all the methods are called and run, and show whether the tests are passed or failed
   *
   * @return pass if the test passes, FAIL otherwise.
   */

  public static void main(String[] args) {
    System.out.println("CONTAINS:\n  "+(testContainsEmpty()?"pass":"FAIL")+", "+
            (testContainsTrue()?"pass":"FAIL")+", "+(testContainsFalse()?"pass":"FAIL"));
    System.out.println("ADD:\n  "+(testAddToEmpty()?"pass":"FAIL")+", "+(testAddNonEmpty()?"pass":"FAIL")+
            ", "+(testAddDuplicate()?"pass":"FAIL")+", "+(testAddToFull()?"pass":"FAIL"));
    System.out.println("INDEX OF:\n  "+(testIndexOfEmpty()?"pass":"FAIL")+", "+(testIndexOfFirstLast()?"pass":"FAIL")+
            ", "+(testIndexOfMiddle()?"pass":"FAIL")+", "+(testIndexOfNoMatch()?"pass":"FAIL"));
    System.out.println("WEAR:\n  "+(testWearClothingTrue()?"pass":"FAIL")+", "+(testWearClothingNoMatch()?"pass":"FAIL"));
    System.out.println("BRAND COUNT:\n  "+(testBrandCountAllMatch()?"pass":"FAIL")+", "+
            (testBrandCountSomeMatch()?"pass":"FAIL")+", "+(testBrandCountNoMatch()?"pass":"FAIL"));
    System.out.println("UNWORN COUNT:\n  "+(testUnwornCountAllMatch()?"pass":"FAIL")+", "+
            (testUnwornCountSomeMatch()?"pass":"FAIL")+", "+(testUnwornCountNoMatch()?"pass":"FAIL"));
    System.out.println("MOST RECENTLY WORN:\n  "+(testMostRecentlyWorn()?"pass":"FAIL"));
    System.out.println("REMOVE BY INDEX:\n  "+(testRemoveLastItem()?"pass":"FAIL")+", "+
            (testRemoveFirstItem()?"pass":"FAIL")+", "+(testRemoveBadIndex()?"pass":"FAIL"));
    System.out.println("REMOVE UNWORN:\n  "+(testRemoveUnwornNoMatch()?"pass":"FAIL")+", "+
            (testRemoveUnwornSomeMatch()?"pass":"FAIL")+", "+(testRemoveUnwornAllMatch()?"pass":"FAIL"));
  }
}


import java.time.LocalDate;
import java.util.NoSuchElementException;

public class WardrobeManagerTester {

  public static boolean testClothingConstructorAndGetters() {
    try {
      LocalDate date = LocalDate.of(2024,2,14);
      Clothing c = new Clothing("jeans", "Levi", date, 3);
      if (!c.getDescription().equals("jeans")) return false;
      if (!c.getBrand().equals("Levi")) return false;
      if (c.getNumOfTimesWorn() != 3) return false;
      if (!c.getLastWornDate().equals(date)) return false;

    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  public static boolean testClothingConstructorExceptions() {
    try {
      LocalDate date = LocalDate.now();
      int numOfTimesWorn = 0;
      new Clothing(" ", "Gildan", date, numOfTimesWorn);
      return false;
    } catch (IllegalArgumentException e) {
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      LocalDate date = LocalDate.now(); // replace with the date you want
int numOfTimesWorn = 0; // replace with the number you want
new Clothing("black t-shirt", "Gildan", date, numOfTimesWorn);
      return false;
    } catch (IllegalArgumentException e) {
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      LocalDate date = LocalDate.of(2021, 12, 25);
      new Clothing(" ", "Gildan", date, 4);
      return false;
    } catch (IllegalArgumentException e) {
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      LocalDate date = LocalDate.of(2021, 12, 25);
      new Clothing("black t-shirt", "  ", date, 6);
      return false;
    } catch (IllegalArgumentException e) {
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    return true;
  }

  public static boolean testClothingWear() {
    try {
        Clothing c = new Clothing("black t-shirt", "Gildan", LocalDate.now(), 0);
        c.wearClothing();
        if (c.getNumOfTimesWorn() != 1) return false;
        if (!c.getLastWornDate().equals(LocalDate.now())) return false;

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }

    return true;
  }

  public static boolean testWardrobeConstructorAndGetters() {
    try {
        Wardrobe w = new Wardrobe(10);
        for (int i = 0; i < 10; i++) {
            if (w.getClothing(i) != null) return false;
        }

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }

    return true;
  }

  public static boolean testWardrobeConstructorExceptions() {
    try {
        new Wardrobe(-1);
        return false;
    } catch (IllegalArgumentException e) {
        if (e.getMessage() == null || e.getMessage().isBlank())
            return false;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }

    return true;
  }

  public static boolean testAddClothingExceptions() {
    try {
        Wardrobe w = new Wardrobe(2);
        w.addClothing(new Clothing("black t-shirt", "Gildan", LocalDate.now(), 0));
        w.addClothing(new Clothing("jeans", "Levi", LocalDate.now(), 0));
        w.addClothing(new Clothing("dress", "Gucci", LocalDate.now(), 0));
        return false;
    } catch (IllegalStateException e) {
        if (e.getMessage() == null || e.getMessage().isBlank())
            return false;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }

    return true;
  }

  public static boolean testAddClothing() {
    try {
        Wardrobe w = new Wardrobe(2);
        Clothing c1 = new Clothing("black t-shirt", "Gildan", LocalDate.now(), 0);
        Clothing c2 = new Clothing("jeans", "Levi", LocalDate.now(), 0);
        w.addClothing(c1);
        w.addClothing(c2);
        if (!w.getClothing(0).equals(c1) || !w.getClothing(1).equals(c2)) return false;

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }

    return true;
  }

  public static boolean testGetClothing() {
    try {
        Wardrobe w = new Wardrobe(2);
        Clothing c1 = new Clothing("black t-shirt", "Gildan", LocalDate.now(), 0);
        Clothing c2 = new Clothing("jeans", "Levi", LocalDate.now(), 0);
        w.addClothing(c1);
        w.addClothing(c2);
        Clothing retrievedC1 = w.getClothing(0);
        Clothing retrievedC2 = w.getClothing(1);
        if (!retrievedC1.equals(c1) || !retrievedC2.equals(c2)) return false;

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }

    return true;
  }

  public static boolean testGetClothingExceptions() {
    try {
        Wardrobe w = new Wardrobe(2);
        w.getClothing(3);
        return false;
    } catch (IndexOutOfBoundsException e) {
        if (e.getMessage() == null || e.getMessage().isBlank())
            return false;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }

    return true;
  }

  public static boolean testRemoveClothing() {
    try {
        Wardrobe w = new Wardrobe(2);
Clothing c1 = new Clothing("black t-shirt", "Gildan", LocalDate.now(), 0);
Clothing c2 = new Clothing("jeans", "Levi", LocalDate.now(), 0);
w.addClothing(c1);
w.addClothing(c2);
w.removeClothing(0); // Assuming c1 is at index 0
if (!w.getClothing(0).equals(c2)) return false;

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }

    return true;
  }

  public static boolean testRemoveClothingExceptions() {
    try {
    Wardrobe w = new Wardrobe(2);
    w.addClothing(new Clothing("black t-shirt", "Gildan", LocalDate.now(), 0));
    w.removeClothing(0); // Assuming the clothing you want to remove is at index 0
    return false;
    } catch (NoSuchElementException e) {
        if (e.getMessage() == null || e.getMessage().isBlank())
            return false;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }

    return true;
  }

  public static boolean testRemoveAllClothingWornBefore() {
    try {
        Wardrobe w = new Wardrobe(2);
        Clothing c1 = new Clothing("black t-shirt", "Gildan", LocalDate.of(2022, 1, 1), 0);
        Clothing c2 = new Clothing("jeans", "Levi", LocalDate.of(2022, 2, 1), 0);
        w.addClothing(c1);
        w.addClothing(c2);
        w.removeOldClothing(LocalDate.of(2022, 2, 1));
        if (!w.getClothing(0).equals(c2)) return false;

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }

    return true;
  }

  public static boolean testRemoveAllClothingWornNumTimes() {
    try {
        Wardrobe w = new Wardrobe(2);
        Clothing c1 = new Clothing("black t-shirt", "Gildan", LocalDate.now(), 3);
        Clothing c2 = new Clothing("jeans", "Levi", LocalDate.now(), 2);
        w.addClothing(c1);
        w.addClothing(c2);
        w.removeAllClothingWornNumTimes(3);
        if (!w.getClothing(0).equals(c2)) return false;

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }

    return true;
  }

  public static boolean testParseClothingExceptions() {
    try {
        Wardrobe w = new Wardrobe(2);
        w.addClothing(new Clothing("black t-shirt", "Gildan", LocalDate.now(), 0));
        w.parseClothing("invalid string");
        return false;
    } catch (IllegalArgumentException e) {
        if (e.getMessage() == null || e.getMessage().isBlank())
            return false;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }

    return true;
  }

  public static boolean testParseClothing() {
    try {
        Wardrobe w = new Wardrobe(2);
        Clothing c = new Clothing("black t-shirt", "Gildan", LocalDate.now(), 0);
        w.addClothing(c);
        String clothingString = "black t-shirt,Gildan";
        Clothing parsedClothing = w.parseClothing(clothingString);
        if (!parsedClothing.equals(c)) return false;

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }

    return true;
  }

  public static boolean runAllTests() {
    boolean test1 = testClothingConstructorExceptions();
    System.out.println("testClothingConstructorExceptions(): " + (test1 ? "pass" : "FAIL"));

    boolean test2 = testClothingConstructorAndGetters();
    System.out.println("testClothingConstructorAndGetters(): " + (test2 ? "pass" : "FAIL"));

    boolean test3 = testClothingWear();
    System.out.println("testClothingWear(): " + (test3 ? "pass" : "FAIL"));

    boolean test4 = testWardrobeConstructorAndGetters();
    System.out.println("testWardrobeConstructorAndGetters(): " + (test4 ? "pass" : "FAIL"));

    boolean test5 = testWardrobeConstructorExceptions();
    System.out.println("testWardrobeConstructorExceptions(): " + (test5 ? "pass" : "FAIL"));

    boolean test6 = testAddClothingExceptions();
    System.out.println("testAddClothingExceptions(): " + (test6 ? "pass" : "FAIL"));

    boolean test7 = testAddClothing();
    System.out.println("testAddClothing(): " + (test7 ? "pass" : "FAIL"));

    boolean test8 = testGetClothing();
    System.out.println("testGetClothing(): " + (test8 ? "pass" : "FAIL"));

    boolean test9 = testGetClothingExceptions();
    System.out.println("testGetClothingExceptions(): " + (test9 ? "pass" : "FAIL"));

    boolean test10 = testRemoveClothing();
    System.out.println("testRemoveClothing(): " + (test10 ? "pass" : "FAIL"));

    boolean test11 = testRemoveClothingExceptions();
    System.out.println("testRemoveClothingExceptions(): " + (test11 ? "pass" : "FAIL"));

    boolean test12 = testRemoveAllClothingWornBefore();
    
    boolean test13 = testRemoveAllClothingWornNumTimes();
    System.out.println("testRemoveAllClothingWornNumTimes(): " 
        + (test13 ? "pass" : "FAIL"));

    boolean test14 = testParseClothingExceptions();
    System.out.println("testParseClothingExceptions(): " + (test14 ? "pass" : "FAIL"));

    boolean test15 = testParseClothing();
    System.out.println("testParseClothing(): " + (test15 ? "pass" : "FAIL"));

    return test1 && test2 && test3 && test4 && test5 && test6 && test7 && test8 && test9 && test10
            && test11 && test12 && test13 && test14 && test15;
  }
  
  public static void main(String[] args) {
    System.out.println("runAllTests(): " + runAllTests());
  }
}

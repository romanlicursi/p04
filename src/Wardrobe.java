import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Wardrobe {
    private static Clothing[] wardrobe;
    private static int wardrobeSize;
    private ArrayList<Object> clothingList;

    public Wardrobe(int capacity) {
        this.wardrobe = new Clothing[capacity];
        this.wardrobeSize = 0;
    }

    public Clothing parseClothing(String clothingData) throws ParseException {
        String[] parts = clothingData.split(",");
        if (parts.length != 2) {
            throw new ParseException("Invalid clothing data", 0);
        }
        String brand = parts[0].trim();
        String description = parts[1].trim();
        LocalDate lastWornDate = LocalDate.now(); // replace with actual date
        int timesWorn = 0; // replace with actual times worn
        Clothing clothing = new Clothing(description, brand, lastWornDate, timesWorn);
        addClothing(clothing); 
        return clothing;
    }

    public void addClothing(Clothing clothing) {
        if (wardrobeSize == wardrobe.length) {
            expandWardrobe();
        }
        wardrobe[wardrobeSize++] = clothing;
    }

    private void expandWardrobe() {
        Clothing[] newWardrobe = new Clothing[wardrobe.length * 2];
        System.arraycopy(wardrobe, 0, newWardrobe, 0, wardrobe.length);
        wardrobe = newWardrobe;
    }

    public Clothing getClothing(int index) {
        if (index < 0 || index >= wardrobeSize) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        return wardrobe[index];
    }

    public void removeOldClothing(LocalDate date) {
    // Assuming clothingList is the list of Clothing objects in the Wardrobe
clothingList.removeIf(clothing -> clothing.getLastWornDate().isBefore(date));}

    public void removeClothing(int index) {
        if (index < 0 || index >= wardrobeSize) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        System.arraycopy(wardrobe, index + 1, wardrobe, index, wardrobeSize - index - 1);
        wardrobe[--wardrobeSize] = null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < wardrobeSize; i++) {
            sb.append(wardrobe[i]).append("\n");
        }
        return sb.toString();
    }

    public void removeAllClothingWornNumTimes(int threshold) {
    int index = 0;
    for (int i = 0; i < wardrobeSize; i++) {
        if (wardrobe[i].getNumTimesWorn() >= threshold) {
            wardrobe[index++] = wardrobe[i];
        }
    }
    for (int i = index; i < wardrobeSize; i++) {
        wardrobe[i] = null;
    }
    wardrobeSize = index;
}

    public boolean loadFromFile(File saveFile) {
    boolean parsedSuccessfully = false;
    try (Scanner scanner = new Scanner(saveFile)) {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            if (parts.length == 4) {
                try {
                    String description = parts[0].trim();
                    String brand = parts[1].trim();
                    LocalDate lastWornDate = LocalDate.parse(parts[2].trim(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                    int timesWorn = Integer.parseInt(parts[3].trim());
                    Clothing clothing = new Clothing(description, brand, lastWornDate, timesWorn);
                    addClothing(clothing);
                    parsedSuccessfully = true;
                } catch (Exception e) {
                    System.out.println("Cannot parse line to Clothing object: " + line);
                }
            } else {
                System.out.println("Cannot parse line to Clothing object: " + line);
            }
        }
    } catch (FileNotFoundException e) {
        System.out.println("File not found: " + saveFile);
    } catch (FileNotFoundException e) {
        throw new RuntimeException(e);
    }
        return parsedSuccessfully;
}

    public boolean saveToFile(File saveFile) {
    try (PrintWriter writer = new PrintWriter(saveFile)) {
        for (int i = 0; i < wardrobeSize; i++) {
            Clothing clothing = wardrobe[i];
            String description = clothing.getDescription();
            String brand = clothing.getBrand();
            String lastWornDate = clothing.getLastWornDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            int timesWorn = clothing.getTimesWorn();
            writer.println(description + "," + brand + "," + lastWornDate + "," + timesWorn);
        }
        return true;
    } catch (FileNotFoundException e) {
        System.out.println("File not found: " + saveFile);
        return false;
    }
}
}
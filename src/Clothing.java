import java.time.LocalDate;

public class Clothing {
    private String brand;
    private String description;
    private LocalDate lastWornDate;
    private int numOfTimesWorn;

    public Clothing(String brand, String description, LocalDate lastWornDate, int numOfTimesWorn) {
    this.brand = brand;
    this.description = description;
    this.lastWornDate = lastWornDate;
    this.numOfTimesWorn = numOfTimesWorn;
}

    public String getBrand() {
        return brand;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getLastWornDate() {
        return lastWornDate;
    }

    public int getNumOfTimesWorn() {
        return numOfTimesWorn;
    }

    public void wearClothing() {
        numOfTimesWorn++;
        lastWornDate = LocalDate.now();
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Clothing clothing = (Clothing) obj;
        return brand.equalsIgnoreCase(clothing.brand) && description.equalsIgnoreCase(clothing.description);
    }

    public int getNumTimesWorn() {
    return numOfTimesWorn;
}
}
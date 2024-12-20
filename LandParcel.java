import java.text.DecimalFormat;

class LandParcel {
    private String parcelID;
    private String ownerName;
    private double landSizeSquareMeters;
    private char landType;
    private String landLocation;
    private double landPrice;

    // Constructor
    public LandParcel(String parcelID, String ownerName, double landSizeSquareMeters, char landType, String landLocation, double landPrice) {
        this.parcelID = parcelID;
        this.ownerName = ownerName;
        this.landSizeSquareMeters = landSizeSquareMeters;
        this.landType = landType;
        this.landLocation = landLocation;
        this.landPrice = landPrice;
    }

    // Accessor methods
    public String getParcelID() {
        return parcelID;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public double getLandSizeSquareMeters() {
        return landSizeSquareMeters;
    }

    public char getLandType() {
        return landType;
    }

    public String getLandLocation() {
        return landLocation;
    }

    public double getLandPrice() {
        return landPrice;
    }

    // Mutator methods
    public void setParcelID(String parcelID) {
        this.parcelID = parcelID;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setLandSizeSquareMeters(double landSizeSquareMeters) {
        this.landSizeSquareMeters = landSizeSquareMeters;
    }

    public void setLandType(char landType) {
        this.landType = landType;
    }

    public void setLandLocation(String landLocation) {
        this.landLocation = landLocation;
    }

    public void setLandPrice(double landPrice) {
        this.landPrice = landPrice;
    }

    // Processor method
    public double calculateTotalPrice() {
        return landSizeSquareMeters * landPrice;
    }

    // Display method
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.00");
        return "\nParcel ID: " + parcelID + "\nOwner Name: " + ownerName +
                "\nLand Size: " + landSizeSquareMeters + " square meters" +
                "\nLand Type: " + landType + "\nLand Location: " + landLocation +
                "\nLand Price per square meter: RM" + landPrice +
                "\nTotal Price: RM" + df.format(calculateTotalPrice());
    }
    public String toStringToFile()
    {
        DecimalFormat df = new DecimalFormat("#.00");
        return "\n" + parcelID + "\n " + ownerName + "\n" + landSizeSquareMeters +  " square meters" + "\n" + landType + "\n" + landLocation
                + "\n" + landPrice + "\n " + df.format(calculateTotalPrice());
    }
}
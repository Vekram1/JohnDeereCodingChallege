public class Field {
    public String name;
    public String location;
    public int acres;
    public String seed;
    public String spray;
    public boolean harvest;
    public boolean tillage;

    public Field(String name, String location, int acres) {
        this.name = name;
        this.location = location;
        this.acres = acres;
    }

    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }

    public String getSpray() {
        return spray;
    }

    public void setSpray(String spray) {
        this.spray = spray;
    }

    public boolean isHarvest() {
        return harvest;
    }

    public void setHarvest(boolean harvest) {
        this.harvest = harvest;
    }

    public boolean isTillage() {
        return tillage;
    }

    public void setTillage(boolean tillage) {
        this.tillage = tillage;
    }

    public Field() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getAcres() {
        return acres;
    }

    public void setAcres(int acres) {
        this.acres = acres;
    }

    @Override
    public String toString() {
        return String.format("Your field's name: %s\nYour field's location: %s\nYour field's size in acres: %d" +
                        "\nYour field seeds: %s\nYour field's spray %s\nYour field's harvest state is %b" +
                        "\nYour field's tillage state is %b"
                , getName(), getLocation(), getAcres(), getSeed(), getSpray(), isHarvest(), isTillage());
    }

    public String toString1() {
        return String.format("%s\nlocation: %s\nacres: %d\n"
                , getName(), getLocation(), getAcres());
    }

    public String toString2() {
        return String.format("%s\nlocation: %s\nacres: %d" +
                        "\nseeds: %s\nspray: %s\nHarvest State: %b" +
                        "\nTillage State: %b"
                , getName(), getLocation(), getAcres(), getSeed(), getSpray(), isHarvest(), isTillage());
    }
}

public class Thing {
    // Basic Thing type that defines all objects in the Adventure

    private String name;
    private String description;

    public Thing(String aName, String aDescription) {
        // constructor
        this.name = aName;
        this.description = aDescription;
    }

    public String getName() {
        return name;
    }

    public void setName(String aName) {
        this.name = aName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String aDescription) {
        this.description = aDescription;
    }
}

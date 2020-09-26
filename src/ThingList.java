import java.util.ArrayList;

public class ThingList extends ArrayList<Thing>
{
    public String describeThings() {
        String s = "";
        if (this.size() == 0) {
            s = "nothing.\n";
        } else {
            for (Thing t : this) {
                s = s + t.getName() + ": " + t.getDescription() + "\n";
            }
        }
        return s;
    }

    public Thing thisOb(String aName) {
        Thing athing = null;
        String thingName = "";
        String aNameLowCase = aName.trim().toLowerCase();
        for (Thing t : this) {
            thingName = t.getName().trim().toLowerCase();
            if (thingName.equals(aNameLowCase)) {
                athing = t;
            }
        }
        return athing;
    }
}

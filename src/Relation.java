
//Tags
// Relation#Gender
// Relations
// Pa - Parents
// Pr - Partner
// Sy - Symlinks
// Ch - Children
// Sl - Self

public class Relation {
    public final String rel;
    public final String gnd;
    private final String rels = "SlFrPaSyPrCh";
    private final String gnds = "MF";
    private final static String[] SupStr = {"SlFr", "PaPr", "SyCh"};
    private  final static String[] SubStr = {"FrPaSyPrCh", "PaSyFr", "PrChFr"};

    public String[] seperat(String tag) {
        return tag.split("#");
    }
    public String Rel() {
        return this.rel + "#" + this.gnd;
    }
    public String toString() {
        return this.Rel();
    }
    private void validate() {
        if ((!this.rels.contains(this.rel)) || (!this.gnds.contains(this.gnd))) throw new IllegalArgumentException("Invalid Tag ");
    }
    public static boolean CheckStruct(String sup, String sub) {
        for (int i = 0; i != 3; i++) {
            if (Relation.SupStr[i].contains(sup) && Relation.SubStr[i].contains(sub)) return true;
        }
        return false;
    }

    public Relation(String rel, String gnd) {
        this.rel = rel;
        this.gnd = gnd;
        this.validate();
    }
    public Relation(String tag) {
        String[] Rel = this.seperat(tag);
        this.rel = Rel[0];
        this.gnd = Rel[1];
        this.validate();
    }
}
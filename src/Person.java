import java.util.ArrayList;

class Person {
    public final Person Sup;
    public final Relation Rel;
    public final String name;
    public ArrayList<Person> Subs = null;

    public Person(String Rel, String name) {
        this.Sup = null;
        if (Rel.length() == 1) this.Rel = new Relation("Sl", Rel.toUpperCase());
        else this.Rel = new Relation(Rel);
        this.name = name.replaceAll(" ", "_").toUpperCase();
    }
    public Person(Person Sup, String Rel, String name) {
        this.Sup = Sup;
        this.Rel = new Relation(Rel);
        this.name = name.replaceAll(" ", "_").toUpperCase();
        if (!this.isRoot()) this.Sup.addSub(this);
    }
    public Person(Person Sup, String tag) {
        this.Sup = Sup;
        String[] tags = tag.split("@");
        this.name = tags[0].replaceAll(" ", "_").toUpperCase();
        this.Rel = new Relation(tags[1]);
        if (!this.isRoot()) this.Sup.addSub(this);
    }

    public void addSub(Person prs) {
        if (isLeaf()) Subs = new ArrayList<Person>();
        if (Relation.CheckStruct(this.Rel.rel,prs.Rel.rel)) {
            this.Subs.add(prs);
            return;
        }
        throw new IllegalArgumentException("Invalid Tree ");
    }

    public Person Clone(Person sup, String rel) {
        Person ret = new Person(sup, rel + "#" + this.Rel.gnd, this.name);
        if (!this.isLeaf()) for (Person prs: this.Subs) {
            if (Relation.CheckStruct(rel, prs.Rel.rel))
                prs.Clone(ret,prs.Rel.rel);
        }
        if (this.isRoot()) return ret;
        for (Person prs : this.Sup.Subs) {
            if (this.name.equals(prs.name)) continue;
            String rel2 = prs.Rel.rel;
            if ("PaCh".contains(this.Rel.rel)) switch (rel2) {
                case "Pa": rel2 = "Pr"; break;
                case "Pr": rel2 = "Pa"; break;
                case "Sy": rel2 = "Ch"; break;
                case "Ch": rel2 = "Sy"; break;
                default: break;
            };
            if (Relation.CheckStruct(rel, rel2)) prs.Clone(ret,rel2);
        }
        String rel3 = this.Rel.rel;
        switch (this.Rel.rel) {
            case "Pa": rel3 = "Ch"; break;
            case "Ch": rel3 = "Pa"; break;
            default: break;
        };
        if (Relation.CheckStruct(rel, rel3)) this.Sup.Clone(ret,rel3);
        return ret;
    }

    @Override
    public String toString() {
        return this.Rel.Rel() + "@" + this.name;
    }
    @Override
    public boolean equals(Object prs) {
        return this.toString().equals(prs.toString());
    }
    public boolean isRoot() {
        return this.Sup==null;
    }
    public boolean isLeaf(){
        return Subs==null;
    }
}
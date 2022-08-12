import java.util.ArrayList;
import java.util.Collections;

class FamilyTree {
    private final Person self;

    public FamilyTree(Person self) {
        this.self = self;
    }

    public Person FindPerson(String name, Person... from) {
        name = name.replace(" ", "_").toUpperCase();
        Person frm = from.length == 1 ? from[0] : this.self;
        if (frm.name.equals(name)) return frm;
        if (!frm.isLeaf()) for (Person prs : frm.Subs) {
            if (prs.name == name) return prs;
            Person ret = FindPerson(name, prs);
            if (ret != null) return ret;
        }
        if (frm.equals(this.self)) throw new IllegalArgumentException("Name is not in tree");
        else return null;
    }

    public void PrintTree(Person frm, String pre) {
        System.out.println(pre + frm);
        if (frm.isLeaf()) return;
        for (Person prs : frm.Subs) {
            this.PrintTree(prs, pre+"|	");
        }
    }
    public void PrintTree() {
        this.PrintTree(this.self, "");
    }

    public FamilyTree SelfAs(String name) {
        return new FamilyTree(this.FindPerson(name).Clone(null,"Sl"));
    }

    public FamilyTree SelfAs(Person prs) {
        return new FamilyTree(prs.Clone(null,"Sl"));
    }

    public ArrayList<Person> FindRoute(String prs1,String prs2){
        FamilyTree tree = this.SelfAs(prs1);
        Person Prs2 = tree.FindPerson(prs2);
        ArrayList<Person> lst = new ArrayList<Person>();
        while (!Prs2.isRoot()){
            lst.add(Prs2);
            Prs2 = Prs2.Sup;
        }
        Collections.reverse(lst);
        return lst;
    }

}
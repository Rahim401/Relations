public class Main {
    public static void main(String[] args) {
        long start = System.nanoTime();
        Person me = new Person("m", "Akash");
        Person prs1 = new Person(me, "Pa#F", "Anjali");
        Person prs2 = new Person(me, "Pa#F", "Vinoth");
        Person prs3 = new Person(me, "Sy#M", "Vishal");
        Person prs4 = new Person(prs3, "Pr#F", "Sujatha");

        System.out.println("My Family Tree");
        FamilyTree fromMe = new FamilyTree(me);
        fromMe.PrintTree();

        System.out.println("\nBrother Family Tree");
        FamilyTree fromBro = fromMe.SelfAs(prs3);
        fromBro.PrintTree();
    }
}

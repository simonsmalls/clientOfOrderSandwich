package be.abis.cllientsandwich.model;

public class SandwichOrder {

    private int id;
    private SandwichType sandwichType;
    private boolean rauwkost;
    private boolean grilledVegs;
    //    private boolean noButter;
    private boolean white;
    private String note;
    private Person person;

    public SandwichOrder() {}

    public SandwichOrder(Person person){
        this.person = person;
    }

    public SandwichOrder(SandwichType sandwichType, boolean rauwkost, boolean grilledVegs, boolean white, String note, Person person) {
        this.sandwichType = sandwichType;
        this.rauwkost = rauwkost;
        this.grilledVegs = grilledVegs;
        this.white = white;
        this.note = note;
        this.person = person;
    }


    // getset

    public SandwichType getSandwichType() {
        return sandwichType;
    }

    public void setSandwichType(SandwichType sandwichType) {
        this.sandwichType = sandwichType;
    }

    public boolean isRauwkost() {
        return rauwkost;
    }

    public void setRauwkost(boolean rauwkost) {
        this.rauwkost = rauwkost;
    }

    public boolean isWhite() {
        return white;
    }

    public void setWhite(boolean white) {
        this.white = white;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isGrilledVegs() {
        return grilledVegs;
    }

    public void setGrilledVegs(boolean grilledVegs) {
        this.grilledVegs = grilledVegs;
    }
}

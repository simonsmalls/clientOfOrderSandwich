package be.abis.cllientsandwich.model;

public class SandwichOrderModel {

    private int i;

    private boolean rauwkost;
    private boolean grilledVegs;
    //    private boolean noButter;
    private boolean white;
    private String note;
    private Person person;

    public SandwichOrderModel() {}

    public SandwichOrderModel(int i, boolean rauwkost, boolean grilledVegs, boolean white, String note, Person person) {
        this.i = i;
        this.rauwkost = rauwkost;
        this.grilledVegs = grilledVegs;
        this.white = white;
        this.note = note;
        this.person = person;
    }

    public SandwichOrderModel(Person person){
        this.person = person;
    }

    public SandwichOrderModel(SandwichType sandwichType, boolean rauwkost, boolean grilledVegs, boolean white, String note, Person person) {

        this.rauwkost = rauwkost;
        this.grilledVegs = grilledVegs;
        this.white = white;
        this.note = note;
        this.person = person;
    }


    // getset


    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
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

    public boolean isGrilledVegs() {
        return grilledVegs;
    }

    public void setGrilledVegs(boolean grilledVegs) {
        this.grilledVegs = grilledVegs;
    }
}

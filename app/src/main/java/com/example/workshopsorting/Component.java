package com.example.workshopsorting;


import static java.lang.Integer.parseInt;

public class Component {
    private String ID;
    private String name;
    private String location;
    private String link;
    private String group;
    private int quantity;
    private String timestamp;

    /*public Component(String nname, int number){
        name=nname;
        location="";
        link="";
        group="";
        quantity=number;
    }*/
    public Component(){
        ID="XXX";
        name="";
        location="";
        link="";
        group="";
        quantity=0;
    }

    public Component(Component x){
        this.ID=x.ID;
        this.name=x.name;
        this.location=x.location;
        this.link=x.link;
        this.group=x.group;
        this.quantity=x.quantity;
    }
    public Component(String nID, String nname, String nlocation, String nlink, String ngroup, String nquantity){
        ID=nID;
        name=nname;
        location=nlocation;
        link=nlink;
        group=ngroup;
        quantity=parseInt(nquantity);
    }
    public String getName() {
        return name;
    }
    public void setName(String name) { this.name = name; }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getQuantity() {
        return quantity;
    }

    public void incrementQuantity(){
        quantity++;
    }
    public void decrementQuantity(){
        quantity--;
    }

    public void setQuantity(String quantity){
        this.quantity=parseInt(quantity);
    }


    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public String getGroup() {
        return group;
    }
    public void setGroup(String group) {
        this.group = group;
    }

    public String getTimestamp() { return timestamp; }

    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
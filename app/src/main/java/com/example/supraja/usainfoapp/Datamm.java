package com.example.supraja.usainfoapp;

/**
 * Created by renik on 6/15/2017.
 */

public class Datamm {


    /**
     * category : Shrubs
     * price : 15.99
     * instructions : Large double. Good grower, heavy bloomer. Early to mid-season, acid loving plants. Plant in moist well drained soil with pH of 4.0-5.5.
     * photo : california_snow.jpg
     * name : Azalea
     * productId : 1
     */

    private String category;
    private double price;
    private String instructions;
    private String photo;
    private String name;
    private int productId;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}

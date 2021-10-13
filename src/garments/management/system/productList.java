/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garments.management.system;

/**
 *
 * @author mh200
 */
public class productList {
    int id;
String name, description,size,accessories;
float mcost,uprice;

    public productList(int id, String name, String description, String size, String accessories, float mcost, float uprice) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.size = size;
        this.accessories = accessories;
        this.mcost = mcost;
        this.uprice = uprice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getAccessories() {
        return accessories;
    }

    public void setAccessories(String accessories) {
        this.accessories = accessories;
    }

    public float getMcost() {
        return mcost;
    }

    public void setMcost(float mcost) {
        this.mcost = mcost;
    }

    public float getUprice() {
        return uprice;
    }

    public void setUprice(float uprice) {
        this.uprice = uprice;
    }


}

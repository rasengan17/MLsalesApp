package com.mpro.heroes.mlsalesapp.model;

/**
 * Created by cmacias on 5/3/16.
 */
public class CatalogItem {
    private String productName;
    private int quantity;
    private String smallDescription;
    private String[] tags;
    private int productId;
    private int ListId;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getListId() {
        return ListId;
    }

    public void setListId(int id) {
        this.ListId = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSmallDescription() {
        return smallDescription;
    }

    public void setSmallDescription(String smallDescription) {
        this.smallDescription = smallDescription;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }
}

package com.mpro.heroes.mlsalesapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by cmacias on 4/28/16.
 */
public class Catalog {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("url_image")
    @Expose
    private String urlImage;
    @SerializedName("catalog_price")
    @Expose
    private Integer catalogPrice;
    @SerializedName("points")
    @Expose
    private Integer points;
    @SerializedName("product_line")
    @Expose
    private String productLine;

    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName The product_name
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return The urlImage
     */
    public String getUrlImage() {
        return urlImage;
    }

    /**
     * @param urlImage The url_image
     */
    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    /**
     * @return The catalogPrice
     */
    public Integer getCatalogPrice() {
        return catalogPrice;
    }

    /**
     * @param catalogPrice The catalog_price
     */
    public void setCatalogPrice(Integer catalogPrice) {
        this.catalogPrice = catalogPrice;
    }

    /**
     * @return The points
     */
    public Integer getPoints() {
        return points;
    }

    /**
     * @param points The points
     */
    public void setPoints(Integer points) {
        this.points = points;
    }

    /**
     * @return The productLine
     */
    public String getProductLine() {
        return productLine;
    }

    /**
     * @param productLine The product_line
     */
    public void setProductLine(String productLine) {
        this.productLine = productLine;
    }

}

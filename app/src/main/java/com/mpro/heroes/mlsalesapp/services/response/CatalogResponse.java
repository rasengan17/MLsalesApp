package com.mpro.heroes.mlsalesapp.services.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CatalogResponse {

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
    private Double catalogPrice;
    @SerializedName("points")
    @Expose
    private Double points;
    @SerializedName("product_line")
    @Expose
    private String productLine;

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     *
     * @param productName
     * The product_name
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     *
     * @return
     * The urlImage
     */
    public String getUrlImage() {
        return urlImage;
    }

    /**
     *
     * @param urlImage
     * The url_image
     */
    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    /**
     *
     * @return
     * The catalogPrice
     */
    public Double getCatalogPrice() {
        return catalogPrice;
    }

    /**
     *
     * @param catalogPrice
     * The catalog_price
     */
    public void setCatalogPrice(Double catalogPrice) {
        this.catalogPrice = catalogPrice;
    }

    /**
     *
     * @return
     * The points
     */
    public Double getPoints() {
        return points;
    }

    /**
     *
     * @param points
     * The points
     */
    public void setPoints(Double points) {
        this.points = points;
    }

    /**
     *
     * @return
     * The productLine
     */
    public String getProductLine() {
        return productLine;
    }

    /**
     *
     * @param productLine
     * The product_line
     */
    public void setProductLine(String productLine) {
        this.productLine = productLine;
    }

}
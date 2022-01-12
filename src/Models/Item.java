package Models;

public class Item {
    private String code;
    private ItemName itemName;
    private Integer quantity;

    public Item(String code, ItemName itemName, Integer quantity, Double price) {
        this.code = code;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
    }

    private Double price;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ItemName getItemName() {
        return itemName;
    }

    public void setItemName(ItemName itemName) {
        this.itemName = itemName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}

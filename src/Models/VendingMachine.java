package Models;

import java.util.List;

public class VendingMachine {
    List<Item> items;
    State currentState;
    Double currentAmount;
    Item selected;

    public VendingMachine(List<Item> items, Double currentAmount) {
        this.items = items;
        this.currentState = State.READY;
        this.currentAmount = currentAmount;
        this.selected = null;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public Double getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(Double currentAmount) {
        this.currentAmount = currentAmount;
    }
}

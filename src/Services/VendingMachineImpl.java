package Services;

import Models.Item;
import Models.State;
import Models.VendingMachine;

import java.util.Scanner;

public class VendingMachineImpl implements  VendingMachineInterface{

    VendingMachine vendingMachine;
    Scanner scanner;
    Double amountReceived;
    String selectedCode;
    Item purchasedItem;
    static Integer MAX_TRY_COUNT = 5;
    Integer tryCount;


    public VendingMachineImpl(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
        scanner = new Scanner(System.in);
        init();
    }

    @Override
    public void init() {
        vendingMachine.setCurrentState(State.READY);
        amountReceived = 0.0;
        selectedCode = null;
        purchasedItem = null;
        tryCount = 0;
    }

    @Override
    public void collectCash() throws Exception {
        if(vendingMachine.getCurrentState()==State.READY){
            System.out.println("Enter coins: ");
            amountReceived = scanner.nextDouble();
            System.out.println("DEBUG collected coins " + amountReceived);
            vendingMachine.setCurrentState(State.SELECTITEM);
            selectItem();
        }
        else{
            throw new Exception("Illegal cmd");
        }
    }

    @Override
    public void showMenu() {
        for(Item item: vendingMachine.getItems()){
            System.out.println(item.getCode() + " " + item.getItemName() + " " + item.getPrice());
        }
    }

    @Override
    public void selectItem() throws Exception {
        if(vendingMachine.getCurrentState()==State.SELECTITEM) {
            System.out.println("Enter the item code: ");
            selectedCode = scanner.next();
            if(!validate(selectedCode)){
                System.out.println("cannot process your transaction at the moment");
                vendingMachine.setCurrentState(State.CANCELLED);
                cancelTransaction();
                return;
            }
            vendingMachine.setCurrentState(State.DISPENSEITEM);
            dispenseItem();
        }
        else{
            throw new Exception("Illegal cmd");
        }
    }

    @Override
    public void dispenseItem() throws Exception {
        if(tryCount == MAX_TRY_COUNT){
            vendingMachine.setCurrentState(State.CANCELLED);
            cancelTransaction();
            return;
        }
        if(vendingMachine.getCurrentState() == State.DISPENSEITEM) {
            System.out.println("1. Proceed to buy item\n2.Cancel transaction");
            Integer option = scanner.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Thank you for purchasing " + purchasedItem.getItemName() + ". Have a nice day");
                    vendingMachine.setCurrentState(State.DISPENSECHANGE);
                    dispenseChange();
                    break;
                case 2:
                    System.out.println("cancelling your transaction, please wait");
                    vendingMachine.setCurrentState(State.CANCELLED);
                    cancelTransaction();
                    break;
                default:
                    System.out.println("Wrong option entered, please try again\n");
                    tryCount++;
                    dispenseItem();
            }
        }
        else{
            throw new Exception("Illegal cmd");
        }
    }

    @Override
    public void dispenseChange() throws Exception {
        if(vendingMachine.getCurrentState()==State.DISPENSECHANGE) {
            Double amountToBeReturned = amountReceived - purchasedItem.getPrice();
            for (Item item : vendingMachine.getItems()) {
                if (item == purchasedItem) {
                    item.setQuantity(item.getQuantity() - 1);
                    break;
                }
            }
            vendingMachine.setCurrentAmount(vendingMachine.getCurrentAmount() + purchasedItem.getPrice());
            if (amountToBeReturned > 0) {
                System.out.println("Remaining amount: " + amountToBeReturned);
            }
            init();
        }
        else{
            throw new Exception("Illegal cmd");
        }
    }

    @Override
    public void cancelTransaction() throws Exception {
        if(vendingMachine.getCurrentState()==State.CANCELLED){
            System.out.println("Please collect your refund: " + amountReceived);
            amountReceived = 0.0;
            init();
        }
        else{
            throw new Exception("Illegal cmd");
        }
    }

    @Override
    public Boolean validate(String selectedCode) {
        if(amountReceived == null) return false;
        for(Item item: vendingMachine.getItems()){
            if(item.getCode().equals(selectedCode)){
                if(item.getQuantity() > 0 && item.getPrice()>amountReceived
                        && vendingMachine.getCurrentAmount() - (amountReceived-item.getPrice()) > 0){
                    purchasedItem = item;
                    return true;
                }
                else{
                    return false;
                }
            }
        }
        return false;
    }
}

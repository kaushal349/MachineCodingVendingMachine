import Models.Item;
import Models.ItemName;
import Models.VendingMachine;
import Services.VendingMachineImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) throws Exception {
        List<Item> items = new ArrayList<>();
        items.add(new Item("code1", ItemName.ITEM1,1,10.00));
        items.add(new Item("code2", ItemName.ITEM2,5,30.00));
        items.add(new Item("code3", ItemName.ITEM3,3,40.00));
        items.add(new Item("code4", ItemName.ITEM4,2,15.00));
        items.add(new Item("code5", ItemName.ITEM5,10,17.00));
        items.add(new Item("code6", ItemName.ITEM6,11,48.00));
        items.add(new Item("code7", ItemName.ITEM7,7,55.00));

        VendingMachine vendingMachine = new VendingMachine(items, 100.0);

        VendingMachineImpl impl = new VendingMachineImpl(vendingMachine);
        impl.showMenu();

        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println("1. Start purchase\n2. Show menu");
            Integer option = scanner.nextInt();
            System.out.println("scanned option " + option);
            switch (option){
                case 1:
                    impl.collectCash();
                    break;
                case 2:
                    impl.showMenu();
                    break;
                default:
                    System.out.println("Wrong option selected");
            }
        }

    }
}

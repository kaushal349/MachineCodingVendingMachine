package Services;

public interface VendingMachineInterface {
    public void init();
    public void collectCash() throws Exception;
    public void showMenu() throws Exception;
    public void selectItem() throws Exception;
    public void dispenseItem() throws Exception;
    public void dispenseChange() throws Exception;
    public void cancelTransaction() throws Exception;
    public Boolean validate(String selectedCode);
}

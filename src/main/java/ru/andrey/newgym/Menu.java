package ru.andrey.newgym;

public class Menu {
    private UnlimitedArray<String> menu = new UnlimitedArray<>();


    protected void addMenuSection(String menuSection) {
        menu.add(menuSection);
    }

    public void showMenuSections() {
        menu.showAllElements();
    }
}



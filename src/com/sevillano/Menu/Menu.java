package com.sevillano.Menu;

import com.sevillano.model.BERUTransporte;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by tsevillano on 11/28/16.
 */
public abstract class Menu {
    BERUTransporte company = BERUTransporte.getInstance();
    protected String title;
    protected ArrayList<String> options;
    private Scanner scanner;

    public Menu(){
        scanner = new Scanner(System.in);
    }

    protected void listOptions(){
        clearScreen();
        System.out.println("#####################");
        System.out.println("    " + title);
        System.out.println("#####################");
        int optNumber = 1;
        for (String option : options) {
            System.out.println(optNumber+". "+option);
            optNumber++;
        }
        menu(awaitInt());
    }

    abstract protected void menu(int opt);

    protected void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    protected void invalidOption(){
        System.out.println("Opción incorrecta.");
    }

    protected int awaitInt(){
        return scanner.nextInt();
    }

    protected String awaitString(){
        return scanner.nextLine();
    }

    protected void quit(){
        System.exit(0);
    }
}

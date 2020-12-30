package Excepcions.ActivitatExceptions;

import Excepcions.ActivitatExceptions.Control.OperacionsBanc;
import Excepcions.ActivitatExceptions.Exceptions.BankAccountException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws BankAccountException {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu();

        int opcion;
        boolean bucle = false;

        OperacionsBanc operacionsBanc = new OperacionsBanc();
        while (bucle != true ){
            menu.MenuInicial();
            opcion = scanner.nextInt();
            switch (opcion){
                case 1:
                    operacionsBanc.crearCuenta();
                    break;
                case 2:
                    operacionsBanc.verificarDNI();
                    break;
                case 3:
                    operacionsBanc.Cuentas();
                    menu.Trans1();
                    operacionsBanc.Transferencias();
                    break;

            }
        }

    }
}

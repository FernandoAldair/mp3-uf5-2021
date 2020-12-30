package Excepcions.ActivitatExceptions.Control;

import Excepcions.ActivitatExceptions.Exceptions.BankAccountException;
import Excepcions.ActivitatExceptions.Exceptions.ClientAccountException;
import Excepcions.ActivitatExceptions.Exceptions.ExceptionMessage;
import Excepcions.ActivitatExceptions.Menu;
import Excepcions.ActivitatExceptions.Model.Client;
import Excepcions.ActivitatExceptions.Model.CompteEstalvi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OperacionsBanc {
    Menu menu = new Menu();
    Scanner scanner = new Scanner(System.in);

    List<CompteEstalvi> compteEstalviList = new ArrayList<>();

    public static boolean verifyDNI(String dni) throws ClientAccountException {
        final String WRONG_DNI = "DNI incorrecte: ";

        boolean correcto = false;
        Pattern pattern = Pattern.compile("(\\d{1,8})([TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke])");
        Matcher matcher = pattern.matcher(dni);

        if (matcher.matches()) {

            String letra = matcher.group(2);

            String letras = "TRWAGMYFPDXBNJZSQVHLCKE";

            int index = Integer.parseInt(matcher.group(1));

            index = index % 23;

            String reference = letras.substring(index, index + 1);

            if (reference.equalsIgnoreCase(letra)) {
                correcto = true;
            } else {
                correcto = false;
                throw new ClientAccountException(WRONG_DNI + dni);
            }
        }
        return correcto;
    }

    public boolean verifyBankAccount(String numCompte) throws BankAccountException {

        for (CompteEstalvi c : compteEstalviList) {
            if (c.getNumCompte().equals(numCompte)) return true;
        }
        throw new BankAccountException(ExceptionMessage.ACCOUNT_NOT_FOUND);
    }


    public void transferir(String numCompte1, String numCompte2, double quantitat) throws BankAccountException {

        if (verifyBankAccount(numCompte1) && verifyBankAccount(numCompte2)) {
            for (CompteEstalvi c : compteEstalviList) {
                if (c.getNumCompte().equals(numCompte1)) {
                    if (c.getSaldo() < quantitat) throw new BankAccountException(ExceptionMessage.ACCOUNT_OVERDRAFT);
                        //lo de arriba es el treure
                    else {
                        for (CompteEstalvi c2 : compteEstalviList) {
                            if (c2.getNumCompte().equals(numCompte2)) {
                                c.treure(quantitat);
                                c2.ingressar(quantitat);
                            }
                        }
                    } //TODO hacer las cosas como recs
                }
            }

        }
    }

    public void crearCuenta() {
        menu.CrearCuenta1();

        String ClientNom = scanner.nextLine();
        String CognomClient = scanner.nextLine();
        String DNI = scanner.nextLine();

        Client client = new Client(ClientNom, CognomClient, DNI);
        List<Client> llistaUsuaris1 = Arrays.asList(client);
        menu.CrearCuenta2();
        String numCompte = scanner.nextLine();
        double saldo = scanner.nextDouble();

        compteEstalviList.add(new CompteEstalvi(numCompte, saldo, llistaUsuaris1));

        System.out.println("Desea crear otra cuenta?");
        System.out.println("si: 1");
        System.out.println("no: 2");

        int option = scanner.nextInt();
        if (option == 1) {
            crearCuenta();
        } else {
            System.out.println("Volviendo atras...");
        }
    }

    public void verificarDNI() {
        menu.VerificacionDNI();
        String DNI = scanner.nextLine();
        try {
            verifyDNI(DNI);
            System.out.println("Correcto");
        } catch (ClientAccountException clientAccountException) {
            System.out.println(clientAccountException);
        }
    }

    public void Cuentas() {

        for (int i = 0; i < compteEstalviList.size(); i++) {
            System.out.println(i + "." + "Numero de cuenta: " + compteEstalviList.get(i).getNumCompte() +
                    " Saldo: " + compteEstalviList.get(i).getSaldo() +
                    " Lista de usuarios:" + compteEstalviList.get(i).getLlista_usuaris());

        }
    }

    public void Transferencias() throws BankAccountException {

        double transferencia = scanner.nextDouble();
        menu.Trans2();
        int num = scanner.nextInt();

        double saldoEmisor = compteEstalviList.get(num).getSaldo();

        saldoEmisor = saldoEmisor - transferencia;
        if (saldoEmisor > 0) {
            compteEstalviList.get(num).setSaldo(saldoEmisor);
            menu.Trans3();
            int num2 = scanner.nextInt();
            double saldoReceptor = compteEstalviList.get(num2).getSaldo();
            saldoReceptor = saldoReceptor + transferencia;
            compteEstalviList.get(num2).setSaldo(saldoReceptor);
        } else {
            throw new BankAccountException(ExceptionMessage.TRANSFER_ERROR);
        }

    }
}

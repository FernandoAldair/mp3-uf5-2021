package Excepcions.ActivitatExceptions.Model;


import Excepcions.ActivitatExceptions.Control.OperacionsBanc;
import Excepcions.ActivitatExceptions.Exceptions.ClientAccountException;

public class Client {
    private String Nom;
    private String Cognoms;
    private String DNI;

    public Client(String nom, String cognoms, String DNI) {
        Nom = nom;
        Cognoms = cognoms;
        try {
            if(OperacionsBanc.verifyDNI(DNI)) this.DNI = DNI;
        } catch (ClientAccountException e) {
            e.printStackTrace();
        }
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getCognoms() {
        return Cognoms;
    }

    public void setCognoms(String cognoms) {
        Cognoms = cognoms;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

}
package Excepcions.ActivitatExceptions;

public class Menu {

    public void MenuInicial(){
        System.out.println("--------Menú--------");
        System.out.println("1.Crear usuario");
        System.out.println("2.Verificar DNI");
        System.out.println("3.Tranferencia");
        System.out.println("");
        System.out.println("Seleccione la opcion deseada:");
    }

    public void CrearCuenta1(){
        System.out.println("Introdueixi el nombre, el cognom y el DNI (no hacerlo seguido)");
    }
    public void CrearCuenta2(){
        System.out.println("Introdueixi el numero de compte i el saldo");
    }

    public void VerificacionDNI(){
        System.out.println("----------------");
        System.out.println("Introduce el DNI para su verificacion");
    }

    public void Trans1(){
        System.out.println("----------------");
        System.out.println("Introduce la cantidd que desea transferir:");
    }
    public void Trans2(){
        System.out.println("----------------");
        System.out.println("Selecciona la cuenta emisora:");
    }
    public void Trans3(){
        System.out.println("----------------");
        System.out.println("Selecciona la cuenta receptora:");
    }

}

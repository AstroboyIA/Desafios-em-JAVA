package desafio18;

import java.util.Scanner;

import desafio18.service.GestaoIncidenteService;

public class Main {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        GestaoIncidenteService service = new GestaoIncidenteService();
        
        service.Welcome();
    }
}

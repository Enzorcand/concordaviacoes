package org.example;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Scanner;
public class Aeronave
{
    // variáveis de instância - substitua o exemplo abaixo pelo seu próprio

    private Poltrona[][] poltronas = new Poltrona[16][4];
    private int voo;


    public Aeronave(int n)
    {
        voo = n;
    }


    /** le as informacoes das poltronas guardadas no disco e as armazena em uma matriz
     */

    public void read() throws Exception {
        FileInputStream file = new FileInputStream("aeronave"+ voo +".txt");
        Scanner in = new Scanner(file);
        for (int i = 0; i < poltronas.length; i++) {
            for (int j = 0; j < poltronas[i].length; j++) {
                if(i == 12 || i == 13){
                    poltronas[i][j] = new Poltrona("corredor", voo);
                }
                else{
                    poltronas[i][j] = new Poltrona("", voo);
                }
                poltronas[i][j].setPosicao(i, j);
                poltronas[i][j].setSituation(in.nextLine());
            }
        }
        in.close();
        file.close();
    }


    /** Metodo que mostra no terminal o mapa de assentos de um avião
     */
    public void print(int n) {

        switch(n){
            case 1:
                System.out.println("POA -> FLN");
                System.out.println("23/06/2023 06:00 -> 07:30 CONCORD 3572\n");
                break;
            case 2:
                System.out.println("POA -> RIA");
                System.out.println("23/06/2023 14:10 -> 15:15 CONCORD 2434\n");
                break;
            case 3:
                System.out.println("FLN -> POA");
                System.out.println("23/06/2023 21:30 -> 23:00 CONCORD 2578\n");
                break;
        }

        System.out.println("    A  B     C  D");
        for (int i = 0; i < poltronas.length; i++) {
            System.out.printf("%2d ", i + 1);

            for (int j = 0; j < poltronas[i].length; j++){
                System.out.print("[" + poltronas[i][j].getSituation() + "]");

                if (j == 1) {
                    System.out.print("   ");
                }
            }
            System.out.printf(" %2d%n", i + 1);
            if (i == 11 || i == 12) {
                System.out.println();
            }
        }
        System.out.println("    A  B     C  D");
    }

    /**grava as informações das poltronas em um arquivo de texto com base nos dados na matriz
     */
    public void write() throws Exception {
        PrintStream file = new PrintStream("aeronave"+ voo +".txt");
        for (int i = 0; i < poltronas.length; i++) {
            for (int j = 0; j < poltronas[i].length; j++) {
                file.println(poltronas[i][j].getSituationText());
            }
        }
        file.close();
    }


    /** verifica se as coordenadas da poltrona são válidas, se a poltrona está ocupada e realiza a compra
     */
    public boolean efetuarCompra(int i, int j) throws Exception {
        boolean result;
        if (j >= 0 && j + 1<= poltronas[i].length && i >= 0 && i <= poltronas.length) {
            Poltrona poltrona = poltronas[i][j];

            if (!poltrona.isDisponivel()) {
                System.out.println("A poltrona está ocupada.");
                result = false;
            } else {



                poltrona.setDisponivel(false);
                System.out.println("Compra concluída");

                poltronas[i][j].setComprador();
                poltronas[i][j].getComprador().registerClient(poltronas[i][j]);

                // pergunta se deseja comprar a poltrona do lado

                if (j == 0 || j == 2) {
                    Poltrona ladoPoltrona = poltronas[i][j + 1];

                    if (ladoPoltrona.isDisponivel()) {
                        Scanner scan = new Scanner(System.in);
                        System.out.print("Deseja comprar a poltrona ao lado? (S/N): ");
                        String answer = scan.nextLine();
                        if (answer.equalsIgnoreCase("S")) {

                            ladoPoltrona.setBloqueada(true);
                            System.out.println("Compra da poltrona do lado concluída.");

                            ladoPoltrona.setSituation("bloqueado");
                            result = true;
                        } else {
                            result = false;
                        }
                    } else {
                        result = false;
                    }
                }else if(j == 1 || j == 3){
                    Poltrona ladoPoltrona = poltronas[i][j - 1];

                    if (ladoPoltrona.isDisponivel()) {
                        Scanner scan = new Scanner(System.in);
                        System.out.print("Deseja comprar a poltrona ao lado? (S/N): ");
                        String answer = scan.nextLine();
                        if (answer.equalsIgnoreCase("S")) {

                            ladoPoltrona.setBloqueada(true);
                            System.out.println("Compra da poltrona do lado concluída.");

                            ladoPoltrona.setSituation(ladoPoltrona.getSituationText());
                            result = true;
                        } else {
                            result = false;
                        }
                    } else {
                        result = false;
                    }
                } else {
                    result = false;
                }
            }
        } else {
            System.out.println("Poltrona indisponível.");
            result = false;

        }

        poltronas[i][j].setSituation("ocupado");
        return result;
    }

    /**verifica se as coordenadas da poltrona são válidas, se a poltrona estiver ocupada cancela a compra, marcando a poltrona como desocupada
     */
    public void cancelarCompra(int i, int j){

        Poltrona poltrona = poltronas[i][j];
        if (j >= 0 && j + 1<= poltronas[i].length && i >= 0 && 1 <= poltronas.length) {
            if (!poltrona.isDisponivel()) {
                poltrona.setDisponivel(true);
                System.out.println("A compra foi cancelada.");
                poltrona.setSituation("vago");
            } else {
                System.out.println("A poltrona não está ocupada.");
                poltrona.setSituation("vago");
            }
        } else {
            System.out.println("Poltrona não encontrada.");
        }

    }

    /** metodo que busca na lista de clientes todas passagens que o cliente possui e as retorna em um array de strings
     */
    public void buscaCliente() throws Exception {

        Scanner scan = new Scanner(System.in);
        System.out.print("Digite o CPF do cliente: ");
        String cpfCliente = scan.nextLine();

        FileInputStream file = new FileInputStream("ListaClientes.txt");
        Scanner fileScan = new Scanner(file);

        int index = 0;

        while(fileScan.hasNextLine()) {
            String line = fileScan.nextLine();
            String[] dadosCliente = line.split(";");

            if (dadosCliente[0].equals(cpfCliente)) {
                index++;
            }
        }

        fileScan.close();
        file.close();

        FileInputStream file1 = new FileInputStream("ListaClientes.txt");
        Scanner fileScan1 = new Scanner(file1);

        String[] passagens = new String[index];
        index = 0;

        while (fileScan1.hasNextLine()) {

            String line = fileScan1.nextLine();
            String[] dadosCliente = line.split(";");

            if (dadosCliente[0].equals(cpfCliente)) {
                String passagem = "Voo: " + dadosCliente[3] + " - Poltrona: " + dadosCliente[4];
                passagens[index] = passagem;
                index++;
            }
        }

        fileScan1.close();
        file1.close();

        scan.close();

        if (index == 0) {
            System.out.println("O Cliente não tem passagens.");
        } else {
            System.out.println("Passagens do cliente com o CPF " + cpfCliente + ":");
            for (int i = 0; i < passagens.length; i++) {
                System.out.println(passagens[i]);
            }
        }
    }

    /** Metodo que define o preço pela passagem baseado no numero de passagens compradas pela mesma pessoa
     */
    public double getSeatValue(int i, int j, int nPassagens){
        if(nPassagens == 1){
            return poltronas[i][j].getPreco()*0.8;
        } else if(nPassagens > 1){
            return poltronas[i][j].getPreco()*0.6;
        } else{
            return poltronas[i][j].getPreco();
        }

    }

    /** Metodo que mostra as informações do comprador de uma poltrona
     */
    public void getClientInfo(int i, int j){
        poltronas[i][j].getComprador().showClientData();
    }
}

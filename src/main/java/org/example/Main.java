package org.example;

import java.util.Scanner;

public class Main
{

    /** cria uma matriz de objetos do tipo Aeronave. inicializa duas variáveis, "numeroDePassagens" e "valorTotal".
     há um loop "for" que itera três vezes. Dentro do loop, um novo objeto Aeronave é criado e armazenado no índice
     correspondente da matriz "voos". o método "read()" é chamado em cada objeto Aeronave, definindo apartir do disco
     as matrizes de poltronas de cada aeronave */

    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        Aeronave[] voos = new Aeronave[3];
        int numeroDePassagens = 0;
        double valorTotal = 0.0;

        for(int i = 0; i < 3; i++){
            voos[i] = new Aeronave(i + 1);
            voos[i].read();
        }



        System.out.println("-- Venda de passagens companhia Concord --");
        System.out.println();

        int comando;
        do{

            System.out.println("Lista de comandos:");
            System.out.println("0 - Sair");
            System.out.println("1 - Comprar passagens");
            System.out.println("2 - Cancelar passagens");
            System.out.println("3 - Conferir passagens de um passageiro");
            System.out.println("4 - Voos e assentos disponiveis");
            System.out.println("5 - Opções de funcionário");
            comando = scan.nextInt();

            int entrada;
            int[] entrada2;
            if(comando == 1){
                entrada = selectVoo() - 1;
                voos[entrada].print(entrada + 1);
                entrada2 = selectSeat();
                boolean sideSeat = voos[entrada].efetuarCompra(entrada2[1], entrada2[0]);
                if(sideSeat) {
                    valorTotal += voos[entrada].getSeatValue(entrada2[1], entrada2[0], numeroDePassagens)*0.5;
                }
                valorTotal += voos[entrada].getSeatValue(entrada2[1], entrada2[0], numeroDePassagens);
                numeroDePassagens++;
            }else if(comando == 2){
                entrada = selectVoo() - 1;
                voos[entrada].print(entrada + 1);
                entrada2 = selectSeat();
                voos[entrada].cancelarCompra(entrada2[1], entrada2[0]);
            }else if(comando == 3){
                voos[1].buscaCliente();
            }else if(comando == 4){
                entrada = selectVoo() - 1;
                voos[entrada].print(entrada + 1);
            }else if(comando == 5){
                System.out.println("1 - Gravar aeronave");
                System.out.println("2 - Ler aeronave");
                System.out.println("3 - Ver dados da poltrona");
                comando = scan.nextInt();

                if(comando == 1){
                    voos[selectVoo() - 1].write();
                }else if(comando == 2){
                    voos[selectVoo() - 1].read();
                }else if(comando == 3){
                    entrada = selectVoo() - 1;
                    voos[entrada].print(entrada + 1);
                    entrada2 = selectSeat();
                    voos[entrada].getClientInfo(entrada2[1], entrada2[0]);
                }
            }
            else if(comando != 0){
                System.out.println("Comando invalido!");
            }
        }while(comando != 0);

        System.out.printf("Valor a ser pago: R$ %.2f%n", valorTotal );
        scan.close();
    }

    /** switch que verifica o primeiro caractere da entrada digitada pelo usuário.
     dependendo do caractere, o valor correspondente é atribuído à primeira posição
     do array saida. No caso das letras 'A', 'B', 'C' e 'D', os valores 0, 1, 2 e 3 são atribuídos */

    public static int[] selectSeat(){
        Scanner scan = new Scanner(System.in);
        int[] saida = new int[2];
        String comando = scan.nextLine();
        scan.close();
        switch(comando.charAt(0)){
            case('A'):
                saida[0] = 0;
                break;
            case('B'):
                saida[0] = 1;
                break;
            case('C'):
                saida[0] = 2;
                break;
            case('D'):
                saida[0] = 3;
                break;
            default:
                break;
        }
        saida[1] = Integer.parseInt(comando.substring(1)) - 1;
        return saida;
    }

    /** imprime no console as opções disponíveis de voos para o usuário selecionar, são exibidos os voos 1, 2 e 3.
     o programa lê um número inteiro digitado pelo usuário usando o método nextInt(). O valor digitado é
     armazenado na variável comando. */
    public static int selectVoo(){
        int comando;
        Scanner scan = new Scanner(System.in);
        System.out.println("Selecione entre os voos disponiveis:");
        System.out.println("Voo 1: POA -> FLN 3572");
        System.out.println("Voo 2: POA -> RIA 2434");
        System.out.println("Voo 3: FLN -> POA 2578");

        comando = scan.nextInt();


        scan.close();
        return comando;
    }
}
package org.example;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Cliente
{
    // variáveis de instância
    private String cpf;
    private String nome;
    private int voo;
    private String position;
    private String dataNascimento;

    /** declaração dos construtores da classe Cliente*/
    public Cliente(String cpf, String nome, String assento, String dataNascimento, int voo)
    {
        this.cpf = cpf;
        this.nome = nome;
        position = assento;
        this.dataNascimento = dataNascimento;
    }

    public Cliente(int voo){
        this.voo = voo;
    }


    /**
     * pede ao usuário que insira informações pessoais (nome, CPF e data de nascimento) e registra essas informações.
     */

    public void registerClient(Poltrona poltrona)  throws Exception
    {
        Scanner scan = new Scanner(System.in);


        System.out.println("Insira o nome do passageiro:");
        nome = scan.nextLine();

        System.out.println("Insira o cpf do passageiro:");
        cpf = scan.nextLine();

        System.out.println("Insira data de nascimento do passageiro (dd/mm/aaaa):");
        dataNascimento = scan.nextLine();

        position = poltrona.getPosicao();
        writeClient();

        scan.close();
    }

    /** Metodo que grava no disco o cliente
     */
    public void writeClient() throws Exception{
        PrintStream file = new PrintStream(new FileOutputStream("ListaClientes.txt", true));
        file.println(cpf + ";" + nome + ";" + dataNascimento + ";" + voo + ";" + position);
        file.close();
    }

    /**
     remove um cliente do arquivo "ListaClientes.txt" com base no CPF fornecido.
     Ele lê o arquivo, verifica cada linha em busca de um CPF correspondente, cria uma nova lista de linhas
     excluindo a linha correspondente e, em seguida, sobrescreve o arquivo com a nova lista de linhas,
     removendo assim o cliente especificado.
     */

    public void removeClient() throws Exception {

        Scanner scan = new Scanner(System.in);

        System.out.println("Insira o CPF do cliente a ser removido:");
        String cpfToRemove = scan.nextLine();

        scan.close();

        int index = 0;

        FileInputStream inputFile = new FileInputStream("ListaClientes.txt");
        Scanner fileScanner = new Scanner(inputFile);

        while (fileScanner.hasNextLine()) {
            index++;
        }

        fileScanner.close();

        String[] lines = new String[index];


        FileInputStream inputFile2 = new FileInputStream("ListaClientes.txt");
        Scanner fileScanner2 = new Scanner(inputFile);

        index = 0;

        while (fileScanner2.hasNextLine()) {
            String line = fileScanner2.nextLine();
            String[] parts = line.split(";");
            String cpf = parts[0];

            if (!cpf.equals(cpfToRemove)) {
                lines[index] = line;
                index++;
            }
        }

        fileScanner2.close();

        PrintStream file = new PrintStream("aeronave"+ voo +".txt");
        for (int i = 0; i < lines.length; i++) {
            file.println(lines[i]);
        }
        file.close();

        System.out.println("Cliente removido.");
    }

    /** usado para obter o CPF do cliente
     */
    public String getClientCpf()
    {
        return cpf;
    }

    /** exibe os dados de um cliente com base no seu CPF.
     O método recebe um objeto "Cliente" como parâmetro.
     */

    public void showClientData(){

        boolean found = false;

        if (cpf.equals(cpf)) {
            found = true;

            System.out.println("Dados do cliente:");
            System.out.println("CPF: " + cpf);
            System.out.println("Nome: " + nome);
            System.out.println("Data de Nascimento: " + dataNascimento);
            System.out.println("Voo: " + voo);
            System.out.println("Assento: " + position);
            System.out.println();

        }

        if (!found) {
            System.out.println("Cliente não encontrado.");
        }
    }
}

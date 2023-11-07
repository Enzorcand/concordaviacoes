package org.example;

public class Poltrona {
    private Cliente comprador;
    private String tipo;
    private int voo;
    private boolean disponivel;
    private boolean bloqueado;
    private String situacao;
    private String posicao;
    private double preco;

    /** permite criar objetos Poltrona com diferentes combinações de valores
     * para os atributos tipo e voo, enquanto os demais atributos são inicializados com valores padrão.
     */
    public Poltrona(String tipo, int voo) {
        this.tipo = tipo;
        this.voo = voo;
        this.disponivel = true;
        this.bloqueado = false;
        calcularValor(voo);
    }

    public Poltrona(int voo) {
        this.voo = voo;
        this.disponivel = true;
        this.bloqueado = false;
    }

    /** Metodo que gera o valor da poltrona
     */
    public void calcularValor(int voo){
        switch(voo){
            case 1, 3:
                preco = 657.00;
                break;
            case 2:
                preco = 320.00;
                break;
            default:
        }
        if(tipo.equals("corredor")){
            preco = preco*1.4;
        }
    }

    public double getPreco(){
        return preco;
    }

    /** recebe uma entrada de texto que representa a situação da poltrona, ocupado, vago ou bloqueado).
     O método atribui a entrada à variável situacao da poltrona. Em seguida, é feito um switch-case para
     determinar o estado da disponibilidade e do bloqueio da poltrona com base na entrada fornecida.
     Dependendo do valor da entrada, as seguintes ações são executadas:

     Caso a entrada seja "ocupado", a poltrona é definida como não disponível (disponivel = false) e não bloqueada (bloqueado = false).
     Caso a entrada seja "vago", a poltrona é definida como disponível (disponivel = true) e não bloqueada (bloqueado = false).
     Caso a entrada seja "bloqueado", a poltrona é definida como não disponível (disponivel = false) e bloqueada (bloqueado = true).
     Para qualquer outro valor de entrada, a poltrona é definida como não disponível (disponivel = false) e não bloqueada (bloqueado = false). */

    public void setSituation(String entrada) {
        situacao = entrada;

        switch (entrada) {
            case "ocupado":
                situacao = "ocupado";
                disponivel = false;
                bloqueado = false;

                break;
            case "vago":
                situacao = "vago";
                disponivel = true;
                bloqueado = false;

                break;
            case "bloqueado":
                situacao = "bloqueado";
                disponivel = false;
                bloqueado = true;

                break;
            default:
                disponivel = false;
                bloqueado = false;
                break;
        }
    }

    /** recebe dois parâmetros inteiros, i e j, que representam a posição da poltrona na matriz de poltronas.
     O método usa um switch-case com base no valor de i para atribuir uma letra correspondente à coluna da
     poltrona. Isso atribui à poltrona uma posição identificável, como "A-1", "B-2" etc. */

    public void setPosicao(int i, int j){
        char coluna = 'A';
        switch(i){
            case 0:
                coluna = 'A';
                break;
            case 1:
                coluna = 'B';
                break;
            case 2:
                coluna = 'C';
                break;
            case 3:

                coluna = 'D';
                break;
        }
        posicao = coluna + "-" + (j + 1);
    }

    /** Metodo que retorna a posiçao da poltrona
     */
    public String getPosicao(){
        return posicao;
    }

    /** Metodo que retorna para o print a demonstração da situação da poltrona
     */
    public String getSituation(){
        if (bloqueado) {
            return "X";
        } else if (!disponivel) {
            return "O";
        } else {
            return " ";
        }
    }

    /** Metodo que retorna em texto a situação da poltrona
     */
    public String getSituationText(){
        return situacao;
    }

    /** Metodo que retorna se a poltrona esta disponivel
     */
    public boolean isDisponivel(){
        return disponivel;
    }

    /** Metodo que retorna se a poltrona esta bloqueada
     */
    public boolean isBloqueada(){
        return bloqueado;
    }

    /** Metodo que define se a poltrona esta disponivel
     */
    public void setDisponivel(boolean bool){
        disponivel = bool;
    }

    /** metodo que bloqueia uma poltrona
     */
    public void setBloqueada(boolean bool){
        bloqueado = bool;
    }

    /** Metodo que gera o comprador da poltrona
     */
    public void setComprador(){
        comprador = new Cliente(voo);
    }

    /** Metodo que retorna o comprador da poltrona
     */
    public Cliente getComprador(){
        return comprador;
    }
}

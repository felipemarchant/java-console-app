import java.io.InputStream;
import java.util.Scanner;

class Leitura {
    private final Scanner leitor;
    private String ultimoTexto;

    public Leitura(InputStream input) {
        leitor = new Scanner(input);
    }

    public Leitura imprimir(String texto) {
        System.out.print(texto);
        ultimoTexto = texto;
        return this;
    }

    public String ler() {
        String texto = "";
        do {
            try {
                texto = leitor.nextLine().trim();
            } catch (Exception error) {
                System.out.print(friendError(error));
                System.out.print(ultimoTexto);
                texto = "";
                leitor.next();
            }
        } while(texto.isEmpty());
        ultimoTexto = "";
        return texto;
    }

    public int lerInt() {
        int inteiro = 0;
        boolean lido;
        do {
            try {
                inteiro = leitor.nextInt();
                lido = true;
            } catch (Exception error) {
                System.out.print(friendError(error));
                System.out.print(ultimoTexto);
                lido = false;
                leitor.next();
            }
        } while (!lido);
        ultimoTexto = "";
        return inteiro;
    }

    public float lerFloat() {
        float flutuante = 0;
        boolean lido;
        do {
            try {
                flutuante = leitor.nextFloat();
                lido = true;
            } catch (Exception error) {
                System.out.print(friendError(error));
                System.out.print(ultimoTexto);
                lido = false;
                leitor.next();
            }
        } while (!lido);
        ultimoTexto = "";
        return flutuante;
    }

    private String friendError(Exception error) {
        String errorS = "\nHouve um erro ao ler o dado informado ("+error+"). Verifique se está correto e tente novamente\n\n";
        return errorS.toUpperCase();
    }
}

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LeitorArquivo {
    public static void main(String[] args) {

        ArrayList<String> arquivoTexto = new ArrayList<>();
        ArrayList<String> arquivoPalavraChave = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o caminho do arquivo com o texto: ");
        String caminho = sc.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                arquivoTexto.add(linha);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Digite o caminho do arquivo com as palavras-chave: ");
        caminho = sc.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                arquivoPalavraChave.add(linha);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
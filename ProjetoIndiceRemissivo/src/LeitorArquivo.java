import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class LeitorArquivo {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        TabelaHash tabela = new TabelaHash(26);

        System.out.println("Digite o caminho do arquivo com o texto:");
        String caminhoTexto = sc.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoTexto))) {
            String linha;
            int numeroLinha = 1;

            while ((linha = br.readLine()) != null) {

                linha = linha.replaceAll("[^\\p{L}\\s-]", " ").toLowerCase();

                String[] palavras = linha.split("\\s+");

                for (String palavra : palavras) {
                    if (!palavra.isBlank()) {

                        String palavraCanonica = palavra;


                        if (palavraCanonica.length() > 1 && palavraCanonica.endsWith("s")) {

                            palavraCanonica = palavraCanonica.substring(0, palavraCanonica.length() - 1);
                        }

                        tabela.inserirOuAtualizar(palavraCanonica, numeroLinha);
                    }
                }

                numeroLinha++;
            }

            System.out.println("\nTexto processado com sucesso!\n");

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo de texto. Verifique o caminho. Erro: " + e.getMessage());
            sc.close();
            return;
        }

        System.out.println("Digite o caminho do arquivo com as palavras-chave:");
        String caminhoChaves = sc.nextLine();

        System.out.println("Digite o nome do arquivo TXT para a SAÍDA (Ex: indice_final.txt): ");
        String caminhoSaida = sc.nextLine();


        try (BufferedReader br = new BufferedReader(new FileReader(caminhoChaves));

             PrintWriter pw = new PrintWriter(new FileWriter(caminhoSaida))) {

            String linha;
            pw.println("ÍNDICE REMISSIVO GERADO:");

            while ((linha = br.readLine()) != null) {

                String chave = linha.trim().toLowerCase();

                if (!chave.isBlank()) {


                    String chaveCanonico = chave;
                    if (chaveCanonico.length() > 1 && chaveCanonico.endsWith("s")) {
                        chaveCanonico = chaveCanonico.substring(0, chaveCanonico.length() - 1);
                    }


                    Palavra palavraEncontrada = tabela.buscar(chaveCanonico);

                    if (palavraEncontrada != null) {

                        pw.println(palavraEncontrada.toString());
                    } else {

                        pw.println(chave + ": [não encontrada no texto]");
                    }
                }
            }

            System.out.println("\nÍNDICE REMISSIVO gerado com sucesso em: " + caminhoSaida + "\n");

        } catch (IOException e) {
            System.err.println("Erro ao gerar o índice remissivo. Erro: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Ocorreu um erro inesperado: " + e.getMessage());
        }

        sc.close();
    }
}
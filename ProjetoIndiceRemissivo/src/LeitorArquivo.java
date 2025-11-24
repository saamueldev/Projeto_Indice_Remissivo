import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

import Hash.TabelaHash;
import ListaEncadeada.Palavra;

public class LeitorArquivo {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Cria a tabela hash com 26 posições (A–Z)
        TabelaHash tabela = new TabelaHash(26);

        System.out.println("Digite o caminho do arquivo com o texto: ");
        String caminhoTexto = sc.nextLine();

        // --- 1. LER E PROCESSAR O TEXTO E PREENCHER A ESTRUTURA ---
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoTexto))) {
            String linha;
            int numeroLinha = 1;

            System.out.println("Processando texto...");

            while ((linha = br.readLine()) != null) {
                // Inclui o hífen (-) como um caractere permitido (para "humano-máquina")
                // Remove pontuações e padroniza para minúsculo
                linha = linha.replaceAll("[^\\p{L}\\s-]", " ").toLowerCase();

                // Divide em palavras, usando um ou mais espaços como separador
                String[] palavras = linha.split("\\s+");

                // Insere cada palavra na tabela
                for (String palavra : palavras) {
                    if (!palavra.isBlank()) {

                        // --- INÍCIO DO TRATAMENTO DE PLURAL SIMPLES ---
                        String plural = palavra;

                        // Verifica se a palavra tem mais de 1 caractere e termina com 's'
                        if (plural.length() > 1 && plural.endsWith("s")) {
                            // Remove o 's' final para obter a forma singular.
                            // Ex: "tecnologicos" -> "tecnologico"
                            plural = plural.substring(0, plural.length() - 1);
                        }
                        // --- FIM DO TRATAMENTO DE PLURAL SIMPLES ---

                        // O termo canônico (singular) é quem será inserido e buscará sua linha.
                        tabela.inserirOuAtualizar(plural, numeroLinha);
                    }
                }

                numeroLinha++;
            }

            System.out.println("\n=== Texto processado e estruturas preenchidas com sucesso! ===\n");

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo de texto. Verifique o caminho. Erro: " + e.getMessage());
            sc.close();
            return;
        }

        // --- 2. LER AS PALAVRAS-CHAVE E GERAR O ÍNDICE REMISSIVO NO ARQUIVO DE SAÍDA ---
        System.out.println("Digite o caminho do arquivo com as palavras-chave (indexar): ");
        String caminhoChaves = sc.nextLine();

        System.out.println("Digite o nome do arquivo TXT para a SAÍDA (Ex: indice_final.txt): ");
        String caminhoSaida = sc.nextLine();


        try (BufferedReader br = new BufferedReader(new FileReader(caminhoChaves));
             // Inicializa o escritor para o arquivo de saída
            PrintWriter pw = new PrintWriter(new FileWriter(caminhoSaida))) {

            String linha;
            pw.println("=== ÍNDICE REMISSIVO GERADO ===");

            while ((linha = br.readLine()) != null) {
                // Padroniza a chave de busca para minúsculo.
                String chave = linha.trim().toLowerCase();

                if (!chave.isBlank()) {
                    // A CHAVE DE BUSCA TAMBÉM DEVE SER NORMALIZADA PARA O SINGULAR
                    String pluralChave = chave;
                    if (pluralChave.length() > 1 && pluralChave.endsWith("s")) {
                        pluralChave = pluralChave.substring(0, pluralChave.length() - 1);
                    }

                    // Busca a palavra na estrutura Hash -> ABB usando o termo singularizado
                    Palavra palavraEncontrada = tabela.buscar(pluralChave);

                    if (palavraEncontrada != null) {
                        // Usa o toString() da Palavra (Ex: and 4 5 6)
                        // NOTA: A saída mostrará o termo singularizado, que é o termo canônico.
                        pw.println(palavraEncontrada.toString());
                    } else {
                        // A palavra-chave original (pode ser o plural) não foi encontrada
                        pw.println(chave + ": [não encontrada no texto]");
                    }
                }
            }

            System.out.println("\n=== ÍNDICE REMISSIVO gerado com sucesso em: " + caminhoSaida + " ===\n");

        } catch (IOException e) {
            System.err.println("Erro ao gerar o índice remissivo. Erro: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Ocorreu um erro inesperado: " + e.getMessage());
        }

        sc.close();
    }
}
package questao.ex2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VulnerableClass {
  private Pattern filePattern = Pattern.compile("[^A-Za-z0-9._]");
  private static final Pattern textPattern = Pattern.compile("^[a-zA-Z0-9\\s]{0,20}$");
    
  private void valida(String mensagem, String input) throws Exception {
    Matcher textMatcher = textPattern.matcher(input);
    if (textMatcher.find()) {
      throw new Exception("Formato improprio " + mensagem);
    }
  }
  public void vulnerableMethod(String filename) throws Exception {
    //Validar nome do Arquivo
    if (filename == null) {
      throw new Exception("Nome de arquivo nulo");
    }
    if (filename.length() < 1) {
      throw new Exception("Nome de arquivo nao pode ser vazio");
    }
    Matcher fileMatcher = filePattern.matcher(filename);
    if (fileMatcher.find()) {
      throw new Exception("Nome de arquivo tem caracteres invalidos");
    }
    while (true) {
      Scanner console = new Scanner(System.in);
      System.out.print("Digite a operacao desejada para realizar no arquivo <R para ler um arquivo,"
        + " W para escrever em um arquivo>? ");
      String opr = console.nextLine();
      if (opr.equals("R")) {
        BufferedReader bufferReader = null;
        try {
          bufferReader = new BufferedReader(new FileReader(filename));
          String currentLine;
          while ((currentLine = bufferReader.readLine()) != null) {
            valida("na leitura da linha do arquivo", currentLine);
            System.out.println(currentLine);
          }
        } catch (IOException e) {
          throw new Exception("Erro na leitura");
        }
      }
      else {
        BufferedWriter buffWrite;
        try {
          buffWrite = new BufferedWriter(new FileWriter(filename));
          String linha = "";
          System.out.println("Escreva algo: ");
          linha = console.nextLine();
          System.out.println(linha);
          valida("na leitura da linha do console", linha);
          buffWrite.append(linha + "\n");
        } catch (Exception e) {
            throw new Exception("Erro na escrita");
        }
      }
    }
  }
}
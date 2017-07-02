package questao.ex2;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Scanner;
import org.junit.*;
import org.junit.rules.ExpectedException;

public class VulnerableClassTest {
		
	@Rule
    public ExpectedException thrown = ExpectedException.none();

	@Test
	public void whenFilenameIsNullThrowsException() throws Exception {
		thrown.expect(Exception.class);
		thrown.expectMessage("Nome de arquivo nulo");
		VulnerableClass classeTeste = new VulnerableClass();
		classeTeste.vulnerableMethod(null);
	}
	
	@Test
	public void whenFilenameIsEmptyThrowsException() throws Exception {
		thrown.expect(Exception.class);
		thrown.expectMessage("Nome de arquivo nao pode ser vazio");
		VulnerableClass classeTeste = new VulnerableClass();
		classeTeste.vulnerableMethod("");
	}
	
	@Test
	public void whenFilenameHasInvalidLetterThrowsException() throws Exception {
		thrown.expect(Exception.class);
		thrown.expectMessage("Nome de arquivo tem caracteres invalidos");
		VulnerableClass classeTeste = new VulnerableClass();
		classeTeste.vulnerableMethod("Realização.txt");
	}
	
	@Test
	public void whenFilenameHasInvalidSymbolThrowsException() throws Exception {
		thrown.expect(Exception.class);
		thrown.expectMessage("Nome de arquivo tem caracteres invalidos");
		VulnerableClass classeTeste = new VulnerableClass();
		classeTeste.vulnerableMethod("(Arquivo).txt");
	}
	
	@Test
	public void whenInvalidSymbolInCommandThrowsException() throws Exception {		
		thrown.expect(Exception.class);
		thrown.expectMessage("Erro na escrita");
		VulnerableClass classeTeste = new VulnerableClass();
		String comando = "Ç";
		System.setIn(new ByteArrayInputStream(comando.getBytes()));
		classeTeste.vulnerableMethod("nomeValido.txt");
	}
	
	@Test
	public void whenArchiveNotFoundInReadingThrowsException() throws Exception {		
		thrown.expect(Exception.class);
		VulnerableClass classeTeste = new VulnerableClass();
		String comando = "R" + System.getProperty("line.separator");
		System.setIn(new ByteArrayInputStream(comando.getBytes()));
		classeTeste.vulnerableMethod("input.txt");
	}
	
	@Test
	public void whenInvalidSymbolWhileReadingConsoleThrowsException() throws Exception {		
		thrown.expect(Exception.class);
		thrown.expectMessage("Formato improprio na leitura da linha do arquivo");
		VulnerableClass classeTeste = new VulnerableClass();		
		String comando = "R" + System.getProperty("line.separator");
		System.setIn(new ByteArrayInputStream(comando.getBytes()));
		classeTeste.vulnerableMethod("input1.txt");
	}
	
	@Test
	public void whenInvalidSymbolWhileWritinggConsoleThrowsException() throws Exception {		
		thrown.expect(Exception.class);
		thrown.expectMessage("No line found");
		VulnerableClass classeTeste = new VulnerableClass();
		String comando = "W" + System.getProperty("line.separator") + "Linha inválida ç `$"  + System.getProperty("line.separator") + "Linha inválida ç `$" ;
		System.setIn(new ByteArrayInputStream(comando.getBytes()));
		classeTeste.vulnerableMethod("saida1.txt");
	}
	
}

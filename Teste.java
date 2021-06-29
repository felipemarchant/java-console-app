import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

class Teste {

	private static final Leitura leitor = new Leitura(System.in);
	private static final List<Carga> veiculosCarga = new ArrayList<>();
	private static final List<Passeio> veiculosPasseio = new ArrayList<>();

	public static void main(String[] args) {
		int escolha;
		do {
			escolha = leitor.imprimir(menu()).lerInt();
			escolha--;
			if (escolha == MenuEscolha.CADASTRAR_VEICULO_PASSEIO.ordinal()) cadastrarVeiculoPasseio();
			else if (escolha == MenuEscolha.CADASTRAR_VEICULO_CARGA.ordinal()) cadastrarVeiculoCarga();
			else if (escolha == MenuEscolha.IMPRIMIR_TODOS_VEICULOS_PASSEIO.ordinal()) imprimirTodosVeiculosPasseio();
			else if (escolha == MenuEscolha.IMPRIMIR_TODOS_VEICULOS_CARGA.ordinal()) imprimirTodosVeiculosCarga();
			else if (escolha == MenuEscolha.IMPRIMIR_VEICULO_PASSEIO_PELA_PLACA.ordinal()) imprimirVeiculosPasseioPelaPlaca();
			else if (escolha == MenuEscolha.IMPRIMIR_VEICULO_CARGA_PELA_PLACA.ordinal()) imprimirVeiculosCargaPelaPlaca();
			else if (escolha == MenuEscolha.SAIR_DO_SISTEMA.ordinal()) sairSistema();
			else opcaoInvalida();
		} while (escolha != MenuEscolha.SAIR_DO_SISTEMA.ordinal());
	}

	private static void cadastrarVeiculoPasseio() {
		leitor.imprimir("\n" + cabecalhoMenu(MenuEscolha.CADASTRAR_VEICULO_PASSEIO) + "\n");
		String continuar;
		do {
			String placa = lerPlacaUnica(porfavorDigite("a PLACA do veículo de passeio: "), Passeio.class);
			String marca = leitor.imprimir(porfavorDigite("a MARCA do veículo de passeio: ")).ler();
			String modelo = leitor.imprimir(porfavorDigite("a MODELO do veículo de passeio: ")).ler();
			float velocMax = leitor.imprimir(porfavorDigite("a VELOCIDADE MÁXIMA do veículo de passeio: ")).lerFloat();
			int qtdePassageiros = leitor.imprimir(porfavorDigite("a QUANTIDADE DE PASSAGEIROS do veículo de passeio: ")).lerInt();
			int qtdPistoes = leitor.imprimir(porfavorDigite("a QUANTIDADE DE PISTÕES: ")).lerInt();
			int potencia = leitor.imprimir(porfavorDigite("a POTÊNCIA: ")).lerInt();
			Passeio passeio = new Passeio(placa, marca, modelo, velocMax, qtdePassageiros);
			passeio.setMotor(qtdPistoes, potencia);
			veiculosPasseio.add(passeio);
			leitor.imprimir("\nVeiculo de Passeio cadastrado com sucesso!\n");
			continuar = leitor.imprimir("Deseja continuar (sim/não) ? ").ler().toUpperCase();
			leitor.imprimir("\n");
		} while (!continuar.equals("NÃO"));
	}

	private static void cadastrarVeiculoCarga() {
		leitor.imprimir("\n" + cabecalhoMenu(MenuEscolha.CADASTRAR_VEICULO_CARGA) + "\n");
		String continuar;
		do {
			String placa = lerPlacaUnica(porfavorDigite("a PLACA do veículo de carga: "), Carga.class);
			String marca = leitor.imprimir(porfavorDigite("a MARCA do veículo de carga: ")).ler();
			String modelo = leitor.imprimir(porfavorDigite("a MODELO do veículo de carga: ")).ler();
			float velocMax = leitor.imprimir(porfavorDigite("a VELOCIDADE MÁXIMA do veículo de carga: ")).lerFloat();
			int tara = leitor.imprimir(porfavorDigite("a TARA do veículo de carga: ")).lerInt();
			int cargaMax = leitor.imprimir(porfavorDigite("a CARGA MÁXIMA: ")).lerInt();
			int qtdPistoes = leitor.imprimir(porfavorDigite("a QUANTIDADE DE PISTÕES: ")).lerInt();
			int potencia = leitor.imprimir(porfavorDigite("a POTÊNCIA: ")).lerInt();
			Carga carga = new Carga(placa, marca, modelo, velocMax, tara, cargaMax);
			carga.setMotor(qtdPistoes, potencia);
			veiculosCarga.add(carga);
			leitor.imprimir("\nVeiculo de Carga cadastrado com sucesso!\n");
			continuar = leitor.imprimir("Deseja continuar (sim/não) ? ").ler().toUpperCase();
			leitor.imprimir("\n");
		} while (!continuar.equals("NÃO"));
	}

	private static void imprimirTodosVeiculosPasseio() {
		leitor.imprimir("\n" + cabecalhoMenu(MenuEscolha.IMPRIMIR_TODOS_VEICULOS_PASSEIO) + "\n");
		for (Passeio passeio : veiculosPasseio) leitor.imprimir(passeio.toString() + "\n\n");
		if (veiculosPasseio.size() > 0)
			 leitor.imprimir("\n\n");
		else leitor.imprimir("NENHUM VEÍCULO DE PASSEIO ENCONTRADO\n\n");
	}

	private static void imprimirTodosVeiculosCarga() {
		leitor.imprimir("\n" + cabecalhoMenu(MenuEscolha.IMPRIMIR_TODOS_VEICULOS_CARGA) + "\n");
		for (Carga carga : veiculosCarga) leitor.imprimir(carga.toString() + "\n\n");
		if (veiculosCarga.size() > 0)
			leitor.imprimir("\n\n");
		else leitor.imprimir("NENHUM VEÍCULO DE CARGA ENCONTRADO\n\n");
	}

	private static void imprimirVeiculosPasseioPelaPlaca() {
		leitor.imprimir("\n" + cabecalhoMenu(MenuEscolha.IMPRIMIR_VEICULO_PASSEIO_PELA_PLACA) + "\n");
		String placa = leitor.imprimir(porfavorDigite("a PLACA do veículo de passeio: ")).ler();
		Passeio passeio = buscaVeiculoPorId(placa, Passeio.class);
		if (passeio != null)
			 leitor.imprimir("\n" + passeio.toString() + "\n\n");
		else leitor.imprimir("\nVEÍCULO DE PASSEIO DE PLACA " + placa + " NÃO FOI ENCONTRADO\n\n");
	}

	private static void imprimirVeiculosCargaPelaPlaca() {
		leitor.imprimir("\n" + cabecalhoMenu(MenuEscolha.IMPRIMIR_VEICULO_CARGA_PELA_PLACA) + "\n");
		String placa = leitor.imprimir(porfavorDigite("a PLACA do veículo de carga: ")).ler();
		Carga carga = buscaVeiculoPorId(placa, Carga.class);
		if (carga != null)
			 leitor.imprimir("\n" + carga.toString() + "\n\n");
		else leitor.imprimir("\nVEÍCULO DE CARGA DE PLACA " + placa + " NÃO FOI ENCONTRADO\n\n");
	}

	private static void sairSistema() {
		leitor.imprimir("\nSaindo...\nDesenvolvido por Felipe Marchant F. Soares <felipemarchant.dev@gmail.com>\n");
	}

	private static void opcaoInvalida() {
		leitor.imprimir("\nOPÇÃO INVÁLIDA. TENTE NOVAMENTE\n\n");
	}

	private static String menu() {
		StringBuilder str = new StringBuilder("Sistema de Gestão de Veículos - Menu Inicial\n\n");
		for (MenuEscolha escolha : MenuEscolha.values()) str.append(cabecalhoMenu(escolha));
		str.append("\n").append("Opção: ");
		return str.toString();
	}

	private static String lerPlacaUnica(String texto, Class<? extends Veiculo> clazz) {
		String placa;
		do {
			placa = leitor.imprimir(texto).ler();
			if (buscaVeiculoPorId(placa, clazz) != null) {
				leitor.imprimir("A PLACA INFORMADA JA ESTÁ SENDO UTILIZADA. TENTE OUTRA\n");
				placa = "";
			}
		} while (placa.isEmpty());
		return placa;
	}

	private static String cabecalhoMenu(MenuEscolha escolha) {
		return escolha.ID + ". " + escolha.LABEL + "\n";
	}

	private static <T> T buscaVeiculoPorId(String placa, Class<T> clazz) {
		String finalPlaca = placa.toUpperCase();
		Predicate<Veiculo> filter = p -> p.getPlaca().toUpperCase().equals(finalPlaca);
		if (clazz.equals(Passeio.class)) {
			Passeio passeio = veiculosPasseio.stream().filter(filter).findAny().orElse(null);
			return clazz.cast(passeio);
		} else {
			Carga carga = veiculosCarga.stream().filter(filter).findAny().orElse(null);
			return clazz.cast(carga);
		}
	}

	private static String porfavorDigite(String texto) {
		texto = texto == null ? "" : texto;
		return "Por favor, digite " + texto;
	}
}

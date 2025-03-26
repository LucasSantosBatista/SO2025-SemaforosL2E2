package AeroportoController;

import java.util.concurrent.Semaphore;

public class ThreadAeroporto extends Thread {
	private static Semaphore pistaNorte = new Semaphore(1);
	private static Semaphore pistaSul = new Semaphore(1);
	private static Semaphore areaDecolagem = new Semaphore(2);

	private String modeloAeronave;
	private String destino;

	public ThreadAeroporto(String modeloAeronave, String destino) {
		this.modeloAeronave = modeloAeronave;
		this.destino = destino;
	}

	@Override
	public void run() {
		try {
			manobrar();
			taxiar();
			decolar();
		} catch (InterruptedException e) {
			System.err.println("Erro durante o voo do avião " + modeloAeronave + ": " + e.getMessage());
		}
	}

	private void manobrar() throws InterruptedException {
		System.out.println("[" + modeloAeronave + "] está manobrando para pista.");
		sleep(300 + (int) (Math.random() * (700 - 300 + 1)));
	}

	private void taxiar() throws InterruptedException {
		System.out.println("[" + modeloAeronave + "] está taxiando até área de decolagem.");
		sleep(500 + (int) (Math.random() * (1000 - 500 + 1)));
	}

	private void afastar() throws InterruptedException {
		System.out.println("[" + modeloAeronave + "] decolou e pista está sendo liberada.");
		sleep(300 + (int) (Math.random() * (800 - 300 + 1)));
	}

	private void decolar() {
		try {
			areaDecolagem.acquire(); // 2 aviões por decolagem

			int escolha = (int) (Math.random() * 2);

			if (escolha == 0) {
				decolarNorte();
			} else {
				decolarSul();
			}
			afastar();
		} catch (InterruptedException e) {
			System.err.println("Erro ao tentar decolar o avião " + modeloAeronave + ": " + e.getMessage());
		} finally {
			areaDecolagem.release(); // garantia a liberação da área de decolagem
		}
	}

	private void decolarNorte() {
		try {
			pistaNorte.acquire(); // 1 avião por pista
			System.err.println("[" + modeloAeronave + "] está decolando na pista norte para o destino " + destino);
			sleep(600 + (int) (Math.random() * (800 - 600 + 1)));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pistaNorte.release(); // garantia que a pista norte seja liberada
		}
	}

	private void decolarSul() {
		try {
			pistaSul.acquire(); // 1 avião por pista
			System.err.println("[" + modeloAeronave + "] está decolando na pista sul para o destino " + destino);
			sleep(600 + (int) (Math.random() * (800 - 600 + 1)));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pistaSul.release(); // garantia que a pista sul seja liberada
		}
	}

}

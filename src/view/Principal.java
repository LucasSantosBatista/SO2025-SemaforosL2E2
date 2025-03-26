package view;

import AeroportoController.ThreadAeroporto;

public class Principal {
	public static void main(String[] args) {
		String[] modelosAvioes = { "Boeing 737 MAX", "Airbus A321", "Embraer E190", "McDonnell Douglas MD-80",
				"Bombardier CRJ200", "Cessna Citation X", "Antonov An-124", "Tupolev Tu-154", "Concorde",
				"Lockheed L-1011 TriStar", "Saab 340", "Fokker 70" };

		String[] destinos = { "São Paulo => Rio de Janeiro", "Nova York => Londres", "Paris => Tóquio",
				"Berlim => Amsterdã", "Dubai => Singapura", "Toronto => Cidade do México", "Los Angeles => Sydney",
				"Moscou => Pequim", "Madri => Roma", "Buenos Aires => Santiago", "Lisboa => Madrid",
				"Chicago => Miami" };

		for (int i = 0; i < 12; i++) {
			int destinoAleatorio = (int) (Math.random() * 12);

			ThreadAeroporto voo = new ThreadAeroporto(modelosAvioes[i], destinos[destinoAleatorio]);
			voo.start();
		}
	}
}

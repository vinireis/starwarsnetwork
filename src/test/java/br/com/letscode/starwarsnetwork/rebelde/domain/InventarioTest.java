package br.com.letscode.starwarsnetwork.rebelde.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class InventarioTest {

	@Test
	void dadoInventario_quandoTentarModificarEstoque_EntaoDeveSerLancadaUmExcecao() {
		assertThrows(UnsupportedOperationException.class,
				() -> buildInventario(Map.ofEntries(Map.entry(TipoItem.AGUA, 5)))
				.getEstoque().put(TipoItem.COMIDA,10));
	}

	private Inventario buildInventario(Map<TipoItem, Integer> estoque) {
		return Inventario.builder()
				.estoque(estoque)
				.build();
	}
	
	@Test
	void dadoInventarioVazio_quandoCacularPontos_EntaoDeveRetornaZero() {
		Inventario inventario = buildInventario(new HashMap<TipoItem, Integer>());
		Integer pontos = Inventario.calculaPontos(inventario.getEstoque());
		assertEquals(pontos, 0);
	}
	
	@Test
	void dadoInventarioComItens_quandoCacularPontos_EntaoDeveRetornaASomaDosItens() {
		Inventario inventario = buildInventario(Map.ofEntries(Map.entry(TipoItem.AGUA, 5),Map.entry(TipoItem.COMIDA, 10)));
		Integer pontos = Inventario.calculaPontos(inventario.getEstoque());
		Integer pontosCalculado = (TipoItem.AGUA.getPontos() * 5) + (TipoItem.COMIDA.getPontos() * 10);
		assertEquals(pontos, pontosCalculado);
	}
}
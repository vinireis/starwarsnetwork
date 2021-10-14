package br.com.letscode.starwarsnetwork.rebelde.domain;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;

import org.junit.jupiter.api.Test;

class InventarioTest {

	@Test
	void dadoResistenciaPadrao_quandoTentarModificarBlackList_EntaoDeveSerLancadaUmExcecao() {
		assertThrows(UnsupportedOperationException.class,
				() -> buildInventario(Map.ofEntries(Map.entry(TipoItem.AGUA, 5)))
				.getEstoque().put(TipoItem.COMIDA,10));
	}

	private Inventario buildInventario(Map<TipoItem, Integer> estoque) {
		return Inventario.builder()
				.estoque(estoque)
				.build();
	}
}

package br.com.letscode.starwarsnetwork.rebelde.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class InventarioTest {
	private Map<TipoItem, Integer> buildEstoqueComUmItem() {
		return Map.ofEntries(Map.entry(TipoItem.AGUA, 3));
	}
	
	private Map<TipoItem, Integer> buildEstoqueComDoisItens() {
		Map<TipoItem, Integer> estoque = new HashMap<TipoItem, Integer>();
		estoque.put(TipoItem.AGUA, 10);
		estoque.put(TipoItem.COMIDA, 20);
		return estoque;
	}
	
	private Map<TipoItem, Integer> buildEstoqueComDoisItemRemovido() {
		return Collections.unmodifiableMap(Map.ofEntries(Map.entry(TipoItem.AGUA, 7),Map.entry(TipoItem.COMIDA, 20)));
	}
	
	private Inventario buildInventario(Map<TipoItem, Integer> estoque) {
		return Inventario.builder()
				.estoque(estoque)
				.build();
	}
	
	@Test
	void dadoInventario_quandoTentarModificarEstoque_EntaoDeveSerLancadaUmExcecao() {
		assertThrows(UnsupportedOperationException.class,
				() -> buildInventario(buildEstoqueComUmItem())
				.getEstoque().put(TipoItem.COMIDA,10));
	}
	
	@Test
	void dadoInventarioVazio_quandoCacularPontos_EntaoDeveRetornaZero() {
		Inventario inventario = buildInventario(new HashMap<TipoItem, Integer>());
		Integer pontos = Inventario.calculaPontos(inventario.getEstoque());
		assertEquals(pontos, 0);
	}
	
	@Test
	void dadoInventarioComItens_quandoCacularPontos_EntaoDeveRetornaASomaDosItens() {
		Inventario inventario = buildInventario(buildEstoqueComDoisItens());
		Integer pontos = Inventario.calculaPontos(inventario.getEstoque());
		Integer pontosCalculado = (TipoItem.AGUA.getPontos() * 10) + (TipoItem.COMIDA.getPontos() * 20);
		assertEquals(pontos, pontosCalculado);
	}
	
	@Test
	void dadoInventarioComItens_quandoRemoverItens_EntaoDeveRetirarEstoque() {
		Inventario inventarioComDoisItens = buildInventario(buildEstoqueComDoisItens());
		inventarioComDoisItens.removeItens(buildEstoqueComUmItem());
		assertEquals(inventarioComDoisItens.getEstoque(), buildEstoqueComDoisItemRemovido());
	}
}
package br.com.letscode.starwarsnetwork.rebelde.domain;

import static br.com.letscode.starwarsnetwork.rebelde.domain.InventarioTest.buildEstoqueComDoisItens;
import static br.com.letscode.starwarsnetwork.rebelde.domain.InventarioTest.buildEstoqueComDoisItensAposTroca;
import static br.com.letscode.starwarsnetwork.rebelde.domain.InventarioTest.buildEstoqueComUmItem;
import static br.com.letscode.starwarsnetwork.rebelde.domain.InventarioTest.buildEstoqueEquivalenteAoDeUmItem;
import static br.com.letscode.starwarsnetwork.rebelde.domain.InventarioTest.buildInventario;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import br.com.letscode.starwarsnetwork.handler.ApiException;

class RebeldeTest {
	
	private Rebelde buildRebelde(Inventario inventario) {
		return Rebelde.builder()
				.inventario(inventario)
				.build();
	}

	@Test
	void dadoUmaTrocaEquivalente_quandoExecutarTroca_EntaoDeveExecutarTrocaComSucesso() {
		Rebelde rebeldeComDoisItens = buildRebelde(buildInventario(buildEstoqueComDoisItens()));
		rebeldeComDoisItens.trocaItens(buildEstoqueComUmItem(), buildEstoqueEquivalenteAoDeUmItem());
		assertEquals(rebeldeComDoisItens.getInventario().getEstoque(), buildEstoqueComDoisItensAposTroca());
	}
	
	@Test
	void dadoUmaTrocaInjusta_quandoExecutarTroca_EntaoDeveLancarExececao() {
		Rebelde rebeldeComDoisItens = buildRebelde(buildInventario(buildEstoqueComDoisItens()));
		ApiException apiException = assertThrows(ApiException.class,
				() -> rebeldeComDoisItens.trocaItens(buildEstoqueComUmItem(), buildEstoqueComDoisItens()));
		assertEquals(apiException.getMessage(), "Os itens oferecidos não possuem a mesma quantidade de pontos!");
	}
	
	@Test
	void dadoTrocasNaQualRelbeldeNaoPossuaPontos_quandoExecutarTroca_EntaoDeveLancarExececao() {
		Rebelde rebeldeComDoisItens = buildRebelde(buildInventario(buildEstoqueComDoisItens()));
		ApiException apiException = assertThrows(ApiException.class,
				() -> {
					rebeldeComDoisItens.trocaItens(buildEstoqueComUmItem(), buildEstoqueEquivalenteAoDeUmItem());
					rebeldeComDoisItens.trocaItens(buildEstoqueComUmItem(), buildEstoqueEquivalenteAoDeUmItem());
					rebeldeComDoisItens.trocaItens(buildEstoqueComUmItem(), buildEstoqueEquivalenteAoDeUmItem());
					rebeldeComDoisItens.trocaItens(buildEstoqueComUmItem(), buildEstoqueEquivalenteAoDeUmItem());
				});
		assertEquals(apiException.getMessage(), String.format("Quantidade de pontos insuficiente para o item %s", TipoItem.AGUA.toString()));
	}
}

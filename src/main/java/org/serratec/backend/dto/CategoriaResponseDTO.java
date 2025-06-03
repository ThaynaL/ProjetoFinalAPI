package org.serratec.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record CategoriaResponseDTO(
		@Schema(
		        description = "Identificador único da categoria",
		        example = "1"
		    )
		Long id, 
		
		@Schema(
		        description = "Nome da categoria",
		        example = "Eletrônicos"
		    )
		String nomeCategoria,
		
		@Schema(
		        description = "Descrição da categoria",
		        example = "Produtos tecnológicos e aparelhos eletrônicos"
		    )
		String descricaoCategoria) {
}




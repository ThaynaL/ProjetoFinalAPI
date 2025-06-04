
package org.serratec.backend.controller;

import java.util.List;

import org.serratec.backend.docs.PedidoExamples;
import org.serratec.backend.dto.PedidoRequestDTO;
import org.serratec.backend.dto.PedidoResponseDTO;
import org.serratec.backend.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pedidos")
@SecurityRequirement(name = "bearerAuth")
public class PedidoController {

    @Autowired
    private PedidoService service;

    
    @Operation(
        summary = "Listar todos os pedidos",
        description = "Retorna todos os pedidos cadastrados",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Pedidos listados com sucesso",
                content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = PedidoExamples.EXEMPLO_LISTA)
                )
            )
        }
    )
    @GetMapping
    public List<PedidoResponseDTO> listarPedidos() {
        return service.listarPedidosPorCliente();
    }

    
    
    @Operation(
    	    summary = "Listar Pedidos Paginados",
    	    description = "Retorna pedidos em formato paginado com parâmetros personalizáveis",
    	    responses = {
    	        @ApiResponse(
    	            responseCode = "200",
    	            description = "Pedidos paginados listados com sucesso",
    	            content = @Content(
    	                mediaType = "application/json",
    	                examples = @ExampleObject(value = PedidoExamples.EXEMPLO_PAGINADO)
    	            )
    	        )
    	    }
    	)
    	@GetMapping("/pagina")
    	public Page<PedidoResponseDTO> listarPorPagina(
    	    @Parameter(description = "Número da página (começa em 0)", example = "0")
    	    @RequestParam(defaultValue = "0") int page,

    	    @Parameter(description = "Quantidade de itens por página", example = "10")
    	    @RequestParam(defaultValue = "10") int size,

    	    @Parameter(description = "Campo de ordenação no formato: campo,direção (ex: id,asc)", example = "id,asc")
    	    @RequestParam(defaultValue = "id,asc") String sort
    	) {
    	    String[] sortParams = sort.split(",");
    	    Sort.Direction direction = sortParams.length > 1 ? Sort.Direction.fromString(sortParams[1]) : Sort.Direction.ASC;
    	    Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortParams[0]));

    	    return service.listarPorPagina(pageable);
    	}
    
    
    

    @Operation(
        summary = "Buscar pedido por ID",
        description = "Busca um pedido específico pelo seu ID",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Pedido encontrado",
                content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = PedidoExamples.EXEMPLO_UNICO)
                )
            ),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado", content = @Content())
        }
    )
    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarPorId(id));
    }

    
    
    @Operation(
    	    summary = "Cadastrar um novo pedido",
    	    responses = {
    	        @ApiResponse(
    	            responseCode = "201",
    	            description = "Pedido criado com sucesso",
    	            content = @Content(
    	                mediaType = "application/json",
    	                examples = @ExampleObject(value = PedidoExamples.EXEMPLO_POST_RESPONSE)
    	            )
    	        )
    	    }
    	)
    	@PostMapping
    	public ResponseEntity<PedidoResponseDTO> criarPedido(@RequestBody @Valid PedidoRequestDTO pedidoDTO) {
    	    return ResponseEntity.status(HttpStatus.CREATED).body(service.criarPedido(pedidoDTO));
    	}

    
    
    
    @Operation(
    	    summary = "Atualizar um pedido existente",
    	    responses = {
    	        @ApiResponse(
    	            responseCode = "200",
    	            description = "Pedido atualizado com sucesso",
    	            content = @Content(
    	                mediaType = "application/json",
    	                examples = @ExampleObject(value = PedidoExamples.EXEMPLO_PUT_RESPONSE)
    	            )
    	        ),
    	        @ApiResponse(responseCode = "404", description = "Pedido não encontrado", content = @Content()),
    	        @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content())
    	    }
    	)
    	@PutMapping("/{id}")
    	public ResponseEntity<PedidoResponseDTO> atualizarPedido(
    	    @PathVariable(value = "id") Long id,
    	    @RequestBody @Valid PedidoRequestDTO pedidoDTO
    	) {
    	    return ResponseEntity.status(HttpStatus.OK).body(service.atualizarPedido(id, pedidoDTO));
    	}


    @Operation(
        summary = "Deletar um pedido",
        description = "Remove um pedido pelo ID",
        responses = {
            @ApiResponse(responseCode = "200", description = "Pedido deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado"),
            @ApiResponse(
                responseCode = "200",
                description = "Resposta de sucesso ao deletar",
                content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = PedidoExamples.EXEMPLO_DELETE)
                )
            )
        }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarPedido(@PathVariable(value = "id") Long id) {
        service.deletarPedido(id);
		return ResponseEntity.status(HttpStatus.OK).body("Pedido deletado com sucesso.");
	}
}

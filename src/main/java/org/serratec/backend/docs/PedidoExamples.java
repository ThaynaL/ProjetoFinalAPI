package org.serratec.backend.docs;

public class PedidoExamples {

    public static final String EXEMPLO_LISTA = """
    [
      {
        "id": 1,
        "dataPedido": "2025-06-03T14:30:00",
        "statusPedido": "EM_PROCESSAMENTO",
        "dataEntregaPedido": "2025-06-10T18:00:00",
        "itemPedidos": [
          {
            "quantidade": 2,
            "valorUnitario": 199.99,
            "descontoPercentual": 0.1,
            "valorTotal": 359.98,
            "produtoResponseDTO": {
              "id": 10,
              "nomeProduto": "Tênis Nike Air",
              "descricaoProduto": "Tênis de corrida confortável",
              "valorProduto": 199.99
            }
          }
        ]
      }
    ]
    """;

    public static final String EXEMPLO_UNICO = """
    {
      "id": 1,
      "dataPedido": "2025-06-03T14:30:00",
      "statusPedido": "EM_PROCESSAMENTO",
      "dataEntregaPedido": "2025-06-10T18:00:00",
      "itemPedidos": [
        {
          "quantidade": 2,
          "valorUnitario": 199.99,
          "descontoPercentual": 0.1,
          "valorTotal": 359.98,
          "produtoResponseDTO": {
            "id": 10,
            "nomeProduto": "Tênis Nike Air",
            "descricaoProduto": "Tênis de corrida confortável",
            "valorProduto": 199.99
          }
        }
      ]
    }
    """;

    public static final String EXEMPLO_PAGINADO = """
    		{
    		  "content": [
    		    {
    		      "id": 1,
    		      "dataPedido": "2025-06-03T14:30:00",
    		      "statusPedido": "EM_PROCESSAMENTO",
    		      "dataEntregaPedido": "2025-06-10T18:00:00",
    		      "itemPedidos": [
    		        {
    		          "quantidade": 2,
    		          "valorUnitario": 199.99,
    		          "descontoPercentual": 0.1,
    		          "valorTotal": 359.98,
    		          "produtoResponseDTO": {
    		            "id": 10,
    		            "nomeProduto": "Tênis Nike Air",
    		            "descricaoProduto": "Tênis de corrida confortável",
    		            "valorProduto": 199.99
    		          }
    		        }
    		      ]
    		    }
    		  ],
    		  "pageable": {
    		    "pageNumber": 0,
    		    "pageSize": 10
    		  },
    		  "totalPages": 1,
    		  "totalElements": 1,
    		  "last": true,
    		  "first": true,
    		  "numberOfElements": 1,
    		  "empty": false
    		}
    		""";

    public static final String EXEMPLO_DELETE = "\"Pedido deletado com sucesso.\"";
}

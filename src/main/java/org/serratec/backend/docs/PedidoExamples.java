package org.serratec.backend.docs;

public class PedidoExamples {

    public static final String EXEMPLO_LISTA = """
    [
      {
        "id": 1,
        "dataPedido": "2025-06-03T14:30:00",
        "statusPedido": "PENDENTE",
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
      "statusPedido": "PENDENTE",
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
    		      "statusPedido": "CANCELADO",
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
    
    public static final String EXEMPLO_POST_RESPONSE = """
    	    {
    	      "idCliente": 1,
    	      "itens": [
    	        {
    	          "idProduto": 2,
    	          "quantidade": 2,
    	          "valorUnitario": 120.5,
    	          "descontoPercentual": 0.05
    	        }
    	      ],
    	      "dataPedido": "2025-06-30T09:23:08",
    	      "dataEntregaPedido": "2025-07-15T10:30:00",
    	      "statusPedido": "PENDENTE"
    	    }
    	    """;
    
    public static final String EXEMPLO_PUT_RESPONSE = """
    		{
    		  "id": 1,
    		  "dataPedido": "2025-06-05T10:00:00",
    		  "statusPedido": "RECEBIDO",
    		  "dataEntregaPedido": "2025-06-15T18:00:00",
    		  "itemPedidos": [
    		    {
    		      "quantidade": 1,
    		      "valorUnitario": 299.90,
    		      "descontoPercentual": 0.0,
    		      "valorTotal": 299.90,
    		      "produtoResponseDTO": {
    		        "id": 12,
    		        "nomeProduto": "Mochila Couro Eco",
    		        "descricaoProduto": "Mochila para notebook com acabamento premium",
    		        "valorProduto": 299.90
    		      }
    		    }
    		  ]
    		}
    		""";


}

import java.util.Scanner;
public class MackShop {
    static Scanner entrada = new Scanner(System.in);
    // catálogo de produtos
    static int[] idsProdutos;
    static String[] nomesProdutos;
    static double[] precosProdutos;
    static int[] estoquesProdutos;
    // venda atual
    static int[] vendaAtualIds = new int[100];
    static int[] vendaAtualQuantidades = new int[100];
    static int contVendaAtual = 0;
    static double totalVenda = 0;
    //histórico de vendas
    static int[] historicoIdsPedidos = new int[100];
    static double[] historicoValoresPedidos = new double[100];;
    static int[][] historicoItensVendidos = new int[100][3];
    static int contHistoricoItens = 0;
    // finalizar venda
    static int idPedido = 1000;
    static int contIdPedidos = 0;

    public static void main(String[] args) {
        int opcao;
        boolean iniciada = false;
        do {
            menu();
            System.out.println("Digite a opção escolhida:");
            opcao = entrada.nextInt();

            switch (opcao) {
                case 1:
                    iniciada = inicializarBase();
                    break;
                case 2:
                    if (iniciada == false) {
                        System.out.print("Base não inicializada, você deve digitar a opção 1 antes");
                        break;
                    } else {
                        catalogoDeProdutos();
                        break;
                    }
                case 3:
                    if (iniciada == false) {
                        System.out.println("Base nao iniciada, você deve digitar a opção 1 antes");
                        break;
                    } else {
                        adicionarItemVenda();
                        break;
                    }
                case 4:
                    if (iniciada == false) {
                        System.out.print("Base não inicializada, você deve digitar a opção 1 antes");
                        break;
                    } else {
                        verResumoDaVendaAtual();
                        break;
                    }
                case 5:
                    if (iniciada == false) {
                        System.out.print("Base não inicializada, você deve digitar a opção 1 antes");
                        break;
                    } else {
                        finalizarVenda();
                        break;
                    }
                case 6:
                    if (iniciada == false) {
                        System.out.print("Base não inicializada, você deve digitar a opção 1 antes");
                        break;
                    } else {
                        verHistoricoVendas();
                        break;
                    }
                case 7:
                    if (iniciada == false) {
                        System.out.print("Base não inicializada, você deve digitar a opção 1 antes");
                        break;
                    } else {
                        buscarVendaEspecifica();
                        break;
                    }
                case 8:
                    if (iniciada == false) {
                        System.out.print("Base não inicializada, você deve digitar a opção 1 antes");
                        break;
                    } else {
                        reporEstoque();
                        break;
                    }
                case 9:
                    if (iniciada == false) {
                        System.out.print("Base não inicializada, você deve digitar a opção 1 antes");
                        break;
                    } else {
                        relatorioEstoqueBaixo();
                        break;
                    }
                case 10:
                    System.out.println("Saindo...");
                // default:
                //     System.out.println("Número inválido");
            }
        } while (opcao != 10);
        entrada.close();
    }

    public static void menu() {
        System.out.println("""

                ***************** MENU DE OPÇÕES *****************
                1. Inicializar base
                2. Exibir catálogo de produtos
                3. Adicionar item à venda
                4. Ver resumo da venda atual
                5. Finalizar venda (gerar histórico e Nota Fiscal)
                6. Ver histórico de vendas
                7. Buscar venda específica do histórico
                8. (Admin) Repor estoque
                9. (Admin) Relatório de estoque baixo
                10. Sair do programa
                **************************************************
                """);
    }

    public static Boolean inicializarBase() {
        idsProdutos = new int[]{1, 2, 3, 4, 5};
        nomesProdutos = new String[]{"Mouse Gamer", "Teclado Mecanico", "Headset 7.1", "Monitor", "Notebook"};
        precosProdutos = new double[]{150.00, 350.00, 420.50, 600.00, 2000.00};
        estoquesProdutos = new int[]{15, 20, 10, 7, 3};

        System.out.println("Base inicializada com sucesso!");
        return true;
    }

    public static void catalogoDeProdutos() {
        System.out.println("\n*************** Catálogo de Produtos **************");
        System.out.printf("%-5s | %-20s | %-10s | %-10s\n", "ID", "Descrição", "Preço", "Estoque");
        System.out.println("---------------------------------------------------");
        for (int i = 0; i < idsProdutos.length; i++) {
            if (estoquesProdutos[i] > 0) {
                System.out.printf("%-5d | %-20s | R$ %-7.2f | %-10d\n", idsProdutos[i], nomesProdutos[i], precosProdutos[i], estoquesProdutos[i]);
            }
        }
    }

    public static void adicionarItemVenda() {
        int idProduto;
        int qntdProduto;
        System.out.println("Digite o ID do produto: ");
        idProduto = entrada.nextInt();
        System.out.println("Digite a quantidade do produto: ");
        qntdProduto = entrada.nextInt();
        
        for (int i = 0; i < idsProdutos.length; i++) {
            if (idsProdutos[i] == idProduto && estoquesProdutos[i] > 0 && qntdProduto <= estoquesProdutos[i]) {
                vendaAtualIds[contVendaAtual] = idProduto;
                vendaAtualQuantidades[contVendaAtual] = qntdProduto;
                contVendaAtual++;
                System.out.println("Produto adicionado à venda.");
            }
        }
    }

    public static void verResumoDaVendaAtual() {
        double subtotal = 0;
        String simbolo = "*";
        System.out.println("\n*********************** Resumo da Venda Atual **********************");
        System.out.printf("* %-3s | %-5s | %-20s | %-5s | %-10s | %-10s%n", "#", "ID", "Descrição", "QTD", "Vl. Unit.", "Vl. Total");
        System.out.println("* --------------------------------------------------------------------");
        for (int i = 0; i < contVendaAtual; i++) {
            int id = vendaAtualIds[i];
            int qntd = vendaAtualQuantidades[i];
            subtotal = qntd * precosProdutos[id-1];
            totalVenda += subtotal;

            System.out.printf("* %-3d | %-5d | %-20s | %-5d | %-10.2f | %-10.2f%n", i, id, nomesProdutos[id-1], qntd, precosProdutos[id-1], subtotal);
        }
        System.out.printf("\n* TOTAL: R$%.2f %30s", totalVenda, simbolo);
    }

    public static void finalizarVenda() {
        if (contVendaAtual == 0) {
            System.out.println("Nenhum item na venda atual para finalizar.");
            return;
        }
        idPedido++;

        historicoIdsPedidos[idPedido - 1001] = idPedido;
        historicoValoresPedidos[idPedido - 1001] = totalVenda;
        contIdPedidos++;
        for (int i = 0; i < contVendaAtual; i++) {
            int idProduto = vendaAtualIds[i];
            int qtd = vendaAtualQuantidades[i];

            historicoItensVendidos[contHistoricoItens][0] = idPedido;     // ID do Pedido
            historicoItensVendidos[contHistoricoItens][1] = idProduto;    // ID do Produto
            historicoItensVendidos[contHistoricoItens][2] = qtd;          // Quantidade

            contHistoricoItens++;

            for (int j = 0; j < idsProdutos.length; j++) {
                if (idsProdutos[j] == idProduto) {
                    estoquesProdutos[j] -= qtd;
                }
            }

        }
        imprimirNota(idPedido);

        for (int i = 0; i < contVendaAtual; i++) {
            vendaAtualIds[i] = 0;
            vendaAtualQuantidades[i] = 0;
        }
        contVendaAtual = 0;
        totalVenda = 0;

        System.out.println("Venda finalizada com sucesso!");
    }

    // public static void imprimirNotaFiscal() {
    //     String linha = "*".repeat(75);
    //     String separador = "-".repeat(75);
    //     String simbolo = "*";
    //     System.out.println(linha);
    //     System.out.printf("* MACKSHOP %61s", simbolo);
    //     System.out.printf("\n* CNPJ: 12.345.678/0001-99  %44s\n", simbolo);
    //     System.out.println(linha);
    //     System.out.printf("* NOTA FISCAL - VENDA AO CONSUMIDOR %36s\n", simbolo);
    //     System.out.printf("* Pedido ID: %-10d %43s\n", idPedido, simbolo);
    //     System.out.printf("* Data de Emissão: 01/09/2025 15:15:30  %32s\n", simbolo);
    //     System.out.println(linha);
    //     System.out.printf("* %-3s | %-5s | %-20s | %-5s | %-10s | %-12s*\n","#", "ID", "DESCRIÇÃO", "QTD", "VL. UNIT.", "VL. TOTAL");
    //     System.out.println(separador);
    //     double subtotal = 0;
    //     for (int i = 0; i < contVendaAtual; i++) {
    //         int id = vendaAtualIds[i];
    //         int qtd = vendaAtualQuantidades[i];
    //         double valorUnitario = precosProdutos[id - 1];
    //         double valorTotal = qtd * valorUnitario;
    //         subtotal += valorTotal;

    //         System.out.printf("* %-3d | %-5d | %-20s | %-5d | R$ %-8.2f | R$ %-10.2f*\n",
    //                 (i + 1), id, nomesProdutos[id - 1], qtd, valorUnitario, valorTotal);
    //     }
    //     System.out.println(separador);
    //     System.out.printf("* %-35s R$ %-10.2f %38s\n", "SUBTOTAL", subtotal, "*");
    //     System.out.printf("* %-35s R$ %-10.2f %38s\n", "TOTAL", subtotal, "*");
    //     System.out.println(linha);
    //     System.out.printf("* OBRIGADO PELA PREFERÊNCIA! VOLTE SEMPRE! %29s\n", simbolo);
    //     System.out.println(linha);
    // }

    public static void verHistoricoVendas() {
        for (int i = 0; i < contIdPedidos; i++) {
            System.out.printf("\nPedido ID: %d - Valor Total: R$ %.2f\n", historicoIdsPedidos[i], historicoValoresPedidos[i]);
        }
    }

    public static void imprimirNota(int idPedido) {
        boolean pedidoEncontrado = false;
        double total = 0.0;
        int contadorItens = 1;

        // Cabeçalho
        System.out.println("*********************************************************************************************");
        System.out.printf("* %-91s *%n", "MACKSHOP");
        System.out.printf("* %-91s *%n", "CNPJ: 12.345.678/0001-99");
        System.out.println("*********************************************************************************************");
        System.out.printf("* %-91s *%n", "NOTA FISCAL - VENDA AO CONSUMIDOR");
        System.out.printf("* Pedido ID: %-82d *%n", idPedido);
        System.out.printf("* Data de Emissão: %-74s *%n", java.time.LocalDateTime.now());
        System.out.println("*********************************************************************************************");
        System.out.printf("* %-3s | %-5s | %-30s | %-5s | %-10s | %-10s *%n",
                "#", "ID", "DESCRIÇÃO", "QTD", "VL. UNIT.", "VL. TOTAL");
        System.out.println("-----------------------------------------------------------------------------------------------------------");

        // Percorre a matriz de histórico para imprimir os itens desse pedido
        for (int i = 0; i < contHistoricoItens; i++) {
            if (historicoItensVendidos[i][0] == idPedido) {
                pedidoEncontrado = true;

                int idProduto = historicoItensVendidos[i][1];
                int qtd = historicoItensVendidos[i][2];

                String descricao = nomesProdutos[idProduto - 1];
                double valorUnit = precosProdutos[idProduto - 1];
                double subtotal = valorUnit * qtd;

                total += subtotal;

                System.out.printf("* %-3d | %-5d | %-30s | %-5d | R$ %-8.2f | R$ %-8.2f *%n",
                        contadorItens++, idProduto, descricao, qtd, valorUnit, subtotal);
            }
        }

        if (!pedidoEncontrado) {
            System.out.println("Pedido não encontrado.");
            System.out.println("*********************************************************************************************");
            return;
        }

        // Rodapé
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        System.out.printf("* %-50s | R$ %-10.2f *%n", "TOTAL", total);
        System.out.println("*********************************************************************************************");
        System.out.printf("* %-91s *%n", "OBRIGADO PELA PREFERÊNCIA! VOLTE SEMPRE!");
        System.out.println("*********************************************************************************************");
    }

    public static void buscarVendaEspecifica() {
        System.out.println("Digite o ID do pedido que deseja buscar:");
        int idSolicitado = entrada.nextInt();
        imprimirNota(idSolicitado);
    }

    // public static void buscarVendaEspecifica() {
    //     System.out.println("Digite o ID do pedido que deseja buscar:");
    //     int idSolicitado = entrada.nextInt();

    //     boolean pedidoEncontrado = false;
    //     double total = 0.0;
    //     int contadorItens = 1;

    //     // Cabeçalho da nota
    //     System.out.println("*********************************************************************************************");
    //     System.out.printf("* %-91s *%n", "MACKSHOP");
    //     System.out.printf("* %-91s *%n", "CNPJ: 12.345.678/0001-99");
    //     System.out.println("*********************************************************************************************");
    //     System.out.printf("* %-91s *%n", "NOTA FISCAL - VENDA AO CONSUMIDOR");
    //     System.out.printf("* Pedido ID: %-82d *%n", idSolicitado);
    //     System.out.printf("* Data de Emissão: %-74s *%n", java.time.LocalDateTime.now());
    //     System.out.println("*********************************************************************************************");
    //     System.out.printf("* %-3s | %-5s | %-30s | %-5s | %-10s | %-10s *%n",
    //             "#", "ID", "DESCRIÇÃO", "QTD", "VL. UNIT.", "VL. TOTAL");
    //     System.out.println("-----------------------------------------------------------------------------------------------------------");

    //     // Percorre a matriz para encontrar todos os itens desse pedido
    //     for (int i = 0; i < contHistoricoItens; i++) {
    //         if (historicoItensVendidos[i][0] == idSolicitado) {
    //             pedidoEncontrado = true;

    //             int idProduto = historicoItensVendidos[i][1];  // coluna 1 = ID Produto
    //             int qtd = historicoItensVendidos[i][2];        // coluna 2 = Quantidade

    //             // Como os produtos começam no índice 0, usamos (idProduto - 1)
    //             String descricao = nomesProdutos[idProduto - 1];
    //             double valorUnit = precosProdutos[idProduto - 1];
    //             double subtotal = valorUnit * qtd;

    //             total += subtotal;

    //             System.out.printf("* %-3d | %-5d | %-30s | %-5d | R$ %-8.2f | R$ %-8.2f *%n",
    //                     contadorItens++, idProduto, descricao, qtd, valorUnit, subtotal);
    //         }
    //     }

    //     if (!pedidoEncontrado) {
    //         System.out.println("Pedido não encontrado.");
    //         System.out.println("*********************************************************************************************");
    //         return;
    //     }

    //     // Rodapé
    //     System.out.println("-----------------------------------------------------------------------------------------------------------");
    //     System.out.printf("* %-50s | R$ %-10.2f *%n", "TOTAL", total);
    //     System.out.println("*********************************************************************************************");
    //     System.out.printf("* %-91s *%n", "OBRIGADO PELA PREFERÊNCIA! VOLTE SEMPRE!");
    //     System.out.println("*********************************************************************************************");
    // }

    public static void reporEstoque(){
        System.out.println("Digite o ID do produto que deseja repor:");
        int idRepor = entrada.nextInt();
        System.out.println("Digite a quantidade do produto que deseja repor:");
        int qntdRepor = entrada.nextInt();

        for (int i = 0; i < idsProdutos.length; i++){
            if (idsProdutos[i] == idRepor){
                estoquesProdutos[i] += qntdRepor;
                System.out.println("Reposição do produto feita");
                return;
            }
        }
    }

    public static void relatorioEstoqueBaixo() {
        System.out.println("----------- Relatorio de estoque baixo ------------------");
        for (int i = 0; i < idsProdutos.length; i++){
            if ( estoquesProdutos[i] < 10 ){
                System.out.printf("ID: %d - %s | Estoque: %d\n", idsProdutos[i], nomesProdutos[i], estoquesProdutos[i]);
            }
        }
    }


    /*public static void vendaAtual() {
        int[] vendaAtualIds = new int[100];
        int[] vendaAtualQuantidades = new int[100];

    }

    public static void historicoDeVendas() {
        int[] historicoIdsPedidos = new int[100];
        double[] historicoValoresPedidos = new int[100];
        int[][] historicoItensVendidos = new int[100][3];

    }*/
}
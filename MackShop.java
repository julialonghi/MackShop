import java.util.Scanner;
public class MackShop {
    static Scanner entrada = new Scanner(System.in);

    static int[] idsProdutos;
    static String[] nomesProdutos;
    static double[] precosProdutos;
    static int[] estoquesProdutos;

    static int[] vendaAtualIds = new int[100];
    static int[] vendaAtualQuantidades = new int[100];

    static int[] historicoIdsPedidos;
    static double[] historicoValoresPedidos;
    static int[][] historicoItensVendidos;

    static int contVendaAtualIds = 0;
    static int contVendaAtualQuantidades = 0;

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
                    verResumoDaVendaAtual();
                    break;
                case 5:
                    imprimirNotaFiscal();
                    break;
                /*case 6:
                    verHistoricoVendas();
                    break;
                case 7:
                    buscarVendaEspecifica();
                    break;
                case 8:
                    reporEstoque();
                    break;
                case 9:
                    relatorioEstoqueBaixo();
                    break;
                default:
                    System.out.println("Número inválido");
                    
            }*/
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
            if (idsProdutos[i] == idProduto && estoquesProdutos[i] > 0 && qntdProduto < estoquesProdutos[i]) {
                // vendaAtualIds = new int[]{idProduto};
                if (contVendaAtualIds == 0) {
                    vendaAtualIds[0] = idProduto;
                    contVendaAtualIds += 1;
                } else {
                    vendaAtualIds[i] = idProduto;
                    contVendaAtualIds += 1;
                }
                if (contVendaAtualQuantidades == 0) {
                    vendaAtualQuantidades[0] = qntdProduto;
                    contVendaAtualQuantidades += 1;
                } else {
                    vendaAtualQuantidades[i] = qntdProduto;
                    contVendaAtualQuantidades += 1;
                }
            }
        }
    }

    public static void verResumoDaVendaAtual() {
        System.out.println("\n*********************** Resumo da Venda Atual **********************");
        System.out.printf("%-3s | %-5s | %-20s | %-5s | %-10s | %-10s%n", "#", "ID", "Descrição", "QTD", "Vl. Unit.", "Vl. Total");
        System.out.println("--------------------------------------------------------------------");
        for (int i = 0; i < contVendaAtualIds; i++) {
            int id = vendaAtualIds[i];
            int qntd = vendaAtualQuantidades[i];
            double subtotal = qntd * precosProdutos[i];

            System.out.printf("%-3d | %-5d | %-20s | %-5d | %-10.2f | %-10.2f%n", i, id, nomesProdutos[i], qntd, precosProdutos[i], subtotal);
        }
    }

    /*public static void finalizarVenda() {
        
    }
*/
    public static void imprimirNotaFiscal() {
        String linha = "*".repeat(72);
        String simbolo = "*";
        System.out.println(linha);
        System.out.printf("* MACKSHOP %61s", simbolo);
        System.out.printf("\n* CNPJ: 12.345.678/0001-99  %44s\n", simbolo);
        System.out.println(linha);
        System.out.printf("* NOTA FISCAL - VENDA AO CONSUMIDOR %36s\n", simbolo);
        System.out.printf("* Pedido ID:  %58s\n", simbolo);
        System.out.printf("* Data de Emissão: 01/09/2025 15:15:30  %32s\n", simbolo);
        System.out.println(linha);
        System.out.printf("%s %-3s | %-5s | %-20s | %-5s | %-10s | %-10s %s\n", simbolo, "#", "ID", "DESCRIÇÃO", "QTD", "VL. UNIT.", "VL. TOTAL", simbolo);
        System.out.println(linha);
        System.out.printf("* OBRIGADO PELA PREFERÊNCIA! VOLTE SEMPRE! %29s\n", simbolo);
        System.out.println(linha);
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
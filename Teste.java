public class Teste {
    public static void main(String[] args) {
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
}
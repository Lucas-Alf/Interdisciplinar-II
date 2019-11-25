package br.com.empresa.modelo;

import java.util.Scanner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ModeloApplicationTests {

	@Test
	public void contextLoads() {
		//public static void main(String[] args) {
			Scanner scan = new Scanner(System.in);
			int primo = 0;
			while (true) {
				System.out.println("Digite um Número: ");
				int num = scan.nextInt();
				for (int i = 1; i <= num; i++) {
					if (num % i == 0) {
						System.out.println(i);
						primo++;
					}  
				}
				if (primo <= 2) {
					System.out.println("É um Número Primo!");
				} else {
					System.out.println("Não é um Número Primo");
				}
			}
		}
	//}

}
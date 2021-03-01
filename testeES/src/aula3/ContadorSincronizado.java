package aula3;

public class ContadorSincronizado {
	
	private int valor;	


	
	public synchronized void incrementar() {  //identificação de região crítica
		valor ++;
	}
		
	public synchronized void decrementar () {  //identificação de região crítica
		valor --;
	}
		
	
	public int getValor () {
		return valor;
	}
	
	
public class Incrementador extends Thread { //classe interna para partilhar os mesmos atributos e separar funcionalidades
		
		
		public void run () {
			for (int i=0; i<1000;i++ )
				incrementar();
		}
		
	} //fim incrementador
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread[] threads = new Thread [4];
		ContadorSincronizado contador= new ContadorSincronizado() ;
		for (int i=0; i<threads.length;i++)
			threads[i]=contador.new Incrementador();
		long initTime= System.currentTimeMillis();
		for (Thread t: threads)
			t.start();
		for (Thread t: threads)
			try {
				t.join(); 						// acção bloqueante das threads
			} catch (InterruptedException e) {
				e.printStackTrace(); 			//podia não ter isto
			}
		System.out.println("Duração:" + (System.currentTimeMillis()-initTime)+ "ValorFinal:"+ contador.getValor());

			

		
		

	}//main

	
	
	
}//fim função
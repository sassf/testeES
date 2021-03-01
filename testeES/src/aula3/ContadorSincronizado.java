package aula3;

public class ContadorSincronizado {
	
	private int valor;	


	
	public synchronized void incrementar() {  //identifica��o de regi�o cr�tica
		valor ++;
	}
		
	public synchronized void decrementar () {  //identifica��o de regi�o cr�tica
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
				t.join(); 						// ac��o bloqueante das threads
			} catch (InterruptedException e) {
				e.printStackTrace(); 			//podia n�o ter isto
			}
		System.out.println("Dura��o:" + (System.currentTimeMillis()-initTime)+ "ValorFinal:"+ contador.getValor());

			

		
		

	}//main

	
	
	
}//fim fun��o
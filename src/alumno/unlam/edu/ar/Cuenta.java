package alumno.unlam.edu.ar;

public class Cuenta {

	private Long cbu;
	private Double saldo;
	
	public Cuenta(Long cbu, Double saldo) {
		this.cbu = cbu;
		this.saldo = saldo;
	}

	public Long getCbu() {
		return cbu;
	}

	public void setCbu(Long cbu) {
		this.cbu = cbu;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public void extraerDinero(Double monto) throws SaldoInsuficienteException {
		Double saldoActual = this.getSaldo();
		if(saldoActual >= monto) {
			saldoActual -= monto;
			this.setSaldo(saldoActual);
		} else {
			throw new SaldoInsuficienteException("No dispone de saldo suficiente para realizar esta operación");
		}
	}

	@Override
	public String toString() {
		return "Cuenta [cbu=" + cbu + "]";
	}
	
	 
}

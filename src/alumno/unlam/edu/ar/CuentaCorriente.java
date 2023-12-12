package alumno.unlam.edu.ar;

public class CuentaCorriente extends Cuenta {

	private Double descubierto;
	private Double comisionAPagar;
	
	public CuentaCorriente(Long cbu, Double saldo, Double descubierto) {
		super(cbu, saldo);
		this.descubierto = descubierto;
		this.comisionAPagar = 0.0;
	}

	public Double getDescubierto() {
		return descubierto;
	}

	public void setDescubierto(Double descubierto) {
		this.descubierto = descubierto;
	}

	
	public Double getComisionAPagar() {
		return comisionAPagar;
	}

	public void setComisionAPagar(Double comisionAPagar) {
		this.comisionAPagar = comisionAPagar;
	}

	@Override
	public Boolean extraerDinero(Double monto) throws SaldoInsuficienteException {
		Double saldoActual = this.getSaldo();
		Double descubierto = this.getDescubierto();
		Double saldoTotal = saldoActual + descubierto;
		Double descubiertoUtilizado = 0.0;
		Boolean seExtrajo = false;
		
		if(saldoActual >= monto) {
			saldoActual -=monto;
			seExtrajo =true;
			this.setSaldo(saldoActual);
		}
		
		if(saldoTotal >= monto && !seExtrajo) {
			descubiertoUtilizado = saldoTotal - monto;
			this.setSaldo(0.0);
			this.setDescubierto(this.getDescubierto()-descubiertoUtilizado);
			calcularComision(descubiertoUtilizado);
			seExtrajo = true;
		} else if(saldoTotal <= monto && !seExtrajo) {
			throw new SaldoInsuficienteException("No cuenta con dinero suficiente para realizar la operación");
		}
		
		return seExtrajo;
	}

	private void calcularComision(Double descubiertoUtilizado) {
		Double comision = this.getComisionAPagar();
		comision += descubiertoUtilizado * 0.05;
		this.setComisionAPagar(comision);
	}
	
}

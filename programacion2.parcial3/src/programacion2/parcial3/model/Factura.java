package programacion2.parcial3.model;

public class Factura {

	private String codigo;
	private String fechaFacturacion;
	private String totalPagar;
	private String cedulaCliente;

	public Factura() {
		super();
	}

	public Factura(String codigo, String fechaFacturacion, String totalPagar, String cedulaCliente) {
		super();
		this.codigo = codigo;
		this.fechaFacturacion = fechaFacturacion;
		this.totalPagar = totalPagar;
		this.cedulaCliente = cedulaCliente;

	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getFechaFacturacion() {
		return fechaFacturacion;
	}

	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}

	public String getTotalPagar() {
		return totalPagar;
	}

	public void setTotalPagar(String totalPagar) {
		this.totalPagar = totalPagar;
	}

	public String getCedulaCliente() {
		return cedulaCliente;
	}

	public void setCedulaCliente(String cedulaCliente) {
		this.cedulaCliente = cedulaCliente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fechaFacturacion == null) ? 0 : fechaFacturacion.hashCode());
		result = prime * result + ((totalPagar == null) ? 0 : totalPagar.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Factura other = (Factura) obj;
		if (fechaFacturacion == null) {
			if (other.fechaFacturacion != null)
				return false;
		} else if (!fechaFacturacion.equals(other.fechaFacturacion))
			return false;
		if (totalPagar == null) {
			if (other.totalPagar != null)
				return false;
		} else if (!totalPagar.equals(other.totalPagar))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Factura [codigo=" + codigo + ", fechaFacturacion=" + fechaFacturacion + ", totalPagar=" + totalPagar
				+ ", cedulaCliente=" + cedulaCliente + "]";
	}







}

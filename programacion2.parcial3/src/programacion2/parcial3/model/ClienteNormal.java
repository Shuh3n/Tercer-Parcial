package programacion2.parcial3.model;

public class ClienteNormal extends Cliente{

	private String apellidos;
	private String cedula;
	private String estrato;

	public ClienteNormal() {
		super();
	}

	public ClienteNormal(String nombre, String apellidos, String cedula, String estrato) {
		super(nombre);
		this.apellidos = apellidos;
		this.cedula = cedula;
		this.estrato = estrato;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getEstrato() {
		return estrato;
	}

	public void setEstrato(String estrato) {
		this.estrato = estrato;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cedula == null) ? 0 : cedula.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClienteNormal other = (ClienteNormal) obj;
		if (cedula == null) {
			if (other.cedula != null)
				return false;
		} else if (!cedula.equals(other.cedula))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ClienteNormal [apellidos=" + apellidos + ", cedula=" + cedula + ", estrato=" + estrato + "]";
	}








}

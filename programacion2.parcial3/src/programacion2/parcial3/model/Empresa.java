package programacion2.parcial3.model;

import java.util.ArrayList;
import java.util.List;

public class Empresa {

	private String nombre;
	private List<ClienteNormal> listaClientesNormales = new ArrayList<>();
	private List<ClienteJuridico> listaClientesJuridicos = new ArrayList<>();
	private List<Factura> listaFacturas = new ArrayList<>();

	public Empresa() {
		super();
	}

	public Empresa(String nombre, List<ClienteNormal> listaClientesNormales, List<ClienteJuridico> listaClientesJuridicos,
			List<Factura> listaFacturas) {
		super();
		this.nombre = nombre;
		this.listaClientesNormales = listaClientesNormales;
		this.listaClientesJuridicos = listaClientesJuridicos;
		this.listaFacturas = listaFacturas;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<ClienteNormal> getListaClientesNormales() {
		return listaClientesNormales;
	}

	public void setListaClientesNormales(List<ClienteNormal> listaClientesNormales) {
		this.listaClientesNormales = listaClientesNormales;
	}

	public List<ClienteJuridico> getListaClientesJuridicos() {
		return listaClientesJuridicos;
	}

	public void setListaClientesJuridicios(List<ClienteJuridico> listaClientesJuridicos) {
		this.listaClientesJuridicos = listaClientesJuridicos;
	}
	public List<Factura> getListaFacturas() {
		return listaFacturas;
	}

	public void setListaFacturas(List<Factura> listaFacturas) {
		this.listaFacturas = listaFacturas;
	}





	/**
	 * Se ingresan los datos del cliente que se desea crear.
	 * @param nombre
	 * @param apellidos
	 * @param cedula
	 * @param estrato
	 * @return Retorna true si se pudo crear el cliente, false lo contrario a esto.
	 */
    public boolean crearClienteNormal(String nombre, String apellidos,String cedula, String estrato){

    	if(!verificarClienteNormal(cedula)){
    		ClienteNormal cliente = new ClienteNormal(nombre, apellidos, cedula, estrato);
    		listaClientesNormales.add(cliente);
    		return true;
    	}
		return false;


    }

    /**
     * Se recibe la cédula del cliente y se revisa si se encuentra registrado
     * @param
     * @return retorna true o false dependiendo del resultado hallado
     */
	public boolean verificarClienteNormal(String cedula){
		boolean encontrado= false;
		for (ClienteNormal cliente : listaClientesNormales){
			if(cliente.getCedula().equals(cedula)){
				encontrado= true;
				return encontrado;
			}
		}
		return encontrado;
	}

	/**
	 * Lo que hace esta función es crear la factura de un Cliente Natural
	 * @param codigo
	 * @param totalPagar
	 * @param cedulaCliente
	 * @param fechaFacturacion
	 * @return
	 */
	public boolean crearFacturaNormal(String codigo, String totalPagar, String cedulaCliente, String fechaFacturacion){
		boolean creado = false;
		if(verificarClienteNormal(cedulaCliente)){
			if(!verificarFacturaClienteNormal(cedulaCliente,fechaFacturacion)){
				Factura factura = new Factura(codigo, fechaFacturacion, totalPagar, cedulaCliente);
				listaFacturas.add(factura);
				creado = true;
				return creado;
			}
		}

		return creado;
	}





	/**
	 * Lo que hace esta función es crear la factura de un Cliente Juridico
	 * @param codigo
	 * @param totalPagar
	 * @param nit
	 * @param fechaFacturacion
	 * @return
	 */
	public boolean crearFacturaJuridico(String codigo, String totalPagar, String nit, String fechaFacturacion){
		boolean creado = false;
		if(verificarClienteJuridico(nit)){
			if(!verificarFacturaClienteJuridico(nit,fechaFacturacion)){
				Factura factura = new Factura(codigo, fechaFacturacion, totalPagar, nit);
				listaFacturas.add(factura);
				creado = true;
				return creado;
			}
		}

		return creado;
	}

	/**
	 * Este método verifica si existe alguna factura registrada a un cliente Natural en una fecha determinada
	 * @param cedula
	 * @param fecha
	 * @return
	 */
	public boolean verificarFacturaClienteNormal(String cedula, String fecha){
		boolean flag = false;
		if(verificarClienteNormal(cedula)){
			for (Factura factura : listaFacturas) {
				if(factura.getCedulaCliente().equals(cedula) && factura.getFechaFacturacion().equals(fecha)){
					flag = true;
					return flag;
				}

			}

		}
		return flag;
	}

	/**
	 * Este método verifica si existe alguna factura registrada a un cliente Juridico en una fecha determinada
	 * @param nit
	 * @param fecha
	 * @return
	 */
	public boolean verificarFacturaClienteJuridico(String nit, String fecha){
		boolean flag = false;
		if(verificarClienteJuridico(nit)){
			for (Factura factura : listaFacturas) {
				if(factura.getCedulaCliente().equals(nit) && factura.getFechaFacturacion().equals(fecha)){
					flag = true;
					return flag;
				}

			}

		}
		return flag;
	}


	/**
	 * Esta Función recibe la identificación de un cliente natural y retorna el cliente si es que este está registrado
	 * @param cedula
	 * @return
	 */
	public ClienteNormal encontrarClienteNormal(String cedula){
		for (ClienteNormal cliente : listaClientesNormales) {
			if(cliente.getCedula().equals(cedula)){
				return cliente;
			}
		}
		return null;
	}

	/**
	 * Esta función revisa si un cliente tiene facturas y las retorna
	 * @param cedulaCliente
	 * @return
	 */
	public Factura encontrarFacturaNormal(String cedulaCliente) {
		for (Factura fac : listaFacturas) {
			if(fac.getCedulaCliente().equals(cedulaCliente)){
				return fac;
			}
		}
		return null;
	}




	/**
	 * Se ingresan los datos del cliente que se desea crear.
	 * @param name
	 * @param nit
	 * @param telefono
	 * @return retorna true si se pudo crear normal y false no contrario
	 */
	public boolean crearClienteJuridico(String name, String nit, String telefono) {
    	if(!verificarClienteJuridico(nit)){
    		ClienteJuridico cliente = new ClienteJuridico(name, nit, telefono);
    		listaClientesJuridicos.add(cliente);
    		return true;
    	}
		return false;


    }

	/**
	 * Se recibe la cédula del cliente y se revisa si se encuentra registrado
	 * @param nit
	 * @return retorna true o false dependiendo del resultado hallado
	 */
	private boolean verificarClienteJuridico(String nit) {
		boolean encontrado = false;
		for (ClienteJuridico cliente : listaClientesJuridicos){
			if(cliente.getNit().equals(nit)){
				encontrado = true;
				return encontrado;
			}
		}
		return encontrado;
	}

	/**
	 * Esta Función recibe la identificación de un cliente natural y retorna el cliente si es que este está registrado
	 * @param nit
	 * @return
	 */
	public ClienteJuridico encontrarClienteJuridico(String nit) {
		for (ClienteJuridico cliente : listaClientesJuridicos) {
			if(cliente.getNit().equals(nit)){
				return cliente;
			}
		}
		return null;
	}





}

package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.Aplicacion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import programacion2.parcial3.model.ClienteJuridico;
import programacion2.parcial3.model.Factura;

public class ClienteJuridicoController implements Initializable{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Factura, String> columnCodigo;

    @FXML
    private TableView<ClienteJuridico> tableViewCliente;

    @FXML
    private TableColumn<?, ?> columnTotalPagar;

    @FXML
    private TextField txtInfoNumCuenta;

    @FXML
    private Button btnVentanaPrincipal;

    @FXML
    private TextField txtTelefono;

    @FXML
    private Button btnLimpiarInfoNormal;

    @FXML
    private TableColumn<ClienteJuridico, String> columnTelefono;

    @FXML
    private Button btnLimpiarCamposFacts;

    @FXML
    private TextField txtNombreCliente;

    @FXML
    private Button btnAniadirFactura;

    @FXML
    private TextField txtFecha;

    @FXML
    private TextField txtTotalpagar;

    @FXML
    private TextField txtNIT;

    @FXML
    private Button btnAgregarClienteNormal;

    @FXML
    private TableColumn<Factura, String> columnFechaFactura;

    @FXML
    private TableColumn<ClienteJuridico, String> columnNombre;

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtCedulaFactura;

    @FXML
    private Button btnInfoLimpiar;

    @FXML
    private Button btnConsultarFacturas;

    @FXML
    private TableView<Factura> tableViewFacturas;

    @FXML
    private Button btnLimpairCamposFac;

    @FXML
    private TableColumn<ClienteJuridico, String> columnNIT;

    @FXML
    private TextField txtNitClienteFactura;

    @FXML
    private Button btnConsultar2;

	private VentanaPrincipalController ventanaPrincipalController;

	private Aplicacion aplicacion;

	private Stage stage;

	ObservableList<ClienteJuridico> listaInfoClientesJuridicos = FXCollections.observableArrayList();
	ObservableList<Factura> listaFacturasClientesJuridicos = FXCollections.observableArrayList();


	//TAB CREAR CLIENTE//

    @FXML
    void limpiarInfoCliente(ActionEvent event) {
    	txtNombreCliente.clear();
		txtNIT.clear();
		txtTelefono.clear();

    }

    /**
     * Este m�todo prepara la informaci�n para que el pr�ximo m�todo cree al cliente, valida los datos y manda alertas.
     * @param event
     */
    @FXML
    void agregarClienteJuridico(ActionEvent event) {
    	String name = txtNombreCliente.getText();
    	String nit = txtNIT.getText();
    	String telefono = txtTelefono.getText();

    	if(validarDatos(name, nit, telefono)){
    		crearClienteJuridico(name, nit, telefono);
    		txtNombreCliente.clear();
    		txtNIT.clear();
    		txtTelefono.clear();

    	}

    }

    /**
     * Este m�todo conecta con el main y la l�gica y crea a Cliente Juridico
     * @param name
     * @param nit
     * @param telefono
     */
    private void crearClienteJuridico(String name, String nit, String telefono) {
    	boolean resultado = aplicacion.crearClienteJuridico(name,nit,telefono);
    	if(resultado==true){
    		mostrarMensaje("Informaci�n Cliente", "Cliente creado", "El cliente se ha creado con �xito", AlertType.INFORMATION);
    	}
    	else{
    		mostrarMensaje("Informaci�n Cliente", "Cliente no creado", "El cliente no ha sido creado", AlertType.INFORMATION);

    	}
	}

    /**
     * Esta funci�n valida los datos que ingresa el usuario, retorna true si est�n bien, y false si no lo est�n seguido de unas alertas.
     * @param name
     * @param nit
     * @param telefono
     * @return
     */
    private boolean validarDatos(String name, String nit, String telefono) {
		String notificacion = "";

		/*Se valida que el saldo ingresado no sea null ni sea cadena vac�a,
		adem�s se valida que sea num�rico para su correcta conversi�n */


		if (name == null || name.equals("")) {
			notificacion += "Por favor ingrese un nombre\n";
		}

		if (nit == null || nit.equals("")) {
			notificacion += "Por favor ingrese el NIT\n";
		}
		if (telefono == null || telefono.equals("")) {
			notificacion += "Por favor ingrese el tel�fono\n";
		}

		else {
			if(!esNumero(telefono)){
				notificacion += "El tel�fono debe ser n�merico\n";
				}
			}

		if (!notificacion.equals("")) {
			mostrarMensaje("Notificaci�n", "Cliente no creado", notificacion, AlertType.WARNING);
			return false;
		}

		return true;
	}

    /**
     * Este m�todo verifica si el String ingresado es n�merico.
     * @param string
     * @return retorna true si si lo es, false lo contrario
     */
	private boolean esNumero(String string) {

		try {

			Float.parseFloat(string);

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Este m�todo muestra las alertas al usuario
	 * @param titulo
	 * @param header
	 * @param contenido
	 * @param alertype
	 */
	public void mostrarMensaje(String titulo, String header, String contenido, AlertType alertype) {
		Alert alert = new Alert(alertype);
		alert.setTitle(titulo);
		alert.setHeaderText(header);
		alert.setContentText(contenido);
		alert.showAndWait();
	}

	/**
	 *
	 * @param event
	 */
	 @FXML
	 void showVentanaPrincipal(ActionEvent event) {
		ventanaPrincipalController.show();
		stage.close();
	 }



	 //TAB CONSULTAR INFO CUENTA

	 /**
	  * Esta funci�n se encarga de mostrar la informaci�n de una cuenta a partir de su CC, esta las muestra en una tableView
	  * @param event
	  */
    @FXML
    void mostrarInfoCuenta(ActionEvent event) {
    	String nit = txtInfoNumCuenta.getText();

    	if (nit != null && !nit.equals("")){
    		ClienteJuridico cliente = aplicacion.encontrarClienteJuridico(nit);
    		if(cliente!=null){
    			tableViewCliente.getItems().clear();
				tableViewCliente.setItems(getCuentaInfo(nit));
    		}
    		else {
    			mostrarMensaje("Notificaci�n cliente", "Cliente no encontrado", "La informaci�n del cliente no ha sido encontrada",AlertType.INFORMATION);
				tableViewCliente.getItems().clear();
			}
		}
		else{
			mostrarMensaje("Notificaci�n", "Datos inv�lidos", "Por favor ingresar correctamente el NIT", AlertType.WARNING);
    	}

    }

    /**
     * Esta funci�n sirve para llenar los datos de la table view
     * @param nit
     * @return
     */
    private ObservableList<ClienteJuridico> getCuentaInfo(String nit) {
    	listaInfoClientesJuridicos.clear();
    	listaInfoClientesJuridicos.add(aplicacion.encontrarClienteJuridico(nit));
		return listaInfoClientesJuridicos;
	}

    @FXML
    void limpiarInfoCuenta(ActionEvent event) {
    	listaInfoClientesJuridicos.clear();
		txtInfoNumCuenta.clear();

    }



    //TAB AGREGAR FACTURAS//

    //TAB REGISTRAR FACTURA

    /**
     * Este m�todo sirve para agregar facturas al programa
     * @param event
     */
    @FXML
    void aniadirFactura(ActionEvent event) {
    	String codigo = txtCodigo.getText();
    	String fecha = txtFecha.getText();
    	String totalPagar = txtTotalpagar.getText();
    	String nit = txtNitClienteFactura.getText();

    	if(validarDatos1(codigo, fecha, totalPagar,nit)){
    		crearFactura(codigo, fecha, totalPagar, nit);

    	}

    }

    /**
     * Este es un aliado de la funci�n anterior, esta sirve para comunicarse con Aplicaci�n y con la l�gica
     * @param code
     * @param fecha
     * @param totalPagar
     * @param nit
     */
    private void crearFactura(String code, String fecha, String totalPagar, String nit) {
       	boolean resultado = aplicacion.crearFacturaJuridico(code, fecha, totalPagar, nit);
    	if(resultado==true){
    		mostrarMensaje("Informaci�n factura", "Factura creada", "La factura a sido creada con �xito", AlertType.INFORMATION);
    		txtTotalpagar.clear();
    		txtNitClienteFactura.clear();
    		txtCodigo.clear();
    		txtFecha.clear();

    	}
    	else{
    		mostrarMensaje("Informaci�n factura", "Factura no creada", "La factura no ha sido creada", AlertType.INFORMATION);
    	}
	}

    /**
     * Esta funci�n valida los datos que ingresa el usuario, retorna true si est�n bien, y false si no lo est�n seguido de unas alertas.
     * @param code
     * @param fecha
     * @param totalPagar
     * @param nit
     * @return
     */
	private boolean validarDatos1(String code, String fecha, String totalPagar, String nit) {
		String notificacion = "";

		/*Se valida que el saldo ingresado no sea null ni sea cadena vac�a,
		adem�s se valida que sea num�rico para su correcta conversi�n */


		if (code == null || code.equals("")) {
			notificacion += "Ingrese el c�digo\n";
		}

		if (fecha == null || fecha.equals("")) {
			notificacion += "Ingrese la fecha\n";
		}
		if (totalPagar == null || totalPagar.equals("")) {
			notificacion += "Ingrese el valor a pagar \n";
		}
		else {
			if(!esNumero(totalPagar)){
				notificacion += "El valor a pagar ingresado debe ser num�rico\n";
			}
		}
		if (nit == null || nit.equals("")) {
			notificacion += "Ingrese el NIT del cliente\n";
		}




		if (!notificacion.equals("")) {
			mostrarMensaje("Notificaci�n", "Factura no creada", notificacion, AlertType.WARNING);
			return false;
		}

		return true;
	}

    @FXML
    void limpiarCamposFac(ActionEvent event) {
    	txtTotalpagar.clear();
		txtNitClienteFactura.clear();
		txtCodigo.clear();
		txtFecha.clear();
    }



    //TAB CONSULTAR FACTURAS//

    //TAB CONSULTAR FACTURAS

    /**
     * Esta funci�n sirve para consultar las facturas recibiendo como par�metro un NIT, se muestra en una tableView
     * @param event
     */
    @FXML
    void consultarFacturas(ActionEvent event) {
		String cedulaCliente = txtCedulaFactura.getText();

		if (cedulaCliente != null && !cedulaCliente.equals("")){
			Factura fac = aplicacion.encontrarFactura(cedulaCliente);
			if(fac!=null){
				tableViewFacturas.getItems().clear();
				tableViewFacturas.setItems(getCuentaInfoFactura(cedulaCliente));
			}
			else {
				mostrarMensaje("Notificaci�n Factura", "La Factura no encontrada", "La informaci�n de la factura no ha sido encontrada",
						AlertType.INFORMATION);
				tableViewCliente.getItems().clear();
			}

		}
		else{
			mostrarMensaje("Notificaci�n Factura", "La factura no existe", "Por favor ingresar correctamente la c�dula",
					AlertType.WARNING);
		}



	}

    /**
     * Esta funci�n sirve para llenar los datos de la table view
     * @param cedulaCliente
     * @return
     */
	private ObservableList<Factura> getCuentaInfoFactura(String cedulaCliente) {
		listaFacturasClientesJuridicos.clear();
		listaFacturasClientesJuridicos.add(aplicacion.encontrarFactura(cedulaCliente));
		return listaFacturasClientesJuridicos;
	}

    @FXML
    void limpiarCamposInfoFacts(ActionEvent event) {
    	tableViewFacturas.getItems().clear();
    	txtCedulaFactura.clear();

    }




    //FUNCIONES PARA ABRIR VENTANAS Y OTRAS COSAS.//
    //Funciones para inicializar ventanas y cosas.
    @FXML
    void initialize() {

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		this.columnNIT.setCellValueFactory(new PropertyValueFactory<>("nit"));
		this.columnTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));


		this.columnCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		this.columnFechaFactura.setCellValueFactory(new PropertyValueFactory<>("fechaFacturacion"));
		this.columnTotalPagar.setCellValueFactory(new PropertyValueFactory<>("totalPagar"));
	}

	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;
	}

	public void init(Stage stage, VentanaPrincipalController ventanaPrincipalController) {
		this.ventanaPrincipalController = ventanaPrincipalController;

		this.stage = stage;
	}
}

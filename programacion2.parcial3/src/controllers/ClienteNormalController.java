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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import programacion2.parcial3.model.ClienteNormal;
import programacion2.parcial3.model.Empresa;
import programacion2.parcial3.model.Factura;

public class ClienteNormalController implements Initializable{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnLimpiarInfoNormal;

    @FXML
    private Button btnInfoLimpiar;

    @FXML
    private TextField txtApellidos;

    @FXML
    private Button btnAgregarClienteNormal;

    @FXML
    private Button btnAniadirFactura;

    @FXML
    private Button btnLimpairCamposFac;

    @FXML
    private TextField txtNombreCliente;

    @FXML
    private TextField txtInfoNumCuenta;


    @FXML
    private TextField txtCedulaClienteFactura;

    @FXML
    private TextField txtFecha;
    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtTotalpagar;

    @FXML
    private Button btnConsultar2;

    @FXML
    private Button btnVentanaPrincipal;

    @FXML
    private TextField txtCedula;

    @FXML
    private TextField txtCedulaFactura;

	@FXML
    private TableView<ClienteNormal> tableViewCliente;

	@FXML
	private TableColumn<ClienteNormal, String> columnNombre;

	@FXML
	private TableColumn<ClienteNormal, String> columnApellidos;

	@FXML
	private TableColumn<ClienteNormal, String> columnCedula;

	@FXML
	private TableColumn<ClienteNormal, String> columnEstrato;

	@FXML
	private TableColumn<Factura, String> columnCodigo;

	@FXML
	private TableColumn<Factura, String> columnFechaFactura;

	@FXML
	private TableColumn<Factura, String> columnTotalPagar;

	@FXML
    private TableView<Factura> tableViewFacturas;

	@FXML
	private ComboBox<String> comboEstrato;


	ObservableList<ClienteNormal> listaInfoClientesNormales = FXCollections.observableArrayList();
	ObservableList<Factura> listaFacturasClientesNormales = FXCollections.observableArrayList();

	private VentanaPrincipalController ventanaPrincipalController;

	private Empresa empresa;

	private Aplicacion aplicacion;

	private Stage stage;

	public void init(Stage stage, VentanaPrincipalController ventanaPrincipalController) {
		this.ventanaPrincipalController = ventanaPrincipalController;
		this.empresa = aplicacion.getEmpresa();

		this.stage = stage;

	}
	 @FXML
	 void showVentanaPrincipal(ActionEvent event) {
		ventanaPrincipalController.show();
		stage.close();
	 }


    @FXML
    void limpiarAhorro(ActionEvent event) {

    	txtNombreCliente.clear();
    	txtApellidos.clear();
    	txtCedula.clear();
    	comboEstrato.getSelectionModel().clearSelection();
    }

    //TAB AGREGAR CLIENTE//

    /**
     * Este método prepara la información para que el próximo método cree al cliente, valida los datos y manda alertas.
     * @param event
     */
    @FXML
    void agregarClienteNormal(ActionEvent event) {
    	String name = txtNombreCliente.getText();
    	String surnames = txtApellidos.getText();
    	String id = txtCedula.getText();
    	String estrato = comboEstrato.getValue();

    	if(validarDatos(name, surnames, id, estrato)){
    		crearClienteNormal(name,surnames,id, estrato);
    		txtNombreCliente.clear();
        	txtApellidos.clear();
        	txtCedula.clear();
        	comboEstrato.getSelectionModel().clearSelection();
    	}




    }

    /**
     * Este método conecta con el main y la lógica y crea a Cliente Natural
     * @param name
     * @param surnames
     * @param id
     * @param estrato
     */
    private void crearClienteNormal(String name, String surnames, String id, String estrato) {
    	boolean resultado = aplicacion.crearClienteNormal(name,surnames,id, estrato);
    	if(resultado==true){
    		mostrarMensaje("Información Cliente", "Cliente creado", "El cliente se ha creado con éxito", AlertType.INFORMATION);
    	}
    	else{
    		mostrarMensaje("Información Cliente", "Cliente no creado", "El cliente no ha sido creado", AlertType.INFORMATION);

    	}
	}

    /**
     * Esta función se encarga de mostrar la información de una cuenta a partir de su CC, esta las muestra en una tableView
     * @param event
     */
	@FXML
    void mostrarInfoCuenta(ActionEvent event) {
		String cedulaCliente = txtInfoNumCuenta.getText();

		if (cedulaCliente != null && !cedulaCliente.equals("")){
			ClienteNormal cliente = aplicacion.encontrarClienteNormal(cedulaCliente);
			if(cliente!=null){
				tableViewCliente.getItems().clear();
				tableViewCliente.setItems(getCuentaInfo(cedulaCliente));
			}
			else {
				mostrarMensaje("Notificación cliente", "Cliente no encontrado", "La información del cliente no ha sido encontrada",
						AlertType.INFORMATION);
				tableViewCliente.getItems().clear();
			}
		}
		else{
			mostrarMensaje("Notificación", "Datos inválidos", "Por favor ingresar correctamente la cédula",
					AlertType.WARNING);
		}
    }

	/**
	 * Esta función sirve para llenar los datos de la table view
	 * @param cedulaCliente
	 * @return
	 */
    private ObservableList<ClienteNormal> getCuentaInfo(String cedulaCliente) {
    	listaInfoClientesNormales.clear();
    	listaInfoClientesNormales.add(aplicacion.encontrarClienteNormal(cedulaCliente));
		return listaInfoClientesNormales;
	}

	@FXML
    void limpiarInfoCuenta(ActionEvent event) {
		listaInfoClientesNormales.clear();
		txtInfoNumCuenta.clear();

    }




	//TAB AGREGAR FACTURA//

	@FXML
    void limpiarCamposFac(ActionEvent event) {
		txtTotalpagar.clear();
		txtCedulaFactura.clear();
		txtCodigo.clear();
		txtFecha.clear();

	}

	/**
	 * Este método sirve para agregar facturas al programa
	 * @param event
	 */
	@FXML
	void aniadirFactura(ActionEvent event) {
		String code = txtCodigo.getText();
		String fecha = txtFecha.getText();
		String totalPagar = txtTotalpagar.getText();
		String idClienteFac = txtCedulaClienteFactura.getText();

		if(validarDatos1(code, fecha, totalPagar, idClienteFac)){
			crearFactura(code, fecha, totalPagar, idClienteFac);


		}
	}

	/**
	 * Este es un aliado de la función anterior, esta sirve para comunicarse con Aplicación y con la lógica
	 * @param code
	 * @param fecha
	 * @param totalPagar
	 * @param idClienteFac
	 */
    private void crearFactura(String code, String fecha, String totalPagar, String idClienteFac) {
       	boolean resultado = aplicacion.crearFactura(code,fecha,totalPagar, idClienteFac);
    	if(resultado==true){
    		mostrarMensaje("Información factura", "Factura creada", "La factura a sido creada con éxito", AlertType.INFORMATION);
    		txtTotalpagar.clear();
    		txtCedulaClienteFactura.clear();
    		txtCodigo.clear();
    		txtFecha.clear();

    	}
    	else{
    		mostrarMensaje("Información factura", "Factura no creada", "La factura no ha sido creada", AlertType.INFORMATION);
    	}
	}






    //TAB CONSULTAR FACTURAS

    /**
     * Esta función sirve para consultar las facturas recibiendo como parámetro una CC, se muestra en una tableView
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
				mostrarMensaje("Notificación Factura", "La Factura no encontrada", "La información de la factura no ha sido encontrada",
						AlertType.INFORMATION);
				tableViewCliente.getItems().clear();
			}

		}
		else{
			mostrarMensaje("Notificación Factura", "La factura no existe", "Por favor ingresar correctamente la cédula",
					AlertType.WARNING);
		}



	}

	/**
	 * Esta función sirve para llenar los datos de la table view
	 * @param cedulaCliente
	 * @return
	 */
	private ObservableList<Factura> getCuentaInfoFactura(String cedulaCliente) {
		listaFacturasClientesNormales.clear();
		listaFacturasClientesNormales.add(aplicacion.encontrarFactura(cedulaCliente));
		return listaFacturasClientesNormales;
	}




	//FUNCIONES UTILIRARIAS//

	@FXML
	void limpiarCamposInfoFacts(ActionEvent event) {
		txtCedulaClienteFactura.clear();
		tableViewFacturas.getItems().clear();
	}

	@FXML
    void initialize() {
    }

    public void initialize(URL location, ResourceBundle resources) {
    	this.columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		this.columnApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
		this.columnCedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));
		this.columnEstrato.setCellValueFactory(new PropertyValueFactory<>("estrato"));


		this.columnCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		this.columnFechaFactura.setCellValueFactory(new PropertyValueFactory<>("fechaFacturacion"));
		this.columnTotalPagar.setCellValueFactory(new PropertyValueFactory<>("totalPagar"));


		comboEstrato.getItems().addAll("1", "2", "3", "4", "5");

    }

	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;

	}

	public void setStage(Stage primaryStage) {
		stage = primaryStage;

	}

	public void mostrarMensaje(String titulo, String header, String contenido, AlertType alertype) {
		Alert alert = new Alert(alertype);
		alert.setTitle(titulo);
		alert.setHeaderText(header);
		alert.setContentText(contenido);
		alert.showAndWait();
	}

	private boolean validarDatos(String name, String surnames, String id, String estrato) {
		String notificacion = "";

		/*Se valida que el saldo ingresado no sea null ni sea cadena vacía,
		además se valida que sea numérico para su correcta conversión */


		if (name == null || name.equals("")) {
			notificacion += "Descripción ingresada es inválida\n";
		}

		if (surnames == null || surnames.equals("")) {
			notificacion += "Ingrese sus apellidos\n";
		}
		if (id == null || id.equals("")) {
			notificacion += "Ingrese una cédula\n";
		}
		else {
			if(!esNumero(id)){
				notificacion += "La cédula ingresada debe ser numérica\n";
			}
		}

		if (estrato == null || estrato.equals("")) {
			notificacion += "Seleccione un estrato\n";
		}

		if (!notificacion.equals("")) {
			mostrarMensaje("Notificación", "Reunión no creada", notificacion, AlertType.WARNING);
			return false;
		}

		return true;
	}

	private boolean validarDatos1(String code, String fecha, String totalPagar, String idClienteFac) {
		String notificacion = "";

		/*Se valida que el saldo ingresado no sea null ni sea cadena vacía,
		además se valida que sea numérico para su correcta conversión */


		if (code == null || code.equals("")) {
			notificacion += "Ingrese el código\n";
		}

		if (fecha == null || fecha.equals("")) {
			notificacion += "Ingrese la fecha\n";
		}
		if (totalPagar == null || totalPagar.equals("")) {
			notificacion += "Ingrese el valor a pagar \n";
		}
		else {
			if(!esNumero(totalPagar)){
				notificacion += "El valor a pagar ingresado debe ser numérico\n";
			}
		}
		if (idClienteFac == null || idClienteFac.equals("")) {
			notificacion += "Ingrese la cédula del cliente\n";
		}




		if (!notificacion.equals("")) {
			mostrarMensaje("Notificación", "Factura no creada", notificacion, AlertType.WARNING);
			return false;
		}

		return true;
	}

	private boolean esNumero(String string) {

		try {

			Float.parseFloat(string);

			return true;
		} catch (Exception e) {
			return false;
		}
	}



}






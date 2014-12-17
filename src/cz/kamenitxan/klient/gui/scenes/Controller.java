package cz.kamenitxan.klient.gui.scenes;

import cz.kamenitxan.klient.Request;
import cz.kamenitxan.klient.XmlCreator;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class Controller {
	@FXML
	private TextField name;
	@FXML
	private TextField department;
	@FXML
	private TextField place;
	@FXML
	private TextField phone;
	@FXML
	private CheckBox restart;
	@FXML
	private ToggleGroup type;
	@FXML
	private TextArea desc;



	@FXML
	private void handleSendButton() {
		RadioButton selectedType = (RadioButton) type.getSelectedToggle();

		Request request = new Request();
		request.setName(name.getText());
		request.setDepartment(department.getText());
		request.setPlace(place.getText());
		request.setPhone(phone.getText());
		request.setRestart(restart.isSelected());
		request.setType(selectedType.getText());
		request.setDesc(desc.getText());
		XmlCreator.startCreator(request);
	}
}

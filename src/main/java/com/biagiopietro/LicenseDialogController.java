package com.biagiopietro;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ResourceBundle;


public class LicenseDialogController
{
    public List<License> licenseList;
    public VBox vBoxLicenses;
    public JFXButton btnCancel;
    public Label lblTitle;
    // Service to handle url
    private HostServices hostServices;
    private ResourceBundle resourceBundle;
    private Scene scene;
    private String titleStage = null;
    private String titleInsideDialog = null;
    private String closeButtonText = null;
    private final ObjectProperty<Window> windowOwner = new SimpleObjectProperty<>();

    public LicenseDialogController()
    {
        super();
    }

    public Window getWindowOwner() { return windowOwner.get(); }

    public ObjectProperty<Window> windowOwnerProperty() { return windowOwner; }

    public void setWindowOwner(Window value) { windowOwner.set(value); }

    public void setScene(Scene scene)
    {
        this.scene = scene;
    }

    public void setHostServices(HostServices hostServices)
    {
        this.hostServices = hostServices;
    }

    public void setLicenseList(List<License> licenseList)
    {
        this.licenseList = licenseList;
    }

    public void setTitleStage(String titleStage) {
        this.titleStage = titleStage;
    }

    public void setTitleInsideDialog(String titleInsideDialog) {
        this.titleInsideDialog = titleInsideDialog;
    }

    public void setCloseButtonText(String closeButtonText) {
        this.closeButtonText = closeButtonText;
    }

    private void populateItems()
    {
        for (License license : licenseList)
        {
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER);
            hBox.getStyleClass().add("card");
            Label lblLibName = new Label(license.getLibName());
            Hyperlink hyperLinkLibUrl = new Hyperlink(license.getUrlLib());
            hyperLinkLibUrl.setOnAction(e -> openURL(license.getUrlLib()));
            lblLibName.setMinWidth(500);
            lblLibName.setPadding(new Insets(2));
            lblLibName.setFont(Font.font ("Verdana", 20));
            lblLibName.setAlignment(Pos.CENTER);
            hyperLinkLibUrl.setMinWidth(500);
            hyperLinkLibUrl.setAlignment(Pos.CENTER);
            String libSummary = "";
            BufferedReader reader;
            switch (license.getLicenseType())
            {
                case APACHE20:
                    reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/files/apache2_license_summary")));
                    libSummary = loadTextFiles(reader);
                    break;
                case MIT:
                    reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/files/mit_license_summary")));
                    libSummary = loadTextFiles(reader);
                    break;
                case LGPL3:
                    reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/files/lgpl3_license_summary")));
                    libSummary = loadTextFiles(reader);
                    break;
                case BSD3:
                    reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/files/bsd3_license_summary")));
                    libSummary = loadTextFiles(reader);
                    break;
            }
            JFXTextArea txtAreaLibLicense = new JFXTextArea(String.format("%s\n\n\n%s",license.getCopyright(), libSummary));
            txtAreaLibLicense.setMinWidth(500);
            txtAreaLibLicense.setMinHeight(200);
            txtAreaLibLicense.setStyle("-fx-padding: 0");
            txtAreaLibLicense.setEditable(false);
            txtAreaLibLicense.setWrapText(true);
            setAncor(lblLibName);
            setAncor(hyperLinkLibUrl);
            setAncor(txtAreaLibLicense);
            vBoxLicenses.getChildren().addAll(lblLibName, hyperLinkLibUrl, txtAreaLibLicense);
        }
        if (closeButtonText != null)
        {
            btnCancel.setText(closeButtonText);
        }
        else
        {
            btnCancel.setText("Close");
        }
        if (titleInsideDialog != null)
        {
            lblTitle.setText(titleInsideDialog);
        }
        else
        {
            lblTitle.setText("Libraries");
        }
    }

    public boolean showLicenseDialog()
    {
        try
        {
            // Loading form.
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("layouts/layout_license.fxml"));
            BorderPane pane = loader.load();
            pane.getStylesheets().add("/css/style.css");
            Scene scene = new Scene(pane);
            final Stage stage = new Stage();
            LicenseDialogController licenseDialogController = loader.getController();
            licenseList.add(new License("JFoenix", "https://github.com/jfoenixadmin/JFoenix", "Copyright © JFoenix 2014", License.licenseType.APACHE20));
            licenseList.add(new License("LicenseDialog", "https://github.com/biagiopietro/LicenseDialog", "Copyright © biagiopietro 2018", License.licenseType.APACHE20));
            licenseDialogController.setLicenseList(licenseList);
            licenseDialogController.setHostServices(hostServices);
            licenseDialogController.populateItems();
            stage.resizableProperty().setValue(Boolean.FALSE);
            stage.initOwner(getWindowOwner());
            stage.initStyle(StageStyle.UTILITY);
            scene.setFill(Color.TRANSPARENT);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            if (titleStage != null)
            {
                stage.setTitle(titleStage);
            }
            else
            {
                stage.setTitle("Open Source Libraries");
            }
            // Add event handler for active screen managing.
            stage.addEventHandler(WindowEvent.WINDOW_SHOWING, (WindowEvent window) ->
            {
                stage.centerOnScreen();
                Platform.runLater(() -> stage.requestFocus());
            });
            // Show form in modal type.
            stage.showAndWait();
        }
        catch (Exception e)
        {
            System.out.println(String.format("[%s]: %s", Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage()));
            return false;
        }
        return true;
    }

    private void openURL(String url)
    {
        this.hostServices.showDocument(url);
    }

    public static String loadTextFiles(BufferedReader bufferedReader)
    {
        String lines ="";
        try
        {
            while(bufferedReader.ready()) {
                lines += (bufferedReader.readLine()) + "\n";
            }

        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        return lines;
    }

    public void setAncor(Node node)
    {
        AnchorPane.setBottomAnchor(node, 0.0);
        AnchorPane.setTopAnchor(node, 5.0);
        AnchorPane.setLeftAnchor(node, 0.0);
        AnchorPane.setRightAnchor(node, 0.0);
    }

    /**
     * Handles Exit button Action Event.
     * @brief Handles Exit button Action Event.
     *
     * @param event Action event to handle.
     */
    @FXML
    private void handleExitAction(ActionEvent event) {
        Node source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
}

package demo;

import com.biagiopietro.License;
import com.biagiopietro.LicenseDialogController;
import javafx.application.HostServices;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable
{
    public Button btnShowLicenseDialog;
    private HostServices hostServices;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        btnShowLicenseDialog.setOnAction(event -> {
            List<License> licenseList = new ArrayList<>();
            LicenseDialogController licenseDialogController = new LicenseDialogController();
            licenseDialogController.setHostServices(hostServices);
            licenseDialogController.setLicenseList(licenseList);
            licenseDialogController.setTitleStage("Open Source Libraries");
            licenseDialogController.setCloseButtonText("Close");
            licenseDialogController.setTitleInsideDialog("Libraries");
            licenseDialogController.showLicenseDialog();
        });
    }

    public void setHostServices(HostServices hostServices)
    {
        this.hostServices = hostServices;
    }

}

[![HitCount](http://hits.dwyl.io/biagiopietro/LicenseDialog.svg)](http://hits.dwyl.io/biagiopietro/LicenseDialog)
[![JitPack lib_version](https://jitpack.io/v/biagiopietro/LicenseDialog.svg)](https://jitpack.io/#biagiopietro/LicenseDialog)
[![GitHub forks](https://img.shields.io/github/forks/biagiopietro/LicenseDialog.svg)](https://github.com/biagiopietro/LicenseDialog/network)
[![GitHub issues](https://img.shields.io/github/issues/biagiopietro/LicenseDialog.svg)](https://github.com/biagiopietro/LicenseDialog/issues)
[![GitHub license](https://img.shields.io/github/license/biagiopietro/LicenseDialog.svg)](https://github.com/biagiopietro/LicenseDialog/blob/master/LICENSE)
---

<h1 align="center">LicenseDialog</h1>
<p align="center">
<sup>
<b>LicenseDialog is an open source Java library, that  allows you to show the license of the libraries used in your project inside a Material Design dialog. </b>
</sup>
</p>


## Screenshot

![](https://imgur.com/lbJs4FZ.png)


## Note

* LicenseDialog requires **Java 1.8u60** and above.
* You need to set JAVA_HOME environment variable to point to Java 1.8 directory.

## How to Include In Maven Project
### Add JitPack in your repositories
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```
### Add LicenseDialog in your dependencies
```xml
<dependency>
    <groupId>com.github.biagiopietro</groupId>
    <artifactId>LicenseDialog</artifactId>
    <version>1.0.2</version>
</dependency>

```
## How to use
```
    // Create License list
    List<License> licenseList = new ArrayList<>();
    // Add Licenses to the list
    licenseList.add(new License("Lib example", "https://www.google.com", "Copyright © example", License.licenseType.APACHE20));
    LicenseDialogController licenseDialogController = new LicenseDialogController();
    // Set the HostServices for the URL
    licenseDialogController.setHostServices(hostServices);
    licenseDialogController.setLicenseList(licenseList);
    licenseDialogController.setTitleStage("Open Source Libraries");
    licenseDialogController.setCloseButtonText("Close");
    licenseDialogController.setTitleInsideDialog("Libraries");
    licenseDialogController.showLicenseDialog();
```
## Supported Licenses
* Apache 2.0
* MIT
* LGPL 3.0

## Issues
Issues can be reported to the Issue tracker. If you can't fix them yourself, please be patiente as I can only work on them during my spare time and according to my priorities.

## Contributions
Contributions are welcome, they can be submitted via Pull requests.

## Special Thanks
Special thanks to <a href="https://www.jetbrains.com">JetBrains</a> for his beautiful IDEE <a href="https://www.jetbrains.com/idea/">IntelliJ IDEA</a> and <a href="http://www.jfoenix.com/">JFoenix</a> for Material Design Components.
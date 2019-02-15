package com.biagiopietro;

public class License
{
    public enum licenseType
    {
        APACHE20,
        MIT,
        LGPL3,
        BSD3
    }

    private String libName;
    private String urlLib;
    private String copyright;
    private licenseType licenseType;

    public License(String libName, String urlLib, String copyright, License.licenseType licenseType)
    {
        this.libName = libName;
        this.urlLib = urlLib;
        this.copyright = copyright;
        this.licenseType = licenseType;
    }

    public String getLibName()
    {
        return libName;
    }

    public void setLibName(String libName)
    {
        this.libName = libName;
    }

    public String getUrlLib()
    {
        return urlLib;
    }

    public void setUrlLib(String urlLib)
    {
        this.urlLib = urlLib;
    }

    public String getCopyright()
    {
        return copyright;
    }

    public void setCopyright(String copyright)
    {
        this.copyright = copyright;
    }

    public License.licenseType getLicenseType()
    {
        return licenseType;
    }

    public void setLicenseType(License.licenseType licenseType)
    {
        this.licenseType = licenseType;
    }
}

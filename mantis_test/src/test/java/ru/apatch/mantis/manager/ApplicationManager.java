package ru.apatch.mantis.manager;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Properties;

public class ApplicationManager {

    private WebDriver driver;
    private String browser;
    private Properties properties;
    private SessionHelper sessionHelper;
    private HttpSessionHelper httpsessionHelper;
    private jamesCliHelper jamesCliHelper;
    private MailHelper mailHelper;
    private RegistrationHelper registrationHelper;
    private jamesApiHelper jamesApiHelper;
    private DeveloperMailHelper developerMailHelper;


    public void init(String browser, Properties properties) {
        this.browser = browser;
        this.properties = properties;

    }

    public WebDriver driver() {
        if (driver == null) {
            if ("chrome".equals(browser)) {
                driver = new ChromeDriver();
            } else if ("firefox".equals(browser)) {
                driver = new FirefoxDriver();
            } else {
                throw new IllegalArgumentException(String.format("Unknow browser %s", browser));
            }
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.get(properties.getProperty("web.baseUrl"));
            driver.manage().window().setSize(new Dimension(1490, 1019));
        }
        return driver;
    }

    public SessionHelper session() {
        if (sessionHelper == null) {
            sessionHelper = new SessionHelper(this);
        }
        return sessionHelper;
    }

    public HttpSessionHelper http() {
        if (httpsessionHelper == null) {
            httpsessionHelper = new HttpSessionHelper(this);
        }
        return httpsessionHelper;
    }

    public jamesCliHelper jamesCli() {
        if (jamesCliHelper == null) {
            jamesCliHelper = new jamesCliHelper(this);
        }
        return jamesCliHelper;
    }

    public MailHelper mail() {
        if (mailHelper == null) {
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }

    public RegistrationHelper registration() {
        if (registrationHelper == null) {
            registrationHelper = new RegistrationHelper(this);
        }
        return registrationHelper;
    }

    public jamesApiHelper jamesApi() {
        if (jamesApiHelper == null) {
            jamesApiHelper = new jamesApiHelper(this);
        }
        return jamesApiHelper;
    }

    public DeveloperMailHelper developerMail() {
        if (developerMailHelper == null) {
            developerMailHelper = new DeveloperMailHelper(this);
        }
        return developerMailHelper;
    }

    public String property(String name) {
        return properties.getProperty(name);
    }
}
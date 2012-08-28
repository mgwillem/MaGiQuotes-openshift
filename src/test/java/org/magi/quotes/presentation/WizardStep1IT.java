package org.magi.quotes.presentation;

import com.thoughtworks.selenium.DefaultSelenium;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.magi.quotes.Deployments;

import java.io.File;
import java.net.URL;

/**
 * @author <a href="mailto:mgw@mmx.lu">Marc Gabriel-Willem</a>
 */
@RunWith(Arquillian.class)
public class WizardStep1IT {

    @Drone
    private DefaultSelenium browser;

    @ArquillianResource
    private URL deploymentURL;

    @Deployment(testable = false)
    public static WebArchive createDeployment() {

        WebArchive archive = Deployments.createWebArchive();
        archive.addAsWebResource(new File(Deployments.WEBAPP_CLIENT_PAGES, "test.xhtml"), "client/test.xhtml");
        archive.addAsWebResource(new File(Deployments.WEBAPP_CLIENT_PAGES, "test2.xhtml"), "client/test2.xhtml");

        System.out.println(archive.toString(true));
        return archive;

    }

    @Test
    public void shouldMembersInitialized() {
        Assert.assertNotNull(browser);
        Assert.assertNotNull(deploymentURL);

        System.out.println(":::deploymentURL:::" + deploymentURL);
    }

    @Test
    public void shouldOpenStep2Successfully() {

        //browser.setSpeed("5000");
        //browser.open(deploymentURL + "faces/client/test.xhtml");
        browser.open("http://user1:password1@127.0.0.1:8080/test-wizard/" + "faces/client/test.xhtml");

        System.out.println(":::title:::" + browser.getTitle());
        browser.click("id=nextBtn");

        browser.waitForPageToLoad("1000");
        Assert.assertTrue("New page should be displayed!",
                browser.isElementPresent("xpath=.//*[@id='wizardForm']/span[contains(text(), 'Welcome!')]"));
    }
}

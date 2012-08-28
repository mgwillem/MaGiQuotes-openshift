package org.magi.quotes;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.magi.quotes.presentation.admin.VaadinAppServlet;
import org.magi.quotes.service.boundary.AuditService;
import org.magi.quotes.service.boundary.ProductService;
import org.magi.quotes.service.control.CrudService;
import org.magi.quotes.service.entity.Audit;
import org.magi.quotes.service.entity.Product;
import org.magi.quotes.service.entity.ProductPrice;

import java.io.File;

/**
 * @author <a href="mailto:mgw@mmx.lu">Marc Gabriel-Willem</a>
 */
public class Deployments {

    public static final String WEBAPP_SRC = "src/main/webapp";
    public static final String WEBAPP_CLIENT_PAGES = WEBAPP_SRC + "/client";
    public static final String WEBAPP_TEMPLATE_PAGES = WEBAPP_SRC + "/templates";

    public static WebArchive createWebArchive() {
        MavenDependencyResolver resolver = DependencyResolvers
                .use(MavenDependencyResolver.class)
                .loadMetadataFromPom("pom.xml");

        return ShrinkWrap.create(WebArchive.class, "test-wizard.war")
                .addAsLibraries(resolver.artifact("com.vaadin:vaadin").resolveAsFiles())
                .addPackage(VaadinAppServlet.class.getPackage())
                .addClasses(AuditService.class, Audit.class)
                .addClasses(ProductService.class, Product.class, ProductPrice.class)
                .addClass(CrudService.class)
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsWebInfResource(new StringAsset("<faces-config version=\"2.0\"/>"), "faces-config.xml")
                .setWebXML(new File(WEBAPP_SRC, "WEB-INF/web.xml"));
    }

    public static WebArchive createBusinessArchive() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("jbossas-ds.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }
}

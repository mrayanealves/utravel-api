package br.ufrn.imd.utravel.config;

import br.ufrn.imd.utravel.controller.*;
import br.ufrn.imd.utravel.model.Passeio;
import br.ufrn.imd.utravel.model.Restaurante;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class AppServicesApplication extends Application {
    public AppServicesApplication() {
        BeanConfig conf = new BeanConfig();
        conf.setTitle("uTravel API");
        conf.setVersion("0.1");
        conf.setHost("localhost:8080");
        conf.setBasePath("/utravel-api/api");
        conf.setSchemes(new String[] {"http"});
        conf.setResourcePackage("br.ufrn.imd.utravel");
        conf.setScan(true);
    }

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        resources.add(EmpresaController.class);
        resources.add(EnderecoController.class);
        resources.add(HospedagemController.class);
        resources.add(LocalizacaoController.class);
        resources.add(Passeio.class);
        resources.add(Restaurante.class);
        resources.add(UsuarioController.class);
        resources.add(VeiculoAlugadoController.class);

        //classes do swagger...
        resources.add(ApiListingResource.class);
        resources.add(SwaggerSerializers.class);
        return resources;
    }
}

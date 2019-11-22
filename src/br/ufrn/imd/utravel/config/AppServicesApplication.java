package br.ufrn.imd.utravel.config;

import br.ufrn.imd.utravel.controller.*;
import br.ufrn.imd.utravel.handler.ConstraintViolationExceptionHandler;
import br.ufrn.imd.utravel.handler.LoginExceptionHandler;
import br.ufrn.imd.utravel.security.AuthenticationFilter;
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
        super();
        BeanConfig conf = new BeanConfig();
        conf.setTitle("uTravel API");
        conf.setVersion("0.1");
        conf.setHost("localhost:8080");
        conf.setBasePath("/utravel/api");
        conf.setSchemes(new String[]{"http"});
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
        resources.add(PasseioController.class);
        resources.add(RestauranteController.class);
        resources.add(UsuarioController.class);
        resources.add(VeiculoController.class);
        resources.add(ViagemController.class);
        resources.add(OrcamentoController.class);
        resources.add(VeiculoAlugadoController.class);
        resources.add(EventoController.class);
        resources.add(PostagemController.class);

        // Providers
        resources.add(CorsFilter.class);

        // Handlers
        resources.add(ConstraintViolationExceptionHandler.class);
        resources.add(LoginExceptionHandler.class);

        //classes do swagger...
        resources.add(ApiListingResource.class);
        resources.add(SwaggerSerializers.class);
        resources.add(AuthenticationFilter.class);
        return resources;
    }
}
